package site.metacoding.reflect.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// View를 찾아주고, request 유지하는 전략
public class ViewResolver {

	public static void resolve(String path, HttpServletRequest req, HttpServletResponse resp) {
		try {
			RequestDispatcher dis = req.getRequestDispatcher(path);
			dis.forward(req, resp);
		} catch (Exception e) {
			writeError(resp);
		}
	}
	
	private static void writeError(HttpServletResponse resp) {
		try {
			// header
			resp.setContentType("text/html; charset=utf-8");
			
			// body
			PrintWriter out = resp.getWriter();
			out.println("<h1>파일을 찾을 수 없습니다.</h1>");
			out.flush(); // 전송
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
