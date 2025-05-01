package tosyncode.library_system.service;

import org.springframework.http.ResponseEntity;
import tosyncode.library_system.dto.BorrowRecordResponseDto;
import tosyncode.library_system.dto.BorrowRequestDto;
import tosyncode.library_system.dto.BorrowResponseDto;
import tosyncode.library_system.dto.ReturnRequestDto;
import tosyncode.library_system.enums.BorrowStatus;

import java.util.List;

public interface BorrowRecordService {

    ResponseEntity<BorrowResponseDto> borrowBook(BorrowRequestDto borrowRequestDto);

    ResponseEntity<BorrowResponseDto> returnBook(ReturnRequestDto request);

    ResponseEntity<List<BorrowRecordResponseDto>> getBorrowHistory(Long userId, BorrowStatus status);
}
