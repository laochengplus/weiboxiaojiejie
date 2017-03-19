package dev.laocheng.weibocrawler;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import dev.laocheng.weibocrawler.crawler.ImageUrlCrawler;
import dev.laocheng.weibocrawler.crawler.OneWeiboCrawler;
import dev.laocheng.weibocrawler.data.WeiboStatusInfo;
import dev.laocheng.weibocrawler.filter.UserIdIncludeFilter;
import weibo4j.Timeline;
import weibo4j.model.FriendsTimelineIds;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;

public class XiaojiejieApp {

	public static void main(String[] args) {
		

		System.setProperty( "phantomjs.binary.path",
				new File("phantomjs.exe").getAbsolutePath());
		WebDriver driver = new PhantomJSDriver();
		
		try {
			System.out.println("                         ========");
			System.out.println("Author:==================LAOCHENG==================");
			System.out.println("                         ========");
			
			Thread.sleep(3000l);
			
			String access_token = WeiboTokenGetter.getToken();
			Timeline tm = new Timeline(access_token);
			FriendsTimelineIds status = tm.getFriendsTimelineIds();

			int total = (int) status.getTotalNumber();
			int page = total/100 + 1;
			Map<String, String> params = new HashMap<String, String>();
			params.put("access_token", access_token);
			params.put("count", "100");
			params.put("feature", "2");
			Collection<String> totalIds = new HashSet<String>(total);
			for(int i = 1; i <= page; i++) {
				Thread.sleep(1000l);
				params.put("page", String.valueOf(i));
				JSONObject object = tm.getFriendsTimelineIds(params);
				//totalIds.addAll();
				JSONArray array = object.getJSONArray("statuses");
				for(int j = 0; j<array.length(); j++) {
					totalIds.add(array.getString(j));
				}
			}
			
			System.out.println("total weibo count: " + totalIds.size());
			
			int realcount = 0;
			UserIdIncludeFilter filter = new UserIdIncludeFilter();
			ImageUrlCrawler crawler = new ImageUrlCrawler("D:\\xiaojiejie");
			
			for(String id : totalIds) {
				WeiboStatusInfo info = new OneWeiboCrawler(id, driver).getWeibo();
				if(info == null) {
					continue;
				}

				if(filter.accept(info)) {
					crawler.downloadImageFromOneWeibo(info);
					realcount ++;
				}
			}
			System.out.println("实际爬得微博条数:" + realcount);
		} catch (WeiboException | InterruptedException | JSONException e) {
			e.printStackTrace();
		} finally {
			driver.close();
		}
	}

}
