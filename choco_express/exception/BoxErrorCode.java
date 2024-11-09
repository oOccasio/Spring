package dgu.choco_express.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BoxErrorCode implements ErrorCode {
    INVALID_BOX_TYPE(HttpStatus.BAD_REQUEST, "BOX_001", "박스 타입이 유효하지 않습니다."),
    NOT_FOUND_BOX(HttpStatus.NOT_FOUND, "BOX_002", "해당 박스가 존재하지 않습니다."),
    MISMATCH_USER_AND_BOX_ID(HttpStatus.BAD_REQUEST, "BOX_003", "해당 박스와 로그인 한 사용자가 일치하지 않습니다."),
    YET_USER_HAS_BOX(HttpStatus.NOT_FOUND, "BOX_004", "해당 유저는 박스가 없습니다."),
    INVALID_BOX_NAME(HttpStatus.BAD_REQUEST, "BOX_005", "박스 이름이 비어있습니다.")
    ;

    private final HttpStatus status;
    private final String errorCode;
    private final String message;
}
