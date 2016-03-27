package org.mockenize.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import org.mockenize.model.LogBean;
import org.mockenize.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;

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

	@SuppressWarnings("unchecked")
	public Collection<LogBean> findByFilters(JsonNode jsonNode) {
		Iterator<String> fieldNames = jsonNode.fieldNames();
		Predicate<String, LogBean> predicate = null;
		while(fieldNames.hasNext()) {
			String field = fieldNames.next();
			String value = jsonNode.get(field).asText();

			Predicate<String, LogBean> tmp = Predicates.equal(field, value);
			if(predicate == null) {
				predicate = tmp;
			} else {
				predicate = Predicates.and(predicate, tmp);
			}
		}
		return logRepository.findByPredicate(predicate);
	}
}
