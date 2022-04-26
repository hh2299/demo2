package com.example.demo.common.exception;

/**
 * SOA统一异常类
 * @author 沈杭春
 * @since 2016-11-7
 */
public class MyException extends RuntimeException {
	private static final long serialVersionUID = 6029038041214105194L;

	/**
	 * 异常对应的错误类型
	 */
	private IExceptionEnum exceptionEnum;

	/**
	 * 对应的异常数据
	 */
	private Object data;

	public MyException() {
		super();
		this.exceptionEnum = CommonExceptionEnum.SYSTEM_ERROR;
	}

    public MyException(String message) {
        super(message);
        this.exceptionEnum = CommonExceptionEnum.SYSTEM_ERROR;
    }


    public MyException(IExceptionEnum exceptionEnum) {
		super(exceptionEnum.message());
		this.exceptionEnum = exceptionEnum;
	}

    public MyException(String message, IExceptionEnum exceptionEnum) {
        super(message != null ? message: exceptionEnum.message());
        this.exceptionEnum = exceptionEnum;
    }

	public MyException(IExceptionEnum exceptionEnum, Object data) {
		super(exceptionEnum.message());
		this.exceptionEnum = exceptionEnum;
		this.data = data;
	}

    public MyException(String message, IExceptionEnum exceptionEnum, Object data) {
        super(message != null ? message: exceptionEnum.message());
        this.exceptionEnum = exceptionEnum;
        this.data = data;
    }
}