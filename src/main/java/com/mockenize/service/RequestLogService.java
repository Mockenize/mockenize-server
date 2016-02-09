package com.mockenize.service;

import com.mockenize.model.LogBean;
import com.mockenize.model.RequestLogBean;
import com.mockenize.repository.RequestLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Service
public class RequestLogService {

    @Autowired
    private RequestLogRepository requestLogRepository;


    public void save(LogBean logBean) {
        requestLogRepository.save(logBean);
    }
}
