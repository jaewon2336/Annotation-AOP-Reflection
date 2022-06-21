package site.metacoding.reflect.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import site.metacoding.reflect.config.MessageConverter;
import site.metacoding.reflect.config.ViewResolver;
import site.metacoding.reflect.config.web.RequestMapping;
import site.metacoding.reflect.domain.Member;
import site.metacoding.reflect.util.UtilsLog;

// API
public class MemberController {
	
	private static final String TAG = "MemberController : ";

	/*	(GET) /join 요청이 오면 호출	*/
	@RequestMapping("/join")
	public void join(Member member, HttpServletRequest req, HttpServletResponse resp) {
		UtilsLog.getInstance().info(TAG, "join()");
		UtilsLog.getInstance().info(TAG, "Service가 호출되어 회원가입 완료되었습니다.");
		UtilsLog.getInstance().info(TAG, member.getUsername() + ", " + member.getPassword());
		req.setAttribute("username", member.getUsername());
		
		// 스프링에서 return "main.jsp"; 와 같은것 = AOP 
		ViewResolver.resolve("main.jsp", req, resp);
	}
	
	/*	(GET) /login 요청이 오면 호출	*/
	@RequestMapping("/login")
	public void login(HttpServletRequest req, HttpServletResponse resp) {
		UtilsLog.getInstance().info(TAG, "login()");
		UtilsLog.getInstance().info(TAG, "Service가 호출되어 로그인이 완료되었습니다.");
		HttpSession session = req.getSession();
		session.setAttribute("principal", new Member(1, "ssar", "1234"));
		
		ViewResolver.resolve("main.jsp", req, resp);
	}
	
	/*	(GET) /findById 요청이 오면 호출	*/
	@RequestMapping("/findById")
	public void findById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		UtilsLog.getInstance().info(TAG, "findById()");
		UtilsLog.getInstance().info(TAG, "Service가 호출되어 Member를 찾았습니다.");
		Member memberEntity = new Member(1, "ssar", "1234");
		
		MessageConverter.resolve(memberEntity, resp);
	}
}
