package digital.b2w.planets.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(0)
public class AuthenticationFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	@Value("${server.servlet.context-path}")
	private String path;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (isPreflight(request)) {
			filterChain.doFilter(request, response);
			return;
		}

		/*
		String token = httpRequest.getHeader("Authorization");

		if (token == null) {
			token = request.getParameter("token");
		}

		if (isSwaggerRequest(httpRequest)) {
			if (!possuiAcesso(token)) {				
				String enderecoIp = request.getRemoteAddr();
				String path = httpRequest.getRequestURI();
				log.error(String.format("Falha na Autenticação: Path: %s, IP: %s, Token: %s", path, enderecoIp, token));
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso não autorizado");
				return;
			}
		}
		*/
		
		filterChain.doFilter(request, response);
	}
	
	private boolean possuiAcesso(String token) {
		return token.equalsIgnoreCase("bbeacbcfb14859cdceb31647e5d7ae77");
	}

	private boolean isPreflight(HttpServletRequest request) {
		return HttpMethod.OPTIONS.toString().equals(request.getMethod());
	}

	private boolean isSwaggerRequest(HttpServletRequest httpRequest) {
		String raiz = path + "/";
		return !httpRequest.getRequestURI().contains("swagger") 
				&& !httpRequest.getRequestURI().contains("api-docs")
				&& !raiz.equals(httpRequest.getRequestURI());
	}
}