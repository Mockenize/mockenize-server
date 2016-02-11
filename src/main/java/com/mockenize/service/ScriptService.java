package com.mockenize.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mockenize.repository.JSRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockenize.exception.JSNotFoundException;
import com.mockenize.model.JSBean;

@Service
public class ScriptService {

    private static final String ENGINE_NAME = "JavaScript";

    private static final String DEFAUL_FUNCTION_NAME = "func";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JSRespository jsRespository;

    public JSBean getByKey(String jsName) {
        JSBean jsBean = jsRespository.findByKey(jsName);

        if (jsBean == null) {
            throw new JSNotFoundException();
        }

        return jsBean;
    }

    public void delete(JSBean jsBean) {
        jsRespository.delete(jsBean.getName());
    }

    public void save(JSBean jsBean) {
        jsRespository.save(jsBean);
    }

    public Collection<JSBean> getAll() {
        return jsRespository.findAll();
    }

    public JsonNode execute(JSBean jsBean, String uri, String body) throws ScriptException, NoSuchMethodException, IOException {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
        scriptEngine.eval(jsBean.getValue());
        Invocable invocable = (Invocable) scriptEngine;
        String result = String.valueOf(invocable.invokeFunction(DEFAUL_FUNCTION_NAME, uri, body));
        return objectMapper.readTree(result);
    }

    public Set<String> getAllKeys() {
        return jsRespository.findAllKeys();
    }
}
