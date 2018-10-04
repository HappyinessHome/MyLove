package com.ma.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class BaiduyunUtil {
   private static final CloseableHttpClient client = HttpClients.createDefault();
    /**
     * 此类只能找一个页面只有唯一的className的link
     * @param searchName
     * @param className
     * @param url
     * @return
     */
    public static String getlink(String searchName, String className,String url) throws IOException {

        HttpGet get = methodGet(url);

        CloseableHttpResponse execute = client.execute(get);

        if(execute.getStatusLine().getStatusCode()==200){

            Document document = Jsoup.parse(EntityUtils.toString(execute.getEntity()));
            Element select = document.select("."+className).get(0);
            String pass = select.parent().html();

            String html = select.html();
            String href = select.attr("href");
            if(pass.contains("提取码")) {

                pass=pass.split("提取码：")[1];
                return href + "@@" + pass;
            }
           return  href;
        }
        return null;
    }
    public  static List<String> searchList(String searchName,String url,String className) throws IOException {
        List<String> list=new ArrayList<>();
        HttpGet httpGet = methodGet(url+searchName);
        CloseableHttpResponse execute = client.execute(httpGet);
        Document document = Jsoup.parse(EntityUtils.toString(execute.getEntity()));
        Elements elements = document.select("." + className);
        List<Element> elements1 = elements.subList(0, 5);
        for (Element element : elements1) {
            Element a = element.getElementsByTag("a").get(0);
            String avalue =a.html();
            String href = a.attr("href");
            if(avalue.contains(searchName)){
                list.add(href+"@@"+avalue);
            }
        }
        if(list.size()>0)
            return  list;
        return null;
    }
    public static String  getLinkToYun(List<String> hrefs) throws IOException {
        String url="http://www.pansoso.com"+hrefs.get(0).split("@@")[0];
        HttpGet httpGet = methodGet(url);
        CloseableHttpResponse execute = client.execute(httpGet);
        Document parse = Jsoup.parse(EntityUtils.toString(execute.getEntity()));
        Element element = parse.select("div[class=down]").get(0);
        Element a = element.select("a").get(1);
        String href = a.attr("href");

        return href;
    }
    public static HttpGet methodGet(String url){
        HttpGet get=new HttpGet(url);
        get.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
        return  get;
    }

}
