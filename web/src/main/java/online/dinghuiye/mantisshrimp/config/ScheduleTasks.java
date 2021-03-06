package online.dinghuiye.mantisshrimp.config;

import online.dinghuiye.bingcollection.consts.BingParam;
import online.dinghuiye.bingcollection.entity.BingImageFile;
import online.dinghuiye.bingcollection.service.impl.Access;
import online.dinghuiye.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Strangeen on 2018/02/21
 * @deprecated AutoCollectBingJob 替代
 */
//@Component
//@EnableScheduling
@Deprecated
public class ScheduleTasks {

    private final Access access;

    //@Autowired
    public ScheduleTasks(Access access) {
        this.access = access;
    }

    //@Scheduled(cron = "0 0 10 * * ?")
    public void reportCurrentByCron(){

        SimpleDateFormat bingSdf = new SimpleDateFormat(BingParam.bing_date_format);
        Date date = DateUtil.now();
        access.create(
                date, // 输入的日期也按照yyyyMMdd格式
                new BingImageFile(
                        BingParam.bing_img_save_path,
                        BingParam.bing_img_folder_format,
                        BingParam.bing_img_prefix + "-" +
                                bingSdf.format(date) + "-" +
                                UUID.randomUUID() + ".jpg"),
                BingParam.bing_small_img_width);
    }

}
