package ass2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Command {
	public abstract boolean execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
