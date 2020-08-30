package com.stockmarket.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.stockmarket.auth.models.UserPrincipal;
import com.stockmarket.auth.service.JWTTokenProvider;
import com.stockmarket.auth.service.UserAuthDetailsService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JWTTokenProvider tokenProvider;

	@Autowired
	private UserAuthDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info("Validating Token!!!!!");
		try {
			String jwt = getJwtFromRequest(request);
			if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				logger.info("Token is Valid ");
				String userNameFromToken = tokenProvider.getUserNameFromToken(jwt);
				UserPrincipal userDetails = (UserPrincipal) userDetailsService.loadUserByUsername(userNameFromToken);
				System.out.println(userDetails.getAuthorities());

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);

			}
		} catch (Exception ex) {
			logger.error("Could not set user authentication in security context", ex);
		}
		filterChain.doFilter(request, response);
	}

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}
}
