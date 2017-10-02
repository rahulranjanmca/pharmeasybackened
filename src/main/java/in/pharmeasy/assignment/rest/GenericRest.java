package in.pharmeasy.assignment.rest;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import in.pharmeasy.assignment.service.GenericService;

public abstract class GenericRest<T, U> {

	private Class<T> t;
	private Class<U> u;

	public abstract GenericService<T, U> getService();

	public GenericRest() {
	}

	public GenericRest(Class<T> t, Class<U> u) {
		this.t = t;
		this.u = u;
	}

	public T instantiateEntity() {
		try {
			return t.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public U instantiateCriteria() {
		try {
			return u.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	// @PreAuthorize("#oauth2.hasScope('read')")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody T get(@PathVariable("id") Long id, Principal principal) {
		return getService().get(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody T save(@RequestBody T t, Principal principal) {

		getService().save(t);
		return t;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody T update(@RequestBody T t, Principal principal) {
		t = getService().update(t);
		return t;
	}

	@RequestMapping(value = "/search/{firstResult}/{maxResult}", method = RequestMethod.POST)
	public @ResponseBody List<T> getListByCriteria(@RequestBody U t, @PathVariable("firstResult") int firstResult,
			@PathVariable("maxResult") int maxResult, Principal principal) {
		return getService().getListByCriteria(t, firstResult, maxResult);
	}

	@RequestMapping(value = "/count", method = RequestMethod.POST)
	public @ResponseBody Long count(@RequestBody U t , Principal principal) {
		return getService().getCount(t);
	}

}
