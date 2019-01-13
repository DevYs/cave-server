package devy.cave.server.interceptor;

import devy.cave.server.api.resp.ApiStatusCode;
import devy.cave.server.db.service.ApiAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ApiAuthInterceptor extends HandlerInterceptorAdapter {

    private final Logger logger = LoggerFactory.getLogger(ApiAuthInterceptor.class);

    @Autowired
    private ApiAuthService apiAuthService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authKey = request.getHeader("authKey");

        logger.info("authKey is " + authKey);

        boolean auth = apiAuthService.auth(authKey);
        if(!auth) {
            response.sendError(ApiStatusCode.UNAUTHORIZED, ApiStatusCode.UNAUTHORIZED_MSG);
        }

        return super.preHandle(request, response, handler);
    }

}