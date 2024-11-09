package dgu.choco_express.service.jwt;

import dgu.choco_express.domain.refreshToken.RefreshToken;
import dgu.choco_express.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void updateRefreshToken(Long userId, String refreshToken) {
        refreshTokenRepository.findById(userId)
                .ifPresentOrElse(
                        existingToken -> {
                            refreshTokenRepository.deleteById(userId);
                            refreshTokenRepository.save(RefreshToken.issueRefreshToken(userId, refreshToken));
                        },
                        () -> refreshTokenRepository.save(RefreshToken.issueRefreshToken(userId, refreshToken))
                );
    }

    @Transactional
    public void deleteRefreshToken(Long userId) {
        refreshTokenRepository.deleteById(userId);
    }
}
