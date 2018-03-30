package plouto.es.common.huwenxuan.strategy;

import com.alibaba.fastjson.JSONObject;
import io.searchbox.action.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plouto.es.common.huwenxuan.entity.param.HealthParam;
import plouto.es.common.huwenxuan.entity.result.HealthResult;
import plouto.es.common.huwenxuan.global.Strategy;
import plouto.es.common.huwenxuan.post.Post;

import javax.annotation.Resource;

@Strategy
@Component
public class Health extends BaseStrategy<HealthParam, HealthResult>{

    @Override
    public HealthResult doStrategy(HealthParam healthParam) {
        Action action = new io.searchbox.cluster.Health.Builder().build();
        String jsonResult = (String) post.post(action);
        HealthResult healthResult = JSONObject.parseObject(jsonResult, HealthResult.class);
        return healthResult;
    }
}
