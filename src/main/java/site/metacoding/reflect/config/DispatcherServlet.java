package site.metacoding.reflect.config;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
		MemberController memberController = new MemberController();
		
		UtilsLog.getInstance().info(TAG, "doGet");
		UtilsLog.getInstance().info(TAG, req.getRequestURI()); // 끝주소
		UtilsLog.getInstance().info(TAG, req.getRequestURL().toString()); // 풀주소

		String identifier = req.getRequestURI(); // 끝주소를 알 수 있음

		// 리플렉션 발동
		Method[] methods = memberController.getClass().getDeclaredMethods(); 
		for(Method method : methods) {
			UtilsLog.getInstance().info(TAG, method.getName());
			
			String idf = identifier.replace("/", "");
			
			if(idf.equals(method.getName())) { // 요청 주소와 같은 이름이 있으면
				UtilsLog.getInstance().info(TAG, idf + " 메서드를 실행합니다.");
				try {
					method.invoke(memberController, req, resp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
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
