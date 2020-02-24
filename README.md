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

访问地址   http://localhost:8080/swagger-ui.html



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

## 优雅导出excel
- 导出任意类型的数据 
- 自由设置表头 
- 自由设置字段的导出格式

-   export函数可以直接向客户端返回一个excel数据，其中productInfoPos为待导出的数据列表，ExcelHeaderInfo用来保存表头信息，包括表头名称，表头的首列，尾列，首行，尾行。因为默认导出的数据格式都是字符串型，所以还需要一个Map参数用来指定某个字段的格式化类型（例如数字类型，小数类型、日期类型）。
```java

@Override
    public void export(HttpServletResponse response, String fileName) {
        // 待导出数据
        List<TtlProductInfoPo> productInfoPos = this.multiThreadListProduct();
        ExcelUtils excelUtils = new ExcelUtils(productInfoPos, getHeaderInfo(), getFormatInfo());
        excelUtils.sendHttpResponse(response, fileName, excelUtils.getWorkbook());
    }

    // 获取表头信息
    private List<ExcelHeaderInfo> getHeaderInfo() {
        return Arrays.asList(
                new ExcelHeaderInfo(1, 1, 0, 0, "id"),
                new ExcelHeaderInfo(1, 1, 1, 1, "商品名称"),

                new ExcelHeaderInfo(0, 0, 2, 3, "分类"),
                new ExcelHeaderInfo(1, 1, 2, 2, "类型ID"),
                new ExcelHeaderInfo(1, 1, 3, 3, "分类名称"),

                new ExcelHeaderInfo(0, 0, 4, 5, "品牌"),
                new ExcelHeaderInfo(1, 1, 4, 4, "品牌ID"),
                new ExcelHeaderInfo(1, 1, 5, 5, "品牌名称"),

                new ExcelHeaderInfo(0, 0, 6, 7, "商店"),
                new ExcelHeaderInfo(1, 1, 6, 6, "商店ID"),
                new ExcelHeaderInfo(1, 1, 7, 7, "商店名称"),

                new ExcelHeaderInfo(1, 1, 8, 8, "价格"),
                new ExcelHeaderInfo(1, 1, 9, 9, "库存"),
                new ExcelHeaderInfo(1, 1, 10, 10, "销量"),
                new ExcelHeaderInfo(1, 1, 11, 11, "插入时间"),
                new ExcelHeaderInfo(1, 1, 12, 12, "更新时间"),
                new ExcelHeaderInfo(1, 1, 13, 13, "记录是否已经删除")
        );
    }

    // 获取格式化信息
    private Map<String, ExcelFormat> getFormatInfo() {
        Map<String, ExcelFormat> format = new HashMap<>();
        format.put("id", ExcelFormat.FORMAT_INTEGER);
        format.put("categoryId", ExcelFormat.FORMAT_INTEGER);
        format.put("branchId", ExcelFormat.FORMAT_INTEGER);
        format.put("shopId", ExcelFormat.FORMAT_INTEGER);
        format.put("price", ExcelFormat.FORMAT_DOUBLE);
        format.put("stock", ExcelFormat.FORMAT_INTEGER);
        format.put("salesNum", ExcelFormat.FORMAT_INTEGER);
        format.put("isDel", ExcelFormat.FORMAT_INTEGER);
        return format;
    }
    
```


#### 成员变量
我们重点看`ExcelUtils`这个类，这个类是实现导出的核心，先来看一下三个成员变量
```java
    private List list;
    private List<ExcelHeaderInfo> excelHeaderInfos;
    private Map<String, ExcelFormat> formatInfo;
```
##### list
该成员变量用来保存待导出的数据

##### ExcelHeaderInfo
该成员变量主要用来保存表头信息，因为我们需要定义多个表头信息，所以需要使用一个列表来保存，`ExcelHeaderInfo`构造函数如下
`ExcelHeaderInfo(int firstRow, int lastRow, int firstCol, int lastCol, String title)`
- `firstRow`：该表头所占位置的首行
- `lastRow`：该表头所占位置的尾行
- `firstCol`：该表头所占位置的首列
- `lastCol`：该表头所占位置的尾行
- `title`：该表头的名称

