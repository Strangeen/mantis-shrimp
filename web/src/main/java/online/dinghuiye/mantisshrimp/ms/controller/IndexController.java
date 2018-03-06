package online.dinghuiye.mantisshrimp.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Strangeen on 2018/02/03
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index() {
        return "ms/index";
    }
}