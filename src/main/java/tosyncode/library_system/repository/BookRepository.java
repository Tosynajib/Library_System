package tosyncode.library_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tosyncode.library_system.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long> {


}
