package project.exception;

public class ErrorResponseDto {

    private int status;
    private String message;
    private long timeStamp;
    private String exception;

    public ErrorResponseDto() {
    }

    public ErrorResponseDto(int status, String message, long timeStamp, String exception) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
        this.exception = exception;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
