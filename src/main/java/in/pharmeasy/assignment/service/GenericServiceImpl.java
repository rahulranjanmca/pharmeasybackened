package in.pharmeasy.assignment.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class GenericServiceImpl<T, U> implements GenericService<T, U> {

	public abstract in.pharmeasy.assignment.repository.AbstractRepository<T, U> getDAO();

	public void save(T t) {
		getDAO().save(t);

	}

	public T update(T t) {
		return getDAO().update(t);
	}

	public T get(Long id) {
		return getDAO().get(id);
	}

	public List<T> getListByCriteria(U approval, int firstResult, int maxResult) {
		return getDAO().getListByCriteria(approval, firstResult, maxResult);
	}

	public List<T> getListByCriteria(U approval, int firstResult, int maxResult, Map<String, Boolean> orderBy,
			boolean distinct) {
		return getDAO().getListByCriteria(approval, firstResult, maxResult, orderBy, distinct);
	}

	public Long getCount(U approval) {
		return getDAO().getCount(approval);
	}

	@Override
	public T getEntityByColumnNameAndValue(String filterFieldName, Object filterFieldValue) {
		return getDAO().getEntityByColumnNameAndValue(filterFieldName, filterFieldValue);
	}
	
	

}
