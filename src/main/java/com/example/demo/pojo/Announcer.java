package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/1 15:44
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announcer {
    private int id;

    private String username;

    private String password;

    private String name;

    private String email;

    public Announcer(String username,String password,String email,String name){
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setName(name);
    }
}
