package kr.co.codingmonkey.domain;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;

@Data
public class SampleDTOList {
	@Autowired 
	private List<SampleDTO> list;
}
