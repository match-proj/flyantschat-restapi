package com.match.flyantschat.context.configuration.exception;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.match.common.exception.BusinessException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Map;

/**
 * @author zhangchao
 * @date 2017/12/12 0012 15:43
 * 异常传递， 微服务中抛出的异常restful始终是以json传输的，所以需要将json转换成真正的异常信息才能走异常aop
 */
@Configuration
@Slf4j
public class FeignErrorDecoder implements ErrorDecoder {

    public static final String BUSINESS_EXCEPTION_STR = "BusinessException";

    private ErrorDecoder delegate = new Default();

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {

//        HttpHeaders responseHeaders = new HttpHeaders();
//        for (Map.Entry<String, Collection<String>> entry : response.headers().entrySet()) {
//            responseHeaders.put(entry.getKey(), new ArrayList<>(entry.getValue()));
//        }

        try {
            String body = Util.toString(response.body().asReader());
            log.info(body);
            Map<String, Object> map = mapper.readValue(body, new TypeReference<Map<String, Object>>() {
            });

            String clazzName = map.get("clazz").toString();
            //转换微服务中自定义异常
            if (StringUtils.contains(clazzName, BUSINESS_EXCEPTION_STR)) {
                return new BusinessException(map.get("resp_code").toString(), map.get("resp_msg").toString());
            } else {
                log.error(map.get("clazz").toString());
                log.error(map.get("cause").toString());
            }
        } catch (IOException e) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR + "", e.getMessage());
        }
        System.out.println(methodKey + " response: " + response);
        return delegate.decode(methodKey, response);
    }
}
