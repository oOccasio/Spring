package dgu.choco_express.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EProvider {
    KAKAO("KAKAO"),
    NAVER("NAVER"),
    GOOGLE("GOOGLE");

    private final String name;
}