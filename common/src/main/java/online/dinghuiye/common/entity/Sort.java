package online.dinghuiye.common.entity;

import org.hibernate.Criteria;

/**
 * {@link Criteria} 数据查询排序字段对象
 *
 * @author Strangeen on 2018/01/11
 */
public class Sort {

    private String sortFieldName;
    private Direct direct;

    public Sort(String sortFieldName, Direct direct) {
        this.sortFieldName = sortFieldName;
        this.direct = direct;
    }

    public String getSortFieldName() {
        return sortFieldName;
    }

    public void setSortFieldName(String sortFieldName) {
        this.sortFieldName = sortFieldName;
    }

    public Direct getDirect() {
        return direct;
    }

    public void setDirect(Direct direct) {
        this.direct = direct;
    }

    public enum Direct {
        asc, desc
    }

}
