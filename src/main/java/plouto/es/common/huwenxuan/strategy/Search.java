package plouto.es.common.huwenxuan.strategy;

import org.springframework.stereotype.Component;
import plouto.es.common.huwenxuan.entity.param.SearchParam;
import plouto.es.common.huwenxuan.entity.result.SearchResult;
import plouto.es.common.huwenxuan.global.Strategy;

@Strategy
@Component
public class Search extends BaseStrategy<SearchParam, SearchResult> {
    @Override
    public SearchResult doStrategy(SearchParam searchParam) {
        return null;
    }
}
