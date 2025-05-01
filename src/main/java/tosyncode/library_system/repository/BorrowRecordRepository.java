package tosyncode.library_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tosyncode.library_system.entities.BorrowRecord;
import tosyncode.library_system.enums.BorrowStatus;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    boolean existsByUserIdAndBookIdAndStatus(Long userId, Long bookId, BorrowStatus borrowStatus);

    List<BorrowRecord> findByUserIdAndStatus(Long userId, BorrowStatus status);

    List<BorrowRecord> findByUserId(Long userId);
}
