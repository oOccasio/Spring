package dgu.choco_express.security.handler.logout;

import dgu.choco_express.constant.Constants;
import dgu.choco_express.exception.CommonException;
import dgu.choco_express.exception.GlobalErrorCode;
import dgu.choco_express.service.jwt.JwtService;
import dgu.choco_express.util.HeaderUtil;
import dgu.choco_express.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomLogoutProcessHandler implements LogoutHandler {
    private final JwtService jwtTokenService;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication == null) {
            throw CommonException.type(GlobalErrorCode.UNAUTHORIZED);
        }

        String accessToken = HeaderUtil.refineHeader(request, Constants.PREFIX_AUTH, Constants.PREFIX_BEARER)
                .orElseThrow(() -> CommonException.type(GlobalErrorCode.INVALID_HEADER_VALUE));

        Claims claims = jwtUtil.validateToken(accessToken);
        jwtTokenService.deleteRefreshToken(claims.get(Constants.CLAIM_USER_ID, Long.class));
    }
}
