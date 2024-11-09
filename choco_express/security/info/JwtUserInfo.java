package dgu.choco_express.security.info;


import dgu.choco_express.domain.user.ERole;

public record JwtUserInfo(Long userId, ERole role) {
}
