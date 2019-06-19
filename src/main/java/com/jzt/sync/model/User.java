package com.jzt.sync.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    /**
     * 编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 外部用户编号
     */
    @Column(name = "user_agent_id")
    private Long userAgentId;

    /**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 登录密码
     */
    @Column(name = "login_pwd")
    private String loginPwd;

    /**
     * 登录密码
     */
    @Column(name = "login_pwd_jm")
    private String loginPwdJm;


    /**
     * 状态 0：停用 1：正常
     */
    private Integer status;

    /**
     * 连接名
     */
    @Column(name = "link_name")
    private String linkName;

    /**
     * 外部电话
     */
    @Column(name = "link_phone")
    private String linkPhone;

    /**
     * 创建时间
     */
    @Column(name = "create_at")
    private Date createAt;

    /**
     * 更新时间
     */
    @Column(name = "update_at")
    private Date updateAt;


}