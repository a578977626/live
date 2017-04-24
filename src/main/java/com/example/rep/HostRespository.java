package com.example.rep;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.base.BaseRepository;
import com.example.model.Host;

public interface HostRespository extends BaseRepository<Host ,Long>{
	
	Host findByRoomId(String roomId);
	
//	@Query("select count(e)>0 from MyEntity e where ...")
//	public boolean existsIfBlaBla(@Param("id") String id);


}
