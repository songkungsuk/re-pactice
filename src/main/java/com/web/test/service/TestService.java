package com.web.test.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.test.dao.TestDAO;
import com.web.test.factory.MybatisSqlSessionFactory;
import com.web.test.mapper.TestMapper;
import com.web.test.vo.TestVO;

public class TestService {
	
	private TestDAO testDAO = new TestDAO();
	
	public List<TestVO> getTests(){
		return testDAO.getTests();
	}
	
	public TestVO getTest(int ctNum) {
		return testDAO.getTest(ctNum);
	}
	
	public int insertTest(TestVO test) {
		return testDAO.insertTest(test);
	}
	
	public int updateTest(TestVO test) {
		return testDAO.updateTest(test);
	}
	
	public int deleteTest(int ctNum) {
		return testDAO.deleteTest(ctNum);
	}
	

	

}
