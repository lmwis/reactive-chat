package com.lmwis.domain;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @Author lmwis
 * @Date 2019-11-02 21:17
 * @Version 1.0
 */
@Document(collection = "user")
public class User {
    @Id
    private String id;

    @NotBlank
    private String name;

    @Range(min = 10,max = 100)
    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
