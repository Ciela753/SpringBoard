package kr.co.codingmonkey.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data @Alias("auth")
public class AuthVo {
	private String userid;
	private String auth;
}
