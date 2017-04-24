package com.example.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Top implements Serializable {
	
	private static final long serialVersionUID = -2166321564770274548L;


	public Top(String tv){
		this.tv = tv;
	}
	
	public Top(String tv ,String name){
		this.tv = tv;
		this.hostname= name;
	}
	public Top(){
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String hostname;

	private Float livenum;

	private String tv;

	private String address;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Float getLivenum() {
		return livenum;
	}

	public void setLivenum(Float livenum) {
		this.livenum = livenum;
	}

	public String getTv() {
		return tv;
	}

	public void setTv(String tv) {
		this.tv = tv;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}
