package project.dto;


import org.springframework.stereotype.Component;

@Component
public class ParamsDTO {
    private Integer angle;
    private Integer numberOfCell;

    public ParamsDTO() {
    }

    public ParamsDTO(Integer angle, Integer numberOfCell) {
        this.angle = angle;
        this.numberOfCell = numberOfCell;
    }

    public Integer getAngle() {
        return angle;
    }

    public void setAngle(Integer angle) {
        this.angle = angle;
    }

    public Integer getNumberOfCell() {
        return numberOfCell;
    }

    public void setNumberOfCell(Integer numberOfCell) {
        this.numberOfCell = numberOfCell;
    }
}
