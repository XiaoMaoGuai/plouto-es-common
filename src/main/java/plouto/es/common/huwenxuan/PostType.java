package plouto.es.common.huwenxuan;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PostType{
    HEALTH("健康检查"),
    SEARCH("查询"),
    PUT("插入"),
    INDICESEXISTS("索引是否存在"),
    ;
    String typeName;
}