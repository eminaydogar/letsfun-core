package com.easoft.letsfun.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class PasswordCrypter {

	private final static int keyStrength = 10;
	private static BCryptPasswordEncoder SINGLETON = null;

	private PasswordCrypter() {}

	public static PasswordEncoder instance() {
		if (SINGLETON == null)
			SINGLETON = new BCryptPasswordEncoder(keyStrength);
		return SINGLETON;
	}

}
