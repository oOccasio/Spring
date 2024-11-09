package dgu.choco_express.util;

import dgu.choco_express.exception.CommonException;
import dgu.choco_express.exception.GlobalErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

import java.util.Optional;

public class HeaderUtil {

    public static Optional<String> refineHeader(
            HttpServletRequest request,
            String headerName,
            String prefix
    ) {
        String headerValue = request.getHeader(headerName);
        if (!StringUtils.hasText(headerValue) || !headerValue.startsWith(prefix))
            throw CommonException.type(GlobalErrorCode.INVALID_HEADER_VALUE);
        return Optional.of(headerValue.substring(prefix.length()));
    }
}
