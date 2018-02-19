package online.dinghuiye.service.impl;

import online.dinghuiye.AbstractTest;
import online.dinghuiye.common.pojo.MsAccountEntity;
import online.dinghuiye.service.MsAccountService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Strangeen on 2018/02/19
 */
public class TestMsAccountServiceImpl extends AbstractTest {

    private static final Logger logger = LoggerFactory.getLogger(TestMsAccountServiceImpl.class);

    @Autowired
    private MsAccountService service;

    @Test
    public void testFindById() {
        MsAccountEntity account = service.findById(33L);
        logger.info("1 ---------------------------\n" + account); // 33
    }

    @Test
    public void testFindBySample() {
        MsAccountEntity account0 = new MsAccountEntity();
        account0.setAccount("bing2");
        logger.info("2 ---------------------------\n" + service.findFirstBySample(account0));

        account0.setAccount(null);
        account0.setPassword("123");
        logger.info("2 ---------------------------\n" + service.findFirstBySample(account0, new Sort(new Sort.Order(Sort.Direction.DESC, "id"))));

        account0.setAccount("bing3");
        account0.setPassword("e10adc3949ba59abbe56e057f20f883e");
        logger.info("2 ---------------------------\n" + service.findFirstBySample(account0));
    }

    @Test
    public void testFindAll() {
        logger.info("3 ---------------------------\n" + service.findAll());
    }

    @Test
    public void testFindAllBySort() {
        logger.info("4 ---------------------------\n" + service.findAll(new Sort(Sort.Direction.DESC, "id")));
    }

    @Test
    public void testFindAllByIds() {
        logger.info("5 ---------------------------\n" + service.findAll(Arrays.asList(1L, 33L, 35L)));
    }

    @Test
    public void testFindAllByPage() {
        Page<MsAccountEntity> page = service.findAll(new PageRequest(1,2, new Sort(Sort.Direction.DESC, "id")));
        logger.info("6 ---------------------------\n" + page.getSize() + "\n"); // 34, 33
        Iterator<MsAccountEntity> it = page.iterator();
        while (it.hasNext())
            logger.info(it.next().toString());
    }

    @Test
    public void testFindAllBySample() {
        MsAccountEntity account0 = new MsAccountEntity();
        account0.setPassword("123");
        logger.info("7 ---------------------------\n" + service.findAll(account0));
    }

    @Test
    public void testFindAllBySampleAndSort() {
        MsAccountEntity account0 = new MsAccountEntity();
        account0.setPassword("123");
        logger.info("8 ---------------------------\n" + service.findAll(account0, new Sort(Sort.Direction.DESC, "id")));
    }

    @Test
    public void testFindAllBySampleAndPage() {
        MsAccountEntity account0 = new MsAccountEntity();
        account0.setPassword("123");
        Page<MsAccountEntity> page = service.findAll(account0, new PageRequest(1,2));
        logger.info("9 ---------------------------\n" + page.getSize() + "\n"); // 35, 36
        Iterator<MsAccountEntity> it = page.iterator();
        while (it.hasNext())
            logger.info(it.next().toString());
    }

    @Test
    public void testSaveOne() {
        MsAccountEntity account0 = new MsAccountEntity();
        account0.setPassword("222");
        account0.setAccount("bing11");
        service.save(account0);
    }

    @Test
    public void testSaveList() {
        List<MsAccountEntity> list = new ArrayList<>();
        {
            MsAccountEntity account0 = new MsAccountEntity();
            account0.setAccount("bing");
            account0.setPassword("e10adc3949ba59abbe56e057f20f883e");
            list.add(account0);
        }
        {
            MsAccountEntity account0 = new MsAccountEntity();
            account0.setAccount("bing2");
            account0.setPassword("123");
            list.add(account0);
        }
        service.save(list);
    }

    @Test
    public void testDeleteById() {
        service.delete(38L);
    }

    @Test
    public void testDeleteByEntites() {
        List<MsAccountEntity> list = new ArrayList<>();
        {
            MsAccountEntity account0 = new MsAccountEntity();
            account0.setId(39L);
            list.add(account0);
        }
        service.delete(list);
    }

    @Test
    public void testDeleteByIds() {
        service.delete(Arrays.asList(35L, 37L));
    }

    @Test
    public void testDeleteAll() {
        service.deleteAll();
    }


    // 额外测试JPA findByxx
    @Test
    public void testFindByAccount() {
        logger.info(service.findByAccount("bing").toString());
    }

}
