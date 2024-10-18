package vn.lqk.controllers;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.lqk.services.IUserService;
import vn.lqk.services.UserServiceImp;
import vn.lqk.utils.*;

@WebServlet(urlPatterns = { "/quen-mat-khau" })
public class ResetPassController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		req.setAttribute("username", username);
		req.getRequestDispatcher("/views/resetpassword.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String password = req.getParameter("newPassword");
		String passwordVerify = req.getParameter("confirmPassword");

		IUserService service = new UserServiceImp();
		String alertMsg = "";

		if (!service.checkExistUsername(username)) {
			alertMsg = "Tài khoản không tồn tại!";
			req.setAttribute("alert", alertMsg);
			RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.RESETPASS);
			dispatcher.forward(req, resp);
			return;
		}

		if (password != null && passwordVerify != null && password.equals(passwordVerify)) {

			boolean isSuccess = service.resetpass(username, password);

			if (isSuccess) {
				req.setAttribute("alert", alertMsg);
				resp.sendRedirect("./dang-nhap");
			} else {
				alertMsg = "Cập nhật không thành công!";
				req.setAttribute("alert", alertMsg);
				RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.RESETPASS);
				dispatcher.forward(req, resp);
			}
		} else {
			alertMsg = "Mật khẩu không khớp!";
			req.setAttribute("alert", alertMsg);
			RequestDispatcher dispatcher = req.getRequestDispatcher(Constant.RESETPASS);
			dispatcher.forward(req, resp);
		}
	}
}
