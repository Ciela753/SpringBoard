package kr.co.codingmonkey.service;

import java.util.List;

import kr.co.codingmonkey.domain.BoardVo;
import kr.co.codingmonkey.domain.Criteria;
import kr.co.codingmonkey.domain.ReplyCriteria;
import kr.co.codingmonkey.domain.ReplyVo;

public interface ReplyService {
	void register(ReplyVo board);
	public ReplyVo get(Long rno);
	public boolean modify(ReplyVo vo);
	public boolean remove(Long rno);
	public List<ReplyVo> getList(ReplyCriteria cri, Long bno);
	
}
