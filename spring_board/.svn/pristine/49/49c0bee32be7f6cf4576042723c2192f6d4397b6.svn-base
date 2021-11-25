package kr.co.codingmonkey.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.codingmonkey.domain.BoardVo;

public class TestEx {
	public static void main(String[] args) {
		List<Map<String, Object>> students;
		
		students = new ArrayList<>();
		
		Map<String, Object> studMap = new HashMap<String, Object>();
		studMap.put("STUDNO", 10101);
		studMap.put("NAME", "전인하");
		students.add(studMap);
		
		studMap = new HashMap<String, Object>();
		studMap.put("STUDNO", 20101);
		studMap.put("NAME", "이동훈");
		students.add(studMap);

		studMap = new HashMap<String, Object>();
		studMap.put("STUDNO", 10102);
		studMap.put("NAME", "박미경");
		students.add(studMap);
		
		
		System.out.println(students);
		
		List<BoardVo> student2 = new ArrayList<>();
		//학번을 게시글 번호에 이름은 제목
		BoardVo stdVo = new BoardVo();
		stdVo.setBno(10101L);
		stdVo.setTitle("전인하");
		student2.add(stdVo);

		stdVo = new BoardVo();
		stdVo.setBno(20101L);
		stdVo.setTitle("이동훈");
		student2.add(stdVo);
		
		stdVo = new BoardVo();
		stdVo.setBno(10102L);
		stdVo.setTitle("박미경");
		student2.add(stdVo);
		
		System.out.println(student2);
	}
}
