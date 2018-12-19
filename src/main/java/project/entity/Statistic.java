package project.entity;

import java.util.List;

public class Statistic {
    private Integer value;
    private Integer count;
    private Double percent;

    public Statistic() {
    }

    public Statistic(Integer value, Integer count, Double number) {
        this.value = value;
        this.count = count;
        this.percent = number;
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

    public Double getNumber() {
        return percent;
    }

    public void setNumber(Double number) {
        this.percent = number;
    }

    public void calculatePercent(Integer generalNumber){
        percent = count.doubleValue() / generalNumber.doubleValue();
    }

}
