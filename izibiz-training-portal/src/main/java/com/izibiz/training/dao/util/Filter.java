package com.izibiz.training.dao.util;

public class Filter {
	
	public static final int OP_EQUAL = 0;
    public static final int OP_NOT_EQUAL = 1;
    public static final int OP_LESS_THAN = 2;
    public static final int OP_GREATER_THAN = 3;
    public static final int OP_LESS_OR_EQUAL = 4;
    public static final int OP_GREATER_OR_EQUAL = 5;
    public static final int OP_IN = 6;
    public static final int OP_NOT_IN = 7;
    public static final int OP_IS_NULL = 8;    
    public static final int OP_IS_NOT_NULL = 9;    
       
    public static final int ORDER_ASC = 200;
    public static final int ORDER_DESC = 201;
    
    public static final int DISTINCT = 300;
    public static final int DISTINCT_ROOT_ENTITY = 301;

}
