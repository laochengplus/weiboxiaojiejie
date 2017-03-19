package dev.laocheng.weibocrawler.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class WeiboStatusInfo {

	private String mUserId;
	private String mMId;
	private String mUserName;
	private String mTitle;
	private Collection<String> mPicIds;
	
	public WeiboStatusInfo() {
		mPicIds = new HashSet<String>();
	}
	
	public String getUserId() {
		return mUserId;
	}
	public void setUserId(String mUserId) {
		this.mUserId = mUserId;
	}
	public String getMId() {
		return mMId;
	}
	public void setMId(String mMId) {
		this.mMId = mMId;
	}
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	public Collection<String> getPicIds() {
		return mPicIds;
	}
	public void setPicIds(List<String> mPicIds) {
		this.mPicIds = mPicIds;
	}
	
	public void addPicId(String id) {
		mPicIds.add(id);
	}

	public String getUserName() {
		return mUserName;
	}

	public void setUserName(String mUserName) {
		this.mUserName = mUserName;
	}
}
