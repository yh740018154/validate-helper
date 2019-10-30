package cn.com.datu.validate.core.validation;

/***
 * 模板类
 * @param <T>
 */
public abstract class AbstractObjectValidator<T> {

    protected T t;

    protected abstract String validate(T t);

    protected abstract T cast(T t);

    /**
     * 模板方法
     * @return
     */
    public final String service() {
        T cast = cast(t);
        return validate(t);
    }
}
