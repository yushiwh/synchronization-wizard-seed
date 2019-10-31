/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: MongoTestDao
 * Author:   nick
 * Date:     2019/10/30 16:04
 * Description:
 * History:
 */
package com.jzt.sync.dao;

import com.jzt.sync.model.MongoTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * 〈〉
 *
 * @author nick
 * @create 2019/10/30
 * @since 1.0.0
 */
@Component
public class MongoTestDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 创建对象
     */
    public void saveTest(MongoTest test) {
        mongoTemplate.save(test);
    }

    /**
     * 根据用户名查询对象
     *
     * @return
     */
    public MongoTest findTestByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        MongoTest mgt = mongoTemplate.findOne(query, MongoTest.class);
        return mgt;
    }

    /**
     * 更新对象
     */
    public void updateTest(MongoTest test) {
        Query query = new Query(Criteria.where("id").is(test.getId()));
        Update update = new Update().set("age", test.getAge()).set("name", test.getName());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, MongoTest.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,TestEntity.class);
    }

    /**
     * 删除对象
     *
     * @param id
     */
    public void deleteTestById(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, MongoTest.class);
    }
}