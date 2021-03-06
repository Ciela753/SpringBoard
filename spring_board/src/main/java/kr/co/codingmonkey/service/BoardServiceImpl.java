package kr.co.codingmonkey.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.codingmonkey.domain.AttachVo;
import kr.co.codingmonkey.domain.BoardVo;
import kr.co.codingmonkey.domain.Criteria;
import kr.co.codingmonkey.mapper.AttachMapper;
import kr.co.codingmonkey.mapper.BoardMapper;
import kr.co.codingmonkey.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	private BoardMapper boardMapper;
	private AttachMapper attachMapper;
	private ReplyMapper replyMapper;
	
	@Override @Transactional 
	public void register(BoardVo boardVo) {
		boardMapper.insertSelectKey(boardVo);
		
		boardVo.getAttachs().forEach(attach ->{
			attach.setBno(boardVo.getBno());
			attachMapper.insert(attach);
		});
	}

	@Override
	public BoardVo get(Long bno) {
		return boardMapper.read(bno);

	}

	@Override @Transactional
	public boolean modify(BoardVo boardVo) {
		boolean result = boardMapper.update(boardVo) > 0;
		attachMapper.deleteAll(boardVo.getBno());
		if(result) {
			boardVo.getAttachs().forEach(vo->{
				vo.setBno(boardVo.getBno());
				attachMapper.insert(vo);
			});
		}
		
		return boardMapper.update(boardVo) > 0;
	}

	@Override @Transactional
	public boolean remove(Long bno) {
		attachMapper.deleteAll(bno);
		replyMapper.deleteAll(bno);
		return boardMapper.delete(bno) >0;
	}

	@Override
	public List<BoardVo> getList(Criteria cri) {
		return boardMapper.getListWithPaging(cri);
	}
	
	
	public List<BoardVo> testGetList(Criteria cri) {
		return boardMapper.getListWithPaging(cri);
	}

//	@Override
//	public void register(BoardVo board) {
//		log.info(board);
//		boardMapper.insertSelectKey(board);
//	}

	@Override
	public int getTotal(Criteria cri) {
		return boardMapper.getTotalCount(cri);
	}

	@Override
	public List<AttachVo> getAttachs(Long bno) {
		return attachMapper.findByBno(bno);
	}
	

}
