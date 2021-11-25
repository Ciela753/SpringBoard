package kr.co.codingmonkey.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTests {
	@Autowired @Setter
	private TimeMapper mapper;
	
	@Test
	public void testExist() {
		assertNotNull(mapper);
		log.info(mapper);
	}
	
	@Test
	public void testGetTime() {
		log.info(mapper.getTime());
	}
	
	
	@Test
	public void testGetTime2() {
		String time = mapper.getTime2();
		log.trace(time);
		log.debug(time);
		log.info(time);
		log.warn(time);
		log.fatal(time);
	}
}
