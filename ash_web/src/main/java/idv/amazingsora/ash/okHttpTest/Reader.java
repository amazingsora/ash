package idv.amazingsora.ash.okHttpTest;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Reader {
	private static OkHttpClient okHttpClient;
	private Map<String, List<Cookie>> cookieStore;
	private CookieJar cookieJar;
	private final static String URL = "https://www.ptt.cc";

	public Reader() throws IOException {
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
		Request request = new Request.Builder().get().url(URL).build();
		okHttpClient.newCall(request).execute();
	}
	/* 進行年齡確認 */
	private void runAdultCheck(String url) throws IOException {
	    FormBody formBody = new FormBody.Builder()
	        .add("from", url)
	        .add("yes", "yes")
	        .build();

	    Request request = new Request.Builder()
	        .url(URL + "/ask/over18")
	        .post(formBody)
	        .build();

	    okHttpClient.newCall(request).execute();
	}
	/* 解析看板文章列表 */
	private void parseArticle(String body) {
		System.out.println("body ==="+body);
	    List<Map<String, String>> result = new ArrayList<>();
	    Document doc = Jsoup.parse(body);
	    Elements articleList = doc.select(".r-ent");
	    Elements e=  doc.getElementsByClass("btn-group-paging");
	    System.out.println("e ===>"+e);
	    Element ee =e.get(0);
	    String href = ee.getElementsByClass("btn").get(1).attr("href");
	    System.out.println("href ==="+href);
	    String[]eee = href.split("./");
	    System.out.println("eee ==="+eee[2]);
	    String page = eee[2].replace("index","").replace(".html","");
	    System.out.println("page ==="+page);

	    
	    for (Element element: articleList) {
	        String url = element.select(".title a").attr("href");
	        String title = element.select(".title a").text();
	        String author = element.select(".meta .author").text();
	        String date = element.select(".meta .date").text();

	        
	        
	        result.add(new HashMap<String, String>(){/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

			{
	            put("url", url);
	            put("title", title);
	            put("author", author);
	            put("date", date);
	        }});
	    }

	    System.out.println("result ==="+result);
	}
	public static void main(String[] args) throws IOException {
		Reader r = new Reader();
		String board = "Gamesale";
		String sendUrl = URL +"//bbs//"+ board;
		r.runAdultCheck(sendUrl);
		System.out.println("sendUrl ==="+sendUrl);
		/* 抓取目標頁面 */
	    Request request = new Request.Builder()
	        .url(sendUrl)
	        .get()
	        .build();

	    Response response = okHttpClient.newCall(request).execute();
	    String body = response.body().string();
	    r.parseArticle(body);
	}
}
