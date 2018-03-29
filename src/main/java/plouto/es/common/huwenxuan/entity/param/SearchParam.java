package plouto.es.common.huwenxuan.entity.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by huwenxuan on 2018/3/29.
 */
@Setter
@Getter
@ToString
public class SearchParam extends BaseParam {
    private String queryString;
}
