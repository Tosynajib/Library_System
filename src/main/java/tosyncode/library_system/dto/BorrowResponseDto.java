package tosyncode.library_system.dto;

public class BorrowResponseDto {
    private String message;

    public BorrowResponseDto(String message) {
        this.message = message;
    }

    //Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
