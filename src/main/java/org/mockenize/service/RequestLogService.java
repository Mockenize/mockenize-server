package org.mockenize.service;

import org.mockenize.model.LogBean;
import org.mockenize.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestLogService {

    @Autowired
    private RequestLogRepository requestLogRepository;


    public void save(LogBean logBean) {
        requestLogRepository.save(logBean);
    }
}
