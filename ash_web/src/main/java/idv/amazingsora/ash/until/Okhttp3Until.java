package idv.amazingsora.ash.until;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Okhttp3Until {
	private  OkHttpClient okHttpClient;
	private Map<String, List<Cookie>> cookieStore;
	private CookieJar cookieJar;
	public Okhttp3Until(String url) throws IOException {
		/* 初始化 */
		cookieStore = new HashMap<>();
		cookieJar = new CookieJar() {
			/* 保存每次伺服器端回傳的 Cookie */
			@Override
			public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
				List<Cookie> cookies = cookieStore.getOrDefault(httpUrl.host(), new ArrayList<>());
				cookies.addAll(list);
				cookieStore.put(httpUrl.host(), cookies);
			}

			/* 每次發送帶上儲存的 Cookie */
			@Override
			public List<Cookie> loadForRequest(HttpUrl httpUrl) {
				return cookieStore.getOrDefault(httpUrl.host(), new ArrayList<>());
			}
		};
		okHttpClient = new OkHttpClient.Builder().cookieJar(cookieJar).build();

		/* 獲得網站的初始 Cookie */
		Request request = new Request.Builder().get().url(url).build();
		okHttpClient.newCall(request).execute();
	}
	
	public String getBody(String sendUrl) throws IOException {
		Request request = new Request.Builder()
		        .url(sendUrl)
		        .get()
		        .build();

		    Response response = okHttpClient.newCall(request).execute();
		    String body = response.body().string();
		return body;
		
	}
	public OkHttpClient getOkHttpClient() {
		return okHttpClient;
	}
	public void setOkHttpClient(OkHttpClient okHttpClient) {
		this.okHttpClient = okHttpClient;
	}
	public Map<String, List<Cookie>> getCookieStore() {
		return cookieStore;
	}
	public void setCookieStore(Map<String, List<Cookie>> cookieStore) {
		this.cookieStore = cookieStore;
	}
	public CookieJar getCookieJar() {
		return cookieJar;
	}
	public void setCookieJar(CookieJar cookieJar) {
		this.cookieJar = cookieJar;
	}

}
