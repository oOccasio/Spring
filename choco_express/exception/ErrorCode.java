package dgu.choco_express.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    HttpStatus getStatus();
    String getErrorCode();
    String getMessage();
}
