package com.singa.springboot.repository.extend.util;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.singa.springboot.repository.extend.constant.Oper;
import com.singa.springboot.repository.extend.vo.Condition;

/**
 * @author Singa
 * @version 1.0.0
 * @filename CriteriaBuilderUtil.java
 * @time 2019年1月5日 下午11:11:17
 * @copyright(C) 2019 深圳市北辰德科技股份有限公司
 */
public class CriteriaBuilderUtil {
	
	public static Predicate buildPredicate(CriteriaBuilder builder, Root<?> root, Class<?> domainClass, Condition condition) {
		Predicate predicate = null;
		
		boolean isField = false;
		try {
			domainClass.getDeclaredField(condition.getValue().toString());
			isField = true;
		} catch (NoSuchFieldException | SecurityException e1) {}
		
		if (Oper.equal.equals(condition.getOper())) {
			if (isField) {
				predicate = builder.equal(root.get(condition.getField()), root.get(condition.getValue().toString()));
			} else {
				predicate = builder.equal(root.get(condition.getField()), condition.getValue());
			}
		} else if (Oper.notEqual.equals(condition.getOper())) {
			if (isField) {
				predicate = builder.notEqual(root.get(condition.getField()), root.get(condition.getValue().toString()));
			} else {
				predicate = builder.notEqual(root.get(condition.getField()), condition.getValue());
			}
		} else if (Oper.like.equals(condition.getOper())) {
			predicate = builder.like(root.get(condition.getField()), condition.getValue().toString());
		} else if (Oper.notLike.equals(condition.getOper())) {
			predicate = builder.notLike(root.get(condition.getField()), condition.getValue().toString());
		} else if (Oper.in.equals(condition.getOper())){
			CriteriaBuilder.In<Object> in = builder.in(root.get(condition.getField()));
			List<?> values = (List<?>) condition.getValue();
			for (Object o : values) {
				in.value(o);
			}
			predicate = in;
//			predicate = root.get(condition.getField()).in(condition.getValue());
		} else {
			Class<?> cls = null;
			try {
				cls = domainClass.getDeclaredField(condition.getField()).getType();
			} catch (NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			}
			if (cls.equals(String.class)) {
				if (Oper.gt.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.greaterThan(root.get(condition.getField()).as(String.class), 
								root.get(condition.getValue().toString()).as(String.class));
					} else {
						predicate = builder.greaterThan(root.get(condition.getField()).as(String.class), (String)condition.getValue());
					}
				} else if (Oper.gtEqual.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.greaterThanOrEqualTo(root.get(condition.getField()).as(String.class), 
								root.get(condition.getValue().toString()).as(String.class));
					} else {
						predicate = builder.greaterThanOrEqualTo(root.get(condition.getField()).as(String.class), (String)condition.getValue());
					}
				} else if (Oper.lt.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.lessThan(root.get(condition.getField()).as(String.class), 
								root.get(condition.getValue().toString()).as(String.class));
					} else {
						predicate = builder.lessThan(root.get(condition.getField()).as(String.class), (String)condition.getValue());
					}
				} else if (Oper.ltEqual.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.lessThanOrEqualTo(root.get(condition.getField()).as(String.class), 
								root.get(condition.getValue().toString()).as(String.class));
					} else {
						predicate = builder.lessThanOrEqualTo(root.get(condition.getField()).as(String.class), (String)condition.getValue());
					}
				}
			} else if (cls.equals(int.class) || cls.equals(Integer.class)) {
				if (Oper.gt.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.greaterThan(root.get(condition.getField()).as(Integer.class), 
								root.get(condition.getValue().toString()).as(Integer.class));
					} else {
						predicate = builder.greaterThan(root.get(condition.getField()).as(Integer.class), (Integer)condition.getValue());
					}
				} else if (Oper.gtEqual.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.greaterThanOrEqualTo(root.get(condition.getField()).as(Integer.class), 
								root.get(condition.getValue().toString()).as(Integer.class));
					} else {
						predicate = builder.greaterThanOrEqualTo(root.get(condition.getField()).as(Integer.class), (Integer)condition.getValue());
					}
				} else if (Oper.lt.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.lessThan(root.get(condition.getField()).as(Integer.class), 
								root.get(condition.getValue().toString()).as(Integer.class));
					} else {
						predicate = builder.lessThan(root.get(condition.getField()).as(Integer.class), (Integer)condition.getValue());
					}
				} else if (Oper.ltEqual.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.lessThanOrEqualTo(root.get(condition.getField()).as(Integer.class), 
								root.get(condition.getValue().toString()).as(Integer.class));
					} else {
						predicate = builder.lessThanOrEqualTo(root.get(condition.getField()).as(Integer.class), (Integer)condition.getValue());
					}
				}
			} else if (cls.equals(long.class) || cls.equals(Long.class)) {
				if (Oper.gt.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.greaterThan(root.get(condition.getField()).as(Long.class), 
								root.get(condition.getValue().toString()).as(Long.class));
					} else {
						predicate = builder.greaterThan(root.get(condition.getField()).as(Long.class), (Long)condition.getValue());
					}
				} else if (Oper.gtEqual.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.greaterThanOrEqualTo(root.get(condition.getField()).as(Long.class), 
								root.get(condition.getValue().toString()).as(Long.class));
					} else {
						predicate = builder.greaterThanOrEqualTo(root.get(condition.getField()).as(Long.class), (Long)condition.getValue());
					}
				} else if (Oper.lt.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.lessThan(root.get(condition.getField()).as(Long.class), 
								root.get(condition.getValue().toString()).as(Long.class));
					} else {
						predicate = builder.lessThan(root.get(condition.getField()).as(Long.class), (Long)condition.getValue());
					}
				} else if (Oper.ltEqual.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.lessThanOrEqualTo(root.get(condition.getField()).as(Long.class), 
								root.get(condition.getValue().toString()).as(Long.class));
					} else {
						predicate = builder.lessThanOrEqualTo(root.get(condition.getField()).as(Long.class), (Long)condition.getValue());
					}
				}
			} else if (cls.equals(float.class) || cls.equals(Float.class)) {
				if (Oper.gt.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.greaterThan(root.get(condition.getField()).as(Float.class), 
								root.get(condition.getValue().toString()).as(Float.class));
					} else {
						predicate = builder.greaterThan(root.get(condition.getField()).as(Float.class), (Float)condition.getValue());
					}
				} else if (Oper.gtEqual.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.greaterThanOrEqualTo(root.get(condition.getField()).as(Float.class), 
								root.get(condition.getValue().toString()).as(Float.class));
					} else {
						predicate = builder.greaterThanOrEqualTo(root.get(condition.getField()).as(Float.class), (Float)condition.getValue());
					}
				} else if (Oper.lt.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.lessThan(root.get(condition.getField()).as(Float.class), 
								root.get(condition.getValue().toString()).as(Float.class));
					} else {
						predicate = builder.lessThan(root.get(condition.getField()).as(Float.class), (Float)condition.getValue());
					}
				} else if (Oper.ltEqual.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.lessThanOrEqualTo(root.get(condition.getField()).as(Float.class), 
								root.get(condition.getValue().toString()).as(Float.class));
					} else {
						predicate = builder.lessThanOrEqualTo(root.get(condition.getField()).as(Float.class), (Float)condition.getValue());
					}
				}
			} else if (cls.equals(double.class) || cls.equals(Double.class)) {
				if (Oper.gt.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.greaterThan(root.get(condition.getField()).as(Double.class), 
								root.get(condition.getValue().toString()).as(Double.class));
					} else {
						predicate = builder.greaterThan(root.get(condition.getField()).as(Double.class), (Double)condition.getValue());
					}
				} else if (Oper.gtEqual.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.greaterThanOrEqualTo(root.get(condition.getField()).as(Double.class), 
								root.get(condition.getValue().toString()).as(Double.class));
					} else {
						predicate = builder.greaterThanOrEqualTo(root.get(condition.getField()).as(Double.class), (Double)condition.getValue());
					}
				} else if (Oper.lt.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.lessThan(root.get(condition.getField()).as(Double.class), 
								root.get(condition.getValue().toString()).as(Double.class));
					} else {
						predicate = builder.lessThan(root.get(condition.getField()).as(Double.class), (Double)condition.getValue());
					}
				} else if (Oper.ltEqual.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.lessThanOrEqualTo(root.get(condition.getField()).as(Double.class), 
								root.get(condition.getValue().toString()).as(Double.class));
					} else {
						predicate = builder.lessThanOrEqualTo(root.get(condition.getField()).as(Double.class), (Double)condition.getValue());
					}
				}
			} else if (cls.equals(Date.class)) {
				if (Oper.gt.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.greaterThan(root.get(condition.getField()).as(Date.class), 
								root.get(condition.getValue().toString()).as(Date.class));
					} else {
						predicate = builder.greaterThan(root.get(condition.getField()).as(Date.class), (Date)condition.getValue());
					}
				} else if (Oper.gtEqual.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.greaterThanOrEqualTo(root.get(condition.getField()).as(Date.class), 
								root.get(condition.getValue().toString()).as(Date.class));
					} else {
						predicate = builder.greaterThanOrEqualTo(root.get(condition.getField()).as(Date.class), (Date)condition.getValue());
					}
				} else if (Oper.lt.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.lessThan(root.get(condition.getField()).as(Date.class), 
								root.get(condition.getValue().toString()).as(Date.class));
					} else {
						predicate = builder.lessThan(root.get(condition.getField()).as(Date.class), (Date)condition.getValue());
					}
				} else if (Oper.ltEqual.equals(condition.getOper())) {
					if (isField) {
						predicate = builder.lessThanOrEqualTo(root.get(condition.getField()).as(Date.class), 
								root.get(condition.getValue().toString()).as(Date.class));
					} else {
						predicate = builder.lessThanOrEqualTo(root.get(condition.getField()).as(Date.class), (Date)condition.getValue());
					}
				}
			} 
		}
		return predicate;
	}
	
	
}
