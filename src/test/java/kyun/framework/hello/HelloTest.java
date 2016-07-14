package kyun.framework.hello;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:META-INF/spring/app-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloTest {

    @Resource
    Hello hello;

    @Test
    public void sayHello() {
        String message = hello.sayHello("World");
        assertEquals("Hello World", message);
    }
}
