package com.udacity.cart.model;

import java.util.Objects;

public class User {
	private String userName;
	private UserType userType;
	private double credit;

	public User(String userName, UserType userType, double credit) {
		this.userName = userName;
		this.userType = userType;
		this.credit = credit;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Double.compare(user.getCredit(), getCredit()) == 0 &&
			Objects.equals(getUserName(), user.getUserName()) &&
			getUserType() == user.getUserType();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUserName(), getUserType(), getCredit());
	}

	@Override
	public String toString() {
		return "User{" +
			"userName='" + userName + '\'' +
			", userType=" + userType +
			", credit=" + credit +
			'}';
	}
}
