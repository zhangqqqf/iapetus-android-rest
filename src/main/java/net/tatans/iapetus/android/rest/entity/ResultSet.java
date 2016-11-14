package net.tatans.iapetus.android.rest.entity;

public class ResultSet {

	/**
	 * response code 0 success -1 error
	 */
	private int code;
	private Object result;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
