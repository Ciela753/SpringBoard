package kr.co.codingmonkey.mapper;

import java.util.Map;

import kr.co.codingmonkey.domain.MemberVo;

public interface MemberMapper {
	void insertMember(Map<String, Object> map);
	void insertAuth(Map<String, Object> map);
	MemberVo read(String userid);
}
