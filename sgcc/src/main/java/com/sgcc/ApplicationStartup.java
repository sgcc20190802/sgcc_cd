package com.sgcc;

import com.sgcc.Service.QuestionService;
import com.sgcc.service.ArticleService;
import com.sgcc.service.BusinessGuideService;
import com.sgcc.service.ConsumerManagerService;
import com.sgcc.service.ServiceHallService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 服务启动自动执行
 */
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        QuestionService questionService = contextRefreshedEvent.getApplicationContext().getBean(QuestionService.class);
        questionService.initCategory();
        questionService.initQuestion();

        ServiceHallService serviceHallService = contextRefreshedEvent.getApplicationContext().getBean(ServiceHallService.class);
        serviceHallService.Initialize();

        ArticleService articleService =contextRefreshedEvent.getApplicationContext().getBean(ArticleService.class);
        articleService.Initialize( null );

        BusinessGuideService businessGuideService = contextRefreshedEvent.getApplicationContext().getBean(BusinessGuideService.class);
        businessGuideService.initRedisBusinessGuide();

        ConsumerManagerService consumerManagerService = contextRefreshedEvent.getApplicationContext().getBean(ConsumerManagerService.class);
        consumerManagerService.initRedis();

        System.out.println("Redis初始化成功");
    }
}
