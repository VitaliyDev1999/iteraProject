package algorithm;

enum color {GREEN, RED, BLACK}

public class NumberClass {

    private int number;

    private double startAngle;

    private double endAngle;

    private color color;

    public algorithm.color getColor() {return color;}

    public void setColor(algorithm.color color) {this.color = color;}

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getStartAngle() {
        return startAngle;
    }

    public void setStartAngle(double startAngle) {
        this.startAngle = startAngle;
    }

    public double getEndAngle() {
        return endAngle;
    }

    public void setEndAngle(double endAngle) {
        this.endAngle = endAngle;
    }

    public NumberClass(int number, color color, double startAngle, double endAngle) {
        this.number = number;
        this.startAngle = startAngle;
        this.endAngle = endAngle;
        this.color = color;
    }
}
