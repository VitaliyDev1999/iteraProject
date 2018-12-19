package project.entity;

import java.util.List;

public class StatisticRequest {

    private List<Statistic> values;
    private String range;
    private Integer count;

    public StatisticRequest() {
    }

    public StatisticRequest(List<Statistic> values, String range, Integer number) {
        this.values = values;
        this.range = range;
        this.count = number;
    }

    public List<Statistic> getValues() {
        return values;
    }

    public void setValues(List<Statistic> values) {
        this.values = values;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void fillStatistic(int[] range){
        for (int i = 0; i < range.length; i++) {
            values.add(new Statistic(range[i], 0, 0.0));
        }
    }

    public void updateStatistic(int number){
        count++;
        for (Statistic stat:
             values) {
            if (stat.getValue().compareTo(number) == 0)
                stat.setNumber(stat.getNumber() + 1);
            stat.calculatePercent(count);
        }
    }

}
