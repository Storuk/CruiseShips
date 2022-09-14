package tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
public class Dates extends TagSupport{
    private String str1;
    private String str2;

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            out.println(str1.concat(" - ".concat(str2)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return SKIP_BODY;
    }
}