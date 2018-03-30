package plouto.es.common.huwenxuan.global;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Config {

    @Value("${plouto.es.server}")
    private String url;

    @Value("${plouto.es.index}")
    private String index;
}
