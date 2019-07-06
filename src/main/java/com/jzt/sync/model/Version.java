package com.jzt.sync.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "version")
public class Version implements Serializable {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 程序名称
     */
    @Column(name = "app_name")
    private String appName;

    /**
     * 更新后启动的程序
     */
    @Column(name = "application_start")
    private String applicationStart;

    /**
     * 是否强制更新 1-是  0-否
     */
    @Column(name = "force_update")
    private Long forceUpdate;

    /**
     * 最小版本号
     */
    @Column(name = "min_version")
    private String minVersion;

    /**
     * 发布时间
     */
    @Column(name = "release_date")
    private Date releaseDate;

    /**
     * 下载地址
     */
    @Column(name = "release_url")
    private String releaseUrl;

    /**
     * 发布版本号
     */
    @Column(name = "release_version")
    private String releaseVersion;

    /**
     * Cover表示覆盖更新，Increment表示增量更新
     */
    @Column(name = "update_mode")
    private String updateMode;

    /**
     * 更新界面提示信息
     */
    @Column(name = "version_desc")
    private String versionDesc;

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