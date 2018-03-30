package plouto.es.common.huwenxuan.strategy;

import com.alibaba.fastjson.JSONObject;
import io.searchbox.action.Action;
import org.springframework.stereotype.Component;
import plouto.es.common.huwenxuan.entity.param.IndicesExistsParam;
import plouto.es.common.huwenxuan.entity.result.IndicesExistsResult;
import plouto.es.common.huwenxuan.global.Strategy;

@Strategy
@Component
public class IndicesExists extends BaseStrategy<IndicesExistsParam, IndicesExistsResult> {
    @Override
    public IndicesExistsResult doStrategy(IndicesExistsParam indicesExistsParam) {
        Action action = new io.searchbox.indices.IndicesExists.Builder(indicesExistsParam.getIndices()).build();
        String jsonResult = (String) post.post(action);
        IndicesExistsResult indicesExistsResult = JSONObject.parseObject(jsonResult, IndicesExistsResult.class);
        return indicesExistsResult;
    }
}
