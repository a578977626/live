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
import com.example.model.TopHost;

public class CommonTools {
	
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
	
	/**
	 * 获取斗鱼主页所有主播的在线信息
	 */
	public static List<TopHost> getDouYuListHostDataOnline(String url){
		Document doc = getDocByUrl(url); 
		doc.setBaseUri("https://www.douyu.com");
		
        Elements links = doc.select("a[data-rid]");  
  
        List<TopHost> hostList = new ArrayList<TopHost>();
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
        	Elements ss = link.getElementsByClass("dy-num fr");
        	String liveNum = "0";
        	if(ss.size()>0){
        		 liveNum = ss.get(0).text();
        	}
        	String herf = link.attr("abs:href");
        	String name = link.getElementsByClass("dy-name").get(0).text();
        	String roomId = link.attr("data-rid");
//        	String headPortrait = getDouYuHPByUrl(herf);不需要头像，其实这里需要改造，到时只需要房间id，因为我已经把主播数据都抓下来了
        	
        	TopHost topHoost = new TopHost("douyu",null);
        	topHoost.setAddress(herf);
        	topHoost.setHostName(name);
//        	topHoost.setHeadPortrait(headPortrait);
        	topHoost.setRoomId(roomId);
        	topHoost.setLiveNum(caculateNum(liveNum));
        	
        	hostList.add(topHoost);
        } 
        return hostList;
	}
	
	/**
	 * 获取熊猫主页所有主播的在线信息
	 */
	public static List<TopHost> getXiongMaoListHostDataOnline(String url){
		Document doc = getDocByUrl(url); 
		doc.setBaseUri("http://www.panda.tv");
		
        Elements links = doc.select("a[data-id]");  
  
        List<TopHost> hostList = new ArrayList<TopHost>();
        for (Element link : links) {
        	String liveNum = "0";
        	if(link.getElementsByClass("video-number").size()>0){
        		liveNum = link.getElementsByClass("video-number").get(0).text();
        	}
        	String herf = link.attr("abs:href");
        	String name = link.getElementsByClass("video-nickname").get(0).text();
        	String roomId = link.attr("data-id");
//        	String headPortrait = getXiongMaoHPByUrl(herf);//不需要头像
        	TopHost host = new TopHost("xiongmao",null);
        	host.setAddress(herf);
        	host.setHostName(name);
        	host.setRoomId(roomId);
        	host.setLiveNum(caculateNum(liveNum));
        	
        	hostList.add(host);
        } 
        return hostList;
	
	}
	
	
	
	/**
	 * 获取熊猫主页所有主播的在线信息
	 */
	public static List<TopHost> getLongZhuListHostDataOnline(String url){
		Document doc = getDocByUrl(url); 
		doc.setBaseUri("http://www.longzhu.com");
		
        Elements links = doc.select("a[data-id]");  
  
        List<TopHost> hostList = new ArrayList<TopHost>();
        for (Element link : links) {
        	String liveNum = "0";
        	if(link.getElementsByClass("video-number").size()>0){
        		liveNum = link.getElementsByClass("video-number").get(0).text();
        	}
        	String herf = link.attr("abs:href");
        	String name = link.getElementsByClass("video-nickname").get(0).text();
        	String roomId = link.attr("data-id");
//        	String headPortrait = getXiongMaoHPByUrl(herf);//不需要头像
        	TopHost host = new TopHost("xiongmao",null);
        	host.setAddress(herf);
        	host.setHostName(name);
        	host.setRoomId(roomId);
        	host.setLiveNum(caculateNum(liveNum));
        	
        	hostList.add(host);
        } 
        return hostList;
	
	}
	
	
	/**
	 * 获取YY主页所有主播的在线信息
	 */
	public static List<TopHost> getYYListHostDataOnline(String url){
		Document doc = getDocByUrl(url); 
		doc.setBaseUri("http://www.yy.com");
		
        Elements links = doc.select("div.video-info");  
  
        List<TopHost> hostList = new ArrayList<TopHost>();
        /*
       <div class="video-info"> 
 <p class="video-title"><a href="/52831903/52831903?tempId=16777217" target="_blank" title="天佑师傅神秘人" data-stat-act-type="3">天佑师傅神秘人</a></p> 
 <div class="audience-count"> 
  <i class="icon-people"></i>20404
 </div> 
</div>
         */
        for (Element link : links) {
        	String liveNum = "0";
        	if(link.getElementsByClass("audience-count").size()>0){
        		liveNum = link.getElementsByClass("audience-count").get(0).text();
        	}
        	String herf = link.select("a").get(0).attr("abs:href");
        	String name = link.select("a").get(0).text();
//        	String roomId = link.attr("data-id");
//        	String headPortrait = getXiongMaoHPByUrl(herf);//不需要头像
        	TopHost host = new TopHost("xiongmao",null);
        	host.setAddress(herf);
        	host.setHostName(name);
        	host.setRoomId(herf);//暂时没有roomID
        	host.setLiveNum(caculateNum(liveNum));
        	
        	hostList.add(host);
        } 
        return hostList;
	
	}
	
	/**
	 * 获取虎牙主页所有主播的在线信息
	 */
	public static List<TopHost> getHuYaListHostDataOnline(String url){
		Document doc = getDocByUrl(url); 
		doc.setBaseUri("http://www.huya.com");
		
        Elements links = doc.select("li.game-live-item");  
  /*
   * <li class="game-live-item"> 
<a href="http://www.huya.com/heigou" class="video-info new-clickstat" target="_blank" report="{&quot;eid&quot;:&quot;click/position&quot;,&quot;position&quot;:&quot;allLive/0/1/11&quot;,&quot;game_id&quot;:&quot;1&quot;,&quot;ayyuid&quot;:&quot;107524968&quot;}"> 
<img class="pic" data-original="http://screenshot.dwstatic.com/yysnapshot/7066622bbd2e94786898def302f1f2d5dec200b7?imageview/4/0/w/338/h/190/blur/1" src="http://assets.dwstatic.com/amkit/p/duya/common/img/default_live.jpg" alt="黑店百地的直播" title="黑店百地的直播"> <em class="tag tag-recommend">大神推荐</em> 
  <div class="item-mask">
  </div>
  <i class="btn-link__hover_i"></i> 
  </a> 
  <a href="http://www.huya.com/heigou" class="title new-clickstat" report="{&quot;eid&quot;:&quot;click/position&quot;,&quot;position&quot;:&quot;allLive/0/1/11&quot;,&quot;game_id&quot;:&quot;1&quot;,&quot;ayyuid&quot;:&quot;107524968&quot;}" title="帮对面上单戒网瘾系列" target="_blank">帮对面上单戒网瘾系列</a> 
  <span class="txt"> 
  <span class="avatar fl">
  <img data-original="http://huyaimg.dwstatic.com/avatar/1033/bd/39b5110640ab7438ca3c59264ac247_180_135.jpg" src="http://assets.dwstatic.com/amkit/p/duya/common/img/default_profile.jpg" alt="黑店百地" title="黑店百地"> 
  <i class="nick" title="黑店百地">黑店百地</i> </span>
  <span class="game-type fr"><a target="_blank" href="http://www.huya.com/g/lol" title="英雄联盟">英雄联盟</a>
  </span> <span class="num"><i class="num-icon"></i><i class="js-num">16.7万</i></span> </span> </li>
   */
        List<TopHost> hostList = new ArrayList<TopHost>();
        for (Element link : links) {
        	String liveNum = "0";
        	if(link.getElementsByClass("js-num").size()>0){
        		liveNum = link.getElementsByClass("js-num").get(0).text();
        	}
        	String herf =link.select("a").get(1).attr("abs:href");
        	String name = link.getElementsByClass("nick").get(0).text();
//        	String roomId = link.attr("data-id");
//        	String headPortrait = getXiongMaoHPByUrl(herf);//不需要头像
        	TopHost host = new TopHost("huya",null);
        	host.setAddress(herf);
        	host.setHostName(name);
        	host.setRoomId(herf);//这个roomId拿不到
        	host.setLiveNum(caculateNum(liveNum));
        	
        	hostList.add(host);
        } 
        return hostList;
	
	}
	
	/**
	 * 获取战旗主页所有主播的在线信息
	 */
	public static List<TopHost> getZhanQiListHostDataOnline(String url){
		Document doc = getDocByUrl(url); 
		doc.setBaseUri("https://www.zhanqi.tv");
		
		Elements links = doc.select("li[data-room-id]"); 
  
        List<TopHost> hostList = new ArrayList<TopHost>();
        /*
         * <li data-room-id="204859" class=""> <a href="/77777" class="js-jump-link"> 
  			<div class="imgBox"> 
   <i class="sub-mask"></i> 
   <i class="play-icon"></i> 
   <img src="https://img2.zhanqi.tv/live/20170426/204859_2017-04-26-20-29-46_big.jpg" alt="盖世亚索 王者组的蒂花之秀~"> 
  </div> 
  <div class="info-area"> 
   <span class="name">盖世亚索 王者组的蒂花之秀~</span> 
   <div class="meat"> 
    <span class="views"> <i class="icon-eye dv"></i> <span class="dv">15.3万</span> </span> 
    <i class="dv sex-woman"></i> 
    <span class="anchor anchor-to-cut dv">七神丶Yasuo</span> 
    <span class="game-name dv">英雄联盟</span> 
   </div> 
  </div> </a> </li>
         */
        for (Element linkOrigin : links) {
        	Element link = linkOrigin.getElementsByClass("js-jump-link").first();
        	String liveNum = "0";
        	if( link.getElementsByClass("dv").size()>2){
        		liveNum = link.getElementsByClass("dv").get(1).text();
        	}
        	String herf = link.attr("abs:href");
        	String name = link.getElementsByClass("anchor-to-cut").get(0).text();
        	String roomId = linkOrigin.attr("data-room-id");
        	TopHost host = new TopHost("zhanqi",null);
        	host.setAddress(herf);
        	host.setHostName(name);
        	host.setRoomId(roomId);
        	host.setLiveNum(caculateNum(liveNum));
        	
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
//        Top maxTop = sortTop(topList, "zhanqi");
        Top maxTop = new Top();
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
	
	public static TopHost sortTop( List<TopHost> topList,String tvName){
		Float max = 1.0f;
        TopHost maxTop = new TopHost(tvName,null);
        for(TopHost top:topList){
        	if(top.getLiveNum()>max){
        		max = top.getLiveNum();
        		maxTop.setHostName(top.getHostName());
        		maxTop.setAddress(top.getAddress());
        		maxTop.setLiveNum(max);
        	}
        };
        return maxTop;
	}
}
