package am.gitc.chat.servlets;

import am.gitc.chat.models.User;
import am.gitc.chat.services.UserService;
import am.gitc.chat.services.impl.UserServiceImpl;
import am.gitc.chat.servlets.validator.RequestValidator;
import am.gitc.chat.util.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RegisterServlet extends HttpServlet {

  private final UserService userService;

  public RegisterServlet() {
    this.userService = new UserServiceImpl();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("WEB-INF/jsp-pages/register.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    RequestValidator<User> requestValidator = validate(req);
    if (requestValidator.isValid()) {
      User user = requestValidator.getValue();
      boolean userExist = this.userService.userExist(user.getEmail());
      if (userExist) {
        req.setAttribute("emailError", "User with this email already exists.");
        req.getRequestDispatcher("WEB-INF/jsp-pages/register.jsp").forward(req, resp);
      } else {
        this.userService.save(user);
        req.getSession().setAttribute("loginSuccessfully", "Successfully registered!");
        resp.sendRedirect("/login");
      }
    } else {
      for (Map.Entry<String, String> error : requestValidator.getErrors().entrySet()) {
        req.setAttribute(error.getKey(), error.getValue());
      }
      req.getRequestDispatcher("WEB-INF/jsp-pages/register.jsp").forward(req, resp);
    }
  }

  private static RequestValidator<User> validate(HttpServletRequest request) {
    RequestValidator<User> validator = new RequestValidator<>();
    String firstName = request.getParameter("firstName");

    if (DataValidator.isNullOrEmpty(firstName)) {
      validator.addError("firstNameError", "First Name is required.");
    }

    String lastName = request.getParameter("lastName");
    if (DataValidator.isNullOrEmpty(lastName)) {
      validator.addError("lastNameError", "Last Name is required.");
    }

    String email = request.getParameter("email");
    if (DataValidator.isNullOrEmpty(email)) {
      validator.addError("emailError", "Email is required.");
    }

    String password = request.getParameter("password");
    if (DataValidator.isNullOrEmpty(password)) {
      validator.addError("passwordError", "Password is required.");
    } else {
      String confirmPassword = request.getParameter("confirmPassword");
      if (DataValidator.isNullOrEmpty(confirmPassword)) {
        validator.addError("confirmPasswordError", "Confirm Password is required.");
      } else if (!password.trim().equals(confirmPassword.trim())) {
        validator.addError("confirmPasswordError", "Passwords does not match.");
      }
    }
    if (validator.isValid()) {
      User user = new User();
      user.setFirstName(firstName.trim());
      user.setLastName(lastName.trim());
      user.setEmail(email.trim());
      user.setPassword(password.trim());
      validator.setValue(user);
    }
    return validator;
  }
}
