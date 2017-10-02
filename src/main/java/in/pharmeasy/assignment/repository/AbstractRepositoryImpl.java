package in.pharmeasy.assignment.repository;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import in.pharmeasy.assignment.model.AuthClient;

public abstract class AbstractRepositoryImpl<T, U> {

	public abstract EntityManager getEntityManager();

	public abstract Class<T> getClassType();

	public T get(Object id) {
		return getEntityManager().find(getClassType(), id);
	}


	public void save(T t) {
		getEntityManager().persist(t);
	}

	public T update(T t) {
		return getEntityManager().merge(t);
	}

	public void delete(Object id) {
		getEntityManager().remove(get(id));
	}

	public boolean isUnique(String propertyName, Object propertyValue) {
		return getEntityManager()
				.createQuery(String.format("select count(c) from %s c where %s = :propertyValue",
						getClassType().getSimpleName(), propertyName), Long.class)
				.setParameter("propertyValue", propertyValue) //
				.getSingleResult() == 0;
	}

	public boolean isUniqueExceptThis(Object id, String propertyName, Object propertyValue) {
		return getEntityManager()
				.createQuery(String.format("select count(c) from %s c where %s = :propertyValue and %s != :id", //
						getClassType().getSimpleName(), propertyName, "id"), Long.class) //
				.setParameter("propertyValue", propertyValue) //
				.setParameter("id", id) //
				.getSingleResult() == 0;
	}

	public boolean isUnique(Object id, String propertyName, Object propertyValue) {
		if (id == null) {
			return isUnique(propertyName, propertyValue);
		} else {
			return isUniqueExceptThis(id, propertyName, propertyValue);
		}
	}

	public List<T> getListByColumnNameAndValue(Class<T> clazz, String filterFieldName, Object filterFieldValue) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> r = cq.from(clazz);
		if (filterFieldValue == null) {
			cq.where(cb.isNull(r.get(filterFieldName)));
		} else {
			cq.where(cb.equal(r.get(filterFieldName), filterFieldValue));
		}
		cq.orderBy(cb.desc(r.get("id")));
		return getEntityManager().createQuery(cq).getResultList();
	}

	public T getEntityByColumnNameAndValue(String filterFieldName, Object filterFieldValue) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClazz());
		Root<T> r = cq.from(getEntityClazz());
		if (filterFieldValue == null) {
			cq.where(cb.isNull(r.get(filterFieldName)));
		} else {
			cq.where(cb.equal(r.get(filterFieldName), filterFieldValue));
		}
		cq.orderBy(cb.desc(r.get("id")));
		List<T> result= getEntityManager().createQuery(cq).getResultList();
		if(result.size()>0)
			return result.get(0);
		else
			return null;
	}

	public Long getCount(U example) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<T> root = cq.from(getEntityClazz());
		cq.select(cb.count(root));
		List<Predicate> predicates = new ArrayList<Predicate>();
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		cq.where(cb.and(getSearchPredicates(root, example)));
		return getEntityManager().createQuery(cq).getSingleResult();
	}

	protected Predicate[] getSearchPredicates(Root<T> root, U example) {
		Predicate[] predicates = new Predicate[0];
		return predicates;
	}

	public Class<T> getEntityClazz() {
		@SuppressWarnings("unchecked")
		Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		return persistentClass;
	};

	public Class<U> getCriteriaClazz() {
		@SuppressWarnings("unchecked")
		Class<U> persistentClass = (Class<U>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
		return persistentClass;
	};

	public List<T> getListByCriteria(U criteriaPopulator, int firstResult, int maxResult, Map<String, Boolean> orderBy,
			boolean distinct) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(getEntityClazz());
		Root<T> r = cq.from(getEntityClazz());
		List<Predicate> predicates = new ArrayList<Predicate>();

		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		cq.where(cb.and(getSearchPredicates(r, criteriaPopulator)));

		if (orderBy == null) {
			cq.orderBy(cb.desc(r.get("id")));
		} else {
			for (Map.Entry<String, Boolean> order : orderBy.entrySet()) {
				if (order.getValue() == false) {
					cq.orderBy(cb.desc(r.get(order.getKey())));
				} else {
					cq.orderBy(cb.asc(r.get(order.getKey())));
				}

			}
		}
		if (distinct) {
			cq.distinct(true);
		}
		TypedQuery<T> typedQuery = getEntityManager().createQuery(cq);
		if (firstResult >= 0) {
			typedQuery.setMaxResults(maxResult);
			typedQuery.setFirstResult(firstResult);
		}

		return typedQuery.getResultList();
	}

	
	
	public List<T> getListByCriteria(U approval, int firstResult, int maxResult) {
		return getListByCriteria(approval, firstResult, maxResult, null, false);
	}
	

}
