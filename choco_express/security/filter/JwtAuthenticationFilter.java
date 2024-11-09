package dgu.choco_express.security.filter;

import dgu.choco_express.constant.Constants;
import dgu.choco_express.domain.user.ERole;
import dgu.choco_express.exception.CommonException;
import dgu.choco_express.exception.GlobalErrorCode;
import dgu.choco_express.security.info.JwtUserInfo;
import dgu.choco_express.security.provider.JwtAuthenticationManager;
import dgu.choco_express.util.HeaderUtil;
import dgu.choco_express.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final JwtAuthenticationManager jwtAuthenticationManager;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException{
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();

        if (requestURI.matches("/api/box/\\d+") && "GET".equalsIgnoreCase(requestMethod))
            return true;
        return Constants.NO_NEED_AUTH.contains(request.getRequestURI());
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = HeaderUtil.refineHeader(request, Constants.PREFIX_AUTH, Constants.PREFIX_BEARER)
                .orElseThrow(() -> CommonException.type(GlobalErrorCode.INVALID_HEADER_VALUE));

        Claims claims = jwtUtil.validateToken(token);
        log.info("claim: getUserId() = {}", claims.get(Constants.CLAIM_USER_ID, Long.class));

        // 클레임에서 사용자 정보 추출
        JwtUserInfo jwtUserInfo = new JwtUserInfo(
                claims.get(Constants.CLAIM_USER_ID, Long.class),
                ERole.valueOf(claims.get(Constants.CLAIM_USER_ROLE, String.class))
        );

        // 인증 받지 않은 인증용 객체 생성
        UsernamePasswordAuthenticationToken unAuthenticatedToken =
                new UsernamePasswordAuthenticationToken(jwtUserInfo, null, null);

        // 인증 받은 후의 인증 객체 생성
        UsernamePasswordAuthenticationToken authenticatedToken =
                (UsernamePasswordAuthenticationToken) jwtAuthenticationManager.authenticate(unAuthenticatedToken);
        log.info("인증 성공");

        // 사용자의 IP 등 세부 정보 인증 정보에 추가
        authenticatedToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authenticatedToken);
        SecurityContextHolder.setContext(securityContext);

        filterChain.doFilter(request, response);
    }
}