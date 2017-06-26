package _01_google;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

//@WebServlet(urlPatterns = { "/login" })
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		try {
			String idToken = req.getParameter("id_token");
			GoogleIdToken.Payload payLoad = DecodeToken.getPayload(idToken);
			/******************************************************/

			String Id = (String) payLoad.getSubject();
            String name = (String) payLoad.get("name");
            String pictureUrl = (String) payLoad.get("picture");
            String locale = (String) payLoad.get("locale");
            String familyName = (String) payLoad.get("family_name");
            String givenName = (String) payLoad.get("given_name");
            String Email = (String)payLoad.getEmail();
//			System.out.println("Id: " + Id);
//			System.out.println("name: " + name);
//			System.out.println("locale: " + locale);
//			System.out.println("familyName: " + familyName);
//			System.out.println("givenName: " + givenName);
//			System.out.println("Email: " + Email);
//			System.out.println("pictureUrl: " + pictureUrl);
			HttpSession session = req.getSession(true);
			session.setAttribute("Id", Id);
			session.setAttribute("name", name);
			session.setAttribute("locale", locale);
			session.setAttribute("familyName", familyName);			
			session.setAttribute("givenName", givenName);
			session.setAttribute("Email", Email);
			session.setAttribute("pictureUrl", pictureUrl);
			
			req.getRequestDispatcher("/_01_google/login_Success.jsp").forward(req, resp);
			return;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}