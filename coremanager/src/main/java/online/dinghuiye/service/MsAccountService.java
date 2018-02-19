package online.dinghuiye.service;

import online.dinghuiye.common.pojo.MsAccountEntity;
import online.dinghuiye.common.service.ICommonService;
import online.dinghuiye.dao.MsAccountDao;

import java.util.List;

/**
 * @author Strangeen on 2018/02/19
 */
public interface MsAccountService extends ICommonService<MsAccountDao, MsAccountEntity, Long> {

    List<MsAccountEntity> findByAccount(String account);
}
