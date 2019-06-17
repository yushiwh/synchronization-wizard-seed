package com.jzt.sync.util;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;

/**
 * project mdt-coupon-platform
 *
 * @author chenghai on 2019/3/19 0019. - 星期二
 * nickName louyedaren
 */
public class HttpHelp {
	public static String postByJson(String url, String jsonStr) {
		HttpRequest post = HttpUtil.createPost(url);
		HttpRequest request = post.body(jsonStr, ContentType.JSON.toString());
		return request.execute().body();
	}

}
