package online.dinghuiye.mantisshrimp.ms.controller;

import online.dinghuiye.common.consts.MsParam;
import online.dinghuiye.common.pojo.MsAccountEntity;
import online.dinghuiye.service.MsAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @author Strangeen on 2018/02/18
 */
@Controller
@RequestMapping("/")
public class LoginController {

    private final MsAccountService accountService;

    @Autowired
    public LoginController(MsAccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/login.ms", method = RequestMethod.GET)
    public String login() {
        return "ms/login";
    }

    @RequestMapping(value = "/doLogin.ms", method = RequestMethod.POST)
    public String doLogin(MsAccountEntity account, HttpSession session, Model model) {
        MsAccountEntity account0 = accountService.findFirstBySample(account);
        if (account0 != null) {
            account0.setPassword(null);
            session.setAttribute(MsParam.user_session_key, account0);
            return "redirect:/";
        }
        model.addAttribute("errorMsgCode", "accountNotFound");
        model.addAttribute("account", account.getAccount());
        return "ms/login";
    }

    @RequestMapping(value = "/logout.ms", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(MsParam.user_session_key);
        return "redirect:/login.ms";
    }

}
