package in.pharmeasy.assignment.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.pharmeasy.assignment.model.AuthClient;
import in.pharmeasy.assignment.service.AuthClientService;
import in.pharmeasy.assignment.service.GenericService;

@RestController
@RequestMapping("auth-clients")
public class AuthClientRest extends GenericRest<AuthClient, AuthClient> {
	
	@Autowired
	AuthClientService authClientService;

	@Override
	public GenericService<AuthClient, AuthClient> getService() {
		return authClientService;
	}

	



}
