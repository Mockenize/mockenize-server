package org.mockenize.service;

import org.mockenize.model.LogBean;
import org.mockenize.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Service
public class LoggingService {

    @Autowired
    private LogRepository logRepository;

    public void save(LogBean logBean) {
        logRepository.save(logBean);
    }

    public Collection<LogBean> getAll() {
        return logRepository.findAll();
    }

    public LogBean getByKey(UUID key) {
        return logRepository.findByKey(key.toString());
    }


    public LogBean delete(UUID key) {
        return logRepository.delete(key.toString());
    }

    public Collection<LogBean> deleteAll() {
       return logRepository.deleteAll();
    }
}
