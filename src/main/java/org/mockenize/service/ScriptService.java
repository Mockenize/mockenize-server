package org.mockenize.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockenize.exception.ScriptNotFoundException;
import org.mockenize.model.ScriptBean;
import org.mockenize.repository.ScriptRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

@Service
public class ScriptService {

    private static final String ENGINE_NAME = "JavaScript";

    private static final String DEFAUL_FUNCTION_NAME = "func";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ScriptRespository scriptRespository;

    public ScriptBean getByKey(String jsName) {
        ScriptBean scriptBean = scriptRespository.findByKey(jsName);

        if (scriptBean == null) {
            throw new ScriptNotFoundException();
        }

        return scriptBean;
    }

    public void delete(ScriptBean scriptBean) {
        scriptRespository.delete(scriptBean.getName());
    }

    public void save(ScriptBean scriptBean) {
        scriptRespository.save(scriptBean);
    }

    public Collection<ScriptBean> getAll() {
        return scriptRespository.findAll();
    }

    public JsonNode execute(ScriptBean scriptBean, String uri, String body) throws ScriptException, NoSuchMethodException, IOException {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
        scriptEngine.eval(scriptBean.getValue());
        Invocable invocable = (Invocable) scriptEngine;
        String result = String.valueOf(invocable.invokeFunction(DEFAUL_FUNCTION_NAME, uri, body));
        return objectMapper.readTree(result);
    }

    public Set<String> getAllKeys() {
        return scriptRespository.findAllKeys();
    }
}
