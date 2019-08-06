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