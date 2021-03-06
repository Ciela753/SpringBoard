package kr.co.codingmonkey.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.codingmonkey.domain.Criteria;
import kr.co.codingmonkey.domain.ReplyCriteria;
import kr.co.codingmonkey.domain.ReplyVo;

public interface ReplyMapper {
	int insert(ReplyVo vo);
	ReplyVo read(Long rno);
	int update(ReplyVo vo);
	int delete(Long rno);
	int deleteAll(Long bno);
	List<ReplyVo> getList(@Param("bno") Long bno, @Param("cri") ReplyCriteria cri);
}
