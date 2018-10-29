package ua.edu.ukma.gpd.storage.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import ua.edu.ukma.gpd.storage.annotation.Email;
import ua.edu.ukma.gpd.storage.annotation.PasswordsMatch;
import ua.edu.ukma.gpd.storage.annotation.UniqueEmail;

@PasswordsMatch(message = "Password does not match")
public class SignupFormDto {

	@Email(message = "Email address is in an invalid format")
	@UniqueEmail(message = "This email is already registered, try another one")
	private String email;
	
	@Size(min = 6, message = "Too short password: it must be at least 6 characters")
	@Size(max = 24, message = "Too long password: it must be no more than 24 characters")
	private String password;
	
	private String passwordRepeat;

	@NotBlank(message = "Please, enter your first name")
	@Size(max = 64, message = "Name must be no more than 64 characters long")
	private String name;

	@NotBlank(message = "Please, enter your last name")
	@Size(max = 64, message = "Surname must be no more than 64 characters long")
	private String surname;
	
	@NotBlank(message = "Please, enter your phone")
	@Size(max = 24, message = "Name must be no more than 24 characters long")
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
