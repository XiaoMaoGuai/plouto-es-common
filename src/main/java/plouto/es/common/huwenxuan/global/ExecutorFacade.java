package plouto.es.common.huwenxuan.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plouto.es.common.huwenxuan.entity.param.*;
import plouto.es.common.huwenxuan.entity.result.*;

/**
 * Created by huwenxuan on 2018/3/29.
 */
@Component
@Slf4j
public class ExecutorFacade {


    @Autowired
    private Executor executor;

    @Autowired
    private Config config;

    /**
     * 判断indices是否存在
     *
     * @param indicesName
     * @return
     */
    public boolean indicesExists(String indicesName) {
        IndicesExistsParam indicesExistsParam = new IndicesExistsParam();
        //
        indicesExistsParam.setIndices(indicesName);
        IndicesExistsResult indicesExistsResult = (IndicesExistsResult) executor.executor(indicesExistsParam);
        if(null == indicesExistsResult){
            log.error("indicesExistsResult is null!");
            throw new IllegalStateException("indicesExistsResult is null!");
        }
        return indicesExistsResult.isFound() && indicesExistsResult.isOk();
    }

    /**
     * 检查健康状态
     *
     * @return
     */
    public HealthResult health() {
        HealthParam healthParam = new HealthParam();
        HealthResult healthResult = (HealthResult) executor.executor(healthParam);
        return healthResult;
    }

    /**
     * 查询ES
     *
     * @param sql
     * @return
     */
    public String search(String sql) {
        SearchParam searchParam = new SearchParam();
        //
        searchParam.setQueryString(sql);
        SearchResult searchResult = (SearchResult) executor.executor(searchParam);
        //
        if(null == searchResult){
            log.error("searchResult is null!");
            throw new IllegalStateException("searchResult is null!");
        }
        return searchResult.getResultString();
    }

    public CreateIndexResult createIndex(Object o){
        CreateIndexParam createIndexParam = new CreateIndexParam();
        //
        createIndexParam.setIndices(config.getIndex());
        createIndexParam.setType(o.getClass().getSimpleName().toLowerCase());
        createIndexParam.setSource(o);
        //
        CreateIndexResult createIndexResult = (CreateIndexResult) executor.executor(createIndexParam);
        return createIndexResult;
    }
}
