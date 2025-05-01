package tosyncode.library_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tosyncode.library_system.dto.BorrowRecordResponseDto;
import tosyncode.library_system.dto.BorrowRequestDto;
import tosyncode.library_system.dto.BorrowResponseDto;
import tosyncode.library_system.dto.ReturnRequestDto;
import tosyncode.library_system.enums.BorrowStatus;
import tosyncode.library_system.service.BorrowRecordService;

import java.util.List;

@RestController
@RequestMapping("api/v1/borrowRecord")
public class BorrowRecordController {

    private final BorrowRecordService borrowRecordService;

    @Autowired
    public BorrowRecordController(BorrowRecordService borrowRecordService) {
        this.borrowRecordService = borrowRecordService;
    }

    @PostMapping("/borrow")
    public ResponseEntity<BorrowResponseDto> borrow(@RequestBody BorrowRequestDto borrowRequestDto) {
        return borrowRecordService.borrowBook(borrowRequestDto);
    }

    @PostMapping("/return")
    public ResponseEntity<BorrowResponseDto> returnBook(@RequestBody ReturnRequestDto request) {
        return borrowRecordService.returnBook(request);
    }

    @GetMapping("/borrow-records")
    public ResponseEntity<List<BorrowRecordResponseDto>> history(@RequestParam Long userId,
                                                                 @RequestParam(required = false) BorrowStatus status) {
        return borrowRecordService.getBorrowHistory(userId, status);
    }






}
