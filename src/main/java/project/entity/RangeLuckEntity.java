package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class RangeLuckEntity {

    private String rangeInput;
    private String betInput;

    @JsonIgnore
    private  List<Integer> range;

    @JsonIgnore
    private  List<Integer>  bet;

    public RangeLuckEntity() {}

    public RangeLuckEntity(String rangeInput, String betInput) {
        this.rangeInput = rangeInput;
        this.betInput = betInput;
    }

    public  List<Integer> getRange() {
        return range;
    }

    public  List<Integer>  getBet() {
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

    public void setRange() {
        this.range = parseRange(rangeInput);
    }

    public void setBet() {
        this.bet = parseRange(betInput);
    }

    private  List<Integer>  parseRange(String rangeString){
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
                    i++;
                    int second = 0;
                    while(i < rangeChars.length && rangeChars[i] >= '0' && rangeChars[i] <= '9'){
                        second = second * 10 + (rangeChars[i] - 48);
                        i++;
                    }
                    while (first <= second){
                        range.add(first);
                        first++;
                    }
                }
            }

        }
        return range;
    }
}
