package test.kafka.test;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * author: suzheng
 * version: v.1.0
 * package: test.kafka.test
 * company: SiBu
 * create_date: 2017/04/10
 * description :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application.xml" })
public class BaseTest {
    public Logger logger = Logger.getLogger(BaseTest.class);
}
