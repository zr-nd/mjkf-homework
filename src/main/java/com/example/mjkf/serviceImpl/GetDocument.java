package com.example.mjkf.serviceImpl;
import com.example.mjkf.po.Paper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/*
 java扩充数据源
 2020.7.24
 */
@Service
public class GetDocument {

    public Paper ieee_info(Map<String, Object> map, String pdfLink){   //获取论文的信息
        //pdf 是 persistentLink
        //metrics
        Paper paper = new Paper();
        paper.setPdfLink(pdfLink);
        if (map.containsKey("title")){
            String realTitle;
            if((map.get("title").getClass().getName()).equals("net.sf.json.JSONArray")) {
                JSONArray title = (JSONArray) map.get("title");
                realTitle = (String) title.get(0);
            }else{
                realTitle = (String)map.get("title");
            }
            paper.setTitle(realTitle);
        }else{
            paper.setTitle("");
        }

        if(map.containsKey("authors")){
            JSONArray authors=(JSONArray)map.get("authors");
            paper.setAuthors(authors.toString());
            ArrayList<String> arr=new ArrayList<String>();  //作者
            ArrayList<String> arr1=new ArrayList<String>();   //机构
            for(Object author:authors){
                JSONObject aus=(JSONObject)author;
                arr.add(aus.get("name").toString());
                JSONArray affiliation=(JSONArray) aus.get("affiliation");
                if(affiliation== null || affiliation.get(0).toString().equals("")){
                    arr1.add("NA");
                    paper.setAffiliations("NA");
                }else {
                    arr1.add(affiliation.get(0).toString());
                }
            }
            // res.put("authors",StringUtils.join(arr,"; "));
            //res.put("affiliations",StringUtils.join(arr1,"; "));
            paper.setAuthors(StringUtils.join(arr,"; "));   //作者
            paper.setAffiliations(StringUtils.join(arr1,"; "));   //机构
        }else{
            paper.setAuthors("");
            paper.setAffiliations("");
        }

        if(map.containsKey("publicationTitle")){
            String realPt;
            if((map.get("publicationTitle").getClass().getName()).equals("net.sf.json.JSONArray")) {
                JSONArray pt = (JSONArray) map.get("publicationTitle");
                realPt = (String) pt.get(0);
            }else{
                realPt = (String)map.get("publicationTitle");
            }
            paper.setPublicTitle(realPt);
        }else{
            paper.setPublicTitle("");
        }

        if(map.containsKey("publicationYear")){
            String realYear;
            if((map.get("publicationYear").getClass().getName()).equals("net.sf.json.JSONArray")) {
                JSONArray year = (JSONArray) map.get("publicationYear");
                realYear = (String) year.get(0);
            }else {
                realYear = (String) map.get("publicationYear");
            }
            paper.setYear(Integer.parseInt(realYear));
        }else{
            paper.setYear(0);
        }

        if(map.containsKey("startPage")){
            String realStart;
            if((map.get("startPage").getClass().getName()).equals("net.sf.json.JSONArray")) {
                JSONArray start = (JSONArray) map.get("startPage");
                realStart = (String) start.get(0);
            }else{
                realStart = (String) map.get("startPage");
            }
            if(Character.isDigit(realStart.charAt(0))) {
                paper.setStartPage(Integer.parseInt(realStart));
            }else{
                paper.setStartPage(0);
            }
        }else{
            paper.setStartPage(0);
        }

        if(map.containsKey("endPage")){
            String realEnd;
            if((map.get("endPage").getClass().getName()).equals("net.sf.json.JSONArray")) {
                JSONArray start = (JSONArray) map.get("endPage");
                realEnd = (String) start.get(0);
            }else{
                realEnd = (String) map.get("startPage");
            }
            if(Character.isDigit(realEnd.charAt(0))) {
                paper.setEndPage(Integer.parseInt(realEnd));
            }else{
                paper.setEndPage(0);
            }
        }else{
            paper.setEndPage(0);
        }

        if(map.containsKey("abstract")){
            String realAbs;
            if((map.get("abstract").getClass().getName()).equals("net.sf.json.JSONArray")) {
                JSONArray abs = (JSONArray) map.get("abstract");
                realAbs = (String) abs.get(0);
            }else {
                realAbs = (String) map.get("abstract");
            }
            paper.setPaperAbstract(realAbs);
        }else{
            paper.setPaperAbstract("");
        }

        if(map.containsKey("keywords")){
            JSONArray keys=(JSONArray)map.get("keywords");
            ArrayList<String> arr=new ArrayList<String>();
            for(Object key:keys){
                JSONObject k=(JSONObject)key;
                if(k.containsKey("type")){
                    if((k.get("type").toString()).equals("Author Keywords ")){
                        JSONArray kwds=(JSONArray)k.get("kwd");
                        for(Object kwd:kwds){
                            arr.add(kwd.toString());
                        }
                    }
                }
            }
            paper.setAuthorKeyWord(arr.toString().substring(1,arr.toString().length()-1));
        }else{
            paper.setPaperAbstract("");
        }

        if(map.containsKey("publisher")){
            String realPublisher;
            if((map.get("publisher").getClass().getName()).equals("net.sf.json.JSONArray")) {
                JSONArray publisher = (JSONArray) map.get("publisher");
                realPublisher = (String) publisher.get(0);
            }else {
                realPublisher = (String) map.get("publisher");
            }
            paper.setPublisher(realPublisher);
        }else{
            paper.setPublisher("");
        }

        if(map.containsKey("subType")){
            String realDocID;
            if((map.get("subType").getClass().getName()).equals("net.sf.json.JSONArray")) {
                JSONArray docID = (JSONArray) map.get("subType");
                realDocID = (String) docID.get(0);
            }else {
                realDocID = (String) map.get("subType");
            }
            paper.setDocID(realDocID);
        }else{
            paper.setDocID("");
        }
        if(map.containsKey("metrics")){
            int referenceCount;
            if((map.get("metrics").getClass().getName()).equals("net.sf.json.JSONObject")){
                JSONObject paperCount = (JSONObject) map.get("metrics");
                referenceCount = Integer.parseInt(paperCount.getString("citationCountPaper"));
                paper.setReferenceCount(referenceCount);
            }
        }else{
            paper.setReferenceCount(0);
        }
        paper.setPublisher("IEEE");
        return paper;
    }


