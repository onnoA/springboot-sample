package com.onnoa.springboot.redis.utils;

public interface KeyPrefix {

	public int expiredTime();

	public String prefix();
}
