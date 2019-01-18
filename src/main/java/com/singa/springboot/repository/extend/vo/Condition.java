package com.singa.springboot.repository.extend.vo;

/**
 * @author Singa
 * @version 1.0.0
 * @filename Condition.java
 * @time 2019年1月4日 下午2:42:14
 * @copyright(C) 2019 深圳市北辰德科技股份有限公司
 */
public class Condition extends Select {
	
	/**
	 * 字段名
	 */
	private String field;
	
	/**
	 * 操作符 = < > != like
	 */
	private String oper;
	
	/**
	 * 值
	 */
	private Object value;
	
	public Condition(String field, String oper, Object value) {
		this.field = field;
		this.oper = oper;
		this.value = value;
	}
	
	public Condition(String field, String oper, Object value, String join) {
		this.field = field;
		this.oper = oper;
		this.value = value;
		super.setJoin(join);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
