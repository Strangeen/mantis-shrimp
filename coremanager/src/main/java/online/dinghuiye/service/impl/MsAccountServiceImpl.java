package online.dinghuiye.service.impl;

import online.dinghuiye.common.pojo.MsAccountEntity;
import online.dinghuiye.common.service.impl.AbstractICommonServiceImpl;
import online.dinghuiye.dao.MsAccountDao;
import online.dinghuiye.service.MsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Strangeen on 2018/02/19
 */
@Service
public class MsAccountServiceImpl extends AbstractICommonServiceImpl<MsAccountDao, MsAccountEntity, Long> implements MsAccountService {

    private final MsAccountDao dao;

    @Autowired
    public MsAccountServiceImpl(MsAccountDao dao) {
        this.dao = dao;
    }

    @Override
    public List<MsAccountEntity> findByAccount(String account) {
        return dao.findByAccount(account);
    }
}
