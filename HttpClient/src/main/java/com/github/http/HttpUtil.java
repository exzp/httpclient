package com.github.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.ResponseDate;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	public String doPost(String url,String json,String charset) throws ClientProtocolException, IOException{
		    HttpPost httpPost = new HttpPost(url);
	        RequestConfig.custom().setSocketTimeout(2000);
	        httpPost.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET,charset);
	        if (null != json) {
	            StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
	            // 将请求实体设置到httpPost对象中
	            httpPost.setEntity(stringEntity);
	        }
	        CloseableHttpResponse response = null;
	        try {
	            // 执行请求
	            response = (new DefaultHttpClient()).execute(httpPost);
	            HttpEntity entity = response.getEntity();
	            if (null == entity) {
	                return null;
	            }
	            return EntityUtils.toString(response.getEntity(), charset);
	        } finally {
	            if (response != null) {
	                response.close();
	            }
	        }
	}
	
}
