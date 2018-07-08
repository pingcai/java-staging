package me.pingcai;

import com.github.pagehelper.PageHelper;
import me.pingcai.dao.mapper.TestMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

	@Resource
	private TestMapper testMapper;

	@Resource
	SqlSessionFactory sqlSessionFactory;


	@Test
	public void add() {
		for (int i = 0; i < 100; i++) {
			me.pingcai.dao.entity.Test t = new me.pingcai.dao.entity.Test();
			t.setAge((byte)i);
			t.setName("name:" + i);
			testMapper.insert(t);
		}
	}

	@Test
	public void testPageHelper(){
		PageHelper.startPage(1,10);
		List<me.pingcai.dao.entity.Test> list = testMapper.selectAll();
		list.forEach((test -> {
			System.out.println(test.getName());
		}));
	}

}
