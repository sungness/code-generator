package com.sungness.code.generate.util;

import com.sungness.code.generate.exception.CodegenException;
import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static boolean isEmpty(String paramString) {
        if (paramString == null)
            return true;
        return paramString.trim().equals("");
    }

    public static boolean isNotEmpty(String paramString) {
        return !isEmpty(paramString);
    }

    public static String replaceVariable(String paramString,
            Map<String, String> paramMap) throws CodegenException {
        Pattern localPattern = Pattern.compile("\\{(.*?)\\}");
        Matcher localMatcher = localPattern.matcher(paramString);
        while (localMatcher.find()) {
            String str1 = localMatcher.group(1);
            String str2 = localMatcher.group(0);
            String str3 = (String) paramMap.get(str1);
            if (str3 != null)
                paramString = paramString.replace(str2, str3);
            else
                throw new CodegenException("娌℃湁鎵惧埌[" + str1
                        + "]瀵瑰簲鐨勫彉閲忓�锛岃妫�煡琛ㄥ彉閲忛厤缃�");
        }
        return paramString;
    }

    public static String replaceVariable(String paramString1,
            String paramString2) {
        Pattern localPattern = Pattern.compile("\\{(.*?)\\}");
        Matcher localMatcher = localPattern.matcher(paramString1);
        if (localMatcher.find()) {
            String str = localMatcher.group(0);
            paramString1 = paramString1.replace(str, paramString2);
        }
        return paramString1;
    }

    public static String trimPrefix(String paramString1, String paramString2) {
        while (paramString1.startsWith(paramString2))
            paramString1 = paramString1.substring(paramString2.length());
        return paramString1;
    }

    public static String trimSufffix(String paramString1, String paramString2) {
        while (paramString1.endsWith(paramString2))
            paramString1 = paramString1.substring(0, paramString1.length()
                    - paramString2.length());
        return paramString1;
    }

    public static String trim(String paramString1, String paramString2) {
        return trimSufffix(trimPrefix(paramString1, paramString2), paramString2);
    }

    public static String combilePath(String frontPath, String afterPath) {
        frontPath = trimSufffix(frontPath, File.separator);
        afterPath = trimPrefix(afterPath, File.separator);
        return frontPath + File.separator + afterPath;
    }

    public static String replace(String paramString1, String paramString2,
            String paramString3, String paramString4) {
        String str1 = paramString1.toLowerCase();
        String str2 = paramString2.toLowerCase();
        String str3 = paramString3.toLowerCase();
        StringBuffer localStringBuffer = new StringBuffer();
        int i = str1.indexOf(str2);
        int j = str1.indexOf(str3);
        while ((i != -1) && (j != -1) && (i < j)) {
            String str4 = paramString1.substring(0,
                    str1.indexOf(str2) + str2.length());
            String str5 = paramString1.substring(str1.indexOf(str3));
            str1 = str5.toLowerCase();
            paramString1 = str5.substring(paramString3.length());
            i = str1.indexOf(str2);
            j = str1.indexOf(str3);
            String str6 = str4 + "\r\n" + paramString4 + "\r\n" + paramString3;
            localStringBuffer.append(str6);
        }
        localStringBuffer.append(paramString1);
        return localStringBuffer.toString();
    }

    public static boolean isExist(String paramString1, String paramString2,
            String paramString3) {
        String str = paramString1.toLowerCase();
        paramString2 = paramString2.toLowerCase();
        paramString3 = paramString3.toLowerCase();
        int i = str.indexOf(paramString2);
        int j = str.indexOf(paramString3);
        return (i != -1) && (j != -1) && (i < j);
    }

    public static String subString(String paramString1, String paramString2,
            String paramString3) {
        String str = paramString1;
        if (paramString1.indexOf(paramString2) != -1)
            if (paramString1.indexOf(paramString3) != -1)
                str = paramString1.substring(paramString1.indexOf(paramString2)
                        + paramString2.length(),
                        paramString1.lastIndexOf(paramString3));
            else
                str = paramString1.substring(paramString1.indexOf(paramString2)
                        + paramString2.length());
        return str;
    }

    public static String toFirstLowerCase(String paramString) {
        Character localCharacter = Character.valueOf(paramString.charAt(0));
        String str = localCharacter.toString().toLowerCase();
        return paramString.replaceFirst(localCharacter.toString(), str);
    }

    public static void main(String[] paramArrayOfString)
            throws CodegenException {
        toFirstLowerCase("ABC");
    }
}
