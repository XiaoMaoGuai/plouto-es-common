package plouto.es.common.huwenxuan.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import plouto.es.common.SpringBootRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootRunner.class)
public class JestPostTest {

    @Autowired
    private JestPost jestPost;

    @Test
    public void testPost() {
        String url = "";
        Map requestMap = new HashMap<String, Object>();
        System.out.println(jestPost.post(url, requestMap));
    }

}
