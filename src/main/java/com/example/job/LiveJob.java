package com.example.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.model.Host;
import com.example.rep.HostRespository;
import com.example.rep.RepTestRepository;
import com.example.util.CommonTools;
import com.example.util.WebDriverManBase;

@Component
public class LiveJob {
    public final static long ONE_MINUTE =  5 * 1000;
    
    public final static long HALF_HOUR =  60 * 30 * 1000;
    
    
    public static int ci = 0;
    @Autowired
	private RepTestRepository repTestRespostoty;
    
    @Autowired
   	private HostRespository hostRespository;
    
//    @Scheduled(fixedDelay=ONE_MINUTE)
//    public void fixedDelayJob(){
//    	 	String zqurl = "http://www.zhanqi.tv/lives"; 
//	        String dyurl = "https://www.douyu.com/directory/all";  
//	        Top topdy = CommonTools.getDouYuData(dyurl);
//	        Top topzq = CommonTools.getZhanQiData(zqurl);
//	        
//	        repTestRespostoty.save(topdy);
//	        repTestRespostoty.save(topzq);
//	        
//        System.out.println(" >>fixedDelay执行....");
//    }
    
    
    /**
     * 抓取斗鱼的主播数据并保存到数据库
     */
//    @Scheduled(fixedDelay=ONE_MINUTE)
    public void getDouYuHostJob(){
    	 System.out.println(" 执行斗鱼主播数据抓取");
    	 
    	 ExampleMatcher matcher = ExampleMatcher.matching()     
    			  .withIgnorePaths("id","headPortrait","address")                      
    			  .withIncludeNullValues();                           
    	 
	        String dyurl = "https://www.douyu.com/directory/all";  
	        List<Host> hostList = CommonTools.getDouYuListHostData(dyurl);
	        
	        for(Host host : hostList){
	        		Example<Host> example = Example.of(host, matcher);
	        		if(!hostRespository.exists(example)){
	        			hostRespository.save(host);
	        			System.out.println(host.getHostName()+"-"+host.getRoomId());
	        		}
	        }
	        
        System.out.println(" >>斗鱼抓取完毕....");
    }
    
    /**
     * 抓取熊猫的主播数据并保存到数据库
     */
    @Scheduled(fixedDelay=ONE_MINUTE)//在上一个任务完成之后，5s后再次执行,是线程安全的
    public void getPandaHostJob(){
    	 System.out.println(" 执行熊猫主播数据抓取");
    	 
    	 ExampleMatcher matcher = ExampleMatcher.matching()     
    			  .withIgnorePaths("id","headPortrait","address")                      
    			  .withIncludeNullValues();                           
    	 
	        String xmurl = "http://www.panda.tv/all";  
	        List<Host> hostList = CommonTools.getXiongMaoListHostData(xmurl);
	        System.out.println("抓到那么多-----"+hostList.size());
	        for(Host host : hostList){
	        		int i= 0;
	        		Example<Host> example = Example.of(host, matcher);
	        		if(!hostRespository.exists(example)){
	        			i++;
	        			hostRespository.save(host);
	        			System.out.println(host.getHostName()+"-"+host.getRoomId());
	        		}
	        		System.out.println("save nUM!!--------"+i);
	        }
	    WebDriverManBase.teardown();    
        System.out.println(" >>熊猫抓取完毕....");
    }

//    @Scheduled(fixedRate=ONE_Minute)
//    public void fixedRateJob(){
//    }
//
//    @Scheduled(cron="0 15 3 * * ?")
//    public void cronJob(){
//    }
}