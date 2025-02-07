package med.voll.api.infra.security.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebFilter(urlPatterns = "/*")
@Component
public class LogFilter implements Filter {

  private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    String method = request.getMethod();
    String uri = request.getRequestURI();
    String timestamp = LocalDateTime.now().format(FORMATTER);
    System.out.println("[" + timestamp + "] Requisição recebida: " + method + " " + uri);
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
