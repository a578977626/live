package com.example.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.model.TopHost;
import com.example.service.RedisOperateService;
import com.example.util.CommonTools;

@Component
public class TopOneHostJob {
    public final static long ONE_MINUTE =  5 * 1000;
    
    public final static long HALF_HOUR =  60 * 30 * 1000;
    
    @Autowired
    private RedisOperateService redisService;
    
    /**
     * 抓取斗鱼的TOP1主播
     */
//    @Scheduled(fixedDelay=ONE_MINUTE)
    public void getDouYuTopHostJob(){
    	 System.out.println(" 执行斗鱼主播数据抓取------topOne-douyu");
    	 
	        String dyurl = "https://www.douyu.com/directory/all";  
	        List<TopHost> hostList = CommonTools.getDouYuListHostDataOnline(dyurl);
	        TopHost douYuTop1 = CommonTools.sortTop(hostList, "douyu") ;
	        redisService.handleTopHostRedis(douYuTop1);
	        
        System.out.println(" >>斗鱼topOne抓取完毕....");
    }
    
    /**
     * 抓取熊猫的TOP1主播
     */
//    @Scheduled(fixedDelay=ONE_MINUTE)
    public void getXiongMaoTopHostJob(){
    	 System.out.println(" 执行熊猫主播数据抓取------topOne-xiongmao");
    	 
	        String xmurl = "http://www.panda.tv/all";  
	        List<TopHost> hostList = CommonTools.getXiongMaoListHostDataOnline(xmurl);
	        TopHost xiongMaoTop1 = CommonTools.sortTop(hostList, "xiongmao") ;
	        redisService.handleTopHostRedis(xiongMaoTop1);
	        
        System.out.println(" >>熊猫topOne抓取完毕....");
    }
    
    /**
     * 抓取战旗的TOP1主播
     */
//    @Scheduled(fixedDelay=ONE_MINUTE)
    public void getZhanQiTopHostJob(){
    	 System.out.println(" 执行战旗主播数据抓取------topOne-zhanqi");
    	 
	        String zqUrl = "https://www.zhanqi.tv/lives";  
	        List<TopHost> hostList = CommonTools.getZhanQiListHostDataOnline(zqUrl);
	        TopHost xiongMaoTop1 = CommonTools.sortTop(hostList, "zhanqi") ;
	        redisService.handleTopHostRedis(xiongMaoTop1);
	        
        System.out.println(" >>战旗topOne抓取完毕....");
    }
    
    /**
     * 抓取虎牙的TOP1主播
     */
//    @Scheduled(fixedDelay=ONE_MINUTE)
    public void getHuYaTopHostJob(){
    	 System.out.println(" 执行虎牙主播数据抓取------topOne-huya");
    	 
	        String hyUrl = "http://www.huya.com/l";  
	        List<TopHost> hostList = CommonTools.getHuYaListHostDataOnline(hyUrl);
	        TopHost Top1 = CommonTools.sortTop(hostList, "huya") ;
	        redisService.handleTopHostRedis(Top1);
	        
        System.out.println(" >>虎牙topOne抓取完毕....");
    }
    
    /**
     * 抓取YY的TOP1主播
     */
//    @Scheduled(fixedDelay=ONE_MINUTE)
    public void getYYTopHostJob(){
    	 System.out.println(" 执行YY主播数据抓取------topOne-huya");
    	 
	        String yyUrl = "http://www.yy.com/";  
	        List<TopHost> hostList = CommonTools.getYYListHostDataOnline(yyUrl);
	        TopHost Top1 = CommonTools.sortTop(hostList, "yy") ;
	        redisService.handleTopHostRedis(Top1);
	        
        System.out.println(" >>YY topOne抓取完毕....");
    }
    
    /**
     * 抓取龙珠的TOP1主播
     */
//    @Scheduled(fixedDelay=ONE_MINUTE)
    public void getLongZhuTopHostJob(){
    	 System.out.println(" 执行龙珠主播数据抓取------topOne-longzhu");
    	 
	        String lzUrl = "http://longzhu.com/channels/all";  
	        List<TopHost> hostList = CommonTools.getLongZhuListHostDataOnline(lzUrl);
	        TopHost Top1 = CommonTools.sortTop(hostList, "longzhu") ;
	        redisService.handleTopHostRedis(Top1);
	        
        System.out.println(" >>龙珠 topOne抓取完毕....");
    }
    
    /**
     * 抓取bilibili的TOP1主播 
     * use phantomJs
     */
//    @Scheduled(fixedDelay=ONE_MINUTE)
    public void getBiLiTopHostJob(){
    	 System.out.println(" 执行Bilibili主播数据抓取------topOne-Bilibl");
    	 
	        String lzUrl = "http://live.bilibili.com/all";  
	        List<TopHost> hostList = CommonTools.getBiLiListHostDataOnline(lzUrl);
	        TopHost Top1 = CommonTools.sortTop(hostList, "bili") ;
	        redisService.handleTopHostRedis(Top1);
	        
        System.out.println(" >>BilBiLI topOne抓取完毕....");
    }    
    
    /**
     * 抓取美拍的TOP1主播
     */
//    @Scheduled(fixedDelay=ONE_MINUTE)
    public void getMeiPaiTopHostJob(){
    	 System.out.println(" 执行美拍主播数据抓取------topOne-meipai");
    	 
	        String xmurl = "http://www.meipai.com/live";  
	        List<TopHost> hostList = CommonTools.getMeiPaiListHostDataOnline(xmurl);
	        TopHost Top1 = CommonTools.sortTop(hostList, "meipai") ;
	        redisService.handleTopHostRedis(Top1);
	        
        System.out.println(" >>美拍topOne抓取完毕....");
    }
    
    	
}
