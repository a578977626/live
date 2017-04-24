package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Book;
import com.example.model.Host;
import com.example.model.Top;
import com.example.rep.RepTestRepository;
import com.example.util.CommonTools;

@Controller
@RequestMapping("/")
public class ReadingListController {

	@Autowired
	private RepTestRepository repTestRespostoty;
	
	@ResponseBody
	@RequestMapping(value = "/{reader}", method = RequestMethod.GET)
	public String readersBooks(@PathVariable("reader") String reader) {
	        String zqurl = "http://www.zhanqi.tv/lives"; 
	        String dyurl = "https://www.douyu.com/directory/all";
//	        String xmurl = "http://www.panda.tv/api_room_v2?roomid=135069";
	        String xmurl = "http://www.panda.tv/all";  
//	        this.getDouYuData(dyurl);
//	        this.getZhanQiData(zqurl);
	        List<Host> xmList = CommonTools.getXiongMaoListHostData(xmurl);
	        
		return "return LIVE!! IS BEAtiful!!";
	}

	@RequestMapping(value="/{reader}", method=RequestMethod.POST)
	public String addToReadingList(@PathVariable("reader") String reader, Book book) {
		book.setReader(reader);
		return "redirect:/{reader}";
		}
	
	
	
	public void getZhanQiData(String url){
        Document doc = this.getDocByUrl(url);
		
        Elements links = doc.select("li[data-room-id]");  
  
        List<Top> topList = new ArrayList<Top>();
        
        for (Element link : links) {
        	Elements ll = link.getElementsByClass("dv");
        	String liveNum = ll.get(1).text();
        	String name  = ll.get(3).text();
        	String herf = link.getElementsByClass("js-jump-link").attr("abs:href");
        	
        	Top top = new Top("zhanqi");
        	top.setAddress(herf);
        	top.setHostname(name);
        	top.setLivenum(this.caculateNum(liveNum));
        	
        	topList.add(top);
        } 
        //sort method.
        Top maxTop = this.sortTop(topList, "zhanqi");
        repTestRespostoty.save(maxTop);
	}
	
	public void getDouYuData(String url){
		Document doc = this.getDocByUrl(url); 
		doc.setBaseUri("https://www.douyu.com");
		
        Elements links = doc.select("a[data-rid]");  
  
        List<Top> topList = new ArrayList<Top>();
        
        for (Element link : links) {
        	Elements ss = link.getElementsByClass("dy-num");
        	String liveNum = "0";
        	if(ss.size()>0){
        		 liveNum = ss.get(0).text();
        	}
        	String herf = link.attr("abs:href");
        	String name = link.getElementsByClass("dy-name").get(0).text();
        	
        	Top top = new Top("douyu");
        	top.setAddress(herf);
        	top.setHostname(name);
        	top.setLivenum(this.caculateNum(liveNum));
        	
        	topList.add(top);
        } 
        //sort method.
        Top maxTop = this.sortTop(topList, "douyu");
        repTestRespostoty.save(maxTop);
	
	}
	/**
	 * 计算结果
	 * @param liveNum
	 * @return
	 */
	public Float caculateNum(String liveNum){
		Float last = 0f;
    	if(liveNum.contains("万")){
    		last = Float.valueOf(liveNum.substring(0, liveNum.length()-1))*10000;
    	}else{
    		last = Float.valueOf(liveNum);
    	}
		return  last;
	}
	
	
	public Top sortTop( List<Top> topList,String tvName){
		Float max = 1.0f;
        Top maxTop = new Top(tvName);
        for(Top top:topList){
        	if(top.getLivenum()>max){
        		max = top.getLivenum();
        		maxTop.setHostname(top.getHostname());
        		maxTop.setAddress(top.getAddress());
        		maxTop.setLivenum(max);
        	}
        };
        return maxTop;
	}
	
	public Document getDocByUrl (String url){
		    Document doc = null;
			try {doc = Jsoup.connect(url).get();} catch (IOException e) {e.printStackTrace();} 
			return doc;
	}
}
