package com.jzt.sync.model;

import lombok.Data;

import javax.persistence.*;

@Table(name = "test_user")
@Data
public class TestUser {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    private String password;


}