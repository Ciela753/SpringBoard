package kr.co.codingmonkey.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import kr.co.codingmonkey.domain.BoardVo;
import kr.co.codingmonkey.domain.ReplyVo;
import kr.co.codingmonkey.service.BoardServiceTests;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
//test controller
@WebAppConfiguration
public class ReplyControllerTests {
	
	@Autowired @Setter
	private WebApplicationContext ctx;
	private MockMvc mvc;//컨트롤러 역할 대신
	
	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void testExist() {
		assertNotNull(ctx);
		assertNotNull(mvc);
		log.info(ctx);
		log.info(mvc);
	}
	
	@Test
	public void testList() throws Exception {
		ModelMap map = mvc.perform(MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "2")
				.param("amount", "4")
				)
		.andReturn()
		.getModelAndView()
		.getModelMap();
		
		log.info(map);
		List<?> list = (List<?>)map.get("list");
		list.forEach(log::info);
	}
	
	@Test
	public void testRegister() throws Exception {
		ModelAndView mav = mvc.perform(MockMvcRequestBuilders.get("/board/register")
		.param("title",  "컨트롤러테스트 글제목")
		.param("content", "컨트롤러테스트 글 내용")
		.param("writer", "컨트롤러 테스터"))
		.andReturn()
		.getModelAndView();
		
		log.info(mav.getViewName());
		
	}
	
	@Test
	public void testGet() throws Exception {
		ModelMap mav = mvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno", "29"))
				.andReturn()
				.getModelAndView()
				.getModelMap();
		
		log.info(mav);
		
	}
	@Test
	public void testModify() throws Exception {
		ModelAndView mav = mvc
		.perform(MockMvcRequestBuilders.post("/board/modify")
		.param("bno", "6")
		.param("title",  "컨트롤러테스트 글제목")
		.param("content", "컨트롤러테스트 글 내용")
		.param("writer", "컨트롤러 테스터"))
		.andReturn().getModelAndView();
		
		log.info(mav.getViewName());
		
	}
	@Test
	public void testCreate() throws Exception {
		ReplyVo vo = new ReplyVo();
		vo.setBno(262144L);
		vo.setReply("컨트롤러 테스트 댓글");
		vo.setReplyer("테스터");
		
		String jsonStr = new Gson().toJson(vo);
		
		log.info("jsonStr ::"+ jsonStr);
		
		mvc.perform(post("/replies/new")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonStr))
		.andExpect(status().is(200));
	}
}
