package in.pharmeasy.assignment.jwt.security;
 
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.util.matcher.RequestMatcher;
 
 
@Configuration
@EnableResourceServer
@EnableWebSecurity
@Order(-20)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
	

	@Autowired
	TokenStore tokenStore;
	
     
	    @Override
	    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			super.configure(resources);
			resources.tokenStore(tokenStore);
	    }
	 
	    @Override
	    public void configure(HttpSecurity http) throws Exception {
	    	 http.authorizeRequests().requestMatchers(new  OauthRequestMatcher()).permitAll();
	    	 http.csrf().disable().cors().disable();
		      
	    }
	    
	    private static class OauthRequestMatcher implements RequestMatcher {
	        @Override
	        public boolean matches(HttpServletRequest request) {
	           WebSecurityConfig.FormRequestMatcher formRequestMatcher= new WebSecurityConfig.FormRequestMatcher();
	           return !formRequestMatcher.matches(request);
	        }
	     }
	    
	    


	
}


