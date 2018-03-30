package plouto.es.common.huwenxuan.entity.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class IndicesExistsResult extends BaseResult{
    private boolean ok;
    private boolean found;
}
