package kr.co.codingmonkey.mapper;

import org.apache.ibatis.annotations.Select;


public interface TimeMapper {
	@Select("SELECT SYSDATE FROM DUAL")
	String getTime();
	
	String getTime2();
}
