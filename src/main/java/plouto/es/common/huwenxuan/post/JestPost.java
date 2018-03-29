package plouto.es.common.huwenxuan.post;

import com.google.gson.GsonBuilder;
import io.searchbox.action.Action;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.cluster.Health;
import io.searchbox.core.Search;
import io.searchbox.indices.IndicesExists;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import plouto.es.common.huwenxuan.entity.param.*;

import java.io.IOException;

@Component
public class JestPost implements Post<BaseParam>, InitializingBean {

    @Value("${plouto.es.server}")
    private String url;

    private JestClient jestClient;

    @Override
    public String post(BaseParam requestParam) {
        assert null != jestClient : "jestClient initialize failure!";
        assert null != requestParam : "requestParam should not be null!";
        //
        Action action = null;
        if (requestParam instanceof UpdateParam) {
            UpdateParam updateParam = (UpdateParam) requestParam;
            action = new IndicesExists.Builder(updateParam.getScript()).build();
        } else if (requestParam instanceof HealthParam) {
            action = new Health.Builder().build();
        } else if (requestParam instanceof SearchParam) {
            String queryString = ((SearchParam) requestParam).getQueryString();
            action = new Search.Builder(queryString).build();
        } else if (requestParam instanceof IndicesExistsParam) {
            String indicesName = requestParam.getIndices();
            assert !StringUtils.isEmpty(indicesName) : "param[indicesName] should not be null!";
            action = new IndicesExists.Builder(indicesName).build();
        }

        JestResult jestResult = null;

        try {
            jestResult = jestClient.execute(action);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (null == jestResult) {
            throw new IllegalStateException("jestResult is null!");
        }

        return jestResult.getJsonString();
    }

    @Override
    public void afterPropertiesSet() {
        assert null != url : "es url should not be null! please check the config!";
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(url)
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create())
                .multiThreaded(true)
                .readTimeout(10000)
                .build());
        jestClient = factory.getObject();
    }
}

