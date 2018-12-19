package project.entity;

import project.algorithm.Color;

public class HistoryDto {

    private String bet;
    private String range;
    private int choice;
    private String game;
    private double resultDegree;
    private Color color;

    public HistoryDto(){}

    public HistoryDto(String bet, String range, int choice, String game, Color color) {
        this.bet = bet;
        this.range = range;
        this.choice = choice;
        this.game = game;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getBet() {
        return bet;
    }

    public void setBet(String bet) {
        this.bet = bet;
    }

    public String getRange() { return range; }

    public void setRange(String range) { this.range = range; }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public String isGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public double getResultDegree() {
        return resultDegree;
    }

    public void setResultDegree(double resultDegree) {
        this.resultDegree = resultDegree;
    }
}
