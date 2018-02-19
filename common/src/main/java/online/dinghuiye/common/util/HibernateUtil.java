package online.dinghuiye.common.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

/**
 * @author Strangeen on 2018/01/11
 */
@Component
public class HibernateUtil {

    private static SessionFactory factory;

    @Autowired
    public void setFactory(EntityManagerFactory emf) {
        factory = emf.unwrap(SessionFactory.class);
    }

    public static Session getSession() {
        Session session;
        try {
            session = factory.getCurrentSession();
        } catch (Exception e) {
            session = factory.openSession();
        }
        return session;
    }

}
