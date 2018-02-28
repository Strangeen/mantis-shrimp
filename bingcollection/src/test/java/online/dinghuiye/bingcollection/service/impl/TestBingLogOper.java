package online.dinghuiye.bingcollection.service.impl;

import online.dinghuiye.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Strangeen on 2018/02/25
 */
public class TestBingLogOper extends AbstractTest {

    @Autowired
    private BingLogOper logOper;

    @Test
    public void testCreate() {
        logOper.create("success", "test", true);
    }

}
