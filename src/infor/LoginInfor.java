package infor;

import java.io.Serializable;

public class LoginInfor implements Serializable {
	String encryptNameAndPasswordLogin;

	public String getEncryptNameAndPassword() {
		return encryptNameAndPasswordLogin;
	}

	public void setEncryptNameAndPassword(String encryptNameAndPassword) {
		this.encryptNameAndPasswordLogin = encryptNameAndPassword;
	}
}
