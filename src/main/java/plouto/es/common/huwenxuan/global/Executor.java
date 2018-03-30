package plouto.es.common.huwenxuan.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plouto.es.common.huwenxuan.entity.param.BaseParam;
import plouto.es.common.huwenxuan.entity.result.BaseResult;
import plouto.es.common.huwenxuan.strategy.BaseStrategy;

@Component
@Slf4j
public class Executor {

    @Autowired
    private StrategyContext strategyContext;

    protected BaseResult executor(BaseParam baseParam){
        String fullName = baseParam.getClass().getSimpleName();
        StringBuilder targetNameBuilder = new StringBuilder();
        targetNameBuilder.append(fullName.substring(0, 1).toLowerCase()).append(fullName.substring(1, fullName.indexOf("Param")));
        BaseStrategy baseStrategy = (BaseStrategy) strategyContext.getStrategyMap().get(targetNameBuilder.toString());
        log.info("aim strategy:{}", baseStrategy);
        BaseResult baseResult = baseStrategy.doStrategy(baseParam);
        return baseResult;
    }
}
