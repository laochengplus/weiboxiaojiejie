# weiboxiaojiejie
科学的 JAVA 微博爬虫，永不封号，不设置cookie，高频爬取关注的人发送的原创图片
(An efficient sina Weibo crawler using JAVA + selenium + PhantomJs + Weibo4j, save your time by download all selfies of your timeline, you won't miss it!!)

#项目环境:

  java8 + selenium java (http://www.seleniumhq.org/download/   只需要下载java的那个zip包) + weibo4j-oauth2.jar(https://github.com/sunxiaowei2014/weibo4j-oauth2-beta3.1.1/) 
 
  并配置以上两个第三方库所依赖的其他第三方库


#如何使用:


  1.将你的微博账号注册为新浪微博开发者，并新建一个应用获得app key 与 app secret. 流程参见: http://blog.csdn.net/xyw_blog/article/details/8907918
 
 
  2.将你希望抓取的微博用户的微博id每人为一行记录在xjjid.txt中，且你的账号需要关注这些微博用户
  
  
  3.如果你是在java ide中，直接运行XiaojiejieApp(如果你是打成jar包使用，则cmd中使用  java -jar xxxx.jar)命令 (注意phantomjs.exe 与 xjjid.txt都需要在项目的根目录下！)
  
  
  4.开始运行第一步是唤起浏览器将code复制输入到程序中,与 http://blog.csdn.net/xyw_blog/article/details/8907918类似
  
  
  5.然后源源不断的图片就会被爬取下来了~~~
