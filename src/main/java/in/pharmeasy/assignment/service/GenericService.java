package in.pharmeasy.assignment.service;

import java.util.List;
import java.util.Map;

public interface GenericService<T,U> {
	public void save(T object) ;
	public T update(T object) ;
	public T get(Long id) ;
	public List<T> getListByCriteria(U criteria, int firstResult, int maxResult);
	public List<T> getListByCriteria(U approval, int firstResult, int maxResult, Map<String, Boolean> orderBy, boolean distinct );
	public Long getCount(U criteria);
	 T getEntityByColumnNameAndValue(String filterFieldName,
				Object filterFieldValue) ;
	 
}
