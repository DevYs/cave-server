package devy.cave.server.interceptor;

import devy.cave.server.api.resp.ApiStatusCode;
import devy.cave.server.db.model.ApiAuth;
import devy.cave.server.db.service.ApiAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
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
        String userId = request.getHeader("userId");
        logger.info("authKey " + authKey);
        logger.info("userId " + userId);

        ApiAuth auth = apiAuthService.auth(authKey);
        if(auth == null) {
            response.sendError(ApiStatusCode.UNAUTHORIZED, ApiStatusCode.UNAUTHORIZED_MSG);
        }

        if(userId == null || userId.isEmpty()) {
            response.sendError(ApiStatusCode.UNAUTHORIZED, ApiStatusCode.UNAUTHORIZED_MSG);
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}