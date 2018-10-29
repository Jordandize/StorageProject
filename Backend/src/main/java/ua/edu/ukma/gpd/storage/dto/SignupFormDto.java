package ua.edu.ukma.gpd.storage.dto;

import javax.validation.constraints.NotBlank;

import ua.edu.ukma.gpd.storage.annotation.Email;
import ua.edu.ukma.gpd.storage.annotation.PasswordsMatch;

@PasswordsMatch
public class SignupFormDto {
	
	@Email
	private String email;
	
	@NotBlank
	private String password;

	@NotBlank
	private String passwordRepeat;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String surname;
	
	@NotBlank
	private String phone;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SignupFormDto [email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append(", passwordRepeat=");
		builder.append(passwordRepeat);
		builder.append(", name=");
		builder.append(name);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", phone=");
		builder.append(phone);
		builder.append("]");
		return builder.toString();
	}

}
