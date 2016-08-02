package com.ecwid.consul.v1;

import com.ecwid.consul.UrlParameters;
import com.ecwid.consul.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
public final class QueryParams implements UrlParameters {

	public static final QueryParams DEFAULT = new QueryParams(ConsistencyMode.DEFAULT);

	private final String datacenter;
	private final ConsistencyMode consistencyMode;

	private final long waitTime;
	private final long index;

	public QueryParams(String datacenter) {
		this(datacenter, ConsistencyMode.DEFAULT, -1, -1);
	}

	public QueryParams(ConsistencyMode consistencyMode) {
		this(null, consistencyMode, -1, -1);
	}

	public QueryParams(String datacenter, ConsistencyMode consistencyMode) {
		this(datacenter, consistencyMode, -1, -1);
	}

	public QueryParams(long waitTime, long index) {
		this(null, ConsistencyMode.DEFAULT, waitTime, index);
	}

	public QueryParams(String datacenter, ConsistencyMode consistencyMode, long waitTime, long index) {
		this.datacenter = datacenter;
		this.consistencyMode = consistencyMode;
		this.waitTime = waitTime;
		this.index = index;
	}

	public String getDatacenter() {
		return datacenter;
	}

	public ConsistencyMode getConsistencyMode() {
		return consistencyMode;
	}

	public long getWaitTime() {
		return waitTime;
	}

	public long getIndex() {
		return index;
	}

	@Override
	public List<String> toUrlParameters() {
		List<String> params = new ArrayList<String>();

		// add basic params
		if (datacenter != null) {
			params.add("dc=" + Utils.encodeValue(datacenter));
		}

		if (consistencyMode != ConsistencyMode.DEFAULT) {
			params.add(consistencyMode.name().toLowerCase());
		}

		if (waitTime != -1) {
			params.add("wait=" + waitTime + "s");
		}
		if (index != -1) {
			params.add("index=" + Utils.toUnsignedString(index));
		}

		return params;
	}
}
