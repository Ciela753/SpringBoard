package kr.co.codingmonkey.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.codingmonkey.service.SalesService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller @AllArgsConstructor @Log4j
public class SalesController {
	private SalesService salesService;
	
	@ResponseBody @GetMapping("sales/{year}")
	public List<Map<String, Object>> getSalesByYear(@PathVariable String year) {
		return salesService.getSalesListBy(year);
		
	}
	@GetMapping("sales/char/{year}")
	public String chart(@ModelAttribute String year) {
		log.info("chart ..."+ year);
		
		return "sales/chart";
	}
}
