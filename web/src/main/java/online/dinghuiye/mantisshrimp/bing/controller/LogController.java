package online.dinghuiye.mantisshrimp.bing.controller;

import online.dinghuiye.bingcollection.consts.BingParam;
import online.dinghuiye.bingcollection.service.BingPullLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Strangeen on 2018/02/20
 */
@Controller
@RequestMapping(value = "/bing/log", method = RequestMethod.GET)
public class LogController {

    @Autowired
    private BingPullLogService logService;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("logs", logService.findAll(new PageRequest(0, BingParam.log_page_size, new Sort(Sort.Direction.DESC, "id"))));
        return "/ms/bing/log/log";
    }
}
