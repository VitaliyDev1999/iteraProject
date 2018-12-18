package project.entity;

import java.util.ArrayList;
import java.util.List;

public class RangeLuckEntity {

    private String rangeInput, betInput;
    private Integer[] range, bet;

    public RangeLuckEntity() {}

    public RangeLuckEntity(String rangeInput, String betInput) {
        this.rangeInput = rangeInput;
        this.betInput = betInput;
        this.range = parseRange(rangeInput);
        this.bet = parseRange(betInput);
    }

    public Integer[] getRange() {
        return range;
    }

    public Integer[] getBet() {
        return bet;
    }

    public String getRangeInput() {
        return rangeInput;
    }

    public void setRangeInput(String rangeInput) {
        this.rangeInput = rangeInput;
    }

    public String getBetInput() {
        return betInput;
    }

    public void setBetInput(String betInput) {
        this.betInput = betInput;
    }

    private Integer[] parseRange(String rangeString){
        char[] rangeChars = rangeString.toCharArray();
        List<Integer> range = new ArrayList<>();
        for (int i = 0; i < rangeChars.length; i++) {
            int first = 0;
            while(i < rangeChars.length && rangeChars[i] >= '0' && rangeChars[i] <= '9'){
                first = first * 10 + (rangeChars[i] - 48);
                i++;
            }
            if (i < rangeChars.length) {
                if (rangeChars[i] == ',')
                    range.add(first);
                else{
                    int second = 0;
                    while(i < rangeChars.length && rangeChars[i] >= '0' && rangeChars[i] <= '9'){
                        second = second * 10 + (rangeChars[i] - 48);
                        i++;
                    }
                    while (first < second){
                        range.add(first);
                        first++;
                    }
                }
            }

        }
        return (Integer[])range.toArray();
    }
}
