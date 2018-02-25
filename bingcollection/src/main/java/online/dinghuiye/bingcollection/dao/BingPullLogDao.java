package online.dinghuiye.bingcollection.dao;

import online.dinghuiye.bingcollection.pojo.BingItemEntity;
import online.dinghuiye.bingcollection.pojo.BingPullLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Strangeen on 2018/02/20
 */
public interface BingPullLogDao extends JpaRepository<BingPullLogEntity, Long>, JpaSpecificationExecutor<BingPullLogEntity> {

}
