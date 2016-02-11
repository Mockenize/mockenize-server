package com.mockenize.service;

import com.mockenize.exception.ResourceNotFoundException;
import com.mockenize.model.ProxyBean;
import com.mockenize.repository.ProxyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Enumeration;

/**
 * Created by rwatanabe on 08/02/16.
 */
@Service
public class ProxyService implements ResponseService {

    @Autowired
    private ProxyRepository proxyRepository;

    @Context
    private HttpServletRequest request;

    private Client client = ClientBuilder.newClient();

    public Collection<ProxyBean> getAll() {
        return proxyRepository.findAll();
    }

    public ProxyBean save(ProxyBean proxyBean) {
        proxyBean.setKey(toKey(proxyBean.getPath()));
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

    @Override
    public Response getResponse(HttpServletRequest request) {
        String path = request.getRequestURI();
        ProxyBean proxyBean = proxyRepository.findByPath(path);
        Builder requestBuilder = client.target(proxyBean.getUrl()).path(path).request();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            requestBuilder.header(key, request.getHeader(key));
        }
        Response response = requestBuilder.method(request.getMethod());
        response.bufferEntity();
        return response;
    }

    private String toKey(String path) {
        return path.toLowerCase().replaceAll("/", "_");
    }
}
