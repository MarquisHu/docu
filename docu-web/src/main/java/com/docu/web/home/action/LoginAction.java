package com.docu.web.home.action;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.citrus.service.requestcontext.parser.ParameterParser;
import com.alibaba.citrus.service.uribroker.URIBrokerService;
import com.alibaba.citrus.service.uribroker.uri.URIBroker;
import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.docu.components.log.Logger;
import com.docu.components.util.StringUtils;
import com.docu.user.dto.DocumentUser;
import com.docu.user.service.DocumentUserService;
import com.docu.web.common.context.EnvUtils;

public class LoginAction {
	private final static String REDIRECT_URL = "redirectUrl";
	private final static org.slf4j.Logger log4j = LoggerFactory.getLogger(LoginAction.class);
	
	@Autowired
	private URIBrokerService uriBrokerService;
	
	@Autowired
	private DocumentUserService userService;
	
	public void doLogin(TurbineRunData rundata, Context context){
		ParameterParser pars = rundata.getParameters();
		String redirectUrl = pars.getString("redirectUrl");
		String userId = pars.getString("userId");
		String password = pars.getString("password");
		String errmsg = null;
		try {
			Logger.svc(userId + "/" + password);
			DocumentUser user = userService.queryUser(userId);
			if (user == null || !password.equals(user.getPassword())) {
				errmsg="Please input right of UserId and Password!";
				return;
			}
			HttpSession session = rundata.getRequest().getSession();
			session.setAttribute("userId", user.getUserId());
			session.setAttribute("chineseName", user.getChineseName());
			session.setAttribute("englishName", user.getEnglishName());
			session.setAttribute("telphoneNumber", user.getTelphoneNumber());
			session.setMaxInactiveInterval(15*60);//15 minutes
			
			if (StringUtils.isEmpty(redirectUrl)) {
				URIBroker loginPageURI = uriBrokerService.getURIBroker("demoLink"); 
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
				context.put("userId", userId);
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
}