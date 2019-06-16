package com.sixtofly.gateway.repository;

import com.sixtofly.gateway.log.RequestLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

public interface RequestLogRepository extends ElasticsearchCrudRepository<RequestLog, String> {
}
