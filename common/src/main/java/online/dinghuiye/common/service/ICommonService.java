package online.dinghuiye.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Strangeen on 2018/02/19
 */
public interface ICommonService<DAOT extends JpaRepository<OT, IDT> & JpaSpecificationExecutor<OT>, OT, IDT extends Serializable> {

/*    OT save(OT entity) throws Exception;

    void delete(IDT id) throws Exception;

    void delete(OT entity) throws Exception;

    OT findById(IDT id);

    OT findBySample(OT sample);

    List<OT> findAll(OT sample);

    Page<OT> findAll(Pageable pageRequest);

    Page<OT> findAll(OT sample, Pageable pageRequest);*/



    OT findById(IDT id);

    OT findFirstBySample(OT sample);

    OT findFirstBySample(OT sample, Sort sort);

    List<OT> findAll();

    List<OT> findAll(Sort sort);

    List<OT> findAll(List<IDT> ids);

    Page<OT> findAll(PageRequest pageRequest);

    List<OT> findAll(OT sample);

    List<OT> findAll(OT sample, Sort sort);

    Page<OT> findAll(OT sample, PageRequest pageRequest);

    OT save(OT entity);

    List<OT> save(List<OT> entities);

    void delete(IDT id);

    void delete(List<IDT> ids);

    void delete(Iterable<OT> entities);

    void deleteAll();

    //OT update(OT entity);
}
