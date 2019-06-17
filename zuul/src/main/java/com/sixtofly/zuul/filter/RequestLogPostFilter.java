package com.sixtofly.zuul.filter;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sixtofly.zuul.log.RequestLog;
import com.sixtofly.zuul.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author Xie Yuan Bing
 * @date 2019-06-17 17:50
 **/
public class RequestLogPostFilter extends ZuulFilter {

    @Autowired
    private RequestLogRepository requestLogRepository;

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }


    private static final String CONTENT_TYPE = "Content-Type";
    private static final String JSON_SIGN = "json";
    private static final String XML_SIGN = "xml";

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();
        RequestLog requestLog = new RequestLog();
        requestLog.initRequestInfo(request, "alibaba");
        String result = IoUtil.read(context.getResponseDataStream(), Charset.forName("UTF-8"));
        context.setResponseDataStream(new ByteArrayInputStream(result.getBytes()));
        context.set("responseText", result);
        context.set("Content-Length", context.getOriginContentLength());
        context.set("httpStatus", context.getResponse().getStatus());
        Date startDate = new Date(Long.valueOf(context.get("startTime").toString()));
        requestLog.setOccurrenceTime(startDate);
        requestLog.setResponseText("" + context.get("responseText"));
        requestLog.setResponseTime(System.currentTimeMillis() - startDate.getTime());
        requestLog.setUrl(context.get("requestURI") + "");
        requestLog.setThrowable("");
        requestLog.setHttpStatus((Integer) context.get("httpStatus"));
        if (context.get(CONTENT_TYPE) != null) {
            requestLog.setResponseContentLength((Long) context.get("Content-Length"));
        } else {
            requestLog.setResponseContentLength(requestLog.getResponseText().getBytes().length);
        }
        requestLog.setId(IdUtil.fastSimpleUUID());
        requestLogRepository.save(requestLog);
        return null;
    }
}
