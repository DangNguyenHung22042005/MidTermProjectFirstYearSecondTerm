package infor;

import java.io.Serializable;

public class SignupInfor implements Serializable {
	String encryptNameAndPasswordSignup;

	public String getEncryptNameAndPassword() {
		return encryptNameAndPasswordSignup;
	}

	public void setEncryptNameAndPassword(String encryptNameAndPassword) {
		this.encryptNameAndPasswordSignup = encryptNameAndPassword;
	}
}
