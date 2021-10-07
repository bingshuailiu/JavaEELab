package cn.edu.xmu.restfuldemo.util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author RenjieZheng
 * 自定义异常类
 */
public class GoodsNameNullException extends Exception{

    private static final Log logger = LogFactory.getLog(JacksonUtil.class);

    public GoodsNameNullException() {
        super();
    }
    public GoodsNameNullException(String s) {
        super(s);
    }
}