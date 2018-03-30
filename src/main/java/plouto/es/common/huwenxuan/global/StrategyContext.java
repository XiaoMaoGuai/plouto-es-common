package plouto.es.common.huwenxuan.global;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@Getter
public class StrategyContext implements ApplicationListener<ContextRefreshedEvent> {

    private Map<String, Object> strategyMap;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(null == contextRefreshedEvent.getApplicationContext().getParent()) {
            strategyMap = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(Strategy.class);
            log.info("strategyMap registed: {}", strategyMap);
        }
    }

}
