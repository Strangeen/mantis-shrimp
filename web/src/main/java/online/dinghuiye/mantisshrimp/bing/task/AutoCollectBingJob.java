package online.dinghuiye.mantisshrimp.bing.task;

import online.dinghuiye.bingcollection.consts.BingParam;
import online.dinghuiye.bingcollection.entity.BingImageFile;
import online.dinghuiye.bingcollection.service.impl.Access;
import online.dinghuiye.common.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Strangeen on 2018/03/05
 */
public class AutoCollectBingJob {

    private static final Logger logger = LoggerFactory.getLogger(AutoCollectBingJob.class);

    private Access access;

    @Autowired
    public void setAccess(Access access) {
        this.access = access;
    }

    /**
     * 定时执行保存图片
     */
    public void run() {
        SimpleDateFormat bingSdf = new SimpleDateFormat(BingParam.bing_date_format);
        Date date = DateUtil.now();

        logger.error("----------- 自动运行：生成的date：" + date + " -----------");

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
