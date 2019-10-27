package cn.com.datu.vo;

import java.util.Date;
import java.util.List;

/**
 * @author yangheng
 * @Classname TestVo
 * @Description TODO
 * @Date 2019/10/27 16:01
 * @group smart video north
 */
public class TestVo {

    private String id;
    private String name;
    private Date birthday;
    private List list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
