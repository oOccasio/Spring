package dgu.choco_express.domain.refreshToken;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "token", timeToLive = 60 * 60 * 24 * 14)
public class RefreshToken {
    @Id
    private Long id;

    @Indexed
    private String token;

    @Builder
    public RefreshToken(Long id, String token) {
        this.id = id;
        this.token = token;
    }

    public static RefreshToken issueRefreshToken(final Long userId, final String refreshToken) {
        return new RefreshToken(userId, refreshToken);
    }
}