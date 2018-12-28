package com.bjsxt.dao;



import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import com.bjsxt.pojo.Users;

public interface UsersDao extends Repository<Users, Integer>{
//	方法名称命名规则
	List<Users> findByUsernameIs(String string);
	List<Users> findByUsernameLike(String string);
	List<Users> findByUsernameAndUserageGreaterThanEqual(String name,Integer age);
//	使用@query注解查询
	@Query(value="from Users where username= ? and userid= ?")
	List<Users> queryUserByNameUserJPQL(String name,Integer userid);
	
//	使用@query注解查询
	@Query("from Users where username like ?")
	List<Users> queryUserByLikeUserJPQL(String name);
	
	@Query("from Users where username = ? and userage >?")
	List<Users> queryUserByNameAndAge(String name,Integer age);
	
	
//	使用@Query注解查询SQL
//	nativeQuery:默认的是false,表示不开启sql查询。是否对value中的语句做转义
	@Query(value="select * from t_users where username=?" ,nativeQuery=true)
	List<Users>  queryUserByNameSQL(String name);
	@Query(value="select * from t_users where userage> ?",nativeQuery=true)
	List<Users> queryUserByAgeSQL(Integer age);
	
	@Query(value="select * from t_users where username like ? and userage >?",nativeQuery=true)
	List<Users>  queryUserByLikeNameAndAgeSQL(String name,Integer age);
	
	@Query(value="update Users set userage = ? where userid = ?")
	@Modifying //@Modifying 当前语句是一个更新语句
	 void updateUserAgeById(Integer age,Integer id);
	
}
