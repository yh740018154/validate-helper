package cn.com.datu.controller;

import cn.com.datu.entity.TestEntity;
import cn.com.datu.validate.core.aop.annotation.ValidateField;
import cn.com.datu.validate.core.aop.annotation.ValidateGroup;
import cn.com.datu.validate.core.common.Constant;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yangheng
 * @Classname TestController
 * @Description ：springMVC 参数校验，示例入参
 * @Date 2019/10/27 17:15
 * @group smart video north
 */
@RestController
public class TestController {

    /**
     * 入参示例：
     * 1.两个基本数据类型
     * @RequestParam String name,@RequestParam int id
     * 2.一个对象类型
     * public String validate(@RequestBody TestEntity testEntity)
     * 3.一个对象中还封装的有一个对象
     * {
     * 	"name":"111111",
     * 	"age":1111,
     * 	"testEntity":{
     * 	"name":"name2222",
     * 	"id":"22222"
     *        }
     * }
     *  @ValidateField(index = 0, notNull = true,maxLength = 19,regexPattern = Constant.IDENTITY_CARD_PATTERN,fieldName = "testEntity.name")
     */
    @ValidateGroup(validateFields = {
            @ValidateField(index = 0, notNull = true,maxLength = 19,regexPattern = Constant.IDENTITY_CARD_PATTERN,fieldName = "testEntity.name"),
            @ValidateField(index = 0,notNull = true,maxValue = 10,fieldName = "age")
    })
    @ResponseBody
    @RequestMapping("/hi")
    public String validate(@RequestBody Map map) {

        return "success";
    }
}
