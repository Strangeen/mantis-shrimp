package online.dinghuiye.common.util;

import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Strangeen on 2018/01/28
 */
public class TestHibernateUtil {

    @Test
    public void testGetSession() {
        Session session = HibernateUtil.getSession();
        Assert.assertNotNull(session);
    }
}
