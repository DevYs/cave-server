package devy.cave.server.db.service;

import devy.cave.server.db.mapper.ApiAuthMapper;
import devy.cave.server.db.mapper.UserMapper;
import devy.cave.server.db.model.ApiAuth;
import devy.cave.server.db.model.ApiAuthKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.UUID;

@Service
public class ApiAuthService {

    private final Logger logger = LoggerFactory.getLogger(ApiAuthService.class);

    private final int EXPIRES_PLUS_DAYS = 7;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ApiAuthMapper apiAuthMapper;

    /**
     * 로그인한 사용자의 인증 정보를 저장
     * @param userId 사용자 ID
     * @return boolean
     */
    public String saveAuth(String userId) {
        String authKey = UUID.randomUUID().toString();
        String expiredDate = LocalDateTime.now().plusDays(EXPIRES_PLUS_DAYS).toString();
        ApiAuth apiAuth = new ApiAuth(authKey, userId, expiredDate);

        cleanAuth(userId);

        boolean isAdded = apiAuthMapper.add(apiAuth);
        if(!isAdded) {
            logger.info("failed add Auth !! " + apiAuth.toString());
            return null;
        }

        logger.info("added auth " + apiAuth.toString());

        return apiAuth.getAuthKey();
    }

    /**
     * 사용자의 인증 정보를 모두 삭제한다.
     * @param userId 인증 정보를 삭제할 사용자 ID
     */
    public void cleanAuth(String userId) {
        Iterator iterator = apiAuthMapper.mapByUserId().duplicates(userId).iterator();
        while(iterator.hasNext()) {
            ApiAuth apiAuth = (ApiAuth) iterator.next();

            logger.info("removed auth user " + userId);
            apiAuthMapper.mapByUserId().remove(userId);

            logger.info("remove auth " + apiAuth);
            apiAuthMapper.remove(new ApiAuthKey(apiAuth.getAuthKey()));
        }
    }

    /**
     * 인증 정보를 조회하여 인증 여부를 확인한다.
     * @param authKey 인증키
     * @return boolean
     */
    public boolean auth(String authKey) {

        // 저장된 인증 정보 조회
        Iterator iterator = apiAuthMapper.sortedMap().duplicates(new ApiAuthKey(authKey)).iterator();
        if(!iterator.hasNext()) {
            return false;
        }

        // 인증키 만료일 비교
        ApiAuth apiAuth = (ApiAuth) iterator.next();
        LocalDateTime expiredDate = LocalDateTime.parse(apiAuth.getExpiredDate());
        LocalDateTime now = LocalDateTime.now();

        boolean isBefore = now.isBefore(expiredDate);
        if(!isBefore) {
            ApiAuth removedApiAuth = (ApiAuth) apiAuthMapper.remove(new ApiAuthKey(authKey));
            logger.info("인증키가 만료되어 인증이 삭제되었습니다.");
            logger.info("removed " + removedApiAuth.toString());
            return false;
        }else {
            apiAuth.setExpiredDate(now.toString());
            apiAuthMapper.map().replace(new ApiAuthKey(authKey), apiAuth);
        }

        return true;
    }

}
