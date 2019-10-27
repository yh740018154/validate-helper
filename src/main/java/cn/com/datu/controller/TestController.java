package cn.com.datu.controller;

import cn.com.datu.entity.TestEntity;
import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.aop.annotation.ValidateGroup;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangheng
 * @Classname TestController
 * @Description TODO
 * @Date 2019/10/27 17:15
 * @group smart video north
 */
@RestController
public class TestController {

    @ValidateGroup(validateFields = {
            @ValidateField(index = 0, notNull = true,maxLength = 3)
    })
    @ResponseBody
    @RequestMapping("/hi")
    public String validate(TestEntity testEntity) {

        return "success";
    }
}
