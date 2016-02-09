package com.mockenize.service;

import com.mockenize.exception.ResourceNotFoundException;
import com.mockenize.model.ProxyBean;
import com.mockenize.repository.ProxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Service
public class ProxyService {

    @Autowired
    private ProxyRepository proxyRepository;

    public Collection<ProxyBean> getAll() {
        return proxyRepository.findAll();
    }

    public ProxyBean save(ProxyBean proxyBean) {
        proxyBean.setKey(toKey(proxyBean.getName()));
        return proxyRepository.save(proxyBean);
    }

    public ProxyBean getByKey(String key) {
        ProxyBean proxyBean = proxyRepository.findByKey(key);

        if (proxyBean == null) {
            throw new ResourceNotFoundException();
        }

        return proxyBean;
    }

    public void delete(String key) {
        proxyRepository.delete(key);
    }

    public void deleteAll() {
        proxyRepository.deleteAll();
    }

    private String toKey(String name) {
        return name.toLowerCase().replaceAll("\\s+", "-");
    }
}
