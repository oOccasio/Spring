package dgu.choco_express.domain.choco;

import dgu.choco_express.domain.box.Box;
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
@Table(name = "choco")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Choco extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private Short type;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "contents")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;

    @Builder
    public Choco(
            final Short type,
            final String nickname,
            final String contents,
            final Box box
    ) {
        this.type = type;
        this.nickname = nickname;
        this.contents = contents;
        this.box = box;
    }

    public static Choco from(
            final Short type,
            final String nickname,
            final String contents,
            final Box box
    ) {
        return Choco.builder()
                .type(type)
                .nickname(nickname)
                .contents(contents)
                .box(box)
                .build();
    }
}
