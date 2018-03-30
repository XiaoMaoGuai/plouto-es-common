package plouto.es.common.huwenxuan.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plouto.es.common.SpringBootRunner;
import plouto.es.common.huwenxuan.global.ExecutorFacade;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootRunner.class)
public class JestPostTest {

    @Autowired
    private ExecutorFacade executorFacade;

    /**
     * 测试健康检查
     * @throws Exception
     */
    @Test
    public void testHealthCheck() throws Exception {
        System.out.println(executorFacade.health());
    }

    /**
     * 判断索引是否存在
     * @throws Exception
     */
    @Test
    public void testIndicesExists() throws Exception {
        System.out.println(executorFacade.indicesExists("hello"));
    }

    /**
     * 搜索
     * @throws Exception
     */
    @Test
    public void testSearch() throws Exception {
//        System.out.println(executorFacade.search("", this.getClass()));
    }

    @Test
    public void testCreateIndex() throws Exception{
        BankStatus bankStatus = new BankStatus();
        bankStatus.setBankCode("ICBC");
        bankStatus.setBankName("工商银行");
        System.out.println(executorFacade.createIndex(bankStatus));
    }
}
