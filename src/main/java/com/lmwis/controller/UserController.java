package com.lmwis.controller;

import com.lmwis.domain.User;
import com.lmwis.repository.UserRepository;
import com.lmwis.util.CheckUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-02 21:19
 * @Version 1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @GetMapping("/")
    public Flux<User> getAll(){
        return userRepository.findAll();
    }


    /**
     * 以SSE形式多次返回数据
     * @return
     */
    @GetMapping(value = "/stream/all",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamGetAll(){
        return userRepository.findAll();
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/")
    public Mono<User> createUser(@Valid @RequestBody User user){

        CheckUtil.checkName(user.getName());

        // jpa 里面，新增和修改都是save，有id是修改，id为空是新增
        return userRepository.save(user);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id")String id){

        return this.userRepository.findById(id)
                .flatMap(this.userRepository::delete)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 修改用户
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> updateUser(@Valid
            @PathVariable("id")String id
            ,@Valid @RequestBody User user
    ){

        CheckUtil.checkName(user.getName());

        // flatMap 操作数据
        // map 修改数据
        return this.userRepository.findById(id)
                .flatMap(u->{
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    return this.userRepository.save(u);
                }).map(u->new ResponseEntity<>(u,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> findById(@PathVariable("id")String id){

        return this.userRepository.findById(id)
                .map(u -> new ResponseEntity<>(u,HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    /**
     * 查询年龄介于start-end之间的user
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/age/{start}/{end}")
    public Flux<User> findByAge(
            @PathVariable() int start,
            @PathVariable int end
    ){
        return this.userRepository.findAllByAgeBetween(start,end);
    }

    /**
     * 查询年龄介于start-end之间的user
     * SSE返回
     * @param start
     * @param end
     * @return
     */
    @GetMapping(value = "/stream/age/{start}/{end}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamFindByAge(
            @PathVariable() int start,
            @PathVariable int end
    ){
        return this.userRepository.findAllByAgeBetween(start,end);
    }

    /**
     * 查询年龄最大的user
     * @return
     */
    @GetMapping("/age/old")
    public Flux<User> oldUser(){
        return this.userRepository.oldUser();
    }

    /**
     * 查询年龄最大的user
     * @return
     */
    @GetMapping(value = "/stream/age/old",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamOldUser(){
        return this.userRepository.oldUser();
    }

}
