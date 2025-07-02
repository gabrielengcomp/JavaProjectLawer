package shared;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.EmailException;

public class Email implements Serializable{
	
	private static final long serialVersionUID = 5459934967815925131L;
	private final String email;
	private final String validator = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	
	public Email(String email) throws EmailException {
		
		if (validateEmailAddress(email)) {
			this.email = email;
		}
		else {
			throw new EmailException("Invalid email.");
		}
	}
	
	private boolean validateEmailAddress(String address) {
		
		Pattern p = Pattern.compile(validator);
		Matcher m = p.matcher(address);
		
		if (m.matches()) {
			return true;
		}
		
		return false;
	}

	public String getEmail() {
		return email;
	}
}
