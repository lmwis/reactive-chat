package com.lmwis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableReactiveMongoRepositories
public class ReactiveChatApp
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        SpringApplication.run(ReactiveChatApp.class);
    }
}
