package com.sixtofly.zuul.repository;

import com.sixtofly.zuul.log.RequestLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface RequestLogRepository extends ElasticsearchCrudRepository<RequestLog, String> {
}