    /*/
        pageNum :  当前爬取的页号
     */
    public ArrayList<Paper> Begin(int pageNum){

        String urlStart = "https://ieeexplore.ieee.org/search/searchresult.jsp?action=search&newsearch=true&matchBoolean=true&queryText=(%22Publication%20Title%22:IEEE%2FACM)&highlight=true&returnType=SEARCH&matchPubs=true&pageNumber=";
        String urlEnd = "&ranges=2015_2020_Year&returnFacets=ALL";
        String urlStartEnd = urlStart + (pageNum) + urlEnd;
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        System.setProperty("webdriver.chrome.bin","/etc/alternatives/google-chrome");
        //System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");  //屏蔽日志
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");//无界面参数
        options.addArguments("no-sandbox");//禁用沙盒 #解决DevToolsActivePort文件不存在的报错
        options.addArguments("--disable-dev-shm-usage");//克服有限的资源问题  【但是用于Linux系统】
        options.addArguments("--disable-gpu");
        options.addArguments("start-maximized");
        ChromeDriver chromeDriver = new ChromeDriver(options);
        //chromeDriver.manage().window().maximize();

        chromeDriver.get(urlStartEnd);
        List<WebElement> list2 = chromeDriver.findElementsByClassName("icon-pdf");
        System.out.println(list2.size());  //装有icon-pdf
        ArrayList<String> list3 = new ArrayList<>();  //list3已经经过去重
        for(int i=0;i< list2.size();i++){
            //有图标
            String linkNum = list2.get(i).getAttribute("href");
            linkNum = linkNum.substring(linkNum.length()-7);
            list3.add(linkNum);
        }
        System.out.println("List3 size : " + list3.size());

        String realUrl;
        ArrayList<Paper> result = new ArrayList<>();
        for(int i=0;i<list3.size();i++){
            realUrl = "https://ieeexplore.ieee.org/document/" + list3.get(i);
            Paper paper = getPaperInfo(realUrl);
            if(paper.getAuthors()!=""&&paper.getAuthors()!=null){
                result.add(paper);
            }
        }
        System.out.println("结果集大小：" + result.size());  //result里面包含抓到的Paper
        chromeDriver.quit();//关闭浏览器且结束进程
        return result;
    }



    public Paper getPaperInfo(String url){
        Paper paper = new Paper();
        Random r = new Random();
        String[] ua = {"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:46.0) Gecko/20100101 Firefox/46.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.87 Safari/537.36 OPR/37.0.2178.32",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.57.2 (KHTML, like Gecko) Version/5.1.7 Safari/534.57.2",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2486.0 Safari/537.36 Edge/13.10586",
                "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko",
                "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0)",
                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)",
                "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0)",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.3 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.277.400 QQBrowser/9.4.7658.400",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 UBrowser/5.6.12150.8 Safari/537.36",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.122 Safari/537.36 SE 2.X MetaSr 1.0",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.116 Safari/537.36 TheWorld 7",
                "Mozilla/5.0 (Windows NT 6.1; W…) Gecko/20100101 Firefox/60.0"};
        int i =r.nextInt(14);
        Connection conn = Jsoup.connect(url).timeout(50000);
        conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        conn.header("Accept-Encoding", "gzip, deflate, sdch");
        conn.header("Accept-Language", "zh-CN,zh;q=0.8");
        conn.header("User-Agent", ua[i]);
        try {
            Document doc = conn.maxBodySize(Integer.MAX_VALUE).get();
            String pattern = "metadata=\\{.*};";
            Pattern pt = Pattern.compile(pattern);
            Matcher ma = pt.matcher(doc.toString());
            String temp="";
            while (ma.find()){
                temp = ma.group(0);
                temp = temp.substring(9,temp.length()-1); //去掉首尾多余的不符合格式的字符(如metadata)
            }
            /*
                String 先转为JSONObject 再 转为map
            */
            if(temp.length()>1) {
                JSONObject jsonObject = JSONObject.fromObject(temp);
                Map<String, Object> map2 = new HashMap<String, Object>();
                Iterator<String> iterator = jsonObject.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    Object value = jsonObject.get(key);
                    //System.out.println(key+":"+value+",,,"+value.getClass().getName());
                    map2.put(key, value);
                }
                paper = ieee_info(map2, url);
            }
            return paper;
        }catch(IOException e){
            e.printStackTrace();
        }

        return paper;
    }
}
