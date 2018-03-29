package plouto.es.common.huwenxuan.post;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JestPost implements Post {

    private JestClient jestClient;
    {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://192.168.92.155:9200")
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create())
                .multiThreaded(true)
                .readTimeout(10000)
                .build());
        jestClient = factory.getObject();
    }

    @Override
    public String post(String url, Map<String, Object> map) {
        return JSONObject.toJSONString(null);
    }
}

@AllArgsConstructor
enum PostType{
    HEALTH(0, "健康检查");

    Integer type;
    String typeName;
}