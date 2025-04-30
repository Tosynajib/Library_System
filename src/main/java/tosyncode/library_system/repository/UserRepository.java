package tosyncode.library_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tosyncode.library_system.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
