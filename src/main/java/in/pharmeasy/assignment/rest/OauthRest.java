package in.pharmeasy.assignment.rest;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.pharmeasy.assignment.model.AuthClient;
import in.pharmeasy.assignment.service.AuthClientService;

@Controller
@RequestMapping("/oauth")
public class OauthRest {
	
	@Autowired
	Environment environment;
	
	@Autowired
	DefaultTokenServices defaultTokenServices;
	
	@Autowired
	AuthClientService  authClientService;

    	

	public static final ObjectMapper MAPPER = new ObjectMapper();

	public static final String CLIENT_ID_KEY = "client_id", REDIRECT_URI_KEY = "redirect_uri",
			CLIENT_SECRET = "client_secret", CODE_KEY = "code", GRANT_TYPE_KEY = "grant_type",
			AUTH_CODE = "authorization_code";

	@RequestMapping(method = RequestMethod.GET, path = "/login")
	public ModelAndView login(@RequestParam(value = "client_id") String client_id,
			@RequestParam(value = "redirect_uri") String redirect_uri) {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("client_id", client_id);
		mv.addObject("redirect_uri", redirect_uri);
		return mv;
	}

	@RequestMapping(path = "oauth_confirm", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> hello(@RequestBody Payload user) {
		return hello2( user);
	}
	
	@RequestMapping(path = "oauth_confirm", method = RequestMethod.OPTIONS)
	public @ResponseBody Map<String,Object> hello2( @RequestBody Payload user) {
		try {
			AuthClient authClientSearch= new AuthClient();
			authClientSearch.setAuthClientId(user.getClientId());
			String secret = authClientService.getListByCriteria(authClientSearch, -1, 0).get(0).getSecret();
			final String accessTokenUrl = environment.getProperty("accessTokenUrl");
						
			  Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

			HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().nonPreemptive()
					.credentials(user.getClientId(), secret).build();

			ClientConfig clientConfig = new ClientConfig();
			clientConfig.register(feature);
			javax.ws.rs.core.Response response;

			Client client = ClientBuilder.newClient(clientConfig);

			final MultivaluedMap<String, String> accessData = new MultivaluedHashMap<String, String>();
			accessData.add(CLIENT_ID_KEY, user.getClientId());
			accessData.add(REDIRECT_URI_KEY, user.getRedirectUri());
			accessData.add(CLIENT_SECRET, secret);
			accessData.add(CODE_KEY, user.getCode());
			accessData.add(GRANT_TYPE_KEY, AUTH_CODE);
			

			response = client.target(accessTokenUrl+"oauth/token").request().post(Entity.form(accessData));
			accessData.clear();
			
			   Set<String> resourceIds = new HashSet<>();
			    Set<String> responseTypes = new HashSet<>();
			    responseTypes.add("code");

			 Map<String, Object> responseEntity = getResponseEntity(response);
			 return responseEntity;
			   
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static class SocialObject {
		@NotNull
		String id;

		OAuth2AccessToken tokenObject;

		Boolean isMyApiLogin = Boolean.FALSE;

		Boolean hasRefreshToken = Boolean.FALSE;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public OAuth2AccessToken getTokenObject() {
			return tokenObject;
		}

		public void setTokenObject(OAuth2AccessToken tokenObject) {
			this.tokenObject = tokenObject;
		}

		public Boolean getIsMyApiLogin() {
			return isMyApiLogin;
		}

		public void setIsMyApiLogin(Boolean isMyApiLogin) {
			this.isMyApiLogin = isMyApiLogin;
		}

		public Boolean getHasRefreshToken() {
			return hasRefreshToken;
		}

		public void setHasRefreshToken(Boolean hasRefreshToken) {
			this.hasRefreshToken = hasRefreshToken;
		}

	}

	public static class Payload {
		@NotNull
		String clientId;

		@NotNull
		String redirectUri;

		Long personaId;

		String userName;

		@NotNull
		String code;

		public String getClientId() {
			return clientId;
		}

		public String getRedirectUri() {
			return redirectUri;
		}

		public String getCode() {
			return code;
		}

		public Long getPersonaId() {
			return personaId;
		}

		public String getUserName() {
			return userName;
		}

	}

	public Map<String, Object> getResponseEntity(final javax.ws.rs.core.Response response)
			throws JsonParseException, JsonMappingException, IOException {
		return MAPPER.readValue(response.readEntity(String.class), new TypeReference<Map<String, Object>>() {
		});
	}

}
