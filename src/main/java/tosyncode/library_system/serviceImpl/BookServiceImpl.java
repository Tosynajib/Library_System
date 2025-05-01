package tosyncode.library_system.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tosyncode.library_system.dto.BookRequestDto;
import tosyncode.library_system.dto.BookResponseDto;
import tosyncode.library_system.entities.Book;
import tosyncode.library_system.repository.BookRepository;
import tosyncode.library_system.service.BookService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @Override
    public ResponseEntity<BookResponseDto> addBook(BookRequestDto bookRequest) {
        Book book = new Book(bookRequest.getTitle(), bookRequest.getAuthor(), bookRequest.getTotalCopies());
        bookRepository.save(book);
        BookResponseDto response = new BookResponseDto(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<BookResponseDto>> getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<BookResponseDto> bookDto = bookRepository.findAll(pageable)
                .stream()
                .map(BookResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(bookDto);
    }

}
