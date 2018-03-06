package online.dinghuiye.common.service.impl;

import online.dinghuiye.common.service.ICommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author Strangeen on 2018/02/19
 */
public class AbstractICommonServiceImpl<DAOT extends JpaRepository<OT, IDT> & JpaSpecificationExecutor<OT>, OT, IDT extends Serializable> implements ICommonService<DAOT, OT, IDT> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractICommonServiceImpl.class);

    private DAOT dao;

    @Autowired
    public void setDao(DAOT dao) {
        this.dao = dao;
    }

    @Override
    public OT findById(IDT id) {
        return dao.findOne(id);
    }

    @Override
    public OT findFirstBySample(OT sample) {
        return findFirstBySample(sample, null);
    }

    @Override
    public OT findFirstBySample(OT sample, Sort sort) {
        /*PageRequest page = new PageRequest(0,1, sort);
        Page<OT> otPage = findAll(sample, page);
        if (otPage.getTotalElements() <= 0) return null;
        Iterator<OT> it = otPage.iterator();
        it.hasNext();
        return it.next();*/

        List<OT> list = findAll(sample, sort);
        if (list.size() > 0) return list.get(0);
        return null;
    }

    @Override
    public List<OT> findAll() {
        return dao.findAll();
    }

    @Override
    public List<OT> findAll(Sort sort) {
        return dao.findAll(sort);
    }

    @Override
    public List<OT> findAll(List<IDT> ids) {
        return dao.findAll(ids);
    }

    // page会发送2条sql，一条查数据，一条查页码
    @Override
    public Page<OT> findAll(PageRequest pageRequest) {
        return dao.findAll(pageRequest);
    }

    @Override
    public List<OT> findAll(OT sample) {
        return dao.findAll(whereSpec(sample));
    }

    @Override
    public List<OT> findAll(OT sample, Sort sort) {
        return dao.findAll(whereSpec(sample), sort);
    }

    @Override
    public Page<OT> findAll(OT sample, PageRequest pageRequest) {
        return dao.findAll(whereSpec(sample), pageRequest);
    }

    @Override
    public OT save(OT entity) {
        return dao.save(entity);
    }

    @Override
    public List<OT> save(List<OT> entities) {
        return dao.save(entities);
    }

    @Override
    public void delete(IDT id) {
//        dao.delete(id);
        delete(Arrays.asList(id));
    }

    @Override
    public void delete(List<IDT> ids) {
        List<OT> entities = findAll(ids);
        if (entities.size() > 0)
            delete(entities);
    }

    @Override
    public void delete(Iterable<OT> entities) {
        dao.deleteInBatch(entities);
    }

    @Override
    public OT update(OT entity) {
        return dao.save(entity);
    }

    @Override
    public void deleteAll() {
        dao.deleteAllInBatch();
    }

    private Specification<OT> whereSpec(OT sample) {
        return (Root<OT> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            try {
                List<Predicate> predicates = new ArrayList<>();
                Class<?> clazz = sample.getClass();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object fieldVal = field.get(sample);
                    if (fieldVal != null) {
                        if (fieldVal instanceof String) {
                            if (!"".equals(((String)fieldVal).trim()))
                                predicates.add(cb.like(root.get(field.getName()), "%" + fieldVal + "%"));
                        } else {
                            predicates.add(cb.equal(root.get(field.getName()), fieldVal));
                        }
                    }
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            } catch (IllegalAccessException e) {
                logger.error("create where condition failed", e);
                throw new RuntimeException(e);
            }
        };
    }
}
