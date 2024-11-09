package dgu.choco_express.dto.common;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import dgu.choco_express.exception.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static dgu.choco_express.exception.GlobalErrorCode.SUCCESS;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"errorCode", "message", "result"})
public class ResponseDto<T> {
    private final String errorCode;
    private final String message;
    private T result;

    public static <T> ResponseDto<T> success(final T data) {
        return new ResponseDto<>(null, "SUCCESS", data);
    }

    public static <T> ResponseDto<T> fail(ErrorResponse errorResponse) {
        return new ResponseDto<>(errorResponse.getErrorCode(), errorResponse.getMessage(), null);
    }

    public ResponseDto(T result) {
        this.errorCode = SUCCESS.getErrorCode();
        this.message = SUCCESS.getMessage();
        this.result = result;
    }
}