package kr.co.codingmonkey.service;

import java.util.List;

import kr.co.codingmonkey.domain.AttachVo;
import kr.co.codingmonkey.domain.BoardVo;
import kr.co.codingmonkey.domain.Criteria;

public interface BoardService {
	void register(BoardVo board);
	public BoardVo get(Long bno);
	public boolean modify(BoardVo board);
	public boolean remove(Long bno);
	public List<BoardVo> getList(Criteria cri);
	int getTotal(Criteria cri);
	List<AttachVo> getAttachs(Long bno);
}
