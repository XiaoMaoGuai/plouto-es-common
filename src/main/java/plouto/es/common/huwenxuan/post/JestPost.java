package plouto.es.common.huwenxuan.post;

import com.google.gson.GsonBuilder;
import io.searchbox.action.Action;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import plouto.es.common.huwenxuan.global.Config;

import java.io.IOException;

@Component
public class JestPost implements Post<Action, String>, InitializingBean {


    private JestClient jestClient;

    @Autowired
    private Config config;

    @Override
    public String post(Action action) {
        assert null != jestClient : "jestClient initialize failure!";

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
        String url = config.getUrl();
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

