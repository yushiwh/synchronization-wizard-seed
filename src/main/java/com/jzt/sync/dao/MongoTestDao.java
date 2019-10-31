/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: MongoTestDao
 * Author:   nick
 * Date:     2019/10/30 16:04
 * Description:
 * History:
 */
package com.jzt.sync.dao;

import com.jzt.sync.model.Commodity;
import com.jzt.sync.model.CommodityNew;
import com.jzt.sync.model.MongoTest;
import com.jzt.sync.model.SingleCommodity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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
     * 创建对象
     */
    public void save(SingleCommodity sc) {
        mongoTemplate.save(sc);
    }


    /**
     * 创建对象
     */
    public void saveCommodityNew(CommodityNew cn) {
        mongoTemplate.save(cn);
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

    public SingleCommodity findTestById(Long id) {
        SingleCommodity sc = mongoTemplate.findById(id, SingleCommodity.class);
        return sc;
    }

    public List<Commodity> findCommodityNewById(Long id) {
        CommodityNew cn = mongoTemplate.findById(id, CommodityNew.class);
        List<Commodity> list = cn.getList();
        list.sort(Commodity.Comparators.LASTTIME);
        return list;
    }


    /**
     * 根据shopid和commodityId查询商品
     *
     * @return
     */
    public List<SingleCommodity> findSingleCommodity(int shopId, int commodityId) {
        Query query = new Query();
        Criteria criteria = Criteria.where("shopId").is(shopId).and("commodityId").is(commodityId);
        query.addCriteria(criteria);
        query.limit(1);
//        query.with(Sort.by(
//                Sort.Order.asc("readOrNot"),
//                Sort.Order.desc("lastTime")
//        ));
        query.with(Sort.by(
                Sort.Order.desc("lastTime")
        ));

        List<SingleCommodity> sc = mongoTemplate.find(query, SingleCommodity.class);
        return sc;
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