package com.bjsxt.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bjsxt.dao.UsersDao;
import com.bjsxt.pojo.Users;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RepositoryTest {

	@Autowired
	private UsersDao usersDao;
	/**
	 * 需求，使用用户名作为查询条件
	 */
	@Test
	public void test2(){
		/**
		 * 判断相等的条件，有三种表示方式
		 * 1.什么都不写，默认的就是做相等判断
		 * 2.Is
		 * 3.Equal
		 */
		List<Users> list = this.usersDao.findByUsernameIs("王小丽");
		for (Users users : list) {
			System.out.println(users);
		}
	}
	/**
	 * 需求，根据用户姓名做like处理
	 * Like：条件关键字
	 */
	@Test
	public void test3(){
		/**
		 * 判断相等的条件，有三种表示方式
		 * 1.什么都不写，默认的就是做相等判断
		 * 2.Is
		 * 3.Equal
		 */
		List<Users> list = this.usersDao.findByUsernameLike("王%");
		for (Users users : list) {
			System.out.println(users);
		}
	}
	/**
	 * 需求，根据用户姓名做like处理
	 * Like：条件关键字
	 */
	@Test
	public void test4(){
		/**
		 * 判断相等的条件，有三种表示方式
		 * 1.什么都不写，默认的就是做相等判断
		 * 2.Is
		 * 3.Equal
		 */
		List<Users> list = this.usersDao.findByUsernameAndUserageGreaterThanEqual("王小丽", 24);
		for (Users users : list) {
			System.out.println(users);
		}
	}
	@PersistenceContext(name="entityManagerFactory")
	private EntityManager em;
	@Test
	public void test1(){
//		org.springframework.data.jpa.repository.support.SimpleJpaRepository@a64e035
/*		System.out.println(this.usersDao);*/
//		class com.sun.proxy.$Proxy30代理对象 是基于JDK的动态代理方式创建的
/*		System.out.println(this.usersDao.getClass());*/
		JpaRepositoryFactory factory = new JpaRepositoryFactory(em);
//		getRepository(UsersDao.class);可以帮助我们为接口生成实现类，而这个实现类是SimpleJpaRepository的对象
//		要求：该接口必须要是继承Repository接口
		UsersDao ud = factory.getRepository(UsersDao.class);
		System.out.println(ud);
		System.out.println(ud.getClass());
	}
	/**
	 * 测试@Query注解查询JPQL
	 */
	@Test
	public void test5(){
		List<Users> list = this.usersDao.queryUserByNameUserJPQL("王小丽",3);
		for (Users users : list) {
			System.out.println(users);
		}
	}
	/**
	 * 测试@Query注解查询JPQL
	 */
	@Test
	public void test6(){
		List<Users> list = this.usersDao.queryUserByLikeUserJPQL("王%");
		for (Users users : list) {
			System.out.println(users);
		}
	}
	@Test
	public void test8(){
		List<Users> list = this.usersDao.queryUserByNameAndAge("王小丽", 20);
		for (Users users : list) {
			System.out.println(users);
		}
	}
	@Test
	public void test9(){
		List<Users> list = this.usersDao.queryUserByNameSQL("王小丽");
		for (Users users : list) {
			System.out.println(users);
		}
	}
	
	@Test
	public void test10(){
		List<Users> list = this.usersDao.queryUserByLikeNameAndAgeSQL("王%",30);
		for (Users users : list) {
			System.out.println(users);
		}
	}
	@Test
	public void test11(){
		List<Users> list = this.usersDao.queryUserByAgeSQL(25);
		for (Users users : list) {
			System.out.println(users);
		}
	}
	/**
	 * 测试@Query查询 SQL
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void test7(){
		this.usersDao.updateUserAgeById(32, 5);
	}
}
