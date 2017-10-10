package chen.yiheng.estore.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class ApacheMailUtils {
	public static boolean sendMail(String sendto, String subject, String msg) {
		try {
			// Email email = new SimpleEmail();
			Email email = new HtmlEmail();
			// hostname of the sending server
			email.setHostName("smtp.qq.com");
			email.setSmtpPort(465);
			// account number and password
			email.setAuthenticator(new DefaultAuthenticator("314323616@qq.com",
					"yrtygfpezinlbgfi"));
			email.setSSLOnConnect(true);
			email.setFrom("314323616@qq.com");
			email.setSubject(subject);
			// email.setMsg(msg);
			email.setContent(msg, "text/html;charset=utf-8");
			email.addTo(sendto);
			email.send();
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			return false;
		}
	}

}
