package kr.co.codingmonkey.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString(callSuper=true) @Alias("attach")
public class AttachVo extends AttachDto {
	private Long bno;
}
