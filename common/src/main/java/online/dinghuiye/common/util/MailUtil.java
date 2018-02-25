package online.dinghuiye.common.util;

import online.dinghuiye.common.entity.MailAttachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Strangeen on 2018/02/24
 */
@Component
public class MailUtil {

    private Logger logger = LoggerFactory.getLogger(MailUtil.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    public void sendMail(String[] toMail, String title, String content, MailAttachment... attachments) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(fromMail);
            helper.setTo(toMail);
            helper.setSubject(title);
            if (attachments != null && attachments.length > 0) {
                helper.setText(parseContent(content, attachments), true);
                for (MailAttachment attach : attachments) {
                    helper.addInline(attach.getId(), attach.getAttchFile());
                }
            } else {
                helper.setText(content);
            }
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            logger.error("send mail error", e);
            throw new RuntimeException(e);
        }
    }

    private String parseContent(String content, MailAttachment... attachements) {
        Pattern pattern = Pattern.compile("#\\[mail\\-attach\\]");
        Matcher matcher = pattern.matcher(content);
        StringBuffer sb = new StringBuffer();
        int idx = 0;
        while (matcher.find()) {
            matcher.appendReplacement(sb, attachements[idx].getShowText());
            idx ++;
        }
        return matcher.appendTail(sb).toString();
    }
}
