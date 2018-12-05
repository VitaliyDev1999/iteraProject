package project.entity;

public class HistoryEntity {

    private String bet;
    private int choice;
    private boolean game;
    private double resultDegree;

    public HistoryEntity(){}

    public HistoryEntity(String bet, int choice, boolean game) {
        this.bet = bet;
        this.choice = choice;
        this.game = game;
    }

    public String getBet() {
        return bet;
    }

    public void setBet(String bet) {
        this.bet = bet;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public boolean isGame() {
        return game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    public double getResultDegree() {
        return resultDegree;
    }

    public void setResultDegree(double resultDegree) {
        this.resultDegree = resultDegree;
    }
}
