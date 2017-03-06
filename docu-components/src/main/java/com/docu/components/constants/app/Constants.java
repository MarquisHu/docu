package com.docu.components.constants.app;

public class Constants {
	public static final String MEMCACHED_SERVERS = "MemcachedServers";
	public static final String PAWN_KEY = "pawn_key";
	
	
	public static String  RESULT_SUCCESS                       = "0:success";
	public static String  RESULT_FAIL                          = "1:fail";
	public static String  RESULT_LASTEST_VERSION               = "100:the current version is the latest version";
	public static String  RESULT_ILLEGAL_ARGUMENT_FORMAT       = "101:[{key}]parameter type is wrong";
	public static String  RESULT_ILLEGAL_ARGUMENT_LEN          = "102:[{key}]parameter length is wrong";
	
	public static String DEFAULT_PASSWORD = "123456";
	public static String DEFAULT_ACTIVE_STATUS = "1";
	public static String USER_TYPE_ADMIN_NO = "0";
	public static String USER_TYPE_ADMIN_YES = "1";
	
	public static String BILL_UUID_SEQUENCE_NAME = "DOCU_BILL_UUID";
	public static String CHARGE_UUID_SEQUENCE_NAME = "DOCU_CHARGE_UUID";
	public static String ACCOUNT_UUID_SEQUENCE_NAME = "DOCU_ACCOUNT_UUID";
	public static String ACCT_DETAIL_UUID_SEQUENCE_NAME = "DOCU_ACCT_DETAIL_UUID";
	public static String ACTIVITY_UUID_SEQUENCE_NAME = "DOCU_ACTIVITY_UUID";
	
	public static String USER_ID_SEPARATOR = "|";
	public static String COMMON_USER_ID = "CMN";
	
	public static String TRANSACTION_TYPE_EXPENSE = "0";
	public static String TRANSACTION_TYPE_CHARGE = "1";
	public static String TRANSACTION_TYPE_ALL = "2";

	public static String ACCOUNT_TYPE_COMMON = "0";
	public static String ACCOUNT_TYPE_PRIVATE = "1";
	public static String ACCOUNT_TYPE_ALL = "2";
	
	public static String FORMATTER_PATTERN = "0.000";
	
	public static String CHARGE_STATUS_INIT = "0";
	public static String CHARGE_STATUS_PROCESSED = "1";
}
