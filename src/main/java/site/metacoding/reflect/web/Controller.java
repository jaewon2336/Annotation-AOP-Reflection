package site.metacoding.reflect.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller {
	
	protected HttpServletRequest req;
	protected HttpServletResponse resp;

	public void injectRequest(HttpServletRequest req) {
		this.req = req;
	}
	
	public void injectResponse(HttpServletResponse resp) {
		this.resp = resp;
	}
}
