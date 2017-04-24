package com.example.rep;

import java.util.List;

import com.example.base.BaseRepository;
import com.example.model.Top;

public interface RepTestRepository extends BaseRepository<Top ,Long>{
	List <Top> findByTv (String tv);//这个6，他是根据方法名来查询的！！！findByXXX，那么就是查询的XXX
}
