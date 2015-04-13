package com.ecwid.consul.v1;

import com.ecwid.consul.ConsulException;
import com.ecwid.consul.transport.RawResponse;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class OperationException extends ConsulException {

	private final int statusCode;
	private final String statusMessage;
	private final String content;

	public OperationException(int statusCode, String statusMessage) {
		this(statusCode, statusMessage, null);
	}
	
	public OperationException(int statusCode, String statusMessage, String content) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.content = content;
	}

	public OperationException(RawResponse rawResponse) {
		this(rawResponse.getStatusCode(), rawResponse.getStatusMessage(), rawResponse.getContent());
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OperationException{");
        sb.append("statusCode=").append(statusCode);
        sb.append(", statusMessage='").append(statusMessage).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
