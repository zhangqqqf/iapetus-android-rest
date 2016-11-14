package net.tatans.android.common.security;

/**
 * 账号状态异常
 */
@SuppressWarnings("serial")
public class AccountStatusException extends AuthenticationException {
	public AccountStatusException() {
	}

	public AccountStatusException(String msg) {
		super(msg);
	}

	public AccountStatusException(String msg, Object extrabase) {
		super(msg, extrabase);
	}
}
