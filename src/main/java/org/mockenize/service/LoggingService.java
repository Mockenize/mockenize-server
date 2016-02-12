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
        return logRepository.findByKey(key);
    }


    public void delete(UUID key) {
        logRepository.delete(key);
    }

    public void deleteAll() {
        logRepository.deleteAll();
    }
}
