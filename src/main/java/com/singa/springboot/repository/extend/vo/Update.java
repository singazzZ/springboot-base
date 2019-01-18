package com.singa.springboot.repository.extend.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Singa
 * @version 1.0.0
 * @filename Update.java
 * @time 2019年1月7日 下午3:40:02
 * @copyright(C) 2019 深圳市北辰德科技股份有限公司
 */
public class Update {
	private Map<String, Object> sets = new HashMap<String, Object>();
	
	public Update(){}
	
	public Update(String field, Object value) {
		sets.put(field, value);
	}
	
	public static Update set(String field, Object value) {
		return new Update(field, value);
	}
	
	public Update addSet(String field, Object value) {
		sets.put(field, value);
		return this;
	}

	public Map<String, Object> getSets() {
		return sets;
	}
	
}
