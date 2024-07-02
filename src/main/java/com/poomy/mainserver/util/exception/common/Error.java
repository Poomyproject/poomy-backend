package com.poomy.mainserver.util.exception.common;

public interface Error {
	String getCode();

	String getMessage(String... values);
}
