package org.ash_javaTest;

import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.input.ReaderInputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;
import org.apache.http.impl.client.CloseableHttpClient;

public class SenApi {
	public final static String HMAC_SHA256 = "HmacSHA256";
	public final static String PCHOME_URL = "https://adm.pcstore.com.tw";// PCHOME商城URL
	public final static String API_URL = "/api/data_exchange/api_main.php";// PCHOME商城 API URL
	private String retailerId;
	private String apikey;
	private String secret;
	private static SenApi util;
	/* 讀取時間(單位：毫秒) */
	public static final int READ_TIMEOUT = 10000;

	/* 連線時間(單位：毫秒) */
	public static final int CONN_TIMEOUT = 10000;

	/**
	 * PCHOME商城 API傳送
	 * 
	 * @param url
	 * @param requestParamMap request參數 Ex: Map<String, List<String>>
	 *                        requestParamMap = new HashMap<String, List<String>>();
	 *                        List<String> list = new ArrayList();
	 *                        //list.add("152982144");
	 *                        requestParamMap.put("CategoryId", list);
	 * @return JSONObject
	 * @throws Exception
	 */
	public Object post(String action, String dataList, String forsigstrSub) throws Exception {

		CloseableHttpClient buildSSLCloseableHttpClient = newClient();
		System.out.println("[tp] => " + action);

		Object resultJsonObject = null;
		// request必要基本參數
		Map<String, String> requestMap = new LinkedHashMap<String, String>();
		// requestMap.put("ApiKey", apikey); // API Key
		Calendar cal = Calendar.getInstance();
		String t = String.valueOf(Math.round(cal.getTime().getTime() / 1000));
		requestMap.put("t", t);
		// request參數
		System.out.println("[t]  => " + t);

		requestMap.put("data_list", dataList);
		requestMap.put("tp", action);
		// 製作簽章str

		// TODO 測試待刪除
		if (org.apache.commons.lang.StringUtils.isBlank(apikey)) {
			apikey = "cab55f3ce553c9922bfe2efa7f8c911b";
			secret = "e764543b1f82dda5c7e88710e9dbfaad";
		}

		System.out.println("forsigstrSub ===" + forsigstrSub);
		String sigstr = apikey + action + t + forsigstrSub;
		requestMap.put("sigstr", sigstr);

		String signature = getSignature(sigstr, secret);
		System.out.println("[sigstr] => " + sigstr);

		System.out.println("[signature] => " + signature);
		System.out.println("[data_list] => " + dataList);
		requestMap.put("signature", signature);

		String requestContent = "";
		for (String key : requestMap.keySet()) {
			String value = requestMap.get(key);
			requestContent += key + "=" + value + "&";
		}
//		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		try {
//			 SSLContext sslContext = new SSLContextBuilder()
//			            .loadTrustMaterial(null, (certificate, authType) -> true).build();
//			    httpClient = HttpClients.custom().setSSLContext(sslContext)
//			            .setSSLHostnameVerifier(new NoopHostnameVerifier())
//			            .build();
//			httpClient = HttpClients.custom().build();

			// 把普通參數和檔案上傳給對方server
			HttpPost httpPost = new HttpPost(PCHOME_URL + API_URL);

			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

			// 傳普通參數,相當於<input type="text" name="name" value=value/>
			for (String key : requestMap.keySet()) {
				String value = requestMap.get(key);
				// builder.addTextBody(key, value);
				StringBody sb = new StringBody(value, ContentType.TEXT_PLAIN);
				builder.addPart(key, sb);

			}
			JSONObject json = JSONObject.fromObject(requestMap);
			System.out.println("json ==>" + json);
			HttpEntity reqEntity = builder.build();
			httpPost.setEntity(reqEntity);
			// 發起請求,並返回請求回應
//			response = httpClient.execute(httpPost);
			response = buildSSLCloseableHttpClient.execute(httpPost);
			// 取得回應對象
			HttpEntity resEntity = response.getEntity();
			String result = EntityUtils.toString(resEntity);
			resultJsonObject = result.toString();
			// 銷毀
			EntityUtils.consume(resEntity);
			System.out.println("response = " + resultJsonObject);

		} catch (IOException e) {
			System.out.println("API異常:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("API異常 Exception :" + e.getMessage());
			e.printStackTrace();
		} finally {
			buildSSLCloseableHttpClient.close();

			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				System.out.println("IOException API異常:" + e.getMessage());

				e.printStackTrace();
			}
		}
		// System.out.println(resultJsonObject.toString());
		return resultJsonObject;
	}

	/**
	 * 生成簽名資料_HmacSHA1加密
	 * 
	 * @param data 待加密的數據
	 * @param key  加密使用的key
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	private static String getSignature(String data, String key) throws Exception {
		byte[] keyBytes = key.getBytes();
		SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA256);
		Mac mac = Mac.getInstance(HMAC_SHA256);
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
		String hexBytes = new String(new Hex().encode(rawHmac), "UTF-8");
		return hexBytes;
	}

	public static String setForsigstrSub(List<Object> dataList, String platformProdNo) {
		String forsigstrSub = "";
		String productNameM = MapUtils.getString(((Map<String, String>) dataList.get(0)), "PRODUCT_NAME_M");
		String productCodeM = MapUtils.getString(((Map<String, String>) dataList.get(0)), "PRODUCT_CODE_M");
		forsigstrSub = platformProdNo + productCodeM + productNameM;
		System.out.println("forsigstrSub ===" + forsigstrSub);
		return forsigstrSub;

	}

	public static SenApi getInstance() {
		if (util == null) {
			util = new SenApi();
		}
		return util;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public static String setForsigstrSub(String productNameM, String productCodeM, String productId) {
		String forsigstrSub = productId + productCodeM + productNameM;
		System.out.println("forsigstrSub ===" + forsigstrSub);
		return forsigstrSub;

	}

	public static String setForsigstrSub(String productNameM, String productCodeM) {
		String forsigstrSub = productCodeM + productNameM;
		System.out.println("forsigstrSub ===" + forsigstrSub);
		return forsigstrSub;

	}

//	public static void main(String[] args) throws Exception {
//		PchomeUtil util = PchomeUtil.getInstance();
//		String apikey = "cab55f3ce553c9922bfe2efa7f8c911b";
//		String secret = "e764543b1f82dda5c7e88710e9dbfaad";
//		String forsigstrSub = "1635993204813479";
//		Map<String ,String> dataMap = new HashMap<String ,String>();
//		dataMap.put("process_id", "1635993204813479");
//		
//		JSONArray jsonArray = new JSONArray();
//		jsonArray.put(dataMap);
//		String dataList = jsonArray.toString();
//		System.out.println("dataList === " + dataList);
//		util.setApikey(apikey);
//		util.setSecret(secret);
//		System.out.println(util.post("prodquery", dataList, forsigstrSub));
//	}

//	private CloseableHttpClient buildSSLCloseableHttpClient() throws Exception {
//		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
//			// 信任所有
//			@Override
//			public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//				return true;
//			}
//		}).build();
//		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,
//				new String[] { "TLSv1", "TLSv1.1", "TLSv1.2" }, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//		return HttpClients.custom().setSSLSocketFactory(sslsf).build();
//	}
	public CloseableHttpClient newClient()
			throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException {
		SSLContext sslContext = createIgnoreVerifySSL();
		System.out.println("當前版本"+sslContext.getProtocol());

		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", new SSLConnectionSocketFactory(sslContext)).build();
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		HttpClients.custom().setConnectionManager(connManager);
		return HttpClients.custom().setConnectionManager(connManager).build();

	}

	public SSLContext createIgnoreVerifySSL() throws IOException, KeyManagementException {
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("TLSv1.2");// 指定TLS版本

		} catch (NoSuchAlgorithmException e) {
		}

		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) throws CertificateException {
			}

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

		};
		try {
			sslContext.init(null, new TrustManager[] { trustManager }, null);
			SSLSocketFactory factory = (SSLSocketFactory) sslContext.getSocketFactory();
			SSLSocket socket = (SSLSocket) factory.createSocket();
			socket.setEnabledProtocols(new String[] {"TLSv1.2","TLSv1.1"});

			String[] protocols = socket.getSupportedProtocols();

			System.out.println("Supported Protocols: " + protocols.length);
			for (int i = 0; i < protocols.length; i++) {
				System.out.println(" " + protocols[i]);
			}

			protocols = socket.getEnabledProtocols();

			System.out.println("Enabled Protocols: " + protocols.length);
			for (int i = 0; i < protocols.length; i++) {
				System.out.println(" " + protocols[i]);
			}
		} catch (KeyManagementException e) {
		}
		return sslContext;
	}

	public static void main(String[] args) throws Exception {
		SenApi s = new SenApi();
		String a = "prodquery";
		String b = "[{\"process_id\":\"1637200560586220\"}]";
		String c = "1637200560586220";

		s.post(a, b, c);

	}

}
