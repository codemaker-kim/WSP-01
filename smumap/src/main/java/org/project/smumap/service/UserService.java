package org.project.smumap.service;

import org.project.smumap.dto.UserInfoRequest;

public interface UserService {

    // 사용자 저장
    Long UserSave(UserInfoRequest request);
}
