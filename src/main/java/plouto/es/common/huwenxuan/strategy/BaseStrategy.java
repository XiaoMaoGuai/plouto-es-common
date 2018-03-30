package plouto.es.common.huwenxuan.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import plouto.es.common.huwenxuan.entity.param.BaseParam;
import plouto.es.common.huwenxuan.entity.result.BaseResult;
import plouto.es.common.huwenxuan.post.Post;

public abstract class BaseStrategy<P extends BaseParam, R extends BaseResult> {
    @Autowired
    protected Post post;
    public abstract R doStrategy(P p);
}
