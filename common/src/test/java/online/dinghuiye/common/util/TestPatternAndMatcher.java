package online.dinghuiye.common.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Strangeen on 2018/02/25
 */
public class TestPatternAndMatcher {
    @Test
    public void testMatcherReplace() {
        String html = "<html>fffffffff<div>#[mail-attach]</div>#[mail-attach]</html>";
        Pattern pattern = Pattern.compile("#\\[mail\\-attach\\]");
        Matcher matcher = pattern.matcher(html);
        StringBuffer sb = new StringBuffer();
        int idx = 0;
        while (matcher.find()) {
            if (idx == 0)
                matcher.appendReplacement(sb, "idx1");
            if (idx == 1)
                matcher.appendReplacement(sb, "idx2");
            idx ++;
        }
        Assert.assertEquals("<html>fffffffff<div>idx1</div>idx2</html>", matcher.appendTail(sb).toString());
    }

    @Test
    public void testMatcherGroup() {
        Pattern pattern = Pattern.compile("^(.*) (.*)$");
        Matcher matcher = pattern.matcher("abc def");
        matcher.find();
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
    }
}
