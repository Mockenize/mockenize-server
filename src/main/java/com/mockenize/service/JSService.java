package com.mockenize.service;

import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mockenize.exception.JSNotFoundException;
import com.mockenize.model.JSBean;

@Service
public class JSService {
	
	private static final String ENGINE_NAME = "JavaScript";
	private static final String DEFAUL_FUNCTION_NAME = "func";
	@Autowired
	private HazelcastService<JSBean> hazelCastService;

	public JSBean getJSBean(JSBean jsBean) {
		jsBean = hazelCastService.get(jsBean.getName());
		if(jsBean == null) {
			throw new JSNotFoundException();
		}
		return jsBean; 
	}

	public void delete(JSBean jsBean) {
		hazelCastService.delete(jsBean.getName());
	}

	public void insert(JSBean jsBean) {
		hazelCastService.insert(jsBean.getName(), jsBean);
	}

	public Map<String, JSBean> getJSBeans() {
		return hazelCastService.getAll();
	}
	
	public String execute(JSBean jsBean, String url, String body) throws ScriptException, NoSuchMethodException {
		ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
		scriptEngine.eval(jsBean.getValue());
		Invocable invocable = (Invocable) scriptEngine;
		return String.valueOf(invocable.invokeFunction(DEFAUL_FUNCTION_NAME, url, body));
	}

}
