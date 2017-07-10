package com.sunlinei.cms.batch.common.util;

import java.util.Hashtable;
import java.util.Map;

public class CommonConstants 
{
	  /** Constant stating the valid state of this user. */
	  public static final String VALID = "V";
	  
	  /** Constant stating the lock state of this user. */
	  public static final String LOCK = "L";
	  
	  /** Constant stating the valid state of this user. */
	  public static final String ACTIVE = "A";
	  
	  /** Constant stating the invalid state of this user. */
	  public static final String INACTIVE = "I";
	  
	  /** Constant stating reset of user's password.  */
	  public static final String RESETPASSWORD = "R";
	  
	  /** Constant stating owner type is cardholder.  */
	  public static final String CARDHOLDER = "C";
	  
	  /** Constant stating owner type is system user.  */
	  public static final String SYSTEM_USER = "U";
	  
	  /** Constant stating the pre-enroll state of this card. */
	  public static final String PRE_ENROLL = "P";
	  
	  /** Constant stating the terminated state of this card. */
	  public static final String TERMINATE = "T";
	  
	  /** Constant stating the new enrollment activity type. */
	  public static final String AT_NEW_ENROLLMENT = "NE";
	  
	  /** Constant stating the change password activity type. */
	  public static final String AT_CHANGE_PASSWORD = "CP";
	  
	  // AMB20093641 [s]
	  /** Constant stating the last password change date **/
	  public static final String LAST_PASSWORD_CHANGE = "LPC";
	  // AMB20093641 [e]
	  
	  /** Constant stating the reset password activity type. */
	  public static final String RESET_PASSWORD = "RP";
	  
	  /** Constant stating the edit personal message activity type. */
	  public static final String EDIT_P_MSG = "EP";
	  
	  /** Constant stating the successful status of activity. */
	  public static final String SUCCESSFUL = "S";
	  
	  /** Constant stating the fail status of activity. */
	  public static final String FAIL = "F";
	  
	  /** Constant stating the success transition of action. */
	  public static final String TRANSITION_SUCCESS = "success";
	  
	  /** Constant stating the error transition of action. */
	  public static final String TRANSITION_ERROR = "error";
	  
	  /** Constant stating the name of session variable which store user id. */
	  public static final String SESSION_USER_ID = "sessionUserId";
	  
	  /** Constant stating the name of session variable which store user name. */
	  public static final String SESSION_USER_NAME = "sessionUserName";
	  
	  /** Constant stating the name of session variable which store user role. */
	  public static final String SESSION_USER_ROLE = "sessionUserRole";
	  
	  /** Constant stating the name of session variable which store user last login. */
	  public static final String SESSION_USER_LAST_LOGIN = "sessionUserLastLogin";
	  
	  /** Constant stating the name of session variable which store user last access. */
	  public static final String SESSION_USER_LAST_ACCESS = "sessionUserLastAccess";
	  
	  /** Constant stating the name of session variable which store user session error code. */
	  public static final String SESSION_USER_ERROR_MSG = "sessionUserErrorMsg";
	  
	  /** Constant stating the status of batch job in progress. */
	  public static final String BATCH_IN_PROGRESS = "I";
	  
	  /** Constant stating the status of batch job completed. */
	  public static final String BATCH_COMPLETED = "C";
	  
	  /** Constant stating the status of batch job has error. */
	  public static final String BATCH_ERROR = "E";
	  
	  /** Constant stating the status of batch job has warning. */
	  public static final String BATCH_WARNING = "W";
	  
	  public static final String[] WILD_CARD = new String[] {"ALL", " "};
	  
	  public static final String WILD_CARD_REPLACEMENT = "%";
	  
	  private static final Map<String, String> COMMON_STATUS_MAP = new Hashtable<String, String>();
	  
	  /** Constant stating the status of new record. */
	  public static final String NEW_RECORD_STATUS = "N";
	  
	  public static final String KEYS_DELIMETER = ",";
	  
	  static{
		  COMMON_STATUS_MAP.put("C", "Completed");
		  COMMON_STATUS_MAP.put("E", "Error");
		  COMMON_STATUS_MAP.put("I", "In Progress");
		  COMMON_STATUS_MAP.put("W", "Warning");
		  COMMON_STATUS_MAP.put("A", "Active");
		  COMMON_STATUS_MAP.put("L", "Locked");
		  COMMON_STATUS_MAP.put("P", "Pre-Enrolled");
		  COMMON_STATUS_MAP.put("T", "Terminated");
	  }
	  
	  public static String getCommonStatusDesc(String status){
		  return COMMON_STATUS_MAP.get(status);
	  }
	  
	  public static int CONST_BIN_LENGTH = 9;
	  
	  public static final String HTTP_SESSION = "httpsession";
	  
	  public static final String PWD_IS_NOT_MATCH = "PasswordIsNotMatched";
	  
	  public static final String FILE_SEP_WINDOWS = "\\";
	  
	  public static final String FILE_SEP_UNIX = "/";
	  
	  public static final String SYSTEM_DEFAULT_USER_ID = "System";

	  public static final String LOCAL_ACCT_TYPE = "1";
	  public static final String FOREIGN_ACCT_TYPE = "2";
	  public static final String RESULT_CODE_ERROR = "3";
	  public static final String RESULT_CODE_SUCCESS = "0";
}
