package online.dinghuiye.bingcollection.entity;

import lombok.Data;

/**
 * bing获取情况实体
 *
 * @author Strangeen on 2019/03/02
 */
@Data
public class BingPullStatus {

    private Integer descPullCount;

    private boolean isSuccess = true;

    public BingPullStatus(Integer descPullCount) {
        this.descPullCount = descPullCount;
    }
}
