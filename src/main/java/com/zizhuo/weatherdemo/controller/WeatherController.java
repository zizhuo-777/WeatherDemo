package com.zizhuo.weatherdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zizhuo.weatherdemo.utils.HttpConn;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("weather")
public class WeatherController {
    //爬取天气的网址
    String url = "https://tianqi.2345.com/";

    @RequestMapping("echart")
    public String echart() {
        return "echart.html";
    }

    @RequestMapping("/weather")
    public String weather(Model model) {
        System.out.println("111111");
        try {
            //爬取整个网页的html源码
            String html = HttpConn.get(url);

            //当前的HTML封装为一个文档对象
            Document document = Jsoup.parse(html);

            //温度区间
            Elements elements = document.getElementsByClass("banner-right-con-list-temp");
            List<String> lowlist = new ArrayList<>();  //最低气温
            List<String> highlist = new ArrayList<>();  //最高气温
            //求一周的最低最高气温
            int maxLow=50,keyLow=0;
            int i=0;
            for(Element element : elements){
                String temp = element.html();
                String low = temp.split("~")[0];  //每天的最低气温
                String high = temp.split("~")[1].replace("°","");  //每天的最高气温
                if(maxLow>Integer.parseInt(low)){
                    maxLow=Integer.parseInt(low);
                    keyLow=i;
                }
                i++;
                //存入集合中
                lowlist.add(low);
                highlist.add(high);
                elements = document.getElementsByClass("banner-right-con-list-time");
                List<String> timeList = new ArrayList<>();
                for(Element e:elements){
                    timeList.add(e.html());
                }
                elements = document.getElementsByClass("banner-right-con-list-status");
                List<String> weatherList = new ArrayList<>();
                for(Element e:elements){
                    weatherList.add(e.html());
                }
                model.addAttribute("maxLow",maxLow);
                model.addAttribute("keyLow",keyLow);
                ObjectMapper mapper = new ObjectMapper();

                model.addAttribute("lowList",mapper.writeValueAsString(lowlist));
                model.addAttribute("highList",mapper.writeValueAsString(highlist));
                model.addAttribute("weatherList",mapper.writeValueAsString(weatherList));
                model.addAttribute("timeList",mapper.writeValueAsString(timeList));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "echart.html";
    }
}
