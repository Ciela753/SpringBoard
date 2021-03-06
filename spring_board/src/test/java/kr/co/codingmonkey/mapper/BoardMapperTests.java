package kr.co.codingmonkey.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.codingmonkey.domain.BoardVo;
import kr.co.codingmonkey.domain.Criteria;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Setter @Autowired
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(log::info);
//		mapper.getList();
	}
	@Test
	public void testGetListPaging() {
		Criteria cri = new Criteria();
//		cri.setType("TC");
//		cri.setKeyword("고양이");
		
		mapper.getListWithPaging(cri).forEach(log::info);
	}
	
	@Test
	public void testInsert() {
		BoardVo  board = new BoardVo();
		board.setTitle("영속 테스트 제목");
		board.setContent("영속 테스트 내용");
		board.setWriter("newbie");
		//프록시 객체
		mapper.insert(board);
		
//		log.info(board);
	}
	@Test
	public void testInsertSelectkey() {
		BoardVo  board = new BoardVo();
		board.setTitle("영속 테스트 제목");
		board.setContent("영속 테스트 내용");
		board.setWriter("newbie");
		
		log.info("before :: " +board);
		mapper.insertSelectKey(board);
		log.info("after :: " +board);
	}
	
	@Test
	public void testGetTotalCount() {
		log.info(mapper.getTotalCount(new Criteria()));
	}
	
	@Test
	public void testRoad() {
		log.info(mapper.read(9L));
	}
	@Test
	public void testUpdate() {
		BoardVo board = new BoardVo();
		board.setBno(9L);
		board.setTitle("수정된 테스트 제목");
		board.setContent("수정된 테스트 내용");
		board.setWriter("newbie");
		
		log.info(mapper.update(board));
		log.info(mapper.read(9L));
	}
	
	@Test
	public void testDelete() {
		log.info(mapper.read(3L));
		log.info(mapper.delete(3L));
		log.info(mapper.read(3L));
	}
	
	@Test
	public void testsGetTotalCount() {
		Criteria cri = new Criteria();
		cri.setType("T");
		cri.setKeyword("고양이");
		log.info(mapper.getTotalCount(cri));
	}
}
