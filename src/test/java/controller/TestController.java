package controller;

import entity.TestEntity;
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

    @ResponseBody
    @RequestMapping("/hi")
    public String validate(@RequestBody TestEntity testEntity) {

        return "success";
    }
}
