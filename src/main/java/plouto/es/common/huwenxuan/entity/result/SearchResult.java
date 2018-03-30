package plouto.es.common.huwenxuan.entity.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class SearchResult  extends BaseResult{
    private static final long serialVersionUID = -916214014168067159L;
    private String resultString;

    private String took;
    private String time_out;
    private Shard _shards;
    private Hits hits;

}

@Setter
@Getter
class Shard{
    private String total;
    private String successful;
    private String failed;
    private String skipped;
}

@Setter
@Getter
@ToString
class Hits<T>{
    private String total;
    private String max_score;
    private List<Hit> hitsList;
}

@Setter
@Getter
@ToString
class Hit<T>{
    private String _index;
    private String _id;
    private String _type;
    private String _score;
    private String source;
}