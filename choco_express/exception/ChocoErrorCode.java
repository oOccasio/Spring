package dgu.choco_express.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ChocoErrorCode implements ErrorCode {
    INVALID_CHOCO_TYPE(HttpStatus.BAD_REQUEST, "CHOCO_001", "초코 타입이 유효하지 않습니다."),
    NOT_FOUND_CHOCO(HttpStatus.NOT_FOUND, "CHOCO_002", "해당 초코가 존재하지 않습니다."),
    INVALID_CHOCO_NAME(HttpStatus.BAD_REQUEST, "CHOCO_003", "초코 작성자 이름이 비어있습니다."),
    CANT_CHOCO_RECURSIVE(HttpStatus.BAD_REQUEST, "CHOCO_004", "자기 자신에게 초코를 보낼 수 없습니다."),
    INVALID_PAGE_CHOCO(HttpStatus.BAD_REQUEST, "CHOCO_005", "유효하지 않은 페이지 넘버입니다."),
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
