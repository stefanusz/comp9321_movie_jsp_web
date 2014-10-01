package ass2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterCommand implements Command{

	@Override
	public boolean execute(HttpServletRequest request, HttpServletResponse response) {
		
		String username = (String) request.getParameter("username");
		String firstName = (String) request.getParameter("firstName");
		String lastName = (String) request.getParameter("lastName");
		String nickname = (String) request.getParameter("nickname");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		String password2 = (String) request.getParameter("password2");
		
		username = username.toLowerCase();
	    firstName = firstName.toLowerCase();
	    lastName = lastName.toLowerCase();
	    nickname = nickname.toLowerCase();
	    email = email.toLowerCase();
		
	    if(username=="" || firstName=="" || nickname=="" || email=="" || password=="" || password2==""){
			//error asd
			return false;
		}
		if(password != password2){
			//error
			return false;
		}
		
		return true;
		
	   
	}

}
