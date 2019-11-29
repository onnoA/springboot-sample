package com.onnoa.springboot.commons.utils.redislock;

public interface KeyPrefix {

	public int expiredTime();

	public String prefix();
}
