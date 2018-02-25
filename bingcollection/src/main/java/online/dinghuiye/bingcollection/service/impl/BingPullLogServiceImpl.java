package online.dinghuiye.bingcollection.service.impl;

import online.dinghuiye.bingcollection.dao.BingPullLogDao;
import online.dinghuiye.bingcollection.pojo.BingPullLogEntity;
import online.dinghuiye.bingcollection.service.BingPullLogService;
import online.dinghuiye.common.service.impl.AbstractICommonServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Strangeen on 2018/02/20
 */
@Service
public class BingPullLogServiceImpl extends AbstractICommonServiceImpl<BingPullLogDao, BingPullLogEntity, Long> implements BingPullLogService {

    /*@Autowired
    private BingItemDao bingItemDao;

    @Override
    public List<BingItemEntity> findPage(BingItemEntity condition, List<Sort> sorts, int page, int pageSize) {
        return bingItemDao.findPage(condition, sorts, page, pageSize);
    }*/
}
