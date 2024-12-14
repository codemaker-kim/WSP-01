package org.project.smumap.user.service;

import org.project.smumap.user.dto.UserInfoRequest;

public interface UserService {

    // 사용자 저장
    Long UserSave(UserInfoRequest request);
}
