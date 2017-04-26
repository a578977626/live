package com.example.model;
/**
 * 排行第一的主播
 * @author chenxb2
 *
 */
public class TopHost extends Host{
	/**
	 * 观看人数
	 */
	private Float liveNum;
	/**
	 * 直播类型
	 */
	private String liveType;
	
	public TopHost(String platform ,String liveType){
		this.liveType = liveType;
		this.setPlatform(platform);
	}
	public String getLiveType() {
		return liveType;
	}

	public void setLiveType(String liveType) {
		this.liveType = liveType;
	}
	public Float getLiveNum() {
		return liveNum;
	}
	public void setLiveNum(Float liveNum) {
		this.liveNum = liveNum;
	}
	
}
