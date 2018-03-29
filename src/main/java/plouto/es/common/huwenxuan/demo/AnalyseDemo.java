package plouto.es.common.huwenxuan.demo;

import com.alibaba.druid.sql.parser.Lexer;
import com.alibaba.druid.sql.parser.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

public class AnalyseDemo {


    public static Queue analyse(String sql) {
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

    public static void main(String[] args) {
        Queue<Command> queue = analyse("select\n" +
                "\t\t \n" +
                "\t\tproduct_code, channel_id ,third_user_no, user_id, acct_id, cert_type, cert_no,\n" +
                "\t\tuser_name\n" +
                "\t \n" +
                "\t\tfrom user_account\n" +
                "\t\twhere third_user_no = ?\n" +
                "\t\tand product_code = ? \n" +
                "\t\tand is_deleted = 'N'");

        for(Command command : queue){
            System.out.println(String.format("command:%-15s, value:%-10s", command.getName(), command.getValue()));
        }
    }
}


@Setter
@Getter
@AllArgsConstructor
class Command {
    private String name;
    private String value;
}
