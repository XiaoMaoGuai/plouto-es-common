package plouto.es.common.huwenxuan.entity.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public abstract class BaseParam {
    private String indices;
    private String type;
    private String id;
}
