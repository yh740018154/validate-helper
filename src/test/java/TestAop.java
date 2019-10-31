import org.junit.Test;

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
