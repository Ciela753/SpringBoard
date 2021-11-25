package kr.co.codingmonkey.mapper;

import java.util.List;

import kr.co.codingmonkey.domain.AttachVo;

public interface AttachMapper {
	void insert(AttachVo vo);
	void delete(String uuid);
	List<AttachVo> findByBno(Long bno);
	AttachVo findBy(String uuid);
	void deleteAll(Long bno);//한게시글 첨부파일 일괄삭제
	
	List<AttachVo> getOldFiles();
}
