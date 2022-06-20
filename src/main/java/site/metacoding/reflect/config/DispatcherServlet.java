package site.metacoding.reflect.config;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import site.metacoding.reflect.config.web.RequestMapping;
import site.metacoding.reflect.domain.Member;
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
		for(Method method : methods) { // memberController가 갖고있는 모든 메서드 순회 (= 리플렉션)
			Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class); // 리턴타입 : 어노테이션 타입
			RequestMapping requestMapping = (RequestMapping) annotation;
			
			if(requestMapping.value().equals(identifier)) {
				
				try {
					Parameter[] params = method.getParameters();
					Object[] queue = new Object[params.length];
					
					for(int i = 0; i < params.length; i++) { // 해당 메서드가 갖고있는 모든 파라미터 순회
						
						if(params[i].getType().equals(HttpServletRequest.class)) { // 1. HttpServletRequest를 찾았다 > req 넣어줘
							queue[i] = req;
						} else if (params[i].getType().equals(HttpServletResponse.class)) { // 2. HttpServletResponse를 찾았다 > resp 넣어줘
							queue[i] = resp;
						} else { // 3. Member를 찾았다 > 없네? > new해서 넣어줘
							Class<?> cls = params[i].getType(); // 타입 (= .class)
							Constructor<?> constructor = cls.getConstructor(); // 생성자
							queue[i] = constructor.newInstance();
						}
					}
					
					method.invoke(memberController, queue); // 메서드 실행, 배열을 넣어놓으면 순서대로 착착 파라미터로 들어감
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				break;
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
