package in.pharmeasy.assignment.repository;

import java.util.List;
import java.util.Map;

public interface AbstractRepository<T, U> {

	public T get(Object id);

	public void save(T t);

	public T update(T t);

	public List<T> getListByCriteria(U approval, int firstResult, int maxResult);

	public List<T> getListByCriteria(U approval, int firstResult, int maxResult, Map<String, Boolean> orderBy,
			boolean distinct);

	public Long getCount(U approval);

	public T getEntityByColumnNameAndValue(String filterFieldName, Object filterFieldValue);

}
