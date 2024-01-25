package com.web.test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.web.test.factory.MybatisSqlSessionFactory;
import com.web.test.mapper.TestMapper;
import com.web.test.vo.TestVO;

public class TestDAO {
	SqlSession session = MybatisSqlSessionFactory.getsqlSessionFactory().openSession();
	
	public List<TestVO> getTests(){
		
		return session.selectList("com.web.test.dao.TestDAO.selectTest");
	}
	
	public TestVO getTest(int ctNum){

		return session.selectOne("com.web.test.dao.TestDAO.selectOne", ctNum);
	}
	
	public int insertTest(TestVO test) {
		return session.insert("com.web.test.dao.TestDAO.insertChat", test);
	}
	
	public int updateTest(TestVO test) {
		return session.update("com.web.test.dao.TestDAO.updateChat", test);
	}
	
	public int deleteTest(int ctNum) {
		return session.delete("com.web.test.dao.TestDAO.deleteChat", ctNum);
	}
	
	public static void main(String[] args) {
		System.out.println(new TestDAO().getTest(1));
	}
}
