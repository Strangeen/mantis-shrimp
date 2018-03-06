package online.dinghuiye.bingcollection.service.impl;

import online.dinghuiye.bingcollection.entity.BingLogException;
import online.dinghuiye.bingcollection.pojo.BingPullLogEntity;
import online.dinghuiye.bingcollection.service.BingPullLogService;
import online.dinghuiye.common.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Strangeen on 2018/02/25
 */
@Service
public class BingLogOper {

    private static final Logger logger = LoggerFactory.getLogger(BingLogOper.class);

    private final BingPullLogService logService;

    @Autowired
    public BingLogOper(BingPullLogService logService) {
        this.logService = logService;
    }

    public void create(String result, String msg, boolean byHand) {
        try {
            BingPullLogEntity log = new BingPullLogEntity();
            log.setPullTime(DateUtil.now())
                    .setResult(result)
                    .setMsg(msg)
                    .setType(byHand ? "hand" : "auto");
            logService.save(log);

        } catch (Exception e) {
            logger.error("create log error", e);
            throw new BingLogException(e);
        }
    }
}
