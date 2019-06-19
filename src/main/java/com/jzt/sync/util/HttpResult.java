/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: HysResult
 * Author:   yushi
 * Date:     2019/5/9 14:54
 * Description: 好药师返回
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jzt.sync.util;

import java.io.Serializable;

/**
 * @author yushi
 * @create 2019/5/9
 * @since 1.0.0
 */

public class HttpResult implements Serializable {


    /**
     * success : true
     * msg :
     * data : {"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzIjoiMTExMTExMTExMiIsImQiOiIxMDg5NzA0OTQ3OTM2MTg2MzY5IiwidCI6IlIwNSIsInUiOjEwODk3MDQ5NDc5MzYxODYzNzAsInYiOiIxIiwibCI6Iua1i-ivleW6l-mTuiIsImV4cCI6MTU2MDkxNTEzOSwiaWF0IjoxNTYwODI4NzM5LCJvIjoiMTM4ODYxMDM2NzYifQ.GXz5Np9aGiiET0J2piW77ENWe3nSHuBVcNsH27EAAcQ","user":{"userAgentId":"1089704947936186370","loginName":"13886103676","loginPwd":"d60a821be765387eab357682c0a228f6","status":"1","userType":"R05","dataId":"1089704947936186369","stationId":"1111111112","linkName":"测试店铺","linkPhone":"17720508119","isChild":"0","tokenVersion":"1","deleteFlag":"0","lastLoginTime":1560828471000,"createUser":"hb_admin","updateUser":"hb_admin","createAt":1548640880000,"updateAt":1548640879000,"b2bUserAgentId":"1089704947936186370","userSource":"0","unionid":null},"storeApprove":{"storeApproveId":"1098408881309687810","userAgentId":"1089704947936186370","storeNo":"1089704947936186369","siteId":"1111111112","storeName":"测试店铺","firmName":"测试店铺","status":"1","storeStatus":"6","adCode":420105,"linkMan":"test","linkTel":"13886103676","storeAdmin":"","authTime":1550715531000,"checkMan":"1064846684039352322","registeTime":1548640880000,"openState":"1","openTime":1548642248000,"storeAddress":"陶家岭路5.5医药产业园区","notpassOpinion":"","deleteFlag":"0","updateBy":"1064846684039352322","createAt":1550716058000},"custApply":null}
     * ext : null
     */

    private boolean success;
    private String msg;
    private DataBean data;
    private Object ext;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getExt() {
        return ext;
    }

    public void setExt(Object ext) {
        this.ext = ext;
    }

    public static class DataBean {
        /**
         * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzIjoiMTExMTExMTExMiIsImQiOiIxMDg5NzA0OTQ3OTM2MTg2MzY5IiwidCI6IlIwNSIsInUiOjEwODk3MDQ5NDc5MzYxODYzNzAsInYiOiIxIiwibCI6Iua1i-ivleW6l-mTuiIsImV4cCI6MTU2MDkxNTEzOSwiaWF0IjoxNTYwODI4NzM5LCJvIjoiMTM4ODYxMDM2NzYifQ.GXz5Np9aGiiET0J2piW77ENWe3nSHuBVcNsH27EAAcQ
         * user : {"userAgentId":"1089704947936186370","loginName":"13886103676","loginPwd":"d60a821be765387eab357682c0a228f6","status":"1","userType":"R05","dataId":"1089704947936186369","stationId":"1111111112","linkName":"测试店铺","linkPhone":"17720508119","isChild":"0","tokenVersion":"1","deleteFlag":"0","lastLoginTime":1560828471000,"createUser":"hb_admin","updateUser":"hb_admin","createAt":1548640880000,"updateAt":1548640879000,"b2bUserAgentId":"1089704947936186370","userSource":"0","unionid":null}
         * storeApprove : {"storeApproveId":"1098408881309687810","userAgentId":"1089704947936186370","storeNo":"1089704947936186369","siteId":"1111111112","storeName":"测试店铺","firmName":"测试店铺","status":"1","storeStatus":"6","adCode":420105,"linkMan":"test","linkTel":"13886103676","storeAdmin":"","authTime":1550715531000,"checkMan":"1064846684039352322","registeTime":1548640880000,"openState":"1","openTime":1548642248000,"storeAddress":"陶家岭路5.5医药产业园区","notpassOpinion":"","deleteFlag":"0","updateBy":"1064846684039352322","createAt":1550716058000}
         * custApply : null
         */

