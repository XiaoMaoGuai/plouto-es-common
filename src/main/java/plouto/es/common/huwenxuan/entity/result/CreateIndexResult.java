package plouto.es.common.huwenxuan.entity.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CreateIndexResult extends BaseResult {
    private String _index;
    private String _type;
    private String _id;
    private String _version;
    private String result;
    private Shard _shards;
    private boolean created;
}
