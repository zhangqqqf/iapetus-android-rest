package net.tatans.android.common.security;

/**
 * 用户被禁用异常
 */
@SuppressWarnings("serial")
public class DisabledException extends AccountStatusException {
	public DisabledException() {
	}

	public DisabledException(String msg) {
		super(msg);
	}

	public DisabledException(String msg, Object extrabase) {
		super(msg, extrabase);
	}
}
