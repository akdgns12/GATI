package com.family.gati.security.oauth.user;

import com.family.gati.entity.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(AuthProvider authProvider, Map<String, Object> attributes){
        switch (authProvider){
            case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
            // 추후 kakao, naver 등등 추가
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}
