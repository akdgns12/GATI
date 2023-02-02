package com.family.gati.security.oauth.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter @Setter
@ToString
// OAuth2UserInfo Mapping 과정
// Provider마다 다른 정보 return하기 때문에 이를 다르게 Mapping 해야함
public abstract class OAuth2UserInfo { 
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();
}
