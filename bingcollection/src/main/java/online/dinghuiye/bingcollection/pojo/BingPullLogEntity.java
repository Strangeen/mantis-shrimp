package online.dinghuiye.bingcollection.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Strangeen on 2018/02/02
 */
@Entity
@Table(name = "BING_PULL_LOG")
public class BingPullLogEntity {

    private Long id;
    private Date pullTime;
    private String result;
    private String msg;
    private String type;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public BingPullLogEntity setId(Long id) {
        this.id = id;
        return this;
    }

    @Basic
    @Column(name = "PULL_TIME")
    public Date getPullTime() {
        return pullTime;
    }

    public BingPullLogEntity setPullTime(Date pullTime) {
        this.pullTime = pullTime;
        return this;
    }

    @Basic
    @Column(name = "RESULT")
    public String getResult() {
        return result;
    }

    public BingPullLogEntity setResult(String result) {
        this.result = result;
        return this;
    }

    @Basic
    @Column(name = "MSG")
    public String getMsg() {
        return msg;
    }

    public BingPullLogEntity setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @Basic
    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public BingPullLogEntity setType(String type) {
        this.type = type;
        return this;
    }
}
