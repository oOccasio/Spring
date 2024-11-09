package dgu.choco_express.service.user;

import dgu.choco_express.domain.user.User;
import dgu.choco_express.exception.CommonException;
import dgu.choco_express.exception.UserErrorCode;
import dgu.choco_express.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRetriever {
    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> CommonException.type(UserErrorCode.NOT_FOUND_USER));
    }
}
