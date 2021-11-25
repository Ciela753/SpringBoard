package kr.co.codingmonkey.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) //메인메서드 없이 실행
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j //롬복
public class SampleTests {
	@Setter @Autowired
	private Restaurant restaurant;
	
	@Test //junit 단위 테스트 테스트메서드는 public이어야 한다.
	public void testExist() {
		//null값 체크
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("==============");
		log.info(restaurant.getChef());
	}
}
