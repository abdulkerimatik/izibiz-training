package com.izibiz.training.dao.util;

public class FilterQuery {

	private String field;
	private int operand;
	private Object value;
	
	public FilterQuery(int operand) {
		this.operand = operand;
	}

	public FilterQuery(String field, int operand) {
		this(operand);
		this.field = field;
	}

	public FilterQuery(String field, int operand, Object value) {
		this(field, operand);
		this.value = value;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getOperand() {
		return operand;
	}

	public void setOperand(int operand) {
		this.operand = operand;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
