package net.guides.springboot.crud.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import net.guides.springboot.crud.Util.JwtUtil;
import net.guides.springboot.crud.service.CustomeUserDetailsService;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtil jwtUtil;
	

	@Autowired
	private CustomeUserDetailsService service;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authorizationHeaders = request.getHeader("Authorization");
		String token=null;
		String username=null;
		if(authorizationHeaders !=null && authorizationHeaders.startsWith("Bearer "))
		{
			token=authorizationHeaders.substring(7);
			username=jwtUtil.extractUsername(token);
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
		UserDetails userdetails=	service.loadUserByUsername(username);
		if (jwtUtil.validateToken(token, userdetails)) {

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
		}
		filterChain.doFilter(request, response);
		
	}
}
