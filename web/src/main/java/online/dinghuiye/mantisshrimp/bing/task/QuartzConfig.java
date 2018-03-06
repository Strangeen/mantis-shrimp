package online.dinghuiye.mantisshrimp.bing.task;

import online.dinghuiye.bingcollection.consts.BingParam;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author Strangeen on 2018/03/05
 */
@Configuration
@ConditionalOnExpression("'${ms.setting.common.dev_mode}'!='true'")
public class QuartzConfig {

    @Bean
    public AutoCollectBingJob bingJob() {
        return new AutoCollectBingJob();
    }

    @Bean
    public MethodInvokingJobDetailFactoryBean getJobDetailFacotry() {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        bean.setName("collect_bing_job");
        bean.setGroup("bing");
        bean.setConcurrent(false);
        bean.setTargetBeanName("bingJob");
        bean.setTargetMethod("run");
        return bean;
    }

    @Bean
    public CronTriggerFactoryBean getTriggerFactory(JobDetail jobDetail, @Autowired BingParam bingParam) {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setName("collect_bing_trigger");
        bean.setGroup("bing");
        bean.setJobDetail(jobDetail);
        bean.setCronExpression(bingParam.getAcquireCronExp());
        return bean;
    }

    @Bean
    public SchedulerFactoryBean getScheduleFacorty(Trigger trigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(trigger);
        return bean;
    }

}
