package kyun.framework;

import org.springframework.stereotype.Component;

@Component
public class HelloImpl implements Hello {

    @Override
    public void sayHello(String message) {
        System.out.println("Hello " + message);
    }

}
