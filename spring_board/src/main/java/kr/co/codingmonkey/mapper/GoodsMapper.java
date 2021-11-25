package kr.co.codingmonkey.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import kr.co.codingmonkey.domain.GoodsVo;

public interface GoodsMapper {
	@Insert("INSERT INTO  TBL_MUSINSA VALUES (#{bno}, #{title}, #{price}, #{brand})")
	void insert(GoodsVo vo);
	@Insert("INSERT INTO  TBL_MUSINSA_ATTACH VALUES (#{bno}, #{ordered})")
	void insertAttach(Map<String, Object> map);
}
