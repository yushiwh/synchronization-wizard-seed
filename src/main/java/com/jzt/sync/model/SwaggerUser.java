/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: SwaggerUser
 * Author:   nick
 * Date:     2019/11/20 10:52
 * Description: Swagger的测试验证实体
 * History:
 */
package com.jzt.sync.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

import javax.validation.constraints.*;

/**
 * 〈Swagger的测试验证实体〉
 *
 * @author nick
 * @create 2019/11/20
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户基本信息")
public class SwaggerUser implements Serializable {
    @ApiModelProperty("姓名")
    @Size(max = 20)
    private String name;
    @ApiModelProperty("年龄")
    @Max(150)
    @Min(1)
    private Integer age;
    @ApiModelProperty("地址")
    @NotNull
    private String address;
    @ApiModelProperty("邮箱")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$")
    private String email;
}