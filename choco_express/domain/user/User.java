package dgu.choco_express.domain.user;


import dgu.choco_express.domain.global.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "serial_id", nullable = false, unique = true, updatable = false)
    private String serialId;

    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name = "provider", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private EProvider provider;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private ERole role;

    @Builder
    private User(
            final String serialId,
            final String name,
            final ERole role,
            final EProvider provider
    ) {
        this.serialId = serialId;
        this.name = name;
        this.role = role;
        this.provider = provider;
    }

    public static User from(
            final String serialId,
            final String name,
            final ERole role,
            final EProvider provider
    ) {
        return User.builder()
                   .serialId(serialId)
                   .name(name)
                   .role(role)
                   .provider(provider)
                   .build();
    }
}
