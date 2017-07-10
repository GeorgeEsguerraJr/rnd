package com.sunlinei.cms.batch.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StringUtil {

    /**
     * e.g. getPadLeft("123", 5, '0')
     * result = "00123"
     */
    public static String getPadLeft(String msg, int size, char padChar) {
    	return getPadLeft(msg, size, padChar, 'L');
    }
    public static String getPadLeftR(String msg, int size, char padChar) {
    	return getPadLeft(msg, size, padChar, 'R');
    }
    
    public static String getPadLeft(String msg, int size, char padChar, char leftOrRight) {
//    	StringBuilder buf = getInitializedString(padChar, size);
    	int strLength = 0;
    	if( msg != null ) {
	    	msg = msg.trim();
	    	strLength = msg.length();
	    	if( strLength == size )
	    		return msg;
	        if( strLength > size ) {
	        	if(leftOrRight == 'L')
	        		return msg.substring(0,size);
	        	else
	        		return msg.substring(strLength-size);
	        }
        }
    	else
    		msg = "";
    	char[] arr = new char[size-strLength];
    	Arrays.fill(arr, padChar);
        StringBuilder buf = new StringBuilder(size);
    	buf.append(arr);
    	buf.append(msg);
        return buf.toString();
    }
    
    private static StringBuilder getInitializedString(char theChar, int size) {
    	char[] arr = new char[size];
    	Arrays.fill(arr, theChar);
    	StringBuilder buf = new StringBuilder(size);
    	buf.append(arr);
        return buf;
    }
    /**
     * e.g. getPadRight("123", 5, '0')
     * result = "12300"
     */
    public static String getPadRight(String msg, int size, char padChar) {
//    	StringBuilder buf = getInitializedString(padChar, size);
    	int strLength = 0;
    	if( msg != null ) {
	    	msg = msg.trim();
	        strLength = msg.length();
	        if( strLength == size )
	        	return msg;
	        if( strLength > size )
	        	return msg.substring(0,size);
    	}
    	else
    		msg = "";
        StringBuilder buf = new StringBuilder(msg);
    	char[] arr = new char[size-strLength];
    	Arrays.fill(arr, padChar);
    	buf.append(arr);
        return buf.toString();
    }

    public static String checkMsg(String msg, int size) {
    	if( msg == null )
    		return "";
        if (msg.length() > size)
        	return msg.substring(0, size);
        return msg;
    }

	public static String chkNull(String msg){
		if (msg == null)
			return "";

		return msg;
	}
    public static Integer chkNull(Integer msg) {
		if (msg == null)
            return new Integer("0");

        return msg;
    }

    public static BigDecimal chkNull(BigDecimal msg) {
        if (msg == null) {
            return new BigDecimal("0");
        }

        return msg;
    }
    
    //SAC20163271 [S]
    public static BigInteger chkNull(BigInteger msg){
		if (msg == null)
			return new BigInteger("0");
		return msg;
	}
    //SAC20163271 [E]

    public static String chkNullNumber(String msg) {
        if (msg == null) {
            return "0";
        } else if (msg.trim().equals("")) {
            return "0";
        }

        return msg;
    }

	public static BigDecimal chkNullBigNumber(String msg) {

		try {
			if (msg == null) {
				return new BigDecimal("0");
			} else if (msg.trim().equals("")) {
				return new BigDecimal("0");
			}
		} catch (Exception e) {
			return new BigDecimal("0");
		}
		return new BigDecimal(msg);
	}

    
    public static BigDecimal chkNullNumber(BigDecimal msg) {
        if (msg == null) {
            return new BigDecimal("0");
        }

        return msg;
    }

    /**
     * takes a string and generate a checkdigit
     */
    public static int generateCheckDigit(String toCheck) {
        long sum = 0;
        byte[] theByte = toCheck.getBytes();

        for (int i = 0; i < theByte.length; i++) {
            sum = sum + theByte[i];
        }

        String theSum = (Long.valueOf(sum)).toString();
        int startIndex = (theSum.length()) - 2;

        return Integer.parseInt(theSum.substring(startIndex));
    }

    /**
     * validates a check digit based on the  string passed in, the checkdigit
     */
    public static boolean isValidCheckDigit(String theStr, int checkDigit) {
        int generatedCheckDigit = generateCheckDigit(theStr);

        if (checkDigit == generatedCheckDigit) {
            return true;
        }

        return false;
    }

    /**
     * assumes that the last 2 digits in the string is the check digit
     * and perform validation of check digit on that
     */
    public static boolean isValidCheckDigit(String theStr) {
        String contentString = theStr.substring(0, theStr.length() - 2);

        String theCheckDigit = theStr.substring(theStr.length() - 2);

        return isValidCheckDigit(contentString, Integer.parseInt(theCheckDigit));
    }

    public static void main(String[] args) {
    	System.out.println(StringUtil.getPadLeft("123",5,'0'));
    	System.out.println(StringUtil.getPadLeft("12345",5,'0'));
    	System.out.println(StringUtil.getPadLeft("123456",5,'0'));
    	System.out.println(StringUtil.getPadLeft(null,5,'0'));
    	System.out.println(StringUtil.getPadLeftR("123",5,'0'));
    	System.out.println(StringUtil.getPadLeftR("12345",5,'0'));
    	System.out.println(StringUtil.getPadLeftR("123456",5,'0'));
    	System.out.println(StringUtil.getPadLeftR(null,5,'0'));
    	System.out.println(StringUtil.getPadRight("123",5,'0'));
    	System.out.println(StringUtil.getPadRight("12345",5,'0'));
    	System.out.println(StringUtil.getPadRight("123456",5,'0'));
    	System.out.println(StringUtil.getPadRight(null,5,'0'));
    	
    }
    public static void main1(String[] args) {
//		String toGenerate = args[0];
//		System.out.println(generateCheckDigit(toGenerate));
        int width = 6;
        String line = "1234567890abcdefghijkl1234567890";
        int mod = line.length() % width;
        int loopCount = line.length() / width;
        if (mod > 0) {
            loopCount += 1;
        }
        System.out.println("mod:" + mod);
        System.out.println("loopCount:" + loopCount);
        String subString = null;
        int beginIndex = 0;
        int endIndex = width;
        for (int i = 0; i < loopCount; i++) {
            if (endIndex > line.length()) {
                endIndex = line.length();
            }
            subString = line.substring(beginIndex, endIndex);
            System.out.println("subString:" + subString);
            beginIndex = endIndex;
            endIndex += width;
        }

    }

    public static String checkValue(double thisValue) {
        if (thisValue == 0.0) {
            return "";
        } else {
            return Double.toString(thisValue);
        }
    }

    public static String checkValue(float thisValue) {
        if (thisValue == 0.0) {
            return "";
        } else {
            return Float.toString(thisValue);
        }
    }

    public static String checkValue(int thisValue) {
        if (thisValue == 0) {
            return "";
        } else {
            return Integer.toString(thisValue);
        }
    }

    public static String checkValue(String thisValue) {
        if (thisValue == null || "0".equals(thisValue)) {
            return "";
        } else {
            return thisValue.trim();
        }
    }

    public static String checkValue(BigDecimal thisValue) {
        String str = "";

        if (thisValue == null || BigDecimal.ZERO.compareTo(thisValue) == 0) {
            return "";
        } else {
            str = thisValue.toString();
            return str;
        }
    }

    public static String formatCardNo(String cardNo) {
        String cardNo1 = cardNo.substring(0, 4);
        String cardNo2 = cardNo.substring(4, 8);
        String cardNo3 = cardNo.substring(8, 12);
        String cardNo4 = "";
        if (cardNo.length() > 16) {
            cardNo4 = cardNo.substring(12, 16);
            cardNo4 += cardNo.substring(16, cardNo.length());
        } else {
            cardNo4 = cardNo.substring(12, cardNo.length());
        }

        return cardNo1 + " " + cardNo2 + " " + cardNo3 + " " + cardNo4;
    }

    public static String formatDisplayAmount(String amount, int currExpo) {
        String formatted = null;
        int temp = amount.indexOf(".") + currExpo + 1;

        formatted = amount.substring(0, temp);
        return formatted;
    }

    public static String checkNull(String msg) {
		if (msg == null)
            return "";

        return msg.trim();
    }

	public static String checkNullAndHandleSqlStmt(String msg) {
		if (msg == null)
			return "";
		String returnStr = "";
		char singleQuote = '\'';
		if (msg != null && msg.trim().length() > 0) {
			for (int i = 0; i < msg.length(); i++) {
				if (msg.charAt(i) == singleQuote)
					returnStr += (msg.substring(i, i + 1) + msg.substring(i, i + 1));
				else
					returnStr += msg.substring(i, i + 1);
			}
			msg = returnStr;
		}
		return msg;
	}
    
    public static String checkNull(BigDecimal msg) {
        if (msg == null) {
            return "";
        }

        return msg.toString();
    }

    public static String checkNull(int msg) {
        return String.valueOf(msg);
    }

    public static String checkNull(long msg) {
        return String.valueOf(msg);
    }

    public static String checkNull(Integer msg) {
        if (msg == null) {
            return "";
        }
        return msg.toString();
    }

    public static String checkNull(Timestamp msg) {
        if (msg == null) {
            return "";
        }

        return msg.toString();
    }

	public static String checkNullValue(BigDecimal msg) {
		if (msg == null) {
			return "";
		}
		return msg.toString();
	}    
    
    public static String checkNullValue(String msg) {
		if (msg == null)
            return "";

        return msg;
    }

 

    public static StringBuilder replaceValue(StringBuilder inBf, String fieldName, String value, int offSet) {
        inBf.insert(inBf.toString().indexOf(fieldName) + fieldName.length() + offSet, value);

        int startDel = inBf.toString().indexOf(value) + value.length();
        int endDel = startDel + inBf.length();

        inBf.delete(startDel, endDel);

		if (offSet == 2)
            inBf.append("' ");
		else
            inBf.append(" ");

        return inBf;
    }
    
    public static StringBuffer replaceValue(StringBuffer inBf, String fieldName, String value, int offSet) {
		inBf.insert(inBf.toString().indexOf(fieldName) + fieldName.length() + offSet, value);

		int startDel = inBf.toString().indexOf(value) + value.length();
		int endDel = startDel + inBf.length();

		inBf.delete(startDel, endDel);

		if (offSet == 2)
			inBf.append("' ");
		else
			inBf.append(" ");

		return inBf;
	}
    
	public static String leftPadder (String msg, char token, int size) {
		String t = String.valueOf(token);
		while (msg.length() < size) {
			msg = t + msg;
		}
		return msg.substring(0, size);
	}
    
	public static String removeValue(String value, char inChar) {
		char quote = inChar;
		int len = value.length();
		char[] tmp = value.toCharArray();
		StringBuffer newVal = new StringBuffer();
		for (int i = 0; i < len; i++) {
			if (tmp[i] == quote) {
				//skip the char
			} else {
				newVal.append(tmp[i]);
			}
		}
		return newVal.toString();
	}

    public static String replacePattern(String strValue, String[] strPattern, String strReplaceWithPattern) {
        if (strValue != null) {
//			System.out.println("strValue:"+strValue);
//			System.out.println("strReplaceWithPattern:"+strReplaceWithPattern);
            for (int i = 0; i < strPattern.length; i++) {
//				System.out.println("strPattern "+i+":"+strPattern[i]);
                strValue = replacePattern(strValue, strPattern[i], strReplaceWithPattern);
            }
        }
        return strValue;
    }

    public static String replacePattern(String strValue, String strPattern, String strReplaceWithPattern) {
        if (strValue != null) {
//			System.out.println("strValue.indexOf(strPattern):"+strValue.indexOf(strPattern));
            strValue = strValue.replaceAll(strPattern, strReplaceWithPattern);
        }
        return strValue;
    }

    public static String appendPattern(String strValue, String strAppendWith) {
        if (strValue != null) {
            strValue = strValue.trim() + strAppendWith;
        }
        return strValue;
    }

    public static String[] split(String strData, String strDelim) {
        List<String> lstTemp = null;
        if (strData == null) {
            return null;
        } else if (strData.trim().equals("")) {
            return new String[]{""};
        }

        if (strDelim == null) {
            return null;
        } else if (strDelim.equals("")) {
            return new String[]{strData};
        }

        lstTemp = new ArrayList<String>();
        int intStartIndex = 0;
        int intCurrentIndex = 0;
        int intEndIndex = strData.length();

        while ((intCurrentIndex = strData.substring(intStartIndex, intEndIndex).indexOf(strDelim)) != -1) {
            lstTemp.add(strData.substring(intStartIndex, intStartIndex + intCurrentIndex));
            intStartIndex = intStartIndex + intCurrentIndex + strDelim.length();
        }
        lstTemp.add(strData.substring(intStartIndex, intEndIndex));
        String[] strResult = new String[lstTemp.size()];
        lstTemp.toArray(strResult);
        return strResult;
    }

    public static BigDecimal checkIntValue(int i) {
        String str = Integer.toString(i);
        if (str  == null)
           return new BigDecimal(0);
        else
           return new BigDecimal(i);
    }
    
    public static String formatAmount(String s) {
        String value = s;
        if (s.startsWith("-")) {
            value = s.substring(1,s.length()) + "-";
        }
        return value;
    }    
    
    public static String fmtToScrNumber(String fmtPattern, String strNumber) {
        String strDollar = "";
        String strCent = "";
        String strNegative = "";
        String strFormattedNumber = "";
        try {
			if (strNumber.equals("")) return "";

            Double.isNaN(Double.parseDouble(strNumber));

            DecimalFormat df = new DecimalFormat(fmtPattern);
            df.setMinimumIntegerDigits(1);
            df.setGroupingUsed(true);

            if (strNumber.indexOf(".") != -1) {
                strDollar = strNumber.substring(0, strNumber.indexOf("."));
                strCent = strNumber.substring(strNumber.indexOf("."));
                strCent = "0" + strCent;
            } else {
                strDollar = strNumber;
                strCent = "0.00";
            }

            if (strDollar.indexOf("-") == 0) {
                strNegative = "-";
                strDollar = strDollar.substring(1);
            }

            for (int i = 0; i < Math.floor((strDollar.length() - (1 + i)) / 3.0); i++) {
                strDollar = strDollar.substring(0, strDollar.length() - (4 * i + 3)) + ',' + strDollar.substring(strDollar.length() - (4 * i + 3));
            }

            strCent = df.format(Double.parseDouble(strCent));
            strCent = strCent.substring(1);

            strFormattedNumber = strNegative + strDollar + strCent;
        } catch (Exception e) {
        }
        return strFormattedNumber;
    }

    /**
     * Replace characters having special meaning inside HTML tags
     * with their escaped equivalents, using character entities.
     * The escaped characters are: <,>,",',\,&
     * -- Will be deprecated due to confusing function naming
     */
    public static String encodeHTMLText(String strData) {
        if (strData == null) {
            return "";
        }
        StringBuilder sbData = new StringBuilder(strData);
        StringUtil.encodeHTMLText(sbData);
        return sbData.toString();
    }

    /**
     * Replace characters having special meaning inside HTML tags
     * with their escaped equivalents, using character entities.
     * The escaped characters are: <,>,",',\,&
     * -- Will be deprecated due to confusing function naming
     */
    public static void encodeHTMLText(StringBuilder sbData) {
        StringUtil.replaceString(sbData, "&", "&amp;");
        StringUtil.replaceString(sbData, "\"", "&quot;");
        StringUtil.replaceString(sbData, "<", "&lt;");
        StringUtil.replaceString(sbData, ">", "&gt;");
    }
    
    public static void encodeHTMLText(StringBuffer sbData) {
		StringUtil.replaceString(sbData, "&", "&amp;");
		StringUtil.replaceString(sbData, "\"", "&quot;");
		StringUtil.replaceString(sbData, "<", "&lt;");
		StringUtil.replaceString(sbData, ">", "&gt;");
	}

    public static String decodeHTMLText(String strData) {
        if (strData == null) {
            return "";
        }
        StringBuilder sbData = new StringBuilder(strData);
        StringUtil.replaceString(sbData, "&gt;", ">");
        StringUtil.replaceString(sbData, "&lt;", "<");
        StringUtil.replaceString(sbData, "&quot;", "\"");
        StringUtil.replaceString(sbData, "&amp;", "&");
        StringUtil.replaceString(sbData, "&nbsp;", " ");
        return sbData.toString();
    }

    public static StringBuilder replaceString(StringBuilder sbData, String strOriString, String strReplaceString) {
        int intStart = 0;
        int intEnd = 0;
        if (strOriString.length() == 0) {
            return sbData;
        }
        while ((intEnd = sbData.toString().indexOf(strOriString, intStart)) != -1) {
            sbData.delete(intEnd, intEnd + strOriString.length());
            sbData.insert(intEnd, strReplaceString);
            intStart = intEnd + strReplaceString.length();
        }
        return sbData;
    }
    
    public static StringBuffer replaceString(StringBuffer sbData, String strOriString, String strReplaceString) {
		int intStart = 0;
		int intEnd = 0;
		if (strOriString.length() == 0) {
			return sbData;
		}
		while ((intEnd = sbData.toString().indexOf(strOriString, intStart)) != -1) {
			sbData.delete(intEnd, intEnd + strOriString.length());
			sbData.insert(intEnd, strReplaceString);
			intStart = intEnd + strReplaceString.length();
		}
		return sbData;		
	}

    public static String getSystemFileSeparator() {
        String fileSep = null;
        if (System.getProperty("os.name").startsWith("Windows")) {
            fileSep = CommonConstants.FILE_SEP_WINDOWS;
        } else {
            fileSep = CommonConstants.FILE_SEP_UNIX;
        }
        return fileSep;
    }

    public static String byteArrayToHexString(byte in[]) {
        byte ch = 0x00;
        int i = 0;
        if (in == null || in.length <= 0) {
            return null;
        }

        String pseudo[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        StringBuilder out = new StringBuilder(in.length * 2);

        while (i < in.length) {
            ch = (byte) (in[i] >> 4); // Strip off high nibble
            // shift the bits down
            ch = (byte) (ch & 0x0F);
            // must do this is high order bit is on!
            out.append(pseudo[(int) ch]); // convert the nibble to a String Character
            ch = (byte) (in[i] & 0x0F); // Strip off low nibble
            out.append(pseudo[(int) ch]); // convert the nibble to a String Character
            i++;
        }
        String rslt = new String(out);
        return rslt;
    }
    static protected final char[] hexChar = new char[] {
    		'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'
    };
    public static String byteArrayToHexString(byte in[],int offset, int len) {
    	if( (in == null) || (in.length <= 0) || (offset < 0)
    			|| (len<= 0) || (in.length < (offset+len)) ) {
            return null;
        }
    	char[] retval = new char[len*2];
    	
    	int j = 0;
        for( int i=0; i<len; i++ ) {
        	retval[j++] = hexChar[ 0x0f&(in[i+offset]>>>4) ];
        	retval[j++] = hexChar[ 0x0f& in[i+offset]      ];
        }
        return new String(retval);
    }

      public static String byteArrayToBinaryString(byte in[]) {  
    	 int i = 0;
         if (in == null || in.length <= 0) {
             return null;
         }
         StringBuilder out = new StringBuilder();
    	while (i < in.length) {
    		out.append(byteToBinaryString((byte) in[i]));
    		i++;
    	}
    	
    	return out.toString();        
    }
    
    public static String byteToBinaryString(byte in) { 
    	
        return String.format("%8s", Integer.toBinaryString(in & 0xFF)).replace(' ', '0');
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
//        System.out.println("len="+len);
        for (int i = 0; i < len-1; i += 2) {
//        	System.out.println(i + 1+" charAt="+s.charAt(i + 1));
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /*
     * Specify the length of random code required
     */
    public static String getRandomCd(int len) {
        final int maxLEN = 12;
        double x;
        String rndCd = "";
        int mylen = maxLEN;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i += mylen) {
            x = Math.random();
//			System.out.println("x="+x);
            long n = (long) (new BigDecimal(x).movePointRight(maxLEN).longValue());
            mylen = len - rndCd.length();
//			System.out.println("mylen="+mylen+","+rndCd.length());
            if (mylen > maxLEN) {
                mylen = maxLEN;
            }
            String tmpstr = Long.toString(n);
//			System.out.println("tmpstr="+tmpstr);
            if (mylen > tmpstr.length()) {
                mylen = tmpstr.length();
            }
            //rndCd += tmpstr.substring(0, mylen);
            sb.append(tmpstr.substring(0, mylen));
//			System.out.println("rndcd="+mylen+","+rndCd+","+rndCd.length());
        }
//      rndCd = StringUtil.getPadLeft(rndCd, len, '0');
        rndCd = StringUtil.getPadLeft(sb.toString(), len, '0');
        return rndCd;
    }

    private static final String ALPHA_NUM = "123456789ABCDEFGHJKLMNPQRSTUVWXYZ987654321"; // omit zero to avoid first digit being 0 (amex requirement)
    /*
     * Specify the length of random code required
     */
    public static String getRandomAlphaNum(int len) {
    	StringBuffer sb = new StringBuffer(len);
    	for (int i = 0; i < len; i++) {
    		int ndx = (int) (Math.random() * ALPHA_NUM.length());
    		sb.append(ALPHA_NUM.charAt(ndx));
    	}
    	return sb.toString();
    }
// BSE20140621/ACMS-3319[s]
    private static final String HEXNUM = "1234567890ABCDEF";
    /*
     * Specify the length of random hex required
     */
    public static String getRandomHex(int len) {
    	StringBuffer sb = new StringBuffer(len);
    	for (int i = 0; i < len; i++) {
    		int ndx = (int) (Math.random() * HEXNUM.length());
    		sb.append(HEXNUM.charAt(ndx));
    	}
    	return sb.toString();
    }
// BSE20140621/ACMS-3319 [e]

    /**
     * Convert COBOL PIC S999v999 type of data to signed numeric values
     * @param ibuf is the COBOL numeric string with signed decimal such as "0000294p"
     * @param ilen is the length of ibuf
     * @param decimal denotes the number of decimal points of this COBOL variable (according to table schema)
     * @return
     * For the input of ("294p", 4, 3), returns converted decimal values of "-2.940"
     */
    public static String str2f(String ibuf, int ilen, int decimal) {
        byte[] buffer = ibuf.getBytes();
        boolean isNeg = false;
        int len = ilen;
        double retval = 0;

        if ((buffer[len - 1] & 0x040) == 0x040) {
            buffer[len - 1] &= 0x03F;
            isNeg = true;
        }
        String tmpstr = new String(buffer);
        retval = Double.parseDouble(tmpstr);
        ibuf = null;

        if (isNeg) {
            retval *= -1;
            len++;
            ilen++;
        }

        if (decimal > 0) {
            len++;
            retval = genDecimal(retval, decimal);
        }

//        String pattern = "0";
        StringBuilder sb = new StringBuilder("0");
        for (int i = 0; i < decimal; i++) {
            if (i == 0) {
//                pattern += ".";
            	sb.append('.');
            }
//            pattern += "0";
            sb.append('0');
        }
        String pattern = sb.toString();
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(retval);
    }

    /**
     * Convert signed values to COBOL PIC S999v999
     * @param ibuf is the numeric with signed decimal such as "-00002.940"
     * @param olen is the desired output length, padded with leading zeroes
     * @param decimal denotes the number of decimal points of the ibuf variable (according to table schema)
     * @return
     * For the input of (-2.94, 6, 3), returns converted output of "00294p"
     */
    public static String f2str(BigDecimal ibuf, int olen, int decimal) {
    	// BSE20141551 [S]
    	// String bdStr = ibuf.setScale(decimal).toString();
    	String bdStr = ibuf.setScale(decimal, BigDecimal.ROUND_HALF_UP).toString();
    	// BSE20141551 [E]
        char[] ibuf2 = bdStr.toCharArray();
        byte[] buffer = bdStr.getBytes();
        int isNeg = 0, dot = 0;
        int len = olen + decimal + 1;
        int strl = bdStr.length();
        int j;
        for (int i = j = 0; i < strl; i++, j++) {
            if (ibuf2[i] == '-') {
                isNeg = 1;
                buffer[j] = '0';
                continue;
            }
            if (ibuf2[i] == '.') {
                j--;
                dot = 1;
                continue;
            }
            buffer[j] = (byte) ibuf2[i];
        }

        if (isNeg > 0) {
            buffer[--j] |= 0x040;
            buffer[++j] = 0;
        }

        byte[] newbuff = new byte[buffer.length - 1];
        for (int i = 0; i < newbuff.length; i++) {
            newbuff[i] = buffer[i];
        }
        //SAC20170121 [s]
        //return getPadLeft(new String(newbuff), olen, '0');
         return getPadLeft(new String(newbuff).replaceFirst ("^0*", ""), olen, '0');
		//SAC20170121 [e]

//    	len--;
//    	int offset = isNeg>0 && (strl == (dot+isNeg+len)) ? dot+isNeg+len : 0;
//    	return new String(buffer)+Integer.toString(offset);
    }

    private static double genDecimal(double d1, int decimal) {
        for (int i = 1; i <= decimal; i++) {
            d1 /= 10.00;
        }
        return d1;
    }

    public static String replaceCharAt(String s, int pos, char c) {
        StringBuilder buf = new StringBuilder(s);
        buf.setCharAt(pos, c);
        return buf.toString();
    }

    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

	public static char getChar(String str, int pos) {
		if(str==null || str.length()<=pos)
			return ' ';
		return str.charAt(pos);
	}

	public static String maskCard(String str) {
		//BSE20140621 [S] 
		if(str == null)
			return "";
		//BSE20140621 [E]  	
		char[] arr = str.toCharArray();
		for( int i=6; i<arr.length-4; i++ )
			arr[i] = '_';
		return new String(arr);
	}
	/** BSE20112972 [S] **/
	public static String printEmvMap(HashMap<Integer, Object> emvData) {
		StringBuffer sb = new StringBuffer();
		String hashData = null;
		for( Map.Entry<Integer,Object> entry : emvData.entrySet() ) {
			hashData = new String((byte[]) entry.getValue());
			if(sb.length() > 0){
				sb.append(",");
			}
		    sb.append(entry.getKey() + ":" + hashData);
			
		}
//		for (Integer key : emvData.keySet()) {
//			hashData = new String((byte[]) emvData.get(key));
//			if(sb.length() > 0){
//				sb.append(",");
//			}
//		    sb.append(key + ":" + hashData);
//		}
		return "map=[" + sb.toString() + "]";
	}
	/** BSE20112972 [E] **/

	// Note the naming confusion - from V3.1 code
	// This actually expect hex input and returns binary
    public static byte[] str2hex(String displayable, int len, String padding) {
        int offset = 0, destLen=len/2;
        byte[] buf = new byte[len + 1];
        byte[] buf2 = displayable.getBytes();
        byte[] small = new byte[2];
        byte[] outBinary = new byte[destLen];

        if (padding.charAt(1) == '0') {
            for (int i = 0; i < buf.length; i++) {
                buf[i] = '0';
            }
        } else {
            for (int i = 0; i < buf.length; i++) {
                buf[i] = 'F';
            }
        }

        if ((len & 0x01) == 0x01) {
            if (padding.charAt(0) == 'L') {
                offset = 1;
            }
            for (int i = 0; i < buf2.length; i++) {
                buf[i + offset] = buf2[i];
            }
            len++;
        } else {
            for (int i = 0; i < buf2.length; i++) {
                buf[i] = buf2[i];
            }
        }

//        destLen = len / 2;
        for (int i = 0; i < destLen; i++) {
            small[0] = buf[i * 2];
            small[1] = buf[i * 2 + 1];
            //outBinary[i] = (byte) Integer.parseInt(new String(small), 16);
            outBinary[i] = (byte) hexStrToInt(small);
        }

        return outBinary;
    }
	// Converts the string stopping at the first invalid char
    // This simulates C routine strtol(str,16)
	public static int hexStrToInt(byte[] arr) {
		int retval = 0;
		int len = arr.length;
		int i;
		byte ch;
		// skip optional white space at the start
		for( i=0; i<len; i++ ) {
			ch = arr[i];
			if( ch != ' ' && ch != '\t' )
				break;;
		}
		for( ; i<len; i++ ) {
			ch = arr[i];
			// skip optional white space at the start
			if( '0' <= ch && ch <= '9' )
				retval = (retval << 4) + (ch & 0x0f);
			else if( ('A' <= ch && ch <= 'F')
					||('a' <= ch && ch <= 'f'))
				retval = (retval << 4) + (ch & 0x0f)+9;
			else
				break;
		}
		return retval;
	}
	public static String str2hex(String str){
		char[] chars = str.toCharArray();

		StringBuffer hex = new StringBuffer();
		for(int i = 0; i < chars.length; i++){
			hex.append(Integer.toHexString((int)chars[i]));
		}

		return hex.toString();
	}
//	public static String hex2str(byte[] data) {
//		try {
//			return new String(Hex.decodeHex(Hex.encodeHex(data)));
//		} catch (DecoderException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
	
//	BSE20140621 [S]
/*
    public static String toHex(byte[] array)
    {
    	BigInteger bi = new BigInteger(1, array);
    	String hex = bi.toString(16);
    	int paddingLength = (array.length * 2) - hex.length();
    	if(paddingLength > 0)
    	{
    		return String.format("%0"  +paddingLength + "d", 0) + hex;
    	}else{
    		return hex;
    	}
    }

	// this is opposite of byteArrayToHexString, duplicate of hexStringToByteArray
    public static byte[] fromHex(String hex)
    {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }
*/
//	BSE20140621 [E]

//	public static String hex2str(String hex){
//		StringBuilder sb = new StringBuilder();
//		StringBuilder temp = new StringBuilder();
//
//		//49204c6f7665204a617661 split into two characters 49, 20, 4c...
//		for( int i=0; i<hex.length()-1; i+=2 ){
//			//grab the hex in pairs
//			String output = hex.substring(i, (i + 2));
//			//convert hex to decimal
//			int decimal = Integer.parseInt(output, 16);
//			//convert the decimal to character
//			sb.append((char)decimal);
//			temp.append(decimal);
//		}
//
//		return sb.toString();
//	}   
	public static String getFormattedNumber(double _number){
		String formattedNumber=null;
		NumberFormat numformatter = new DecimalFormat("###,###,###.##");	        
		formattedNumber=(numformatter.format(_number));



		return formattedNumber;
	}
	public static BigDecimal checkNullNumber(String msg) {
		if (msg == null) {
			return new BigDecimal("0");
		} else if (msg.trim().equals("")) {
			return new BigDecimal("0");
		}
		return new BigDecimal(msg);
	}
}
