package online.dinghuiye.mantisshrimp.ms.controller;

import online.dinghuiye.common.pojo.MsAccountEntity;
import online.dinghuiye.service.MsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Strangeen on 2018/02/20
 */
@Controller
@RequestMapping(value = "/account", method = RequestMethod.GET)
public class AccountController {

    @Autowired
    private MsAccountService accountService;

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("accs", accountService.findAll());
        return "ms/account/account";
    }

    @RequestMapping("/create.ms")
    public String create(String id, Model model) {
        return "ms/account/save";
    }

    @RequestMapping("/update.ms")
    public String update(String id, Model model) {
        model.addAttribute("acc", accountService.findById(Long.valueOf(id)));
        return "ms/account/save";
    }

    @RequestMapping(value = "/doSave.ms", method = RequestMethod.POST)
    public String doSave(MsAccountEntity account) {
        accountService.save(account);
        return "redirect:/account";
    }

    @RequestMapping(value = "/doUpdate.ms", method = RequestMethod.POST)
    public String update(MsAccountEntity account) {
        accountService.update(account);
        return "redirect:/account";
    }

    @RequestMapping("/doDelete.ms")
    public String doDelete(String id, Model model) {
        accountService.delete(Long.valueOf(id));
        return "redirect:/account";
    }
}