##### ExcelFormat
该参数主要用来格式化字段，我们需要预先约定好转换成那种格式，不能随用户自己定。所以我们定义了一个枚举类型的变量，该枚举类只有一个字符串类型成员变量，用来保存想要转换的格式，例如`FORMAT_INTEGER`就是转换成整型。因为我们需要接受多个字段的转换格式，所以定义了一个Map类型来接收，该参数可以省略（默认格式为字符串）
```java
public enum ExcelFormat {

    FORMAT_INTEGER("INTEGER"),
    FORMAT_DOUBLE("DOUBLE"),
    FORMAT_PERCENT("PERCENT"),
    FORMAT_DATE("DATE");

    private String value;

    ExcelFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
```
#### 核心方法
##### 1. 创建表头
> 该方法用来初始化表头，而创建表头最关键的就是poi中Sheet类的`addMergedRegion(CellRangeAddress var1)`方法，该方法用于`单元格融合`。我们会遍历ExcelHeaderInfo列表，按照每个ExcelHeaderInfo的坐标信息进行单元格融合，然后在融合之后的每个单元`首行`和`首列`的位置创建单元格，然后为单元格赋值即可，通过上面的步骤就完成了任意类型的表头设置。
```java
    // 创建表头
    private void createHeader(Sheet sheet, CellStyle style) {
        for (ExcelHeaderInfo excelHeaderInfo : excelHeaderInfos) {
            Integer lastRow = excelHeaderInfo.getLastRow();
            Integer firstRow = excelHeaderInfo.getFirstRow();
            Integer lastCol = excelHeaderInfo.getLastCol();
            Integer firstCol = excelHeaderInfo.getFirstCol();

            // 行距或者列距大于0才进行单元格融合
            if ((lastRow - firstRow) != 0 || (lastCol - firstCol) != 0) {
                sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
            }
            // 获取当前表头的首行位置
            Row row = sheet.getRow(firstRow);
            // 在表头的首行与首列位置创建一个新的单元格
            Cell cell = row.createCell(firstCol);
            // 赋值单元格
            cell.setCellValue(excelHeaderInfo.getTitle());
            cell.setCellStyle(style);
            sheet.setColumnWidth(firstCol, sheet.getColumnWidth(firstCol) * 17 / 12);
        }
    }
```

##### 2. 转换数据
> 在进行正文赋值之前，我们先要对原始数据列表转换成字符串的二维数组，之所以转成字符串格式是因为可以统一的处理各种类型，之后有需要我们再转换回来即可。
```java
    // 将原始数据转成二维数组
    private String[][] transformData() {
        int dataSize = this.list.size();
        String[][] datas = new String[dataSize][];
        // 获取报表的列数
        Field[] fields = list.get(0).getClass().getDeclaredFields();
        // 获取实体类的字段名称数组
        List<String> columnNames = this.getBeanProperty(fields);
        for (int i = 0; i < dataSize; i++) {
            datas[i] = new String[fields.length];
            for (int j = 0; j < fields.length; j++) {
                try {
                    // 赋值
                    datas[i][j] = BeanUtils.getProperty(list.get(i), columnNames.get(j));
                } catch (Exception e) {
                    LOGGER.error("获取对象属性值失败");
                    e.printStackTrace();
                }
            }
        }
        return datas;
    }
```
这个方法中我们通过使用反射技术，很巧妙的实现了任意类型的数据导出（这里的任意类型指的是任意的报表类型，不同的报表，导出的数据肯定是不一样的，那么在Java实现中的实体类肯定也是不一样的）。要想将一个List转换成相应的二维数组，我们得知道如下的信息；
- 二维数组的列数
- 二维数组的行数
- 二维数组每个元素的值

如果获取以上三个信息呢？
- 通过反射中的`Field[] getDeclaredFields()`这个方法获取实体类的所有字段，从而间接知道一共有多少列
- List的大小不就是二维数组的行数了嘛
- 虽然每个实体类的字段名不一样，那么我们就真的无法获取到实体类某个字段的值了吗？不是的，你要知道，你拥有了`反射`，你就相当于拥有了全世界，那还有什么做不到的呢。这里我们没有直接使用反射，而是使用了一个叫做`BeanUtils`的工具，该工具可以很方便的帮助我们对一个实体类进行字段的赋值与字段值的获取。很简单，通过`BeanUtils.getProperty(list.get(i), columnNames.get(j))`这一行代码，我们就获取了实体`list.get(i)`中名称为`columnNames.get(j)`这个字段的值。`list.get(i)`当然是我们遍历原始数据的实体类，而`columnNames`列表则是一个实体类所有字段名的数组，也是通过反射的方法获取到的，具体实现可以参考LZ的源代码。

##### 3. 赋值正文
> 这里的正文指定是正式的表格数据内容，其实这一些没有太多的奇淫技巧，主要的功能在上面已经实现了，这里主要是进行单元格的赋值与导出格式的处理（主要是为了导出excel后可以进行方便的运算）
```java
    // 创建正文
    private void createContent(Row row, CellStyle style, String[][] content, int i, Field[] fields) {
        List<String> columnNames = getBeanProperty(fields);
        for (int j = 0; j < columnNames.size(); j++) {
            if (formatInfo == null) {
                row.createCell(j).setCellValue(content[i][j]);
                continue;
            }
            if (formatInfo.containsKey(columnNames.get(j))) {
                switch (formatInfo.get(columnNames.get(j)).getValue()) {
                    case "DOUBLE":
                        row.createCell(j).setCellValue(Double.parseDouble(content[i][j]));
                        break;
                    case "INTEGER":
                        row.createCell(j).setCellValue(Integer.parseInt(content[i][j]));
                        break;
                    case "PERCENT":
                        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));
                        Cell cell = row.createCell(j);
                        cell.setCellStyle(style);
                        cell.setCellValue(Double.parseDouble(content[i][j]));
                        break;
                    case "DATE":
                        row.createCell(j).setCellValue(this.parseDate(content[i][j]));
                }
            } else {
                row.createCell(j).setCellValue(content[i][j]);
            }
        }
    }
```

