package dgu.choco_express.domain.box;

import dgu.choco_express.domain.global.BaseTimeEntity;
import dgu.choco_express.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@Table(name = "boxes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Box extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private Short type;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id")
    private User user;

    @Builder
    private Box(
            final String name,
            final Short type,
            final User user
    ) {
        this.name = name;
        this.type = type;
        this.user = user;
    }

    public static Box from(
            final String name,
            final Short type,
            final User user
    ) {
        return Box.builder()
                  .name(name)
                  .type(type)
                  .user(user)
                  .build();
    }

    public void updateBox(
            final String name,
            final Short type
    ) {
        this.name = name;
        this.type = type;
    }
}
