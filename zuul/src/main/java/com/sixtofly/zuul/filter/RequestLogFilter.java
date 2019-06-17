package com.sixtofly.zuul.filter;

import com.sixtofly.zuul.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xie yuan bing
 * @date 2019-06-16 16:32
 * @description
 */
@Component
public class RequestLogFilter {

    @Autowired
    private RequestLogRepository requestLogRepository;

//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        ServerHttpResponse response = exchange.getResponse();
//        DataBufferFactory dataBufferFactory = response.bufferFactory();
//        // TODO 获取响应数据
//        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(response) {
//            @Override
//            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
//                if (body instanceof Flux) {
//                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
//                    return super.writeWith(fluxBody.map(dataBuffer -> {
//                        // probably should reuse buffers
//                        byte[] content = new byte[dataBuffer.readableByteCount()];
//                        dataBuffer.read(content);
//                        //释放掉内存
//                        DataBufferUtils.release(dataBuffer);
//                        String result = new String(content, Charset.forName("UTF-8"));
//                        System.out.println(result);
//                        //TODO，s就是response的值，想修改、查看就随意而为了
//                        byte[] uppedContent = new String(content, Charset.forName("UTF-8")).getBytes();
//                        return dataBufferFactory.wrap(uppedContent);
//                    }));
//                }
//                // if body is not a flux. never got there.
//                return super.writeWith(body);
//            }
//        };
//        RequestLog log = new RequestLog();
//        log.setApplication("alibaba");
//        log.setMessage("测试搭建日志记录");
//        log.setUrl(request.getURI().getPath());
//        log.setId(UUID.randomUUID().toString().replace("-", ""));
//        log.setOccurrenceTime(new Date());
////        log.setResponseCode(response.getStatusCode().value() + "");
////        log.setResponseText(JSONObject.toJSONString(result));
//        RequestLog save = requestLogRepository.save(log);
//        System.out.println(save);
////        Mono<Void> filter = chain.filter(exchange.mutate().response(decoratedResponse).build());
//        return chain.filter(exchange);
//    }

//    @Override
//    public int getOrder() {
//        return -2;
//    }
}
