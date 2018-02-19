package online.dinghuiye.dao;

import online.dinghuiye.common.pojo.MsAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Strangeen on 2018/02/19
 */
public interface MsAccountDao extends JpaRepository<MsAccountEntity, Long>, JpaSpecificationExecutor<MsAccountEntity> {

    List<MsAccountEntity> findByAccount(String account);
}
