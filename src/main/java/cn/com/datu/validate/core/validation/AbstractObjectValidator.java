package cn.com.datu.validate.core.validation;

/***
 * 模板类
 * @param <T>
 */
public abstract class AbstractObjectValidator {


    protected abstract String validate();

    protected abstract void cast();

    /**
     * 模板方法
     * @return
     */
    public final String service() {
        cast();
        return validate();
    }
}
