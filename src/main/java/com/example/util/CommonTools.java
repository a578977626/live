package com.example.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.job.LiveJob;
import com.example.model.Host;
import com.example.model.Top;

public class CommonTools {
	/**
	 * 获取top1
	 */
	public static Top getDouYuData(String url){
		List<Top> topList = getDouYuListData(url);
        Top maxTop = sortTop(topList, "douyu");
        return maxTop;
	}
	/**
	 * 获取斗鱼主页所有主播的基本信息
	 */
	public static List<Top> getDouYuListData(String url){
		Document doc = getDocByUrl(url); 
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
        	top.setLivenum(caculateNum(liveNum));
        	
        	topList.add(top);
        } 
        return topList;
	}
	
	/**
	 * 获取斗鱼主页所有主播的基本信息
	 */
	public static List<Host> getDouYuListHostData(String url){
		Document doc = getDocByUrl(url); 
		doc.setBaseUri("https://www.douyu.com");
		
        Elements links = doc.select("a[data-rid]");  
  
        List<Host> hostList = new ArrayList<Host>();
        /* link
         * <a class="play-list-link" data-rid="627180" data-tid="222" data-sid="301" data-rpos="0" data-sub_rt="0" href="/daxuekuaipao" title="大学快跑" target="_blank"> <span class="imgbox"> <b></b> <i class="black"></i> <img data-original="https://rpic.douyucdn.cn/a1702/20/23/627180_170220235859.jpg" src="https://shark.douyucdn.cn/app/douyu/res/page/list-item-def-thumb.gif" width="283" height="163"> </span> 
 				<div class="mes"> 
  					<div class="mes-tit"> 
   						<h3 class="ellipsis"> 大学快跑 </h3> 
   						<span class="tag ellipsis">校园</span> 
  					</div> 
  				<p> <span class="dy-name ellipsis fl">大学快跑</span> </p> 
 				</div> 
 		   </a>
         */
        for (Element link : links) {
        	Elements ss = link.getElementsByClass("dy-num");
        	String liveNum = "0";
        	if(ss.size()>0){
        		 liveNum = ss.get(0).text();
        	}
        	String herf = link.attr("abs:href");
        	String name = link.getElementsByClass("dy-name").get(0).text();
        	String roomId = link.attr("data-rid");
        	String headPortrait = getDouYuHPByUrl(herf);
        	
        	Host host = new Host("douyu");
        	host.setAddress(herf);
        	host.setHostName(name);
        	host.setHeadPortrait(headPortrait);
        	host.setRoomId(roomId);
//        	host.setLivenum(caculateNum(liveNum));
        	
        	hostList.add(host);
        } 
        return hostList;
	}
	
	
	public static List<Host> getXiongMaoListHostData(String url){
		Document doc = getDocByUrl(url); 
		doc.setBaseUri("http://www.panda.tv");
		
        Elements links = doc.select("a[data-id]");  
  
        List<Host> hostList = new ArrayList<Host>();
        /* link
        <a href="/6666" class="video-list-item-wrap" data-id="6666"> 
 			<div class="video-cover"> 
  			<img class="video-img video-img-lazy" data-original="http://i9.pdim.gs/90/08d5513cfa976089925e1e87df3d3aba/w338/h190.jpg" alt="这个夜晚属于无限火力"> 
  				<div class="video-overlay"></div> 
  				<div class="video-play"></div> 
 			</div> 
 			<div class="video-title" title="这个夜晚属于无限火力">
  				这个夜晚属于无限火力
 			</div> 
 			<div class="video-info"> 
  			<span class="video-nickname">即将拥有人鱼线的PDD</span> 
  			<span class="video-number">3824410</span> 
  			<span class="video-cate">英雄联盟</span> 
 			</div> 
 			<div class="video-progress-bar"></div> </a>
         */
        LiveJob.ci++;
        int subCI = 0;
        for (Element link : links) {
        	subCI++;
        	String herf = link.attr("abs:href");
        	String name = link.getElementsByClass("video-nickname").get(0).text();
        	String roomId = link.attr("data-id");
        	long begin = System.currentTimeMillis(); // 这段代码放在程序执行前
        	String headPortrait = getXiongMaoHPByUrl(herf);
        	//tiem calc
        	long end = System.currentTimeMillis() - begin; // 这段代码放在程序执行后
        	System.out.println("这是第"+Integer.toString(LiveJob.ci)+"次执行Job--total:"+links.size()+"/"+Integer.toString(subCI)+"--耗时：" + end + "毫秒");
        	//time calc
        	Host host = new Host("xiongmao");
        	host.setAddress(herf);
        	host.setHostName(name);
        	host.setHeadPortrait(headPortrait);
        	host.setRoomId(roomId);
        	
        	//用phnomijs的还是读一个保存一个，因为这个东西不是很稳定而且慢n
        	hostList.add(host);
        } 
        return hostList;
	
	}
	/**
	 * 传入房间链接获取斗鱼主播头像
	 * @param url
	 * @return
	 */
	public static String getDouYuHPByUrl(String url) {
		Document doc = getDocByUrl(url);
		Elements ll =null;
		try {
			 ll = doc.getElementsByClass("anchor-pic fl");
		} catch (Exception e) {
			ll = new Elements();
			System.out.println("nabudaotouxiang");
		}
		/*
		  <div class="anchor-pic fl"> 
 				<img src="https://apic.douyucdn.cn/upload/avanew/face/201702/14/18/3d676d572a428d5f699a55b7d2d2b48d_middle.jpg?rltime"> 
			</div>
		 */
		if(ll.size()>0){
			String hd = ll.get(0).getElementsByTag("img").attr("src");
			return hd;
		}
		return null;
	}
	
	/**
	 * 熊猫tv的主播详情页不是静态的html，所以用jsoup不能直接使用。
	 * 用PhantomJs 呵呵哈哈哈
	 * @param url
	 * @return
	 */
	public static String getXiongMaoHPByUrl(String url) {
		Document doc = PhantomJsTools.getSouceCodeByPhantomJs(url);
		Elements ll =null;
		try {
			ll = doc.select(".room-head-info-cover"); 
		} catch (Exception e) {
			ll = new Elements();
			System.out.println("没有头像");
		}
		if(ll.size()>0){
			String hd = ll.get(0).getElementsByTag("img").attr("src");
			System.out.println("the headrait path"+hd);
			return hd;
		}
		System.out.println("the headrait path is  null");
		return null;
	}
	
	public static Top getZhanQiData(String url){
        Document doc = getDocByUrl(url);
		
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
        	top.setLivenum(caculateNum(liveNum));
        	
        	topList.add(top);
        } 
        Top maxTop = sortTop(topList, "zhanqi");
        return maxTop;
	}
	public static Document getDocByUrl (String url){
	    Document doc = null;
		try {doc = Jsoup.connect(url).get();} catch (IOException e) {e.printStackTrace();} 
		return doc;
	}
	
	/**
	 * 计算结果
	 * @param liveNum
	 * @return
	 */
	public static Float caculateNum(String liveNum){
		Float last = 0f;
    	if(liveNum.contains("万")){
    		last = Float.valueOf(liveNum.substring(0, liveNum.length()-1))*10000;
    	}else{
    		last = Float.valueOf(liveNum);
    	}
		return  last;
	}
	
	public static Top sortTop( List<Top> topList,String tvName){
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
}
