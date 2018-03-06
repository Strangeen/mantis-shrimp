package online.dinghuiye.bingcollection.dao;

import online.dinghuiye.bingcollection.pojo.BingItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Strangeen on 2018/02/20
 */
public interface BingItemDao extends JpaRepository<BingItemEntity, Long>, JpaSpecificationExecutor<BingItemEntity> {
}
