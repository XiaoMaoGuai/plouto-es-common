package plouto.es.common.huwenxuan.strategy;

import com.alibaba.fastjson.JSONObject;
import io.searchbox.action.Action;
import io.searchbox.core.Index;
import org.springframework.stereotype.Component;
import plouto.es.common.huwenxuan.entity.param.CreateIndexParam;
import plouto.es.common.huwenxuan.entity.result.CreateIndexResult;
import plouto.es.common.huwenxuan.global.Strategy;

@Strategy
@Component
public class CreateIndex extends BaseStrategy<CreateIndexParam, CreateIndexResult> {

    @Override
    public CreateIndexResult doStrategy(CreateIndexParam createIndexParam) {
        Action action = new Index.Builder(createIndexParam.getSource())
                .index(createIndexParam.getIndices())
                .type(createIndexParam.getType())
                .build();
        String jsonResult = (String) post.post(action);
        System.out.println(jsonResult);
        CreateIndexResult createIndexResult = JSONObject.parseObject(jsonResult, CreateIndexResult.class);
        return createIndexResult;
    }
}
