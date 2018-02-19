package online.dinghuiye.mantisshrimp.controller.ms;

import online.dinghuiye.common.pojo.MsAccountEntity;
import online.dinghuiye.mantisshrimp.consts.Consts;
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

    @RequestMapping(value = "/login.ms", method = RequestMethod.GET)
    public String login() {
        return "ms/login";
    }

    @RequestMapping(value = "/doLogin.ms", method = RequestMethod.POST)
    public String doLogin(MsAccountEntity account, HttpSession session, Model model) {
        if ("admin".equals(account.getAccount()) && "123".equals(account.getPassword())) {
            session.setAttribute(Consts.user_session_key, true);
            return "redirect:/";
        }
        model.addAttribute("errorMsgCode", "accountNotFound");
        model.addAttribute("account", account.getAccount());
        return "ms/login";
    }

    @RequestMapping(value = "/logout.ms", method = RequestMethod.GET)
    public String logout() {

        // TODO: 登出
        return "redirect:/";
    }

}
