package kyun.framework.hello;

import org.springframework.stereotype.Component;

@Component
public class HelloImpl implements Hello {

    @Override
    public String sayHello(String message) {
        return "Hello " + message;
    }

}
