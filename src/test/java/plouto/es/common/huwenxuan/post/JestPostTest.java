package plouto.es.common.huwenxuan.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plouto.es.common.SpringBootRunner;
import plouto.es.common.huwenxuan.ESExecutor;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootRunner.class)
public class JestPostTest {

    @Autowired
    private ESExecutor esExecutor;

    @Test
    public void testHealthCheck() throws Exception {
        System.out.println(esExecutor.health());
    }

    @Test
    public void testIndicesExists() throws Exception {
        System.out.println(esExecutor.indicesExists("hello"));
    }

    @Test
    public void testSearch() throws Exception {
        System.out.println(esExecutor.search("", this.getClass()));
    }
}
