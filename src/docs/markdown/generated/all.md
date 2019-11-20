# ç§å­é¡¹ç®


<a name="overview"></a>
## Overview
ç§å­é¡¹ç®çswagger2


### Version information
*Version* : 1.1.0.RELEASE


### Contact information
*Contact* : yushi


### License information
*License* : nick  
*License URL* : https://www.apache.org/licenses/LICENSE-2.0.html  
*Terms of service* : https://github.com/dyc87112/spring-boot-starter-swagger


### URI scheme
*Host* : localhost:8082  
*BasePath* : /


### Tags

* 用户管理 : Swagger User Controller




<a name="paths"></a>
## Paths

<a name="createusingpost"></a>
### 创建用户
```
POST /swaggerUsers
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Header**|**access_token**  <br>*required*|user access token|string|
|**Header**|**timestamp**  <br>*optional*|access timestamp|integer (int32)|
|**Body**|**user**  <br>*required*|user|[用户基本信息](#f27103dead187fe6c304ecd07f70768d)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[用户基本信息](#f27103dead187fe6c304ecd07f70768d)|
|**201**|Created|No Content|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Consumes

* `application/json`


#### Produces

* `*/*`


#### Tags

* 用户管理


#### Security

|Type|Name|Scopes|
|---|---|---|
|**apiKey**|**[Authorization](#authorization)**|global|


<a name="listusingget"></a>
### 用户列表
```
GET /swaggerUsers
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Header**|**access_token**  <br>*required*|user access token|string|
|**Header**|**timestamp**  <br>*optional*|access timestamp|integer (int32)|
|**Query**|**pageIndex**  <br>*optional*|查看第几页|integer (int32)|
|**Query**|**pageSize**  <br>*optional*|每页多少条|integer (int32)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|< [用户基本信息](#f27103dead187fe6c304ecd07f70768d) > array|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* 用户管理


#### Security

|Type|Name|Scopes|
|---|---|---|
|**apiKey**|**[Authorization](#authorization)**|global|


<a name="findbyidusingget"></a>
### 用户详情
```
GET /swaggerUsers/{id}
```


#### Parameters

|Type|Name|Description|Schema|
|---|---|---|---|
|**Header**|**access_token**  <br>*required*|user access token|string|
|**Header**|**timestamp**  <br>*optional*|access timestamp|integer (int32)|
|**Path**|**id**  <br>*required*|id|integer (int64)|


#### Responses

|HTTP Code|Description|Schema|
|---|---|---|
|**200**|OK|[用户基本信息](#f27103dead187fe6c304ecd07f70768d)|
|**401**|Unauthorized|No Content|
|**403**|Forbidden|No Content|
|**404**|Not Found|No Content|


#### Produces

* `*/*`


#### Tags

* 用户管理


#### Security

|Type|Name|Scopes|
|---|---|---|
|**apiKey**|**[Authorization](#authorization)**|global|




<a name="definitions"></a>
## Definitions

<a name="f27103dead187fe6c304ecd07f70768d"></a>
### 用户基本信息

|Name|Description|Schema|
|---|---|---|
|**address**  <br>*optional*|地址|string|
|**age**  <br>*optional*|年龄  <br>**Minimum value** : `1`  <br>**Maximum value** : `150`|integer (int32)|
|**email**  <br>*optional*|邮箱  <br>**Pattern** : `"^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$"`|string|
|**name**  <br>*optional*|姓名  <br>**Length** : `0 - 20`|string|




<a name="securityscheme"></a>
## Security

<a name="authorization"></a>
### Authorization
*Type* : apiKey  
*Name* : TOKEN  
*In* : HEADER



