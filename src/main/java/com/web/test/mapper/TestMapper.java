package com.web.test.mapper;

import com.web.test.vo.TestVO;

public interface TestMapper {
	TestVO selectOne(int ctNum);

	int insertTest(TestVO test);

	int delectTest(TestVO test);

	int updateTest(TestVO test);
}