        private String token;
        private UserBean user;
        private StoreApproveBean storeApprove;
        private Object custApply;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public StoreApproveBean getStoreApprove() {
            return storeApprove;
        }

        public void setStoreApprove(StoreApproveBean storeApprove) {
            this.storeApprove = storeApprove;
        }

        public Object getCustApply() {
            return custApply;
        }

        public void setCustApply(Object custApply) {
            this.custApply = custApply;
        }

        public static class UserBean {
            /**
             * userAgentId : 1089704947936186370
             * loginName : 13886103676
             * loginPwd : d60a821be765387eab357682c0a228f6
             * status : 1
             * userType : R05
             * dataId : 1089704947936186369
             * stationId : 1111111112
             * linkName : 测试店铺
             * linkPhone : 17720508119
             * isChild : 0
             * tokenVersion : 1
             * deleteFlag : 0
             * lastLoginTime : 1560828471000
             * createUser : hb_admin
             * updateUser : hb_admin
             * createAt : 1548640880000
             * updateAt : 1548640879000
             * b2bUserAgentId : 1089704947936186370
             * userSource : 0
             * unionid : null
             */

            private String userAgentId;
            private String loginName;
            private String loginPwd;
            private String status;
            private String userType;
            private String dataId;
            private String stationId;
            private String linkName;
            private String linkPhone;
            private String isChild;
            private String tokenVersion;
            private String deleteFlag;
            private long lastLoginTime;
            private String createUser;
            private String updateUser;
            private long createAt;
            private long updateAt;
            private String b2bUserAgentId;
            private String userSource;
            private Object unionid;

            public String getUserAgentId() {
                return userAgentId;
            }

            public void setUserAgentId(String userAgentId) {
                this.userAgentId = userAgentId;
            }

            public String getLoginName() {
                return loginName;
            }

            public void setLoginName(String loginName) {
                this.loginName = loginName;
            }

            public String getLoginPwd() {
                return loginPwd;
            }

