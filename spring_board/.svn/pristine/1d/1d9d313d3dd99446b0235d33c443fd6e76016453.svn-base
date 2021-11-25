package kr.co.codingmonkey.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.codingmonkey.domain.BoardVo;
import kr.co.codingmonkey.domain.Criteria;
import kr.co.codingmonkey.domain.ReplyCriteria;
import kr.co.codingmonkey.domain.ReplyVo;
import kr.co.codingmonkey.mapper.BoardMapper;
import kr.co.codingmonkey.mapper.ReplyMapper;
import lombok.AllArgsConstructor;

@AllArgsConstructor @Service
public class RelpyServiceImpl implements ReplyService {
	private ReplyMapper mapper;
	private BoardMapper boardMapper;

	@Override
	@Transactional
	public void register(ReplyVo vo) {
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		mapper.insert(vo);
		
	}

	@Override
	public ReplyVo get(Long rno) {
		return mapper.read(rno);
	}

	@Override
	public boolean modify(ReplyVo vo) {
		return mapper.update(vo) > 0;
	}

	@Override
	public boolean remove(Long rno) {
		boardMapper.updateReplyCnt(get(rno).getBno(), -1);
		return mapper.delete(rno) > 0;
	}

	@Override
	public List<ReplyVo> getList(ReplyCriteria cri, Long bno) {
		return mapper.getList(bno, cri);
	}
}
