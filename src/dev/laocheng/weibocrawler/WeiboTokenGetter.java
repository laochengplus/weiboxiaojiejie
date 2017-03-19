package dev.laocheng.weibocrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;

public class WeiboTokenGetter {

	public static void main(String[] args) {

		getToken();
	}
 
	public static String getToken() {
		try {
			Oauth oauth = new Oauth();
			BareBonesBrowserLaunch.openURL(oauth.authorize("code"));
			System.out.println(oauth.authorize("code"));
			System.out.print("Hit enter when it's done.[Enter]:");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String code = br.readLine();
			System.out.println("code: " + code);
			try{
				AccessToken token = oauth.getAccessTokenByCode(code);
				System.out.println("please remember this token:" + token.getAccessToken());
				return token.getAccessToken();
			} catch (WeiboException e) {
				if(401 == e.getStatusCode()){
					System.out.println("Unable to get the access token.");
					System.out.println("请使用您拥有对应应用的开发者账号进行授权.");
				}else{
					e.printStackTrace();
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
