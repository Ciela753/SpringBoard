package kr.co.codingmonkey.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.codingmonkey.domain.BoardVo;
import kr.co.codingmonkey.domain.Criteria;
import kr.co.codingmonkey.domain.ReplyCriteria;
import kr.co.codingmonkey.domain.ReplyVo;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyServiceTests {
	@Setter @Autowired
	private ReplyService service;
	
	@Test
	public void testExist() {
		assertNotNull(service);
	}
	
	@Test
	public void testGet() {
		log.info(service.get(2L));
	}	
	@Test
	public void testGetList() {
		service.getList(new ReplyCriteria(), 262144L).forEach(log::info);
	}	
	@Test
	public void testRegister() {
		ReplyVo replyVo = new ReplyVo();
		replyVo.setReply("서비스 테스트 등록글 제목");
		replyVo.setReplyer("서비스 테스터");
		replyVo.setBno(262144L);
		service.register(replyVo);
	}
	
	@Test
	public void testModify() {
		ReplyVo replyVo = new ReplyVo();
		replyVo.setReply("서비스 테스트 수정글 제목");
		replyVo.setReplyer("서비스 테스터");
		replyVo.setRno(5L);
		service.modify(replyVo);
	}
	@Test
	public void testList() {
		ReplyCriteria cri = new ReplyCriteria();
		cri.setLastRno(11L);
		log.info(service.getList(cri, 262144L));
	}
	
	@Test
	public void testDelete() {
		log.info(service.remove(262144L));
	}
	
	@Test
	public void testClass() {
		log.info(service);
		log.info(service.getClass().getSimpleName());
	}
}
