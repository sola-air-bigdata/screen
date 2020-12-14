package com.nzkj.screen.Entity.DTO;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.Serializable;
import java.util.Set;

public class Result implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final int SUCCESS = 1;
	private static final int FAIL = 0;
	
	private static final String SUCCESS_MSG = "操作成功";
	private static final String FAIL_MSG = "操作失败";
	private static final String EMPTY_DTO_MSG = "对象为空";
	private static final String EMPTY_ID = "ID为空";
	private static final String EMPTY_SELLER_ID = "商家ID为空";
	private static final String SELLER_NOT_EXIST = "商家不存在";
	private static final String USER_NOT_EXIST = "用户不存在";
	
	/*信息*/
	private String message;
	/*状态码*/
	private int stateCode;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStateCode() {
		return stateCode;
	}
	public void setStateCode(Integer stateCode) {
		this.stateCode = stateCode;
	}
	public void set(Integer stateCode, String message) {
		this.message = message;
		this.stateCode = stateCode;
	}
	public void success() {
		this.message = SUCCESS_MSG;
		this.stateCode = SUCCESS;
	}

	public Result() {
	}

	public Result(String message, int stateCode) {
		this.message = message;
		this.stateCode = stateCode;
	}

	public static Result OK() {
		return new Result(SUCCESS_MSG, SUCCESS);
	}

	public static Result FAIL() {
		return new Result(FAIL_MSG, FAIL);
	}

	public static Result FAIL(String msg) {
		return new Result(msg, FAIL);
	}
	public void fail() {
		this.message = FAIL_MSG;
		this.stateCode = FAIL;
	}
	public void daoFlag(int flag) {
		if(flag != -1){
			this.stateCode = SUCCESS;
			this.message = SUCCESS_MSG;
		}else {
			this.stateCode = FAIL;
			this.message = FAIL_MSG;
		}
	}
	public void fail(String msg) {
		this.message = msg;
		this.stateCode = FAIL;
	}

	public void fail(int code, String msg) {
		this.stateCode = code;
		this.message = msg;
	}
	
	public void success(String msg) {
		this.message = msg;
		this.stateCode = SUCCESS;
	}
	public <T> boolean validate(Validator validator, T entity){
		Set<ConstraintViolation<T>> set = validator.validate(entity);
		if(CollectionUtils.isEmpty(set)){
			return true;
		}
		stateCode = FAIL;
		message = set.iterator().next().getMessage();
		return false;
	}
	public void emptyDto(){
		this.message = EMPTY_DTO_MSG;
		this.stateCode = FAIL;
	}
	
	public void emptyId() {
		this.message = EMPTY_ID;
		this.stateCode = FAIL;
	}

	public void emptySellerId() {
		this.message = EMPTY_SELLER_ID;
		this.stateCode = FAIL;
	}
	
	public void sellerNotExist() {
		this.message = SELLER_NOT_EXIST;
		this.stateCode = FAIL;
	}
	
	public void userNotExist() {
		this.message = USER_NOT_EXIST;
		this.stateCode = FAIL;
	}
	
}
