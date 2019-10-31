/**
 * Copyright (C), 2018-2020, 998电商集团
 * FileName: MongoTest
 * Author:   nick
 * Date:     2019/10/30 16:04
 * Description: MongoTest
 * History:
 */
package com.jzt.sync.mongocontroller;

import com.jzt.sync.configurer.WebLogAspect;
import com.jzt.sync.dao.MongoTestDao;
import com.jzt.sync.model.Commodity;
import com.jzt.sync.model.CommodityNew;
import com.jzt.sync.model.MongoTest;
import com.jzt.sync.model.SingleCommodity;
import com.jzt.sync.util.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 〈MongoTest〉
 *
 * @author nick
 * @create 2019/10/30
 * @since 1.0.0
 */

@RestController
public class MongoTestController {
    @Autowired
    private MongoTestDao mtdao;
    private Logger logger = LoggerFactory.getLogger(MongoTestController.class);
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @GetMapping(value = "/test1")
    public void saveTest() throws Exception {
        MongoTest mgtest = new MongoTest();
        mgtest.setId(9999);
        mgtest.setAge(4444);
        mgtest.setName("nick");
        mtdao.saveTest(mgtest);
    }

    @GetMapping(value = "/test2")
    public MongoTest findTestByName() {
        MongoTest mgtest = mtdao.findTestByName("ceshi");
        System.out.println("mgtest is " + mgtest);
        return mgtest;
    }

    @GetMapping(value = "/test3")
    public void updateTest() {
        MongoTest mgtest = new MongoTest();
        mgtest.setId(11);
        mgtest.setAge(44);
        mgtest.setName("ceshi2");
        mtdao.updateTest(mgtest);
    }

    @GetMapping(value = "/test4")
    public void deleteTestById() {
        mtdao.deleteTestById(11);
    }

    /**
     * 单条存储数据
     *
     * @throws Exception
     */
    @GetMapping(value = "/single")
    public void single() throws Exception {
        Random df = new Random();
        System.out.println("开始执行插入" + new Date());
        ExecutorService executor1 = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 1000000; i++) {
            SingleCommodity sc = new SingleCommodity();
            sc.setId(IdWorker.getId());
            sc.setShopId(df.nextInt(5));
            sc.setCommodityId(df.nextInt(5));
            sc.setConstPrice(df.nextInt(99));
            sc.setLastTime(new Date());
            executor1.submit(() -> {
                mtdao.save(sc);
            });
        }
        System.out.println("插入结束" + new Date());
    }

    /**
     * 单条数据记录，得到指定的数据
     *
     * @param shopId
     * @param commodityId
     * @return
     */
    @GetMapping(value = "/singlefind")
    public List<SingleCommodity> singlefind(@RequestParam int shopId, @RequestParam int commodityId) {
        startTime.set(System.currentTimeMillis());
        List<SingleCommodity> sc = mtdao.findSingleCommodity(shopId, commodityId);
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get() + "毫秒"));
        return sc;
    }

    /**
     * 单条记录进行查询
     * @param id
     * @return
     */
    @GetMapping(value = "/singlefindbyid")
    public SingleCommodity singlefind(@RequestParam Long id) {
        startTime.set(System.currentTimeMillis());
        SingleCommodity sc = mtdao.findTestById(id);
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get() + "毫秒"));
        return sc;
    }

    /**
     * 集合的存储查询，并且list进行排序
     * @param id
     * @return
     */
    @GetMapping(value = "/singlefindbyidorder")
    public List<Commodity> singlefindbyidorder(@RequestParam Long id) {
        startTime.set(System.currentTimeMillis());
        List<Commodity> list = mtdao.findCommodityNewById(id);
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get() + "毫秒"));
        return list;
    }


    /**
     * 集合存储
     *
     * @throws Exception
     */
    @GetMapping(value = "/commoditynew")
    public void commoditynew() throws Exception {
        Random df = new Random();
        System.out.println("开始执行插入" + new Date());
        ExecutorService executor1 = Executors.newFixedThreadPool(20);
        for (int k = 0; k < 500000; k++) {
            CommodityNew cn = new CommodityNew();
            List list = new ArrayList();
            for (int i = 0; i < 100; i++) {
                Commodity c = new Commodity();
                c.setConstPrice(df.nextInt(5));
                Date date = randomDate("2019-01-01", "2019-12-31");
                c.setLastTime(date);
                list.add(c);
            }
            cn.setId(IdWorker.getId());
            cn.setCommodityId(df.nextInt(5));
            cn.setShopId(df.nextInt(5));
            cn.setList(list);
            executor1.submit(() -> {
                mtdao.saveCommodityNew(cn);
            });
        }
        System.out.println("插入结束" + new Date());
    }


    public static Date randomDate(String beginDate, String endDate) {
        //时间格式化
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        try {
            //定义开始时间
            start = format.parse(beginDate);
            //定义结束时间
            end = format.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (start.getTime() >= end.getTime()) {
            return null;
        }
        long date = random(start.getTime(), end.getTime());
        return new Date(date);
    }

    private static long random(long begin, long end) {

        long rtn = begin + (long) (Math.random() * (end - begin));
        //如果返回的是开始时间和结束时间，通过递归调用本函数查找随机值
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }


}