导出工具类的核心方法就差不多说完了，下面说一下关于多线程查询的问题

### 多扯两点
#### 1. 多线程查询数据
> 理想很丰满，现实虽然不是很残酷，但是也跟想象的不一样。LZ虽然对50w的数据分别创建20个线程去查询，但是总体的效率并不是50w/20，而是仅仅快了几秒钟，知道原因的小伙伴可以给我留个言一起探讨一下。

下面先说说具体思路：因为多个线程之间是同时执行的，你不能够保证哪个线程先执行完毕，但是我们却得保证数据顺序的一致性。在这里我们使用了`Callable`接口，通过实现`Callable`接口的线程可以拥有返回值，我们获取到所有子线程的查询结果，然后合并到一个结果集中即可。那么如何保证合并的顺序呢?我们先创建了一个`FutureTask`类型的List，该`FutureTask`的类型就是返回的结果集。
```java
List<FutureTask<List<TtlProductInfoPo>>> tasks = new ArrayList<>();
```

当我们每启动一个线程的时候，就将该线程的`FutureTask`添加到`tasks`列表中，这样tasks列表中的元素顺序就是我们启动线程的顺序。
```java
           FutureTask<List<TtlProductInfoPo>> task = new FutureTask<>(new listThread(map));
            log.info("开始查询第{}条开始的{}条记录", i * THREAD_MAX_ROW, THREAD_MAX_ROW);
            new Thread(task).start();
            // 将任务添加到tasks列表中
            tasks.add(task);
```

接下来，就是顺序塞值了，我们按顺序从`tasks`列表中取出`FutureTask`，然后执行`FutureTask`的`get()`方法，该方法会阻塞调用它的线程，知道拿到返回结果。这样一套循环下来，就完成了所有数据的按顺序存储。
```
       for (FutureTask<List<TtlProductInfoPo>> task : tasks) {
            try {
                productInfoPos.addAll(task.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
```

#### 2. 如何解决接口超时
如果需要导出海量数据，可能会存在一个问题：`接口超时`，主要原因就是整个导出过程的时间太长了。其实也很好解决，接口的响应时间太长，我们缩短响应时间不就可以了嘛。我们使用`异步编程`解决方案，异步编程的实现方式有很多，这里我们使用最简单的spring中的`Async`注解，加上了这个注解的方法可以立马返回响应结果。关于注解的使用方式，大家可以自己查阅一下，下面讲一下关键的实现步骤：
1. 编写异步接口，该接口负责接收客户端的导出请求，然后开始执行导出（注意：这里的导出不是直接向客户端返回，而是下载到服务器本地），只要下达了导出指令，就可以马上给客户端返回一个该excel文件的唯一标志（用于以后查找该文件），接口结束。
2. 编写excel状态接口，客户端拿到excel文件的唯一标志之后，开始每秒轮询调用该接口检查excel文件的导出状态
3. 编写从服务器本地返回excel文件接口，如果客户端检查到excel已经成功下载到`到服务器本地`，这个时候就可以请求该接口直接下载文件了。

这样就可以解决接口超时的问题了。

### 源码服用姿势
1. 建表（数据自己插入哦）
```sql
CREATE TABLE `ttl_product_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录唯一标识',
  `product_name` varchar(50) NOT NULL COMMENT '商品名称',
  `category_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '类型ID',
  `category_name` varchar(50) NOT NULL COMMENT '冗余分类名称-避免跨表join',
  `branch_id` bigint(20) NOT NULL COMMENT '品牌ID',
  `branch_name` varchar(50) NOT NULL COMMENT '冗余品牌名称-避免跨表join',
  `shop_id` bigint(20) NOT NULL COMMENT '商品ID',
  `shop_name` varchar(50) NOT NULL COMMENT '冗余商店名称-避免跨表join',
  `price` decimal(10,2) NOT NULL COMMENT '商品当前价格-属于热点数据，而且价格变化需要记录，需要价格详情表',
  `stock` int(11) NOT NULL COMMENT '库存-热点数据',
  `sales_num` int(11) NOT NULL COMMENT '销量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_del` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '记录是否已经删除',
  PRIMARY KEY (`id`),
  KEY `idx_shop_category_salesnum` (`shop_id`,`category_id`,`sales_num`),
  KEY `idx_category_branch_price` (`category_id`,`branch_id`,`price`),
  KEY `idx_productname` (`product_name`)
) ENGINE=InnoDB AUTO_INCREMENT=15000001 DEFAULT CHARSET=utf8 COMMENT='商品信息表';
```


http://localhost:8080/excelUtils/export

---

使用 localdatetime，具体例子可见
1. TestLocalDateTimeController。有前端传入和后端传出的例子，需要定义一个自定义的转化类LocalDateTimeConverter
2. 更多例子见：LocalDateTimeUtil


