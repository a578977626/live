package com.example.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.model.Host;
import com.example.model.TopHost;
import com.example.rep.HostRespository;
import com.example.service.RedisOperateService;
import com.example.util.CommonTools;
import com.example.util.WebDriverManBase;

@Component
public class TopOneHostJob {
    public final static long ONE_MINUTE =  5 * 1000;
    
    public final static long HALF_HOUR =  60 * 30 * 1000;
    
    
    
    @Autowired
   	private HostRespository hostRespository;
    @Autowired
    private RedisOperateService redisService;
    
    /**
     * 抓取斗鱼的TOP1主播
     */
    @Scheduled(fixedDelay=ONE_MINUTE)
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
    @Scheduled(fixedDelay=ONE_MINUTE)
    public void getXiongMaoTopHostJob(){
    	 System.out.println(" 执行斗鱼主播数据抓取------topOne-xiongmao");
    	 
	        String xmurl = "http://www.panda.tv/all";  
	        List<TopHost> hostList = CommonTools.getDouYuListHostDataOnline(xmurl);
	        TopHost xiongMaoTop1 = CommonTools.sortTop(hostList, "xiongmao") ;
	        redisService.handleTopHostRedis(xiongMaoTop1);
	        
        System.out.println(" >>熊猫topOne抓取完毕....");
    }
}
