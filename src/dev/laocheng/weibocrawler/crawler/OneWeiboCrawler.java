package dev.laocheng.weibocrawler.crawler;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import dev.laocheng.weibocrawler.data.WeiboStatusInfo;

public class OneWeiboCrawler {

	private String mMId;
	private WebDriver mWebDriver;
	private WeiboStatusInfo mInfo;
	
	public OneWeiboCrawler(String mId, WebDriver driver) {
		mMId = mId;
		mWebDriver = driver;
		mInfo = getWeibo();
	}
	
	public WeiboStatusInfo getWeibo() {
		if(mInfo != null) {
			return mInfo;
		}
		
		String baseUrl = "http://m.weibo.cn/status/" + mMId;
		
		try {
			mWebDriver.get(baseUrl);
			String content = mWebDriver.getPageSource();
			System.out.println("获取微博,id为" + mMId);
			int begin = content.lastIndexOf("render_data = [{");
			int end = content.lastIndexOf("}]");
			String statusString = content.substring(begin + 15, end + 1);
			//System.out.println(statusString);
			
			WeiboStatusInfo info = new WeiboStatusInfo();
			ObjectMapper mapper = new ObjectMapper();
		
			ObjectNode total = mapper.readValue(statusString, ObjectNode.class);
			JsonNode status = total.get("status");
			if (status == null) {
				return null;
			}
			if (status.get("status_title") == null) {
				return null;
			}
			String title = status.get("status_title").asText();
			info.setMId(mMId);
			info.setTitle(title);
			System.out.println("微博文本内容为:" + title);
			
			String userId = status.get("user").get("id").asText();
			String userName = status.get("user").get("screen_name").asText();
			info.setUserId(userId);
			info.setUserName(userName);
			System.out.println("此微博作者:" + userName);
			ArrayNode picIds = (ArrayNode)status.get("pic_ids");
			for(JsonNode pic : picIds) {
				info.addPicId(pic.asText());
				System.out.println("微博图片id为:" + pic.asText());
			}
			
			return info;
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
