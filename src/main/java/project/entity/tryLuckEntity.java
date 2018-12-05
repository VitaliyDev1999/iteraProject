package project.entity;

public class tryLuckEntity {

    private Type type;
    private int[] values;
    private double degree;

    public tryLuckEntity(){}

    public tryLuckEntity(Type type, int[] values, double degree) {
        this.type = type;
        this.values = values;
        this.degree = degree;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int[] getValues() {
        return values;
    }

    public void setValues(int[] values) {
        this.values = values;
    }

    public double getDegree() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }
}
