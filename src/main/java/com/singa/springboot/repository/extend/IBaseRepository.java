package com.singa.springboot.repository.extend;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.singa.springboot.repository.extend.vo.Select;
import com.singa.springboot.repository.extend.vo.Update;

/**
 * @author Singa
 * @version 1.0.0
 * @filename IBaseRepository.java
 * @time 2018年12月26日 下午4:43:08
 * @copyright(C) 2018 深圳市北辰德科技股份有限公司
 */
@NoRepositoryBean
public interface IBaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
	
	/**
	 * 查询单个实体
	 * @param query
	 * @return
	 */
	T selectOne(Select query);
	
	/**
	 * 复杂条件查询
	 * @param query
	 * @return
	 */
	List<T> selectAll(Select query);
	
	/**
	 * 复杂条件分页查询
	 * @param page
	 * @param size
	 * @param select
	 * @return
	 */
	Page<T> selectPage(int page, int size, Select select);
	
	/**
	 * 查询总数
	 * @param select
	 * @return
	 */
	int selectCount(Select select);
	
	/**
	 * 分页查询一页的数据
	 * @param startIndex
	 * @param size
	 * @param select
	 * @return
	 */
	List<T> selectOnePage(int startIndex, int size, Select select);
	
	/**
	 * 复杂条件更新
	 * @param update
	 * @param select
	 * @return
	 */
	int update(Update update, Select select);
	
	/**
	 * 复杂条件删除
	 * @param deleteCondition
	 * @return
	 */
	int delete(Select deleteCondition);
	
	/**
	 * sql 更新
	 * @param sql
	 */
	@Deprecated
	int updateBySql(String sql);

	/**
	 * sql 查询
	 * @param sql
	 * @return
	 */
	@Deprecated
	List<Object[]> findBySql(String sql);
	
}
