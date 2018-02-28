package online.dinghuiye.bingcollection.service.impl;

import online.dinghuiye.AbstractTest;
import online.dinghuiye.bingcollection.dao.BingItemDao;
import online.dinghuiye.bingcollection.pojo.BingItemEntity;
import online.dinghuiye.bingcollection.service.BingItemService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Strangeen on 2018/01/27
 */
public class TestBingItemServiceImpl extends AbstractTest {

    @Autowired
    private BingItemService bingItemService;

    @Test
    public void testSave() {
        for (int i = 0; i < 5; i ++)
            bingItemService.save(new BingItemEntity());
    }

    @Test
    public void testFindAllByPage() {
        Page<BingItemEntity> list = bingItemService.findAll(new PageRequest(1, 2));
        // 没有数据的情况下
        System.out.println(list.getNumber());           // 1 该页页码-1
        System.out.println(list.getNumberOfElements()); // 0 该页的条目数
        System.out.println(list.getSize());             // 2 每页条目数（pageSize）
        System.out.println(list.getTotalPages());       // 0 总页数
        System.out.println(list.getTotalElements());    // 0 条目总数
    }
}
