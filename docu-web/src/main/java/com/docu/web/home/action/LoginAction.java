package com.docu.web.home.action;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.service.uribroker.uri.URIBroker;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.docu.components.util.StringUtils;
import com.docu.user.dto.User;
import com.docu.user.service.UserService;
import com.docu.web.common.context.EnvUtils;

public class LoginAction {
	private final static String REDIRECT_URL = "redirectUrl";
	private final static org.slf4j.Logger log4j = LoggerFactory.getLogger(LoginAction.class);
	
	@Autowired
	private URIBrokerService uriBrokerService;
	
	@Autowired
	private UserService userService;
	
	public void doLogin(TurbineRunData rundata, Context context){
		ParameterParser pars = rundata.getParameters();
		String redirectUrl = pars.getString("redirectUrl");
		String loginUserId = pars.getString("loginUserId");
		String password = pars.getString("password");
		String errmsg = null;
		try {
			User user = userService.queryUser(loginUserId);
			if (user == null || !password.equals(user.getPassword())) {
				errmsg="Please input right of UserId and Password!";
				return;
			}
			HttpSession session = rundata.getRequest().getSession();
			session.setAttribute("loginUserId", loginUserId);
			session.setMaxInactiveInterval(15*60);//15 minutes
			context.put("loginUserId", loginUserId);
			
			if (StringUtils.isEmpty(redirectUrl)) {
				URIBroker loginPageURI = uriBrokerService.getURIBroker("loginLink"); 
				String page = loginPageURI.render();
				redirectUrl = EnvUtils.addContextPath(page, rundata); 
			}
			rundata.setRedirectLocation(redirectUrl);
		} catch (Exception e) {
			errmsg =(e.getMessage() == null ? "NullPointerException" : e.getMessage());
			log4j.error("", errmsg);
		} finally {
			if(StringUtils.isNotEmpty(errmsg)){
				rundata.setRedirectTarget("/index.vm");
				context.put(REDIRECT_URL, redirectUrl);
				context.put("loginUserId", loginUserId);
				context.put("errorMsg", errmsg); 
			}
		}
	}
	
	public void doLoginOut(TurbineRunData rundata, Context context){
	    try {
            rundata.getRequest().getSession().invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rundata.setRedirectTarget("/index.vm");
        }
	}
	
	public void doJsonTest(TurbineRunData rundata, Context context){
		HttpSession session = rundata.getRequest().getSession();
		session.setAttribute("data", "test data");
	}
}
