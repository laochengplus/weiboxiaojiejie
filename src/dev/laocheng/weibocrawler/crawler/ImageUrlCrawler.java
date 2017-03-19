package dev.laocheng.weibocrawler.crawler;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import dev.laocheng.weibocrawler.data.WeiboStatusInfo;

public class ImageUrlCrawler {

	private String mDownloadPath;
	
	public ImageUrlCrawler(String directoryPath) {
		mDownloadPath = directoryPath;
		File file = new File(mDownloadPath);
		if (!file.exists()) {
			file.mkdir();
		} else if(!file.isDirectory()){
			FileUtils.deleteQuietly(file);
			file.mkdir();
		}
	}

	public void downloadImageFromOneWeibo(WeiboStatusInfo info) {
		String userPath = mDownloadPath + "/" + info.getUserName();
		File userFile = new File(userPath);
		if(!userFile.exists()) {
			userFile.mkdir();
		}
		if(info != null) {
			for(String id : info.getPicIds()) {
				String url = "http://wx3.sinaimg.cn/large/" + id + ".jpg";
				String filePath = userPath + "/" + id + ".jpg";
				File image = new File(filePath);
				if(!image.exists()) {
					try {
						FileUtils.copyURLToFile(new URL(url), image,
								2000, 20000);
						System.out.println("≥…π¶œ¬‘ÿÕº∆¨: " + filePath);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
