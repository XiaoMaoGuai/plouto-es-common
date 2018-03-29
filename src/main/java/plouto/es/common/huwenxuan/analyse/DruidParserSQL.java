package plouto.es.common.huwenxuan.analyse;

import com.alibaba.druid.sql.parser.Lexer;
import com.alibaba.druid.sql.parser.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class DruidParserSQL implements SQLAnalyzer {

    @Override
    public Queue analyse(String sql) {
        Lexer lexer = new Lexer(sql);
        Queue<Command> queue = new LinkedList();
        //解析
        while (true) {
            lexer.nextToken();
            Token token = lexer.token();
            //结束
            if (token == Token.EOF) {
                break;
            }
            String tokenName = token.name();
            String tokenNameVals = token.name;
            String val =lexer.stringVal();

            if (token == Token.IDENTIFIER || token == Token.MULTI_LINE_COMMENT || token == Token.LITERAL_CHARS) {
//                System.out.println(String.format("1%s: %s", tokenName, val));
                queue.offer(new Command(tokenName, val));
            } else {
//                System.out.println(String.format("2%s: %s", tokenName, tokenNameVals));
                queue.offer(new Command(tokenName, tokenNameVals));
            }
        }
        return queue;
    }

}


@Setter
@Getter
@AllArgsConstructor
class Command{
    private String name;
    private String value;
}