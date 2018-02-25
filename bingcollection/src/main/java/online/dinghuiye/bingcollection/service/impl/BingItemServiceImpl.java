package online.dinghuiye.bingcollection.service.impl;

import online.dinghuiye.bingcollection.dao.BingItemDao;
import online.dinghuiye.bingcollection.pojo.BingItemEntity;
import online.dinghuiye.bingcollection.service.BingItemService;
import online.dinghuiye.common.service.impl.AbstractICommonServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Strangeen on 2018/02/20
 */
@Service
public class BingItemServiceImpl extends AbstractICommonServiceImpl<BingItemDao, BingItemEntity, Long> implements BingItemService {

    /*@Autowired
    private BingItemDao bingItemDao;

    @Override
    public List<BingItemEntity> findPage(BingItemEntity condition, List<Sort> sorts, int page, int pageSize) {
        return bingItemDao.findPage(condition, sorts, page, pageSize);
    }*/
}
