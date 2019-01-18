package com.singa.springboot.repository.extend.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.singa.springboot.repository.extend.constant.Direction;
import com.singa.springboot.repository.extend.constant.Join;

/**
 * @author Singa
 * @version 1.0.0
 * @filename Query.java
 * @time 2019年1月4日 下午2:31:29
 * @copyright(C) 2019 深圳市北辰德科技股份有限公司
 */
public class Select {
	/**
	 * 查询条件
	 */
	private List<Select> conditions = new ArrayList<Select>();
	
	/**
	 * 排序的字段
	 */
	private String[] orderByFields;
	
	/**
	 * 排序方向
	 */
	private String orderByDirection;
	/**
	 * 查询条件之间的连接符 and/or
	 */
	private String join = Join.and;
	
	public Select(){}
	
	public Select(Select query) {
		if (null != query) {
			conditions.add(query);
		}
	}
	
	public static Select where(String field, String oper, Object value) {
		return new Select(field, oper, value);
	}
	
	public static Select where (Select query) {
		return new Select(query);
	}
	
	/**
	 * 构建查询条件
	 * @param field
	 * @param oper
	 * @param value
	 */
	public Select(String field, String oper, Object value) {
		if (!StringUtils.isEmpty(value)) {
			conditions.add(new Condition(field, oper, value));
		}
	}
	
	/**
	 * 添加and查询天剑
	 * @param field
	 * @param oper
	 * @param value
	 * @return
	 */
	public Select and(String field, String oper, Object value) {
		if (!StringUtils.isEmpty(value)) {
			conditions.add(new Condition(field, oper, value));
		}
		return this;
	}
	
	/**
	 * 添加and查询条件，允许空值作为查询条件
	 * @param field
	 * @param oper
	 * @param value
	 * @return
	 */
	public Select andAllowEmpty(String field, String oper, Object value) {
		conditions.add(new Condition(field, oper, value));
		return this;
	}
	
	/**
	 * 添加 and（      ）中的查询
	 * @param query
	 * @return
	 */
	public Select and(Select query) {
		if (null != query) {
			conditions.add(query);
		}
		return this;
	}
	
	/**
	 * 添加or的查询条件
	 * @param field
	 * @param oper
	 * @param value
	 * @return
	 */
	public Select or(String field, String oper, Object value) {
		if (!StringUtils.isEmpty(value)) {
			conditions.add(new Condition(field, oper, value, Join.or));
		}
		return this;
	}
	
	/**
	 * 添加or的查询条件，允许空值作为查询条件
	 * @param field
	 * @param oper
	 * @param value
	 * @return
	 */
	public Select orAllowEmpty(String field, String oper, Object value) {
		conditions.add(new Condition(field, oper, value, Join.or));
		return this;
	}
	
	/**
	 * 添加 or（      ）中的查询
	 * @param query
	 * @return
	 */
	public Select or(Select query) {
		if (null != query) {
			query.setJoin(Join.or);
			conditions.add(query);
		}
		return this;
	}
	
	/**
	 * 添加顺序排序字段
	 * @param fields
	 * @return
	 */
	public Select orderByAsc(String... fields) {
		this.orderByFields = fields;
		this.orderByDirection = Direction.ASC;
		return this;
	}
	
	/**
	 * 添加倒序排序字段
	 * @param fields
	 * @return
	 */
 	public Select orderByDesc(String... fields) {
 		this.orderByFields = fields;
		this.orderByDirection = Direction.DESC;
		return this;
 	}
	
	public List<Select> getConditions() {
		return conditions;
	}

	public String getJoin() {
		return join;
	}

	public void setJoin(String join) {
		this.join = join;
	}

	public String[] getOrderByFields() {
		return orderByFields;
	}

	public String getOrderByDirection() {
		return orderByDirection;
	}
	
}
