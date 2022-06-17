package site.metacoding.reflect.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import site.metacoding.reflect.util.UtilsLog;
import site.metacoding.reflect.web.MemberController;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet { // 톰캣이 들고있는 것, 모든 요청(Get, Post, Put, Delete)의 진입점이 됨

	private static final long serialVersionUID = 1L;
	private static final String TAG = "DispatcherServlet : ";

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UtilsLog.getInstance().info(TAG, "doDelete()");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberController controller = new MemberController();
		
		UtilsLog.getInstance().info(TAG, "doGet");
		UtilsLog.getInstance().info(TAG, req.getRequestURI()); // 끝주소
		UtilsLog.getInstance().info(TAG, req.getRequestURL().toString()); // 풀주소

		String identifier = req.getRequestURI();

		if (identifier.equals("/join")) {
			controller.join(req, resp);
		} else if (identifier.equals("/login")) {
			controller.login(req, resp);
		} else if (identifier.equals("/findById")) {
			controller.findById(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UtilsLog.getInstance().info(TAG, "doPost()");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UtilsLog.getInstance().info(TAG, "doPut()");
	}

}
