package kr.co.codingmonkey.service;

import org.springframework.stereotype.Service;

import kr.co.codingmonkey.domain.GoodsVo;
import kr.co.codingmonkey.mapper.GoodsMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GoodsServiceImpl implements GoodsService {
	private GoodsMapper goodsMapper;

	@Override
	public void register(GoodsVo vo) {
		goodsMapper.insert(vo);
		vo.getAttachs().forEach(goodsMapper::insertAttach);
	}
	
	

}
