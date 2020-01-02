package com.krry.dao.impl;

import com.krry.dao.IUserDao;
import com.krry.entity.User;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author
 */
@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void addUser(User user) {
        //1.如果没有指定集合，则默认添加到和对象名称相同的集合中，没有则创建一个
        //2.也可以指定集合 mongoTemplate.save(User, "User_db");
        mongoTemplate.save(user);
    }

    public void removeUser(String id) {
    	
        User User = findById(id);
        mongoTemplate.remove(User);
    }

    public void saveOrUpdateUser(User user) {

        mongoTemplate.save(user,"user");
    }

    public User findById(String id) {

        return mongoTemplate.findById(id, User.class);
    }

    public List<User> findAll() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC,"createTime"));
        return mongoTemplate.find(query,User.class,"user");
    }

	public User findByUsername(String username) {
		//根据username查询
		Query sql = new Query(Criteria.where("username").is(username));
		
		return mongoTemplate.findOne(sql,User.class);
	}

	public WriteResult delete(String username){
        Query sql = new Query(Criteria.where("username").is(username));
        return mongoTemplate.remove(sql,User.class);
    }

    public boolean update(String username) {
	    Query query = new Query(Criteria.where("username").is(username));
        Update update = Update.update("username","哈哈哈");
	    User user = mongoTemplate.findAndModify(query,update,User.class,"user");
	    if(user != null){
	        return true;
        }
        return false;
    }

    public void save(User user) {
        mongoTemplate.save(user,"logger");
    }
}




