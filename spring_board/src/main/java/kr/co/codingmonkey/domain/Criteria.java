package kr.co.codingmonkey.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {
	private int pageNum;
	private int amount;
	private String type;
	private String keyword;
	private int category = 1;
	
	
	public Criteria() {
		this(1,10);
	}
	
	
	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
	public String getParams() {
		return UriComponentsBuilder.newInstance()
				.queryParam("pageNum", pageNum)
				.queryParam("amount", amount)
				.queryParam("type", type)
				.queryParam("keyword", keyword)
				.build().toString();
		
	}
}
