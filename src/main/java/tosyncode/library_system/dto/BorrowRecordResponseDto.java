package tosyncode.library_system.dto;

import tosyncode.library_system.enums.BorrowStatus;

import java.time.LocalDateTime;

public class BorrowRecordResponseDto {
    private String bookTitle;
    private BorrowStatus status;
    private LocalDateTime borrowDate;

    public BorrowRecordResponseDto(String bookTitle, BorrowStatus status, LocalDateTime borrowDate) {
        this.bookTitle = bookTitle;
        this.status = status;
        this.borrowDate = borrowDate;
    }

    //Getters and Setters
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public BorrowStatus getStatus() {
        return status;
    }

    public void setStatus(BorrowStatus status) {
        this.status = status;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }
}
