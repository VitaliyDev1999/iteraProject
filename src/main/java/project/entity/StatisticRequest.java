package project.entity;

import java.util.List;

public class StatisticRequest {

    private List<Integer> values;
    private List<Integer> range;
    private Integer number;

    public StatisticRequest() {
    }

    public StatisticRequest(List<Integer> values, List<Integer> range, Integer number) {
        this.values = values;
        this.range = range;
        this.number = number;
    }

    public List<Integer> getValues() {
        return values;
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }

    public List<Integer> getRange() {
        return range;
    }

    public void setRange(List<Integer> range) {
        this.range = range;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
