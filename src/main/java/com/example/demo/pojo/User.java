package com.example.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author 梦里花开又半夏~
 * @date 2022/5/1 16:02
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;

    private String username;

    private String password;

    private String email;

    private String name;

    private String gender;

    private String birthday;

    private String education;

    private String city;

    private String institution;
    public User(String username,String password,String email,String name,String gender,
                String birthday,String education,String city,String institution){
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setName(name);
        this.setGender(gender);
        this.setBirthday(birthday);
        this.setBirthday(birthday);
        this.setEducation(education);
        this.setCity(city);
        this.setInstitution(institution);
    }

    public User(String username,String email, String name, String gender, String birthday, String education, String city, String institution) {
        this.setUsername(username);
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.education = education;
        this.city = city;
        this.institution = institution;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
