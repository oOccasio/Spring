package dgu.choco_express.repository;

import dgu.choco_express.domain.refreshToken.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
}
