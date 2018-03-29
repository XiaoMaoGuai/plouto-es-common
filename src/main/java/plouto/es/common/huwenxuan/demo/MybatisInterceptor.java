package plouto.es.common.huwenxuan.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.cache.CacheKey;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.session.ResultHandler;
//import org.apache.ibatis.session.RowBounds;
//
//import java.util.Collections;
import java.util.List;
//import java.util.Properties;

/**
 * Created by huwenxuan on 2018/3/21.
 */
@Slf4j
//@Intercepts({
//        @Signature(type = Executor.class,
//                method = "query",
//                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class,
//                        CacheKey.class, BoundSql.class}),
//        @Signature(type = Executor.class,
//                method = "query",
//                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
//})
//public class MybatisInterceptor implements Interceptor {

//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        Object target = invocation.getTarget();
//        String resultStr = "{}";
//        BlcsResult blcsResult;
//        if (target instanceof Executor) {
//            log.info("====args={}", invocation.getArgs());
//            final Object[] args = invocation.getArgs();
//            MappedStatement ms = (MappedStatement) args[0];
//            String op = ms.getSqlCommandType().name();
//            log.info("====command={}", op);
//            BoundSql boundSql = ms.getBoundSql(args[1]);
//            String sql = boundSql.getSql();
//            log.info("====sql={}", sql);
//            String parameterObjects = JSONUtils.toFormatJsonString(boundSql.getParameterObject());
//            log.info("====parameterObjects={}", parameterObjects);
//            //blcs
//            resultStr = new BlcsClientDemo().packAndSent(AnalyseDemo.analyse(sql), parameterObjects);
//            log.info("====result={}", resultStr);
//            //
////            ResultMap resultMap = ms.getResultMaps().get(0);
////            List<ResultMapping> resultMappingList = resultMap.getPropertyResultMappings();
//        }
//        blcsResult = JSONUtils.parseJson(resultStr, BlcsResult.class);
//
//        log.info("====blcsResult={}", blcsResult);
//
//        List results = blcsResult.getHitsData();
//        if (null == results) {
//            return Collections.EMPTY_LIST;
//        }
//        return results;
//    }

//    @Override
//    public Object plugin(Object target) {
//        return Plugin.wrap(target, this);
//    }

//    @Override
//    public void setProperties(Properties properties) {
//
//    }
//}

@Getter
@Setter
@ToString
class BlcsResult {
    private boolean success;
    private Long totalHits;
    private List hitsData;
}