            public void setLoginPwd(String loginPwd) {
                this.loginPwd = loginPwd;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getUserType() {
                return userType;
            }

            public void setUserType(String userType) {
                this.userType = userType;
            }

            public String getDataId() {
                return dataId;
            }

            public void setDataId(String dataId) {
                this.dataId = dataId;
            }

            public String getStationId() {
                return stationId;
            }

            public void setStationId(String stationId) {
                this.stationId = stationId;
            }

            public String getLinkName() {
                return linkName;
            }

            public void setLinkName(String linkName) {
                this.linkName = linkName;
            }

            public String getLinkPhone() {
                return linkPhone;
            }

            public void setLinkPhone(String linkPhone) {
                this.linkPhone = linkPhone;
            }

            public String getIsChild() {
                return isChild;
            }

            public void setIsChild(String isChild) {
                this.isChild = isChild;
            }

            public String getTokenVersion() {
                return tokenVersion;
            }

            public void setTokenVersion(String tokenVersion) {
                this.tokenVersion = tokenVersion;
            }

            public String getDeleteFlag() {
                return deleteFlag;
            }

            public void setDeleteFlag(String deleteFlag) {
                this.deleteFlag = deleteFlag;
            }

            public long getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(long lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public String getCreateUser() {
                return createUser;
            }

            public void setCreateUser(String createUser) {
                this.createUser = createUser;
            }

            public String getUpdateUser() {
                return updateUser;
            }

            public void setUpdateUser(String updateUser) {
                this.updateUser = updateUser;
            }

            public long getCreateAt() {
                return createAt;
            }

            public void setCreateAt(long createAt) {
                this.createAt = createAt;
            }

            public long getUpdateAt() {
                return updateAt;
            }

            public void setUpdateAt(long updateAt) {
                this.updateAt = updateAt;
            }

            public String getB2bUserAgentId() {
                return b2bUserAgentId;
            }

            public void setB2bUserAgentId(String b2bUserAgentId) {
                this.b2bUserAgentId = b2bUserAgentId;
            }

            public String getUserSource() {
                return userSource;
            }

            public void setUserSource(String userSource) {
                this.userSource = userSource;
            }

            public Object getUnionid() {
                return unionid;
            }

            public void setUnionid(Object unionid) {
                this.unionid = unionid;
            }
        }

        public static class StoreApproveBean {
            /**
             * storeApproveId : 1098408881309687810
             * userAgentId : 1089704947936186370
             * storeNo : 1089704947936186369
             * siteId : 1111111112
             * storeName : 测试店铺
             * firmName : 测试店铺
             * status : 1
             * storeStatus : 6
             * adCode : 420105
             * linkMan : test
             * linkTel : 13886103676
             * storeAdmin :
             * authTime : 1550715531000
             * checkMan : 1064846684039352322
             * registeTime : 1548640880000
             * openState : 1
             * openTime : 1548642248000
             * storeAddress : 陶家岭路5.5医药产业园区
             * notpassOpinion :
             * deleteFlag : 0
             * updateBy : 1064846684039352322
             * createAt : 1550716058000
             */

            private String storeApproveId;
            private String userAgentId;
            private String storeNo;
            private String siteId;
            private String storeName;
            private String firmName;
            private String status;
            private String storeStatus;
            private int adCode;
            private String linkMan;
            private String linkTel;
            private String storeAdmin;
            private long authTime;
            private String checkMan;
            private long registeTime;
            private String openState;
            private long openTime;
            private String storeAddress;
            private String notpassOpinion;
            private String deleteFlag;
            private String updateBy;
            private long createAt;

            public String getStoreApproveId() {
                return storeApproveId;
            }

            public void setStoreApproveId(String storeApproveId) {
                this.storeApproveId = storeApproveId;
            }

            public String getUserAgentId() {
                return userAgentId;
            }

            public void setUserAgentId(String userAgentId) {
                this.userAgentId = userAgentId;
            }

            public String getStoreNo() {
                return storeNo;
            }

            public void setStoreNo(String storeNo) {
                this.storeNo = storeNo;
            }

            public String getSiteId() {
                return siteId;
            }

            public void setSiteId(String siteId) {
                this.siteId = siteId;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }

            public String getFirmName() {
                return firmName;
            }

            public void setFirmName(String firmName) {
                this.firmName = firmName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStoreStatus() {
                return storeStatus;
            }

            public void setStoreStatus(String storeStatus) {
                this.storeStatus = storeStatus;
            }

            public int getAdCode() {
                return adCode;
            }

            public void setAdCode(int adCode) {
                this.adCode = adCode;
            }

            public String getLinkMan() {
                return linkMan;
            }

            public void setLinkMan(String linkMan) {
                this.linkMan = linkMan;
            }

            public String getLinkTel() {
                return linkTel;
            }

            public void setLinkTel(String linkTel) {
                this.linkTel = linkTel;
            }

            public String getStoreAdmin() {
                return storeAdmin;
            }

            public void setStoreAdmin(String storeAdmin) {
                this.storeAdmin = storeAdmin;
            }

            public long getAuthTime() {
                return authTime;
            }

            public void setAuthTime(long authTime) {
                this.authTime = authTime;
            }

            public String getCheckMan() {
                return checkMan;
            }

            public void setCheckMan(String checkMan) {
                this.checkMan = checkMan;
            }

            public long getRegisteTime() {
                return registeTime;
            }

            public void setRegisteTime(long registeTime) {
                this.registeTime = registeTime;
            }

            public String getOpenState() {
                return openState;
            }

            public void setOpenState(String openState) {
                this.openState = openState;
            }

            public long getOpenTime() {
                return openTime;
            }

            public void setOpenTime(long openTime) {
                this.openTime = openTime;
            }

            public String getStoreAddress() {
                return storeAddress;
            }

            public void setStoreAddress(String storeAddress) {
                this.storeAddress = storeAddress;
            }

            public String getNotpassOpinion() {
                return notpassOpinion;
            }

            public void setNotpassOpinion(String notpassOpinion) {
                this.notpassOpinion = notpassOpinion;
            }

            public String getDeleteFlag() {
                return deleteFlag;
            }

            public void setDeleteFlag(String deleteFlag) {
                this.deleteFlag = deleteFlag;
            }

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
            }

            public long getCreateAt() {
                return createAt;
            }

            public void setCreateAt(long createAt) {
                this.createAt = createAt;
            }
        }
    }
}