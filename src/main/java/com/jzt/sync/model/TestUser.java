package com.jzt.sync.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Administrator
 */
@Table(name = "test_user")
@Data
public class TestUser implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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