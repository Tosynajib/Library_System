package tosyncode.library_system.service;

import org.springframework.http.ResponseEntity;
import tosyncode.library_system.dto.UserRequestDto;
import tosyncode.library_system.dto.UserResponseDto;

public interface UserService {
    ResponseEntity<UserResponseDto> registerUser(UserRequestDto userRequest);
}
