package tosyncode.library_system.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tosyncode.library_system.dto.BorrowRecordResponseDto;
import tosyncode.library_system.dto.BorrowRequestDto;
import tosyncode.library_system.dto.BorrowResponseDto;
import tosyncode.library_system.dto.ReturnRequestDto;
import tosyncode.library_system.entities.Book;
import tosyncode.library_system.entities.BorrowRecord;
import tosyncode.library_system.entities.User;
import tosyncode.library_system.enums.BorrowStatus;
import tosyncode.library_system.repository.BookRepository;
import tosyncode.library_system.repository.BorrowRecordRepository;
import tosyncode.library_system.repository.UserRepository;
import tosyncode.library_system.service.BorrowRecordService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BorrowRecordServiceImpl(BorrowRecordRepository borrowRecordRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.borrowRecordRepository = borrowRecordRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public ResponseEntity<BorrowResponseDto> borrowBook(BorrowRequestDto borrowRequestDto) {
        Book book = bookRepository.findById(borrowRequestDto.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));
        User user = userRepository.findById(borrowRequestDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        if (book.getAvailableCopies() <= 0) {
            return ResponseEntity.badRequest().body(new BorrowResponseDto("No copies available."));
        }

        if (borrowRecordRepository.existsByUserIdAndBookIdAndStatus(borrowRequestDto.getUserId(), borrowRequestDto.getBookId(), BorrowStatus.BORROWED)) {
            return ResponseEntity.badRequest().body(new BorrowResponseDto("You have already borrowed this book."));
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        BorrowRecord record = new BorrowRecord(book, user, LocalDateTime.now(), BorrowStatus.BORROWED);
        borrowRecordRepository.save(record);

        return ResponseEntity.status(HttpStatus.CREATED).body(new BorrowResponseDto("Book borrowed successfully."));
    }


    @Override
    public ResponseEntity<BorrowResponseDto> returnBook(ReturnRequestDto request) {
        BorrowRecord record = borrowRecordRepository.findById(request.getRecordId())
                .orElseThrow(() -> new RuntimeException("Borrow record not found"));

        if (record.getStatus() != BorrowStatus.BORROWED) {
            return ResponseEntity.badRequest().body(new BorrowResponseDto("Book already returned."));
        }

        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        record.setReturnDate(LocalDateTime.now());
        record.setStatus(BorrowStatus.RETURNED);
        borrowRecordRepository.save(record);

        return ResponseEntity.ok(new BorrowResponseDto("Book returned successfully."));
    }

    @Override
    public ResponseEntity<List<BorrowRecordResponseDto>> getBorrowHistory(Long userId, BorrowStatus status) {
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<BorrowRecord> records = (status != null)
                ? borrowRecordRepository.findByUserIdAndStatus(userId, status)
                : borrowRecordRepository.findByUserId(userId);

        List<BorrowRecordResponseDto> borrowResponseDto = records.stream()
                .map(record -> new BorrowRecordResponseDto(record.getBook().getTitle(), record.getStatus(), record.getBorrowDate()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(borrowResponseDto);
    }
}
