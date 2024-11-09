package dgu.choco_express.constant;

import java.util.List;

public class Constants {
    public static String CLAIM_USER_ID = "uuid";
    public static String CLAIM_USER_ROLE = "role";
    public static String PREFIX_BEARER = "Bearer ";
    public static String PREFIX_AUTH = "authorization";
    public static String ACCESS_COOKIE_NAME = "access_token";
    public static String REFRESH_COOKIE_NAME = "refresh_token";
    public static List<String> NO_NEED_AUTH = List.of(
            "/api/health-check",
            "/api/auth/sign-up",
            "/api/auth/sign-in",
            "/oauth2/authorization/kakao"
    );
}
