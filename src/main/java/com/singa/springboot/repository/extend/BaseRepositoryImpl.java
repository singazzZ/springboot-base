package com.singa.springboot.repository.extend;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.singa.springboot.repository.extend.constant.Direction;
import com.singa.springboot.repository.extend.constant.Join;
import com.singa.springboot.repository.extend.util.CriteriaBuilderUtil;
import com.singa.springboot.repository.extend.vo.Condition;
import com.singa.springboot.repository.extend.vo.Select;
import com.singa.springboot.repository.extend.vo.Update;

/**
 * @author Singa
 * @version 1.0.0
 * @filename BaseRepository.java
 * @time 2018年12月26日 下午4:42:46
 * @copyright(C) 2018 深圳市北辰德科技股份有限公司
 */
public class BaseRepositoryImpl<T, ID extends Serializable> 
		extends SimpleJpaRepository<T, Serializable> 
		implements IBaseRepository<T, Serializable>{

	private static final Logger logger = LoggerFactory.getLogger(BaseRepositoryImpl.class);

	private final EntityManager entityManager;
	
	private final Class<T> domainClass;
    
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.entityManager = em;
		this.domainClass = domainClass;

	}
	
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	

	/**
	 * sql 更新
	 */
	@Override
	public int updateBySql(String sql){
		int updateItem = 0;
		try {
			Query query=entityManager.createNativeQuery(sql);
			updateItem = query.executeUpdate();
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
		return updateItem;
	}
	
	/**
	 * sql 查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findBySql(String sql){
		List<Object[]> result = null;
		try {
			Query query=entityManager.createNativeQuery(sql);
			result = query.getResultList();
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
		return result;
	}
	
	
	/**
	 * 多条复杂查询多条
	 * @param query
	 * @return
	 */
	@Override
	public List<T> selectAll(Select select) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(domainClass);
		Root<T> root = criteriaQuery.from(domainClass);
		setQueryCondition(builder, criteriaQuery, root, select);
		return (List<T>) entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	/**
	 * 多条复杂查询单条
	 * @param query
	 * @return
	 */
	@Override
	public T selectOne(Select select) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(domainClass);
		Root<T> root = criteriaQuery.from(domainClass);
		setQueryCondition(builder, criteriaQuery, root, select);
		return (T) entityManager.createQuery(criteriaQuery).getSingleResult();
	}
	
	/**
	 * 多条复杂查询总数
	 * @param page	页码
	 * @param size	一页条数
	 * @param query 查询条件
	 * @return
	 */
	@Override
	public int selectCount(Select select) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		Root<T> root = criteriaQuery.from(domainClass);
		setQueryCondition(builder, criteriaQuery, root, select);
		criteriaQuery.select(builder.countDistinct(root));
		return entityManager.createQuery(criteriaQuery).getSingleResult().intValue();
	}
	
	/**
	 * 多条复杂分页查询
	 * @param page	页码
	 * @param size	一页条数
	 * @param query 查询条件
	 * @return
	 */
	@Override
	public List<T> selectOnePage(int startIndex, int size, Select select) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(domainClass);
		Root<T> root = criteriaQuery.from(domainClass);
		setQueryCondition(builder, criteriaQuery, root, select);
		return entityManager.createQuery(criteriaQuery)
									.setFirstResult(startIndex)
									.setMaxResults(size)
									.getResultList();
	}
	
	/**
	 * 多条复杂分页查询
	 * @param page	页码
	 * @param size	一页条数
	 * @param query 查询条件
	 * @return
	 */
	@SuppressWarnings("serial")
	@Override
	public Page<T> selectPage(int page, int size, Select select) {
		return findAll(
				new Specification<T>(){
					@Override
					public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
							CriteriaBuilder criteriaBuilder) {
						setQueryCondition(criteriaBuilder, query, root, select);
						return null;
					}
				}, PageRequest.of(page, size));
	}
	
	
	/**
	 * 多条件复杂更新操作
	 * @param update	更新的值对象
	 * @param select	更新条件对象
	 * @return
	 */
	public int update(Update update, Select updateCondition) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<T> criteriaUpdate = builder.createCriteriaUpdate(domainClass);
		Root<T> root = criteriaUpdate.from(domainClass);
		Map<String, Object> sets = update.getSets();
		for (String key : sets.keySet()) {
			criteriaUpdate.set(root.get(key), sets.get(key));
		}
		if (null != updateCondition && updateCondition.getConditions().size() > 0) {
			List<Select> list = updateCondition.getConditions();
			Predicate predicate = getPredicate(builder, root, list);
			criteriaUpdate.where(predicate);
		}
		return entityManager.createQuery(criteriaUpdate).executeUpdate();
	}
	
	/**
	 * 多条件复杂删除操作
	 * @param deleteCondition 删除条件
	 * @return
	 */
	public int delete(Select deleteCondition) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaDelete<T> criteriaDelete = builder.createCriteriaDelete(domainClass);
		Root<T> root = criteriaDelete.from(domainClass);
		if (null != deleteCondition && deleteCondition.getConditions().size() > 0) {
			List<Select> list = deleteCondition.getConditions();
			Predicate predicate = getPredicate(builder, root, list);
			criteriaDelete.where(predicate);
		}
		return entityManager.createQuery(criteriaDelete).executeUpdate();
	}

	/**
	 * 组装查询条件
	 * @param builder
	 * @param criteriaQuery
	 * @param query
	 */
	public void setQueryCondition(CriteriaBuilder builder, CriteriaQuery<?> criteriaQuery, 
			Root<T> root, Select select) {
		if (null != select) {
			List<Select> list = select.getConditions();
			Predicate predicate = getPredicate(builder, root, list);
			if (null != predicate) {
				criteriaQuery.where(predicate);
			}
			if (null != select.getOrderByFields() && select.getOrderByFields().length > 0) {
				String[] fields = select.getOrderByFields();
				Order[] orders = new Order[fields.length];
				for (int i = 0; i < fields.length; i++) {
					if (Direction.ASC.equals(select.getOrderByDirection())) {
						orders[i] = builder.asc(root.get(fields[i]));
					} else {
						orders[i] = builder.desc(root.get(fields[i]));
					}
				}
				criteriaQuery.orderBy(orders);
			}
		}
	}
	
	/**
	 * 拼接单个查询条件
	 * @param builder
	 * @param root
	 * @param conditions
	 * @return
	 */
	public Predicate getPredicate(CriteriaBuilder builder, Root<T> root, List<Select> conditions) {
		Predicate predicate = null;
		for (Select q : conditions) {
			if (q instanceof Condition) {
				Condition condition = (Condition) q;
				Predicate p = CriteriaBuilderUtil.buildPredicate(builder, root, domainClass, condition);
				if (null == predicate) {
					predicate = p;continue;
				}
				if (Join.and.equals(condition.getJoin())) {
					predicate = builder.and(predicate, p);
				} else {
					predicate = builder.or(predicate, p);
				}
			} else {
				List<Select> list = q.getConditions();
				Predicate p = getPredicate(builder, root, list);
				if (predicate == null) {
					predicate = p;continue;
				}
				if (Join.and.equals(q.getJoin())) {
					predicate = builder.and(predicate, p);
				} else {
					predicate = builder.or(predicate, p);
				}
			}
		}
		return predicate;
	}
	
	
}

