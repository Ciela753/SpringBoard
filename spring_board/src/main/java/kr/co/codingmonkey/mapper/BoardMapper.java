package kr.co.codingmonkey.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import kr.co.codingmonkey.domain.BoardVo;
import kr.co.codingmonkey.domain.Criteria;

public interface BoardMapper {
//	@Select("SELECT * FROM TBL_BOARD WHERE BNO > 0")
	List<BoardVo> getList();
	List<BoardVo> getListWithPaging(Criteria cri);
	void insert(BoardVo board);
	void insertSelectKey(BoardVo board);
	BoardVo read(Long bno);
	int update(BoardVo board);
	int delete(Long bno);
	int getTotalCount(Criteria cri);
	void updateReplyCnt(@Param("bno") Long bno, @Param("amount") int amount);
	
}