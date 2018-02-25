package online.dinghuiye.common.entity;

import java.io.File;

/**
 * @author Strangeen on 2018/02/24
 */
public abstract class MailAttachment {

    protected String id;
    protected File attchFile;

    public MailAttachment(String id, File attchFile) {
        this.id = id;
        this.attchFile = attchFile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public File getAttchFile() {
        return attchFile;
    }

    public void setAttchFile(File attchFile) {
        this.attchFile = attchFile;
    }

    public abstract String getShowText();
}
