package cn.com.datu.entity;

import java.util.Date;
import java.util.List;

/**
 * @author yangheng
 * @Classname TestEntity
 * @Description TODO
 * @Date 2019/10/27 17:20
 * @group smart video north
 */
public class TestEntity {
    private String id;
    private Date date;
    private List list;
    private int age;
    private TestEntity testEntity;

    public TestEntity getTestEntity() {
        return testEntity;
    }

    public void setTestEntity(TestEntity testEntity) {
        this.testEntity = testEntity;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
