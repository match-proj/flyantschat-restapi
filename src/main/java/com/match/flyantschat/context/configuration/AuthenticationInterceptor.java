package com.match.flyantschat.context.configuration;

import com.match.common.annotation.Anonymous;
import com.match.common.context.User;
import com.match.common.context.UserContext;
import com.match.common.exception.TokenExpireException;
import com.match.flyantschat.context.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;


/**
 * zhangchao
 */
@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {


    public static final String HTTP_HEADER_AUTHORIZATION = "Authorization";


    @Autowired
    UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {

        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader(HTTP_HEADER_AUTHORIZATION);
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有Anonymous注释，有则跳过认证
        if (method.isAnnotationPresent(Anonymous.class)) {
            return true;
        }

        log.info("Authorization:{}",token);

        //检查有没有需要用户权限的注解
        // 执行认证
        if (StringUtils.isEmpty(token)) {
            throw new TokenExpireException("401","登录超时");
        }
        // 获取 token 中的 user id

        Optional<String> optional = userService.getPeopleIdByAccessToken(token);

        if(!optional.isPresent()){
            throw new TokenExpireException("401","登录超时");
        }

        User user = userService.getUserByPeopleId(optional.get());
        UserContext.setUser(user);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
