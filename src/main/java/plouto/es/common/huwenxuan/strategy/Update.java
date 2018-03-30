package plouto.es.common.huwenxuan.strategy;

import org.springframework.stereotype.Component;
import plouto.es.common.huwenxuan.entity.param.UpdateParam;
import plouto.es.common.huwenxuan.entity.result.UpdateResult;
import plouto.es.common.huwenxuan.global.Strategy;

@Strategy
@Component
public class Update extends BaseStrategy<UpdateParam, UpdateResult>{
    @Override
    public UpdateResult doStrategy(UpdateParam updateParam) {
        return null;
    }
}
