package com.izibiz.training.dao.common;

public interface LogService {
	
	void putErrorLog(String operation, Throwable exception, String... messages);

	void putErrorLog(String operation, Object relatedObject, Throwable exception, String... messages);

	void putDebugLog(String operation, String... messages);

	void putDebugLog(String operation, Object relatedObject, String... messages);

	void putWarnLog(String operation, String message);

	void putWarnLog(String operation, String message, Throwable exception);

	void putWarnLog(String operation, Object relatedObject, String message, Throwable exception);

	void putWarnLog(String operation, Object relatedObject, String message);

	void putInfoLog(String operation, String... messages);

	void putInfoLog(String operation, Object relatedObject);

	void putInfoLog(String operation, Object relatedObject, String... messages);

	void putInfoLog(String operation, Object relatedObject, Throwable exception, String... messages);

}
