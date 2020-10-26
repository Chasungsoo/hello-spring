package hello.hellospring.util;

import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Utility class for Spring Security.
 */
@Component
public class SecurityUtils {
	public static boolean hasAuthority(String authority) {
		return hasRole(authority);
	}

	public static boolean hasAnyAuthority(String... authorities) {
		return hasAnyRole(authorities);
	}

	public static boolean hasRole(String role) {
		return getAuthorities().contains(role);
	}

	public static boolean hasRole(Role role) {
		return getAuthorities().contains(role.toString());
	}

	public static boolean hasAnyRole(String... roles) {
		Set<String> authorities = getAuthorities();
		for (String role : roles) {
			if (authorities.contains(role)) {
				return true;
			}
		}
		// No roles matches
		return false;
	}

	public static boolean hasAnyRole(Role... roles) {
		Set<String> authorities = getAuthorities();
		for (Role role : roles) {
			if (authorities.contains(role.toString())) {
				return true;
			}
		}
		// No roles matches
		return false;
	}

	public static boolean isAnonymous() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}
		return AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}

	public static boolean isAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}
		return !isAnonymous();
	}

	public static boolean isFullyAuthenticated() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}
		return !isAnonymous() && !isRememberMe();
	}

	public static boolean isRememberMe() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}
		return RememberMeAuthenticationToken.class.isAssignableFrom(authentication.getClass());
	}

	public static boolean isSwitchedUser() {
		return hasRole("ROLE_PREVIOUS_ADMINISTRATOR");
	}

	private static Set<String> getAuthorities() {
		return AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities());
	}

	/**
	 * If the current user has a specific authority (security role).
	 *
	 * <p>The name of this method comes from the isUserInRole() method in the Servlet API</p>
	 *
	 * @param authority the authorithy to check
	 * @return true if the current user has the authority, false otherwise
	 */
	public static boolean isCurrentUserInRole(String authority) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication != null) {
			if (authentication.getPrincipal() instanceof UserDetails) {
				UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
				return springSecurityUser.getAuthorities().contains(new SimpleGrantedAuthority(authority));
			}
		}
		return false;
	}

	/**
	 * Get the login of the current user.
	 *
	 * @return the login of the current user
	 */
	public static UserDetails getCurrentUser() {
		if(isAuthenticated()) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication == null) {
				return null;
			}
			return (UserDetails)authentication.getPrincipal();
		}
		return null;
	}

	/**
	 * Signout
	 */
	public static void signout() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}

}
