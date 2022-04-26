package com.example.demo.common.exception;


import com.example.demo.common.util.StringUtil;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Collection;

//import org.apache.commons.codec.binary.StringUtils;


/**
 * 异常的断言，当断言不成立时，抛出SOA异常
 * @author 沈杭春
 * @since 2016-11-7
 */


public class MyExceptionAssert {
	/**
	 * Assert a boolean expression, throwing <code>GMSOAException</code>
	 * if the test result is <code>false</code>.
	 * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
	 * @param expression a boolean expression
	 * @param message the exception message to use if the assertion fails
	 * @throws MyException if expression is <code>false</code>
	 */
	public static void isTrue(boolean expression, String message) {
		isTrue(expression, message,null, null);
	}

	public static void isTrue(boolean expression, String message,Object errorData) {
		isTrue(expression, message,null, errorData);
	}

	public static void isTrue(boolean expression, IExceptionEnum exceptionEnum) {
		isTrue(expression, null,exceptionEnum, null);
	}

	public static void isTrue(boolean expression, IExceptionEnum exceptionEnum,Object errorData) {
		isTrue(expression, null,exceptionEnum, errorData);
	}


	private static void isTrue(boolean expression, String message,IExceptionEnum exceptionEnum, Object errorData) {
		if (!expression) {
			throw new MyException(message,exceptionEnum,errorData);
		}
	}

	/**
	 * Assert that an object is <code>null</code> .
	 * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 * @throws MyException if the object is not <code>null</code>
	 */
	public static void isNull(Object object, String message) {
		boolean expression = object == null;
		isTrue(expression, message,null, null);
	}

	public static void isNull(Object object, String message,Object errorData) {
		boolean expression = object == null;
		isTrue(expression, message,null, errorData);
	}

	public static void isNull(Object object, IExceptionEnum exceptionEnum) {
		boolean expression = object == null;
		isTrue(expression, null,exceptionEnum, null);
	}

	public static void isNull(Object object, IExceptionEnum exceptionEnum,Object errorData) {
		boolean expression = object == null;
		isTrue(expression, null,exceptionEnum, errorData);
	}
	
	/**
	 * 判断值是否为不空，为空抛出异常
	 * @param object
	 * @param message
	 * @author 沈杭春
	* @since 2016-11-7
	 */
	public static void isNotNull(Object object, String message) {
		boolean expression = object != null;
		isTrue(expression, message,null, null);
	}

	public static void isNotNull(Object object, String message,Object errorData) {
		boolean expression = object != null;
		isTrue(expression, message,null, errorData);
	}

	public static void isNotNull(Object object, IExceptionEnum exceptionEnum) {
		boolean expression = object != null;
		isTrue(expression, null,exceptionEnum, null);
	}

	public static void isNotNull(Object object, IExceptionEnum exceptionEnum,Object errorData) {
		boolean expression = object != null;
		isTrue(expression, null,exceptionEnum, errorData);
	}

	
	/**
	 * 判断是否不为空， 如果为空（null/单个空格/多个空格），则抛出异常
	 * @param text 判断的字符串
	 * @param message 错误信息
	 * @author 沈杭春
	* @since 2016-11-7
	 */
	public static void isNotBlank(String text, String message) {
		boolean expression = StringUtil.isNotNull(text);
		isTrue(expression, message,null, null);
	}

	public static void isNotBlank(String text, String message,Object errorData) {
		boolean expression = StringUtil.isNotNull(text);
		isTrue(expression, message,null, errorData);
	}

	public static void isNotBlank(String text, IExceptionEnum exceptionEnum) {
		boolean expression = StringUtil.isNotNull(text);
		isTrue(expression, null,exceptionEnum, null);
	}

	public static void isNotBlank(String text, IExceptionEnum exceptionEnum,Object errorData) {
		boolean expression = StringUtil.isNotNull(text);
		isTrue(expression, null,exceptionEnum, errorData);
	}

	
	/**
	 * 判断值是否有值， 如果为空或0，则抛出异常
	 * @param value 判断的值
	 * @param message 错误信息
	 * @author 沈杭春
	* @since 2016-11-7
	 */
	public static void hasValue(String value, String message) {
		boolean expression = !(value == null || value.equals(0L));
		isTrue(expression, message,null, null);
	}

	public static void hasValue(String value, String message,Object errorData) {
		boolean expression = !(value == null || value.equals(0L));
		isTrue(expression, message,null, errorData);
	}

	public static void hasValue(String value, IExceptionEnum exceptionEnum) {
		boolean expression = !(value == null || value.equals(0L));
		isTrue(expression, null,exceptionEnum, null);
	}

