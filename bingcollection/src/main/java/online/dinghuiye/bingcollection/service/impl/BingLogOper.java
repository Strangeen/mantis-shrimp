package online.dinghuiye.bingcollection.service.impl;

import online.dinghuiye.bingcollection.entity.BingLogException;
import online.dinghuiye.bingcollection.pojo.BingItemEntity;
import online.dinghuiye.bingcollection.pojo.BingPullLogEntity;
import online.dinghuiye.bingcollection.service.BingPullLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Strangeen on 2018/02/25
 */
@Service
public class BingLogOper {

    private static final Logger logger = LoggerFactory.getLogger(BingLogOper.class);

    @Autowired
    private BingPullLogService logService;

    public void create(String result, String msg, boolean byHand) {
        try {
            BingPullLogEntity log = new BingPullLogEntity();
            log.setPullTime(new Date())
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
