package dgu.choco_express.security.info.factory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter
@RequiredArgsConstructor
public abstract class Oauth2UserInfo {
    protected final Map<String, Object> attributes;
    public abstract String getId();
    public abstract String getNickname();
}
