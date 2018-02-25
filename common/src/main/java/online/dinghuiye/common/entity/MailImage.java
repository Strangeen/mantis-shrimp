package online.dinghuiye.common.entity;

import java.io.File;

/**
 * @author Strangeen on 2018/02/24
 */
public class MailImage extends MailAttachment {

    public MailImage(String id, File attchFile) {
        super(id, attchFile);
    }

    @Override
    public String getShowText() {
        return "<img src=\"cid:" + this.id + "\" >";
    }
}
