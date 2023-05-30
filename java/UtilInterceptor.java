package mirosimo.car_showroom2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import mirosimo.car_showroom2.Utils.ImageUtil;
import mirosimo.car_showroom2.service.MenuService;

/* 
 * Used for adding root menuItem and imgUtil attribute into model
 * because theese two are used almost in every view.
 * 
 * We don't have to setup theese attributes in Controllers 
 * So controllers are sustained little bit cleaner
 * 
 * */
public class UtilInterceptor  implements HandlerInterceptor {
	@Autowired
	private MenuService menuService;		
	
	@Override
	public boolean preHandle(
	  HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {	    
	    req.setAttribute("menuItem", menuService.getMenuById(1));
	    req.setAttribute("imgUtil", new ImageUtil());
	    return true;
	}
	
	/*@Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
            ModelAndView modelAndView) throws Exception {
        request.setAttribute("menuItem", menuService.getMenuById(1)); 

    }*/
}
