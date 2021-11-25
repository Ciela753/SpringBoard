package kr.co.codingmonkey.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("sample3/*")
public class Sample3Controller {
	@GetMapping("all")
	public void doAll() {
		log.info("all ...");
	}
	@GetMapping("member")
	public void doMember() {
		log.info("member ...");
	}
	@GetMapping("admin")
	public void doAdmin() {
		log.info("admin ...");
	}
	@GetMapping("doMember")
	@PreAuthorize("hasRole('ROLE_MEMBER')")
	public String doMemmber2() {
		log.info("member ...");
		return "sample3/member";
	}
}
