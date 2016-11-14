package net.tatans.iapetus.android.rest.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;


/**
 * 本类为HTTP请求相关工具类，在该类中可以处理通用的HTTP各种请求
 * 
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
public class HttpUtil {

	private String url = null;
	private HttpClient httpClient = null;
	private HttpRequestBase request = null;
	private HttpResponse response = null;

	/**
	 * HTTP请求类型
	 * 
	 * @author yuanyao
	 * 
	 */
	public enum HttpType {
		GET("Get"), POST("Post"), HEAD("Head"), PUT("Put"), DELETE("Delete"), OPTIONS(
				"Options"), TRACE("Trace"), PATCH("Patch");
		private String key;

		private HttpType(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}
	}

	public HttpUtil(String url) {
		this.url = url;
	}

	/**
	 * 获取HTTP请求响应内容
	 * 
	 * @param url
	 *            请求URL地址
	 * @param type
	 *            HTTP请求类型
	 * @param postParams
	 *            当请求为POST时提供的POST参数
	 * @return HTTP响应返回内容
	 * @throws Exception
	 */
	public String getHttpResponseContent(HttpType type,
			Map<String, String> postParams) throws Exception {
		HttpEntity entity = getHttpResponseEntity(type, postParams);
		String content = "";
		if (entity != null) {
			InputStream instreams = entity.getContent();
			content = convertStreamToString(instreams);
		}
		close();
		return content;
	}

	/**
	 * 返回HTTP响应结果实体
	 * 
	 * @param url
	 *            请求URL地址
	 * @param type
	 *            HTTP请求类型
	 * @param postParams
	 *            当请求为POST时提供的POST参数
	 * @return HTTP响应实体
	 * @throws Exception
	 */
	private HttpEntity getHttpResponseEntity(HttpType type,
			Map<String, String> postParams) throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpRequestBase request = getMethod(type, url);
		HttpResponse response = client.execute(request);
		if (type == HttpType.POST && postParams != null) {
			setPostParams(request, postParams);
		}
		return response.getEntity();
	}

	private void close() {
		if (request != null && !request.isAborted()) {
			request.abort();
		}
		//HttpClientUtils.closeQuietly(response);
		//HttpClientUtils.closeQuietly(httpClient);
	}

	/**
	 * 设置POST请求POST参数
	 * 
	 * @param request
	 *            POST请求
	 * @param postParams
	 *            POST参数
	 * @throws Exception
	 */
	private void setPostParams(HttpRequestBase request,
			Map<String, String> postParams) throws Exception {
		List<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
		for (Map.Entry<String, String> entry : postParams.entrySet()) {
			postData.add(new BasicNameValuePair(entry.getKey(), entry
					.getValue()));
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postData,
				HTTP.UTF_8);
		((HttpResponse) request).setEntity(entity);
	}

	private HttpRequestBase getMethod(HttpType type, String url)
			throws Exception {
		if (url == null || url.length() <= 0) {
			throw new RuntimeException("请提供HTTP请求的URL地址");
		}
		String className = "org.apache.http.client.methods.Http"
				+ type.getKey();
		Class clazz = Class.forName(className);
		return ((HttpRequestBase) clazz.getConstructor(String.class)
				.newInstance(url));
	}

	/**
	 * 从输入流中读取数据并装换为字符串输出
	 * 
	 * @param is
	 *            输入流
	 * @return 输入流读取出的字符串
	 * @throws Exception
	 */
	private String convertStreamToString(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		is.close();
		return sb.toString().trim();
	}

}
