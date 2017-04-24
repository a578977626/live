package com.example.rep;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Top;


public interface ReadingListRespostoty extends JpaRepository<Top ,Long>{

	List <Top> findBytv (String tv);//这个6，他是根据方法名来查询的！！！findByXXX，那么就是查询的XXX
	
	
//	@Modifying 
//	@Query("update Top u set u.firstname = ?1 where u.lastname = ?2") 
//	int setFixedFirstnameFor(String firstname, String lastname); 
	
//	 Top findBytv (String tv);//这个6，他是根据方法名来查询的！！！findByXXX，那么就是查询的XXX
}
