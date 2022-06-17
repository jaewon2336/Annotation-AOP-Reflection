package site.metacoding.reflect.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class MessageConverter {

	public static void resolve(Object data, HttpServletResponse resp) {
		try {
			resp.setContentType("application/json; charset=utf-8"); // 버퍼보다 먼저 설정해야함
			PrintWriter out = resp.getWriter(); // 클라이언트 브라우저에 버퍼 달기
			// out.println(data); // 브라우저가 해석 못해서 toString() 값이 나옴, json으로 변경해야한다
			
			Gson gson = new Gson();
			String responseBody = gson.toJson(data);
			
			out.println(responseBody);
			out.flush();
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
			out.println("<h1>JSON 변환에 실패하였습니다.</h1>");
			out.flush(); // 전송
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
