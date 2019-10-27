package entity;

import java.util.Date;
import java.util.List;

/**
 * @author yangheng
 * @Classname TestEntity
 * @Description TODO
 * @Date 2019/10/27 17:16
 * @group smart video north
 */
public class TestEntity {
    private String id;
    private Date date;
    private List list;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
