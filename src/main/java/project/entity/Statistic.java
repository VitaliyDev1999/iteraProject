package project.entity;

import java.util.List;

public class Statistic {
    private Integer value;
    private Integer count;
    private Integer number;

    public Statistic() {
    }

    public Statistic(Integer value, Integer count, Integer number) {
        this.value = value;
        this.count = count;
        this.number = number;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
