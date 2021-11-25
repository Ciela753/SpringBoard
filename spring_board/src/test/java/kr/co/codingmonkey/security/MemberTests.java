package kr.co.codingmonkey.security;


import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.annotations.Select;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.codingmonkey.domain.BoardVo;
import kr.co.codingmonkey.domain.Criteria;
import kr.co.codingmonkey.domain.ReplyCriteria;
import kr.co.codingmonkey.domain.ReplyVo;
import kr.co.codingmonkey.mapper.MemberMapper;
import kr.co.codingmonkey.task.FileCheckTask;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class MemberTests {
	@Autowired @Setter @Qualifier("BCryptPasswordEncoder")
	private PasswordEncoder encoder;
	@Autowired @Setter
	private MemberMapper memberMapper;
	
	private List<Map<String, Object>> members = new ArrayList<Map<String, Object>>();
	
	@Before
	public void init() {
		for(int i= 90; i<100; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userpw", encoder.encode("pw"+i));
			
			if(i<80) {
				map.put("userid", "user"+i);
				map.put("username", "일반사용자"+i);
				map.put("auth", "ROLE_USER");
			}
			else if(i<90) {
				map.put("userid", "member"+i);
				map.put("username", "회원"+i);
				map.put("auth", "ROLE_MEMBER");
			}
			else {
				map.put("userid", "admin"+i);
				map.put("username", "관리자"+i);
				map.put("auth", "ROLE_ADMIN");
			}
				
			members.add(map);
		}
	}
	
	//member
	@Test
	public void testInsert() {
		members.forEach(m->memberMapper.insertMember(m));
	}
	
	//auth
	@Test
	public void testAuth() {
		members.forEach(m->memberMapper.insertAuth(m));
	}
	
	@Test
	public void testAuth2() {
		List<Map<String, Object>> list = new ArrayList<>();
		for(int i=90; i<100; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("userid", "admin"+i);
			map.put("auth", "ROLE_MEMBER");
			list.add(map);
		}
		list.forEach(m->memberMapper.insertAuth(m));
	}
	
}
