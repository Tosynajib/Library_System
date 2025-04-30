package tosyncode.library_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tosyncode.library_system.entities.BorrowRecord;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

}
