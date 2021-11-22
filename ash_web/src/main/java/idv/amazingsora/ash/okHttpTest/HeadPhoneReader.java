package idv.amazingsora.ash.okHttpTest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import idv.amazingsora.ash.until.Okhttp3Until;
import okhttp3.FormBody;
import okhttp3.Request;

public class HeadPhoneReader {
	final static String M = "M.1530392323.A.695.html";
	final static String URL = "https://www.ptt.cc/bbs/Headphone/";
	final static String searchAction = "search?q=";

	public void doing() throws IOException, ParseException {
		Okhttp3Until until = new Okhttp3Until(URL);
		runAdultCheck(URL + M, until);
		String body = until.getBody(URL + M);
		String [] keys = {"sony"};
		parseArticle(body,keys,"");
	}

	/* 進行年齡確認 */
	private void runAdultCheck(String url, Okhttp3Until until) throws IOException {
		FormBody formBody = new FormBody.Builder().add("from", url).add("yes", "yes").build();

		Request request = new Request.Builder().url(URL + "/ask/over18").post(formBody).build();

		until.getOkHttpClient().newCall(request).execute();
	}

	/* 解析看板文章列表 */
	private void parseArticle(String body,String keyWord[],String StartDate) throws ParseException {
//		System.out.println("body ===" + body);
		List<Map<String, String>> result = new ArrayList<>();
		Document doc = Jsoup.parse(body);
		Elements articleList = doc.select(".push");
		SimpleDateFormat sdf = new SimpleDateFormat("MM/ddHH:mm");

		for (Element element : articleList) {
			boolean flag =  true;
			String pushUserid = element.select(".push-userid").html();
			String pushContent = element.select(".push-content").html();
			String pushIpdatetime = element.select(".push-ipdatetime").html();

			if(org.apache.commons.lang3.StringUtils.isNoneBlank(StartDate)) {
				Date date = sdf.parse(pushIpdatetime.replace(" ", ""));
				Date startDate = sdf.parse(StartDate.replace(" ", ""));
				if(!(date.getTime()>startDate.getTime())) {
					continue;
				}
			}
			
			
			List <String>keys = Arrays.asList(keyWord);
			
			
			for(String key : keys) {
				if(!(pushContent.toUpperCase().indexOf(key.toUpperCase())>-1)) {
					flag = false;
					break;
				}
			}
			
			if(!flag) {
				continue;
			}
			System.out.println(pushUserid+pushContent+"	"+pushIpdatetime);

		}
	}

	public static void main(String[] args) throws IOException, ParseException {
		HeadPhoneReader h = new HeadPhoneReader();
		h.doing();
	}
}
