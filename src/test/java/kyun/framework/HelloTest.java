package kyun.framework;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:app-context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloTest {

    @Resource
    Hello hello;

    @Test
    public void sayHello() {
        hello.sayHello("World");
    }
}