	public static void hasValue(String value, IExceptionEnum exceptionEnum,Object errorData) {
		boolean expression = !(value == null || value.equals(0L));
		isTrue(expression, null,exceptionEnum, errorData);
	}

	
	/**
	 * 判断值是否有值， 如果为空或0，则抛出异常
	 * @param value 判断的值
	 * @param message 错误信息
	 * @author 沈杭春
	* @since 2016-11-7
	 */
	public static void hasValue(Integer value, String message) {
		boolean expression = !(value == null || value.equals(0));
		isTrue(expression, message,null, null);
	}

	public static void hasValue(Integer value, String message,Object errorData) {
		boolean expression = !(value == null || value.equals(0));
		isTrue(expression, message,null, errorData);
	}

	public static void hasValue(Integer value, IExceptionEnum exceptionEnum) {
		boolean expression = !(value == null || value.equals(0));
		isTrue(expression, null,exceptionEnum, null);
	}

	public static void hasValue(Integer value, IExceptionEnum exceptionEnum,Object errorData) {
		boolean expression = !(value == null || value.equals(0));
		isTrue(expression, null,exceptionEnum, errorData);
	}

	/**
	 * 判断值是否有值， 如果为空或0，则抛出异常
	 * @param value 判断的值
	 * @param message 错误信息
	 * @author 沈杭春
	* @since 2016-11-7
	 */
	public static void hasValue(BigDecimal value, String message) {
		boolean expression = !(value == null || value.compareTo(BigDecimal.ZERO) == 0);
		isTrue(expression, message,null, null);
	}

	public static void hasValue(BigDecimal value, String message,Object errorData) {
		boolean expression = !(value == null || value.compareTo(BigDecimal.ZERO) == 0);
		isTrue(expression, message,null, errorData);
	}

	public static void hasValue(BigDecimal value, IExceptionEnum exceptionEnum) {
		boolean expression = !(value == null || value.compareTo(BigDecimal.ZERO) == 0);
		isTrue(expression, null,exceptionEnum, null);
	}

	public static void hasValue(BigDecimal value, IExceptionEnum exceptionEnum,Object errorData) {
		boolean expression = !(value == null || value.compareTo(BigDecimal.ZERO) == 0);
		isTrue(expression, null,exceptionEnum, errorData);
	}


	/**
	 * Assert that an array has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * <pre class="code">Assert.notEmpty(array, "The array must have elements");</pre>
	 * @param array the array to check
	 * @param message the exception message to use if the assertion fails
	 * @throws MyException if the object array is <code>null</code> or has no elements
	 */
	public static void isNotEmpty(Object[] array, String message) {
		boolean expression = !ObjectUtils.isEmpty(array);
		isTrue(expression, message,null, null);
	}

	public static void isNotEmpty(Object[] array, String message,Object errorData) {
		boolean expression = !ObjectUtils.isEmpty(array);
		isTrue(expression, message,null, errorData);
	}

	public static void isNotEmpty(Object[] array, IExceptionEnum exceptionEnum) {
		boolean expression = !ObjectUtils.isEmpty(array);
		isTrue(expression, null,exceptionEnum, null);
	}

	public static void isNotEmpty(Object[] array, IExceptionEnum exceptionEnum,Object errorData) {
		boolean expression = !ObjectUtils.isEmpty(array);
		isTrue(expression, null,exceptionEnum, errorData);
	}

	/**
	 * Assert that a collection has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * <pre class="code">Assert.notEmpty(collection, "Collection must have elements");</pre>
	 * @param collection the collection to check
	 * @param message the exception message to use if the assertion fails
	 * @throws MyException if the collection is <code>null</code> or has no elements
	 */

	public static void isNotEmpty(Collection<?> collection, String message) {
		boolean expression = !CollectionUtils.isEmpty(collection);
		isTrue(expression, message,null, null);
	}

	public static void isNotEmpty(Collection<?> collection, String message,Object errorData) {
		boolean expression = !CollectionUtils.isEmpty(collection);
		isTrue(expression, message,null, errorData);
	}

	public static void isNotEmpty(Collection<?> collection, IExceptionEnum exceptionEnum) {
		boolean expression = !CollectionUtils.isEmpty(collection);
		isTrue(expression, null,exceptionEnum, null);
	}

	public static void isNotEmpty(Collection<?> collection, IExceptionEnum exceptionEnum,Object errorData) {
		boolean expression = !CollectionUtils.isEmpty(collection);
		isTrue(expression, null,exceptionEnum, errorData);
	}
}