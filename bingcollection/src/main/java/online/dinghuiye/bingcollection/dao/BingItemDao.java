package online.dinghuiye.bingcollection.dao;

import online.dinghuiye.bingcollection.pojo.BingItemEntity;
import online.dinghuiye.common.entity.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Strangeen on 2018/02/20
 */
public interface BingItemDao extends JpaRepository<BingItemEntity, Long>, JpaSpecificationExecutor<BingItemEntity> {

//    Long save(BingItemEntity item);
//    List<BingItemEntity> findPage(BingItemEntity condition, List<Sort> sorts, int page, int pageSize);
}
