package com.example.demo;

import com.example.demo.entity.Usertest;
import com.example.demo.mapper.UserTestMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	public UserTestMapper userTestMapper;

	//		测试插入
//	@Test
	public void testinsert(){
		Usertest usertest = new Usertest();
		usertest.setName("tbre2s");
		usertest.setAge(12323);
		usertest.setEmail("123123@qq.com");
		int result = userTestMapper.insert(usertest);
		System.out.println(result);
		System.out.println(usertest);
	}

	//		测试更新
//	@Test
	public void testupdate(){
		Usertest usertest = new Usertest();
//		通过条件自动拼接动态sql
		usertest.setId(6L);
		usertest.setName("fewfw的氛围去");
		usertest.setAge(12323);
		usertest.setEmail("123123@qq.com");
//		updateById参数是一个对象
		int i = userTestMapper.updateById(usertest);
		System.out.println(i);
		System.out.println(usertest);
	}

	//		测试查询全部参数
//	@Test
	void contextLoads() {
//		参数是一个Wrapper，条件构造器，这里我们先不用null
//		查询全部信息
		List<Usertest> users=userTestMapper.selectList(null);
		users.forEach(System.out::println);

	}

//	自定义批量查询
//	@Test
	public void testSelectByid(){
		List<Usertest> users=userTestMapper.selectBatchIds(Arrays.asList(1,2,3));
		users.forEach(System.out::println);
	}

	//	条件查询之一使用map操作
//	@Test
	public void testSelectByids(){
		HashMap<String, Object> map = new HashMap<>();
//		自定义要查询的条件
		map.put("name","tbs");
		map.put("age","123");
		List<Usertest> users = userTestMapper.selectByMap(map);
		users.forEach(System.out::println);
	}

	//	测试删除
//	@Test
	public void testDeleteByid(){
		userTestMapper.deleteById(1);
	}

	//	批量删除
//	@Test
	public void testDeleteBatchid(){
		userTestMapper.deleteBatchIds(Arrays.asList(1,2,3));
	}

	//	map删除
//	@Test
	public void testDeleteBatchids(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("name","tbs");
		userTestMapper.deleteByMap(map);
	}

}
