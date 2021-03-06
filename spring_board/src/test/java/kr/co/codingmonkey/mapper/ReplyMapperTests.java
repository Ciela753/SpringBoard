package kr.co.codingmonkey.mapper;

import static org.junit.Assert.assertNotNull;

import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import kr.co.codingmonkey.domain.BoardVo;
import kr.co.codingmonkey.domain.Criteria;
import kr.co.codingmonkey.domain.ReplyCriteria;
import kr.co.codingmonkey.domain.ReplyVo;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import oracle.net.ano.Service;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	@Setter @Autowired
	private ReplyMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
	}
	

	
	@Test
	public void testInsert() {
		IntStream.range(0, 25).forEach(i->{
			ReplyVo vo = new ReplyVo();
			
			vo.setBno(270L);
			vo.setReply("댓글 테스트"+i);
			vo.setReplyer("댓글러");
			
			mapper.insert(vo);
		});
	}
	
	@Test
	public void testRead() {
		log.info(mapper.read(1L));
	}
	
	@Test
	public void testUpdate() {
		ReplyVo vo = new ReplyVo();
		vo.setReply("수정된 댓글");
		vo.setReplyer("수정맨");
		vo.setRno(3L);
		mapper.update(vo);
	}
	@Test
	public void testRemove() {
		log.info(mapper.delete(2L));
	}
	@Test
	public void testGetList() {
		ReplyCriteria criteria = new ReplyCriteria();
//		criteria.setLastRno(11L);
		mapper.getList(262144L, criteria).forEach(log::info);
	}
	
//	@Test
//	public void testRegister() {
//		ReplyVo replyVo = new ReplyVo();
//		replyVo.setReply("서비스 테스트 등록글 제목");
//		replyVo.setReplyer("서비스 테스터");
//		replyVo.setBno(262144L);
//		service.register(replyVo);
//	}
//	@Test
//	public void testGet() {
//		log.info(service.get(5L));
//	}
//	@Test
//	public void testModify() {
//		ReplyVo replyVo = new ReplyVo();
//		replyVo.setReply("서비스 테스트 수정글 제목");
//		replyVo.setReplyer("서비스 테스터");
//		replyVo.setRno(5L);
//		service.modify(replyVo);
//	}
	
}
