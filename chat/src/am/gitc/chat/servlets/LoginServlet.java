package am.gitc.chat.servlets;

import am.gitc.chat.models.User;
import am.gitc.chat.services.UserService;
import am.gitc.chat.services.impl.UserServiceImpl;
import am.gitc.chat.util.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

  private final UserService userService;

  public LoginServlet() {
    this.userService = new UserServiceImpl();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("WEB-INF/jsp-pages/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    boolean isOk = true;
    String email = req.getParameter("email");
    if (DataValidator.isNullOrEmpty(email)) {
      req.setAttribute("emailError", "Email is required");
      isOk = false;
    }
    String password = req.getParameter("password");
    if (DataValidator.isNullOrEmpty(password)) {
      req.setAttribute("passwordError", "Password is required");
      isOk = false;
    }

    if (isOk) {
      Optional<User> userOptional = this.userService.getByEmail(email.trim());
      if (userOptional.isPresent()) {
        User user = userOptional.get();
        if (user.getPassword().equals(password.trim())) {
          req.getSession().setAttribute("user", user);
          resp.sendRedirect("/home");
          return;
        }
      }
    }
    if (isOk) {
      req.setAttribute("emailPasswordError", "Incorrect Email/Password");
    }
    req.getRequestDispatcher("WEB-INF/jsp-pages/login.jsp").forward(req, resp);
  }
}
