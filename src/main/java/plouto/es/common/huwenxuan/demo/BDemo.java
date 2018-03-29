package plouto.es.common.huwenxuan.demo;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
public class BDemo {

    private String index_writer = "search4za-pre-xxr_current_writer";
    private String index_reader = "search4za-pre-xxr_current_reader";
    private String url = "http://zis-search-za-search-service.test.za.net";
    private String type = "za-pre-xxr";

    private String getSearchUrl() {
        StringBuilder stringBuilder = new StringBuilder(url);
        return stringBuilder.append("/search/").append(type).toString();
    }


    private String post(String url, Map<String, Object> reqMap) {
        RestTemplate template = new RestTemplate();

        //HTTPHEAD
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        //HTTPBODY
        String body = JSONObject.toJSONString(reqMap);
        //POST
        System.out.println(String.format("=========postBody:%s", body));
        String result = template.postForObject(url, new HttpEntity(body, headers), String.class);
        System.out.println(String.format("=========result:%s", result));
        return result;
    }

    /**
     * 通过指令队列执行blcs语法转换
     *
     * 必输
     * indexes 库array/list
     * types  表array/list
     * <p>
     * 可选
     * query 查询条件
     * field 某列array/list
     * from 起始行
     * to   结束行
     * size 条数
     * sort 排序map
     * @param commandQueue
     * @return
     */
    public String packAndSent(Queue<Command> commandQueue, String parameterObjects) {
        Map<String, Object> paramObjectMap = JSONObject.parseObject(parameterObjects, Map.class);
        log.info("====paramObjectMap={}", paramObjectMap);
        //
        Map<String, Object> param = new HashMap();
        List indexes = new ArrayList();
        List tables = new ArrayList();
        List fields = new ArrayList();
        StringBuilder stringBuffer = new StringBuilder();
        //
        String requestStr = null;
        //组装请求报文
        String url = "";
        while(0 != commandQueue.size()) {
            Command command = commandQueue.poll();
            String name = command.getName();
            String value = command.getValue();
            if ("SELECT".equalsIgnoreCase(name)) {
                url = getSearchUrl();
            } else if ("UPDATE".equalsIgnoreCase(name)) {
                /*TODO*/
            } else if ("IDENTIFIER".equalsIgnoreCase(name)) {//字段名
                fields.add(value);
                log.info("字段名{}", value);
            } else if ("FROM".equalsIgnoreCase(name)) {
                do{
                    Command fromCommand = commandQueue.poll();
                    name = fromCommand.getName();
                    if("IDENTIFIER".equalsIgnoreCase(name)){//表名
                        tables.add(fromCommand.getValue());
                        log.info("表名{}", fromCommand.getValue());
                    }
                } while("IDENTIFIER".equalsIgnoreCase(commandQueue.element().getName()) || "COMMA".equalsIgnoreCase(commandQueue.element().getName()));//不是表名或逗号
            } else if("WHERE".equalsIgnoreCase(name)){
//                StringBuffer stringBuffer = new StringBuffer();
                String conditionName = "";
                do{
                    //conditions.append("bank_code:0001").append(" AND ").append("is_active:2");//AND的空格
                    Command conditionCommand = commandQueue.poll();
                    name = conditionCommand.getName();
                    if("IDENTIFIER".equalsIgnoreCase(name)){//条件名
                        conditionName = conditionCommand.getValue();
                        stringBuffer.append(conditionName).append(":");
                    } else if("QUES".equalsIgnoreCase(name)){
                        stringBuffer.append(paramObjectMap.get(conditionName));
                    } else if("AND".equalsIgnoreCase(name)){
                        stringBuffer.append(" AND ");
                    } else if("LITERAL_CHARS".equalsIgnoreCase(name)){
                        stringBuffer.append(conditionCommand.getValue());
                    }
                } while(0 != commandQueue.size() && ("LITERAL_CHARS".equalsIgnoreCase(commandQueue.element().getName())
                        || "EQ".equalsIgnoreCase(commandQueue.element().getName())
                        || "AND".equalsIgnoreCase(commandQueue.element().getName())
                        || "IDENTIFIER".equalsIgnoreCase(commandQueue.element().getName())
                        || "QUES".equalsIgnoreCase(commandQueue.element().getName())));//TODO
            }
        }
        indexes.add(index_reader);
        param.put("indexes", indexes);//库
        param.put("query", stringBuffer.toString());//条件
        param.put("types", tables);//表
        param.put("field", fields);//只展示某列

        log.info("====blcsParam={}", param);
        //请求并返回
        return post(url, param);
    }
}