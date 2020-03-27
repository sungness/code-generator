package com.sungness.code.generate.db.impl;

import com.sungness.code.generate.db.MapCmd;
import com.sungness.code.generate.model.schema.ColumnSchema;
import com.sungness.code.generate.util.StringUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB2MapCmd implements MapCmd<ColumnSchema> {
    public ColumnSchema getObjecFromRs(ResultSet paramResultSet)
            throws SQLException {
        ColumnSchema localColumnSchema = new ColumnSchema();
        String str1 = paramResultSet.getString("COLNAME");
        String str2 = paramResultSet.getString("TYPENAME");
        int i = Integer.parseInt(paramResultSet.getString("LENGTH"));
        int j = i;
        int k = paramResultSet.getInt("SCALE");
        String str3 = paramResultSet.getString("REMARKS");
        int m = string2Int(paramResultSet.getString("KEYSEQ"), 0);
        str3 = str3 == null ? str1 : str3;
        String str4 = paramResultSet.getString("NULLS");
        String str5 = getDisplayDbType(str2, i, j, k);
        String str6 = getJavaType(str2, j, k);
        boolean bool = "N".equalsIgnoreCase(str4);
        localColumnSchema.setColumnName(str1);
        localColumnSchema.setColDbType(str2);
        localColumnSchema.setComment(str3);
        localColumnSchema.setIsNotNull(bool);
        localColumnSchema.setLength(i);
        localColumnSchema.setPrecision(i);
        localColumnSchema.setScale(k);
        localColumnSchema.setDisplayDbType(str5);
        localColumnSchema.setColType(str6);
        localColumnSchema.setIsPK(m > 0);
        return localColumnSchema;
    }

    private String getDisplayDbType(String paramString, long paramLong,
            int paramInt1, int paramInt2) {
        if (("CHAR".equalsIgnoreCase(paramString))
                || ("VARCHAR".equalsIgnoreCase(paramString))
                || ("LONG VARCHAR".equalsIgnoreCase(paramString)))
            return paramString + "(" + paramLong + ")";
        if ("DECIMAL".equalsIgnoreCase(paramString))
            return "DECIMAL(" + (paramInt1 - paramInt2) + "," + paramInt2 + ")";
        if (("BIGINT".equalsIgnoreCase(paramString))
                || ("DOUBLE".equalsIgnoreCase(paramString))
                || ("INTEGER".equalsIgnoreCase(paramString))
                || ("REAL".equalsIgnoreCase(paramString))
                || ("SMALLINT".equalsIgnoreCase(paramString)))
            return paramString;
        return paramString;
    }

    private String getJavaType(String paramString, int paramInt1, int paramInt2) {
        paramString = paramString.toUpperCase();
        if (("BLOB".equals(paramString)) || ("GRAPHIC".equals(paramString))
                || ("LONG VARGRAPHIC".equals(paramString))
                || ("VARGRAPHIC".equals(paramString)))
            return "byte[]";
        if (("CLOB".equals(paramString)) || ("XML".equals(paramString))
                || ("DBCLOB".equals(paramString)))
            return "String";
        if (("CHARACTER".equals(paramString))
                || ("LONG VARCHAR".equals(paramString))
                || ("VARCHAR".equals(paramString)))
            return "String";
        if (("TIMESTAMP".equals(paramString)) || ("TIME".equals(paramString))
                || ("DATE".equals(paramString)))
            return "java.util.Date";
        if ("BIGINT".equalsIgnoreCase(paramString))
            return "Long";
        if (("INTEGER".equalsIgnoreCase(paramString))
                || ("SMALLINT".equalsIgnoreCase(paramString)))
            return "Integer";
        if (("DOUBLE".equalsIgnoreCase(paramString))
                || ("REAL".equalsIgnoreCase(paramString)))
            return "Double";
        if (paramString.indexOf("DECIMAL") > 0)
            return "Double";
        return "String";
    }

    private int string2Int(String paramString, int paramInt) {
        int i = paramInt;
        if (StringUtil.isNotEmpty(paramString))
            try {
                i = Integer.parseInt(paramString);
            } catch (Exception localException) {
                localException.printStackTrace();
            }
        return i;
    }
}
