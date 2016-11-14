package net.tatans.android.common.security;

/**
 * 登录异常
 */
@SuppressWarnings("serial")
public class AuthenticationException extends Exception {
	private Object extrabase;

	public AuthenticationException() {

	}

	public AuthenticationException(String msg) {
		super(msg);
	}

	public AuthenticationException(String msg, Object extrabase) {
		super(msg);
		this.extrabase = extrabase;
	}

	/**
	 * Any additional base about the exception. Generally a
	 * <code>UserDetails</code> object.
	 * 
	 * @return extra base or <code>null</code>
	 */
	public Object getExtrabase() {
		return extrabase;
	}

	public void clearExtrabase() {
		this.extrabase = null;
	}
}
