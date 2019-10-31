import cn.com.datu.ValidateApplication;
import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.common.ResponseMsg;
import cn.com.datu.validate.core.validation.AbstractObjectValidator;
import cn.com.datu.validate.core.validation.FieldValidation;
import cn.com.datu.validate.core.validation.impl.StringValidator;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yangheng
 * @Classname TestAop
 * @Description TODO
 * @Date 2019/10/27 17:06
 * @group smart video north
 */

public class TestAop {

    @Test
    public void test(){

        Object a=1;
        System.out.println("a.getClass().getName() = " + a.getClass().getName());
        System.out.println("a.getClass().getSimpleName() = " + a.getClass().getSimpleName());
        System.out.println("a.getClass().getCanonicalName() = " + a.getClass().getCanonicalName());
        System.out.println("a.getClass().getTypeName() = " + a.getClass().getTypeName());


    }

}
