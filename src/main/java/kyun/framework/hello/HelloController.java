package kyun.framework.hello;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Resource
    Hello hello;

    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("message", "Hello World!");
        return "hello";
    }

    @RequestMapping("/hello/sayHello")
    @ResponseBody
    public String sayHello(String message) {
        return hello.sayHello(message);
    }
}