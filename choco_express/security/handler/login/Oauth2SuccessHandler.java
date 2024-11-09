package dgu.choco_express.security.handler.login;

import dgu.choco_express.dto.jwt.response.JwtDto;
import dgu.choco_express.security.info.AuthenticationResponse;
import dgu.choco_express.security.info.UserPrincipal;
import dgu.choco_express.service.jwt.JwtService;
import dgu.choco_express.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler implements AuthenticationSuccessHandler {

    @Value("${server.domain}")
    private String domain;
    private final JwtUtil jwtUtil;
    private final JwtService jwtService;

    @Override
    @Transactional
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        JwtDto jwtDto = jwtUtil.generateTokens(principal.getUserId(), principal.getRole());

        jwtService.updateRefreshToken(principal.getUserId(), jwtDto.refreshToken());

        AuthenticationResponse.makeLoginSuccessResponse(response, domain, jwtDto, jwtUtil.getRefreshExpiration());

        response.sendRedirect("https://" + domain);
    }
}
