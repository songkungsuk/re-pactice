package com.web.test.factory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisSqlSessionFactory {

	private static SqlSessionFactory ssf;

	static {
		String resource = "mybatisConfig.xml";
		SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
		try {
			ssf = ssfb.build(Resources.getResourceAsStream(resource));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static SqlSessionFactory getsqlSessionFactory() {

		return ssf;
	}
	
	public static void main(String[] args) {
		System.out.println(MybatisSqlSessionFactory.getsqlSessionFactory());
	}
}
