package com.onnoa.springboot.redis.utils;

public abstract class BasePrefix implements KeyPrefix {

	private int expiredTime;
	private String prefix;

	public BasePrefix(String prefix, int expiredTime) {
		this.expiredTime = expiredTime;
		this.prefix = prefix;
	}

	public BasePrefix(String prefix) {// 0表示永不过期
		this(prefix, 0);
	}

	@Override
	public int expiredTime() {
		return expiredTime;
	}

	@Override
	public String prefix() {
		return this.prefix;
	}

}
