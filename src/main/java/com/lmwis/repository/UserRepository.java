package com.lmwis.repository;

import com.lmwis.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-02 21:19
 * @Version 1.0
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {

    /**
     * 根据年龄查找用户
     * @param start
     * @param end
     * @return
     */
    Flux<User> findAllByAgeBetween(int start,int end);

    @Query("{'age':{'$gte':20,'$lte':30}}")
    Flux<User> oldUser();
}
