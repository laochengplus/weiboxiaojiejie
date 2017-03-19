package dev.laocheng.weibocrawler.filter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import dev.laocheng.weibocrawler.data.WeiboStatusInfo;

public class UserIdIncludeFilter implements BaseFilter{

	private static String CONFIG_FILE_PATH = "xjjid.txt";
	
	private Collection<String> mUserIds;
	
	public UserIdIncludeFilter() {
		mUserIds = new HashSet<String>();
		File file = new File(CONFIG_FILE_PATH);
		if(!file.exists()) {
			mUserIds = new HashSet<String>();
		}
		try {
			mUserIds = FileUtils.readLines(file);

			System.out.println("load target id count:" + mUserIds.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mUserIds = new ArrayList<String>(0);
		}
	}
	
	@Override
	public boolean accept(WeiboStatusInfo info) {
		return mUserIds.contains(info.getUserId());
	}
}
