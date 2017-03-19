package dev.laocheng.weibocrawler.filter;

import dev.laocheng.weibocrawler.data.WeiboStatusInfo;

public interface BaseFilter {

	public boolean accept(WeiboStatusInfo info);
}
