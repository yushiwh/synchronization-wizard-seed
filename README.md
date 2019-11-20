![Licence](https://img.shields.io/badge/licence-none-green.svg)
[![GitHub Release](https://img.shields.io/github/release/lihengming/spring-boot-api-project-seed.svg)](https://github.com/lihengming/spring-boot-api-project-seed/releases)
## 简介
Spring Boot API Project Seed 是一个基于Spring Boot & MyBatis的种子项目，用于快速构建中小型API、RESTful API项目，该种子项目已经有过多个真实项目的实践，稳定、简单、快速，使我们摆脱那些重复劳动，专注于业务代码的编写，减少加班。下面是一个简单的使用演示，看如何基于本项目在短短几十秒钟内实现一套简单的API，并运行提供服务。

[![请选择超清](https://raw.githubusercontent.com/lihengming/java-codes/master/shared-resources/github-images/project-example-youku.png)](http://v.youku.com/v_show/id_XMjg1NjYwNDgxNg==.html?spm=a2h3j.8428770.3416059.1)
## 特征&提供
- 最佳实践的项目结构、配置文件、精简的POM（[查看项目结构图](https://github.com/lihengming/java-codes/blob/master/shared-resources/github-images/project-struct.png)）
- 统一响应结果封装及生成工具
- 统一异常处理
- 简单的接口签名认证
- 常用基础方法抽象封装
- 使用Druid Spring Boot Starter 集成Druid数据库连接池与监控
- 使用FastJsonHttpMessageConverter，提高JSON序列化速度
- 集成MyBatis、通用Mapper插件、PageHelper分页插件，实现单表业务零SQL
- 提供代码生成器根据表名生成对应的Model、Mapper、MapperXML、Service、ServiceImpl、Controller等基础代码，其中Controller模板默认提供POST和RESTful两套，根据需求在```CodeGenerator.genController(tableName)```方法中自己选择，默认使用POST模板。代码模板可根据实际项目的需求来扩展，由于每个公司业务都不太一样，所以只提供了一些比较基础、通用的模板，**主要是提供一个思路**来减少重复代码的编写，我在实际项目的使用中，其实根据公司业务的抽象编写了大量的模板。另外，使用模板也有助于保持团队代码风格的统一
- 另有彩蛋，待你探索
 
## 快速开始
1. 克隆项目
2. 对```test```包内的代码生成器```CodeGenerator```进行配置，主要是JDBC，因为要根据表名来生成代码
3. 如果只是想根据上面的演示来亲自试试的话可以使用```test resources```目录下的```demo-user.sql```，否则忽略该步
3. 输入表名，运行```CodeGenerator.main()```方法，生成基础代码（可能需要刷新项目目录才会出来）
4. 根据业务在基础代码上进行扩展
5. 对开发环境配置文件```application-dev.properties```进行配置，启动项目，Have Fun！
 
## 开发建议
- 表名，建议使用小写，多个单词使用下划线拼接
- Model内成员变量建议与表字段数量对应，如需扩展成员变量（比如连表查询）建议创建DTO，否则需在扩展的成员变量上加```@Transient```注解，详情见[通用Mapper插件文档说明](https://mapperhelper.github.io/docs/2.use/)
- 建议业务失败直接使用```ServiceException("message")```抛出，由统一异常处理器来封装业务失败的响应结果，比如```throw new ServiceException("该手机号已被注册")```，会直接被封装为```{"code":400,"message":"该手机号已被注册"}```返回，无需自己处理，尽情抛出
- 增加Util包的工具类和configurer的配置，包括redis等，集成全力异常，返回值的封装的
- 需要工具类的话建议先从```apache-commons-*```和```guava```中找，实在没有再造轮子或引入类库，尽量精简项目
- 开发规范建议遵循阿里巴巴Java开发手册（[最新版下载](https://github.com/alibaba/p3c))
- 建议在公司内部使用[ShowDoc](https://github.com/star7th/showdoc)、[SpringFox-Swagger2](https://github.com/springfox/springfox) 、[RAP](https://github.com/thx/RAP)等开源项目来编写、管理API文档
 
## 技术选型&文档
- Spring Boot（[查看Spring Boot学习&使用指南](http://www.jianshu.com/p/1a9fd8936bd8)）
- MyBatis（[查看官方中文文档](http://www.mybatis.org/mybatis-3/zh/index.html)）
- MyBatisb通用Mapper插件（[查看官方中文文档](https://mapperhelper.github.io/docs/)）
- MyBatis PageHelper分页插件（[查看官方中文文档](https://pagehelper.github.io/)）
- Druid Spring Boot Starter（[查看官方中文文档](https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter/)）
- Fastjson（[查看官方中文文档](https://github.com/Alibaba/fastjson/wiki/%E9%A6%96%E9%A1%B5)）
- 其他略

## License
无，纯粹开源分享，感谢大家 [Star](https://github.com/lihengming/spring-boot-api-project-seed/stargazers) & [Fork](https://github.com/lihengming/spring-boot-api-project-seed/network/members) 的支持。


- 增加文件上传UploadController和一些工具类


- 增加shiro+jwt的验证token 可以参照https://github.com/yushiwh/springboot2.0-chapter中的springboot-shiro-jwt小项目进行理解
  - http://localhost:8080/guest/welcome   游客页面 正常不用登录
  - http://localhost:8080/user/getMessage 您没有权限访问
  - http://localhost:8080/user/getMessage  登录后 http://localhost:8080/login?username=wq&password=1234567
    拿到对应的token 然后在上面的地址的headers中增加参数 key:Token value:获取到的token。能成功返回成功获得信息
  - 只需要在controller上面加上对应的注解即可  
    - @RequiresRoles("admin") admin权限
    - @RequiresRoles(logical = Logical.OR, value = {"user", "admin"}) 拥有 user, admin 角色的用户
    -  @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
       @RequiresPermissions("vip")  拥有 vip 权限可以访问该页面  角色和权限的双重控制

- 增加消息队列：RabbitMq   test中的RabbitMqTest
  - 配置：RabbitConfig
  - 发送：test中的RabbitMqTest
  - 接收：Receiver
  
- 延迟消息队列
  - 配置：MessageRabbitMqConfiguration
  - 发送：MessageProvider
  - 消费：MessageConsumer
  - 测试：RabbitMqTest

- 增加Http发送请求与对象复制，得到json数据后进行解析和反序列化  HttpController为例
- 增加JUnit的支持 测试方法详见test路径下的RabbitMqTest 需要继承Tester




## 一些需要的说明参数
- 数据库：
  -  开发/测试： 10.4.9.161:3307
  -  用户名：sync
  -  密码：zEiWLps49MFm
  
- mq：
  - 测试环境
  - host：10.3.5.30
  - port：5672
  - username=admin
  - password=admin
  - 访问url：http://rabbit.test.yyjzt.com/#/queues admin admin
  
  
- 上传
   - 访问页面：http://10.4.9.170:8080/upload
   - 访问域名：http://10.4.9.170:2212/download/
   - 上传绝对路径：/home/admin/htdocs
   - 上传路径：syncwizard.dev.yyjzt.com/upload/（拼接上面路径就是服务器路径）
   - 下载：http://10.4.9.170:2212/download/20190619/15609094490471a7.rar
   - 文件存放位置 cd /home/admin/htdocs/syncwizard.dev.yyjzt.com/upload/
   
-  服务器查看日志
   -  本地日志  10.4.9.170     admin  jztdzswadmin70
   -  tail -n 99 /home/admin/htdocs/syncwizard.dev.yyjzt.com/syncwizard.wizard.log
   -  tail -f out.log
   -  tail -n 999 out.log 这是看最新999行的
   -  sz  out_2019-01-11-07.log   拷贝日志\
   -  cat out.log|grep 'abcd'   查看字符串的日志   

- 增加读写分离 
  -   @RoutingDataSource(DataSources.SLAVE_DB)  //从库
  -  //@RoutingDataSource(DataSources.MASTER_DB) //主库
  -  保存 用basemapper的方法 走主库
     http://localhost:8082/test/user/add?userName=999&password=1232143214
  -  查询： 走从库 localhost:8082/test/user/testmsdatasource
  
  1. pom里面 
   <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>2.1.9</version>
        </dependency>

   <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper-spring-boot-starter</artifactId>
            <version>2.0.0</version>
        </dependency>
  
  2. datasource包里面的配置 特别注意MybatisConfig，注意里面的import
     tk.mybatis.spring.annotation.MapperScan;是导入这个包
  3. 注意DatasourceConfig的数据源获取的前缀
  
  
- 自定义filter，拦截token，非shiro
- http://localhost:8082/getToken 获取token
- http://localhost:8082/testToken header中带上bear 
- 自定义拦截器为SynchronizationWizardFilter，注意在启动类上面加上@ServletComponentScan



### mongdb

- <font color=red>  **本机**</font>：http://git.yyjzt.com/mdt/synchronization-wizard.git
  - 地址：D:\mongodb（包直接解压：d:\mongodb-win32-x86_64-v3.2-latest.zip
    配置环境变量path d:\mongodb\bin）
- <font color=red> **命令**：</font>
  -  本机： 
     -    开启：cmd-->C:\Users\Administrator>mongod -storageEngine mmapv1 -dbpath d:\mongodb\(一直保持开启)
     
 --- 

### 增加利用Spring Boot的自动化配置特性来实现快速的将swagger2引入spring boot应用来生成API文档

#### 如何使用

在该项目的帮助下，我们的Spring Boot可以轻松的引入swagger2，主需要做下面两个步骤：

- 在`pom.xml`中引入依赖：

```xml
<dependency>
	<groupId>com.spring4all</groupId>
	<artifactId>spring-boot-starter-swagger</artifactId>
	<version>1.5.1.RELEASE</version>
</dependency>
```

- 在应用主类中增加`@EnableSwagger2Doc`注解

```java
@EnableSwagger2Doc
@SpringBootApplication
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

}
```

默认情况下就能产生所有当前Spring MVC加载的请求映射文档。

#### 参数配置

更细致的配置内容参考如下：

##### 配置示例

```properties
swagger.enabled=true

swagger.title=spring-boot-starter-swagger
swagger.description=Starter for swagger 2.x
swagger.version=1.4.0.RELEASE
swagger.license=Apache License, Version 2.0
swagger.licenseUrl=https://www.apache.org/licenses/LICENSE-2.0.html
swagger.termsOfServiceUrl=https://github.com/dyc87112/spring-boot-starter-swagger
swagger.contact.name=didi
swagger.contact.url=http://blog.didispace.com
swagger.contact.email=dyc87112@qq.com
swagger.base-package=com.didispace
swagger.base-path=/**
swagger.exclude-path=/error, /ops/**

swagger.globalOperationParameters[0].name=name one
swagger.globalOperationParameters[0].description=some description one
swagger.globalOperationParameters[0].modelRef=string
swagger.globalOperationParameters[0].parameterType=header
swagger.globalOperationParameters[0].required=true
swagger.globalOperationParameters[1].name=name two
swagger.globalOperationParameters[1].description=some description two
swagger.globalOperationParameters[1].modelRef=string
swagger.globalOperationParameters[1].parameterType=body
swagger.globalOperationParameters[1].required=false

// 取消使用默认预定义的响应消息,并使用自定义响应消息
swagger.apply-default-response-messages=false
swagger.global-response-message.get[0].code=401
swagger.global-response-message.get[0].message=401get
swagger.global-response-message.get[1].code=500
swagger.global-response-message.get[1].message=500get
swagger.global-response-message.get[1].modelRef=ERROR
swagger.global-response-message.post[0].code=500
swagger.global-response-message.post[0].message=500post
swagger.global-response-message.post[0].modelRef=ERROR
```

#### 配置说明

##### 默认配置

```properties
 swagger.enabled=是否启用swagger，默认：true
 swagger.title=标题
 swagger.description=描述
 swagger.version=版本
 swagger.license=许可证
 swagger.licenseUrl=许可证URL
 swagger.termsOfServiceUrl=服务条款URL
 swagger.contact.name=维护人
 swagger.contact.url=维护人URL
 swagger.contact.email=维护人email
 swagger.base-package=swagger扫描的基础包，默认：全扫描
 swagger.base-path=需要处理的基础URL规则，默认：/**
 swagger.exclude-path=需要排除的URL规则，默认：空
 swagger.host=文档的host信息，默认：空
 swagger.globalOperationParameters[0].name=参数名
 swagger.globalOperationParameters[0].description=描述信息
 swagger.globalOperationParameters[0].modelRef=指定参数类型
 swagger.globalOperationParameters[0].parameterType=指定参数存放位置,可选header,query,path,body.form
 swagger.globalOperationParameters[0].required=指定参数是否必传，true,false
```
 

#### Path规则说明

`swagger.base-path`和`swagger.exclude-path`使用ANT规则配置。

我们可以使用`swagger.base-path`来指定所有需要生成文档的请求路径基础规则，然后再利用`swagger.exclude-path`来剔除部分我们不需要的。

比如，通常我们可以这样设置：

```properties
management.context-path=/ops

swagger.base-path=/**
swagger.exclude-path=/ops/**, /error
```

上面的设置将解析所有除了`/ops/`开始以及spring boot自带`/error`请求路径。

其中，`exclude-path`可以配合`management.context-path=/ops`设置的spring boot actuator的context-path来排除所有监控端点。

#### 分组配置

当我们一个项目的API非常多的时候，我们希望对API文档实现分组。从1.2.0.RELEASE开始，将支持分组配置功能。

![分组功能](https://github.com/dyc87112/spring-boot-starter-swagger/blob/master/images/swagger-group.png)

具体配置内容如下：

```properties
 swagger.docket.<name>.title=标题
 swagger.docket.<name>.description=描述
 swagger.docket.<name>.version=版本
 swagger.docket.<name>.license=许可证
 swagger.docket.<name>.licenseUrl=许可证URL
 swagger.docket.<name>.termsOfServiceUrl=服务条款URL
 swagger.docket.<name>.contact.name=维护人
 swagger.docket.<name>.contact.url=维护人URL
 swagger.docket.<name>.contact.email=维护人email
 swagger.docket.<name>.base-package=swagger扫描的基础包，默认：全扫描
 swagger.docket.<name>.base-path=需要处理的基础URL规则，默认：/**
 swagger.docket.<name>.exclude-path=需要排除的URL规则，默认：空
 swagger.docket.<name>.name=参数名
 swagger.docket.<name>.modelRef=指定参数类型
 swagger.docket.<name>.parameterType=指定参数存放位置,可选header,query,path,body.form
 swagger.docket.<name>.required=true=指定参数是否必传，true,false
 swagger.docket.<name>.globalOperationParameters[0].name=参数名
 swagger.docket.<name>.globalOperationParameters[0].description=描述信息
 swagger.docket.<name>.globalOperationParameters[0].modelRef=指定参数存放位置,可选header,query,path,body.form
 swagger.docket.<name>.globalOperationParameters[0].parameterType=指定参数是否必传，true,false
```

说明：`<name>`为swagger文档的分组名称，同一个项目中可以配置多个分组，用来划分不同的API文档。


**分组配置示例**

```properties
swagger.docket.aaa.title=group-a
swagger.docket.aaa.description=Starter for swagger 2.x
swagger.docket.aaa.version=1.3.0.RELEASE
swagger.docket.aaa.termsOfServiceUrl=https://gitee.com/didispace/spring-boot-starter-swagger
swagger.docket.aaa.contact.name=zhaiyongchao
swagger.docket.aaa.contact.url=http://spring4all.com/
swagger.docket.aaa.contact.email=didi@potatomato.club
swagger.docket.aaa.excludePath=/ops/**
swagger.docket.aaa.globalOperationParameters[0].name=name three
swagger.docket.aaa.globalOperationParameters[0].description=some description three override
swagger.docket.aaa.globalOperationParameters[0].modelRef=string
swagger.docket.aaa.globalOperationParameters[0].parameterType=header

swagger.docket.bbb.title=group-bbb
swagger.docket.bbb.basePackage=com.yonghui
```

说明：默认配置与分组配置可以一起使用。在分组配置中没有配置的内容将使用默认配置替代，所以默认配置可以作为分组配置公共部分属性的配置。`swagger.docket.aaa.globalOperationParameters[0].name`会覆盖同名的全局配置。

#### JSR-303校验注解支持

支持对JSR-303校验注解的展示，如下图所示：

![JSR-303校验展示](https://github.com/dyc87112/spring-boot-starter-swagger/blob/master/images/jsr-303.png)

目前共支持以下几个注解：

- `@NotNull`
- `@Max、@Min`
- `@Size`
- `@Pattern`

### 自定义全局响应消息配置

支持 POST,GET,PUT,PATCH,DELETE,HEAD,OPTIONS,TRACE 全局响应消息配置，配置如下

```$xslt
// 取消使用默认预定义的响应消息,并使用自定义响应消息
swagger.apply-default-response-messages=false
swagger.global-response-message.get[0].code=401
swagger.global-response-message.get[0].message=401get
swagger.global-response-message.get[1].code=500
swagger.global-response-message.get[1].message=500get
swagger.global-response-message.get[1].modelRef=ERROR
swagger.global-response-message.post[0].code=500
swagger.global-response-message.post[0].message=500post
swagger.global-response-message.post[0].modelRef=ERROR
```
#### 使用
1. swagger2的原生使用：启动后访问http://localhost:8080/swagger-ui.html。可以直接找到对应的controller，然后进行发送请求
2. 生成文档。启动项目后，可以在test里面运行对应的程序，得到对应的文档
   - pom里面：加上对应的包的引用
   - Application中开启@EnableSwagger2Doc
   - properties：注意包的扫描swagger.base-package=com.jzt.sync.controller.swagger

--- 