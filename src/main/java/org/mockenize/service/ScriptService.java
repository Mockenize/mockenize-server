package org.mockenize.service;

import java.io.IOException;
import java.util.Collection;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.mockenize.exception.ScriptNotFoundException;
import org.mockenize.model.ScriptBean;
import org.mockenize.repository.ScriptRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ScriptService {

	private static final String ENGINE_NAME = "JavaScript";

	private static final String DEFAUL_FUNCTION_NAME = "_func";

	private static final String PARSE_FUNCTION = ""
			+ "function _func(url, body) {"
			+ "obj=null;try{obj=JSON.parse(body)}catch(ex){};"
			+ "ret = func(url, body, obj);"
			+ "try{return (typeof ret === 'string') ? ret : JSON.stringify(ret)}catch(ex){};"
			+ "return ret};";

	private static final String EMPTY = "";

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

	public ScriptBean delete(ScriptBean scriptBean) {
		return scriptRespository.delete(scriptBean.getName());
	}
	
	public Collection<ScriptBean> deleteAll() {
		return scriptRespository.deleteAll();
	}

	public void save(ScriptBean scriptBean) {
		scriptRespository.save(scriptBean);
	}

	public Collection<ScriptBean> getAll() {
		return scriptRespository.findAll();
	}

	public JsonNode execute(ScriptBean scriptBean, String uri, JsonNode body)
			throws ScriptException, NoSuchMethodException, IOException {
		ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
		scriptEngine.eval(PARSE_FUNCTION + scriptBean.getValue());
		Invocable invocable = (Invocable) scriptEngine;
		String stringBody = body != null ? body.toString() : EMPTY;
		String ret = String.valueOf(invocable.invokeFunction(DEFAUL_FUNCTION_NAME, uri, stringBody));
		try {
			return objectMapper.readTree(ret);
		} catch(JsonParseException parseException) {		
			return objectMapper.createObjectNode().textNode(ret);
		}
	}

	public Collection<String> getAllKeys() {
		return scriptRespository.findAllKeys();
	}

}
