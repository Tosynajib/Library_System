package tosyncode.library_system.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tosyncode.library_system.dto.UserRequestDto;
import tosyncode.library_system.dto.UserResponseDto;
import tosyncode.library_system.entities.User;
import tosyncode.library_system.repository.UserRepository;
import tosyncode.library_system.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<UserResponseDto> registerUser(UserRequestDto userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new RuntimeException("User with email already exists");
        }

        User user = new User(userRequest.getName(), userRequest.getEmail());
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponseDto(user));
    }
}
