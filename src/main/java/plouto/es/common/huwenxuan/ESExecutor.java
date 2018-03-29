package plouto.es.common.huwenxuan;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plouto.es.common.huwenxuan.entity.result.HealthResult;
import plouto.es.common.huwenxuan.entity.result.IndicesExistsResult;
import plouto.es.common.huwenxuan.global.Constant;
import plouto.es.common.huwenxuan.post.Post;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huwenxuan on 2018/3/29.
 */
@Component
public class ESExecutor {


    @Autowired
    private Post post;

    /**
     * 判断indices是否存在
     *
     * @param indicesName
     * @return
     */
    public boolean indicesExists(String indicesName) {
        Map requestMap = new HashMap<String, Object>();
        requestMap.put(Constant.POST_TYPE, PostType.INDICESEXISTS);
        requestMap.put(Constant.INDICES_NAME, indicesName);
        //
        IndicesExistsResult indicesExistsResult = JSONObject.parseObject(post.post(requestMap), IndicesExistsResult.class);
        return indicesExistsResult.isOk() && indicesExistsResult.isFound();
    }

    /**
     * 检查健康状态
     *
     * @return
     */
    public HealthResult health() {
        Map requestMap = new HashMap<String, Object>();
        requestMap.put(Constant.POST_TYPE, PostType.HEALTH);
        //
        HealthResult healthResult = JSONObject.parseObject(post.post(requestMap), HealthResult.class);
        return healthResult;
    }

    /**
     * 查询ES
     *
     * @param sql
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T search(String sql, Class<T> clazz) {
        Map requestMap = new HashMap<String, Object>();
        requestMap.put(Constant.POST_TYPE, PostType.SEARCH);
        /*TODO sql to essql*/
        //
        T result = JSONObject.parseObject(post.post(requestMap), clazz);
        return result;
    }
}
