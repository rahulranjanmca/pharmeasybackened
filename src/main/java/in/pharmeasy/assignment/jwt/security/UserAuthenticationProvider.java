package in.pharmeasy.assignment.jwt.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import in.pharmeasy.assignment.model.User;
import in.pharmeasy.assignment.service.UserService;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		User user = new User();
		user.setEmail(authentication.getPrincipal().toString());
		user = userService.getEntityByColumnNameAndValue("email", authentication.getPrincipal().toString());
		if (user == null) {
			throw new BadCredentialsException("BAD_USER_CREDENTIALS");
		} else {
			return login(authentication, user);
		}

	}

	public boolean supports(Class<?> arg0) {
		return true;
	}

	public Authentication login(Authentication authentication, User user) {
		try {
			String password = authentication.getCredentials().toString();

			if (!(password.equals(user.getPassword()))) {//

				throw new BadCredentialsException("PASSWORD_NOT_CORRECT");
			} else {
				Set<String> permissions = userService.getPermissionsById(user.getId());
				List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
				for (String permission : permissions) {
					SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permission);
					grantedAuthorities.add(authority);
				}

				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getId(),
						authentication.getCredentials(), grantedAuthorities);

				return auth;

			}
		} finally {

		}

	}

}