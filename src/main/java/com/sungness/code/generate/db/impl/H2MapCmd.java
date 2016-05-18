package com.sungness.code.generate.db.impl;

import com.sungness.code.generate.db.MapCmd;
import com.sungness.code.generate.model.schema.ColumnSchema;
import com.sungness.code.generate.util.StringUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class H2MapCmd implements MapCmd<ColumnSchema> {
    public ColumnSchema getObjecFromRs(ResultSet paramResultSet)
            throws SQLException {
        ColumnSchema localColumnSchema = new ColumnSchema();
        String str1 = paramResultSet.getString("COLUMN_NAME");
        String str2 = paramResultSet.getString("IS_NULLABLE");
        String str3 = paramResultSet.getString("TYPE_NAME");
        String str4 = paramResultSet.getString("LENGTH");
        String str5 = paramResultSet.getString("PRECISIONS");
        String str6 = paramResultSet.getString("SCALE");
        String str7 = paramResultSet.getString("COLUMN_LIST");
        String str8 = paramResultSet.getString("REMARKS");
        int i = string2Int(str4, 0);
        int j = string2Int(str5, 0);
        int k = string2Int(str6, 0);
        String str9 = getDisplayDbType(str3, i, j, k);
        String str10 = getJavaType(str3, j, k);
        boolean bool1 = "NO".equalsIgnoreCase(str2);
        localColumnSchema.setColumnName(str1);
        localColumnSchema.setColDbType(str3);
        localColumnSchema.setComment(str8);
        localColumnSchema.setIsNotNull(bool1);
        localColumnSchema.setLength(i);
        localColumnSchema.setPrecision(j);
        localColumnSchema.setScale(k);
        localColumnSchema.setDisplayDbType(str9);
        localColumnSchema.setColType(str10);
        boolean bool2 = false;
        if (StringUtil.isNotEmpty(str7)) {
            String[] arrayOfString1 = str7.split(",");
            for (String str11 : arrayOfString1) {
                if (!str1.trim().equalsIgnoreCase(str11.trim()))
                    continue;
                bool2 = true;
                break;
            }
        }
        localColumnSchema.setIsPK(bool2);
        return localColumnSchema;
    }

    private String getDisplayDbType(String paramString, long paramLong,
            int paramInt1, int paramInt2) {
        String str = paramString.toUpperCase();
        if (paramString.indexOf("CHAR") > 0)
            return str + "(" + paramLong + ")";
        if (("DECIMAL".equalsIgnoreCase(str))
                || ("NUMBER".equalsIgnoreCase(str))
                || ("DEC".equalsIgnoreCase(str))
                || ("NUMERIC".equalsIgnoreCase(str)))
            return str + "(" + (paramInt1 - paramInt2) + "," + paramInt2 + ")";
        return str;
    }

    private String getJavaType(String paramString, int paramInt1, int paramInt2) {
        paramString = paramString.toUpperCase();
        if (("BLOB".equals(paramString)) || ("TINYBLOB".equals(paramString))
                || ("MEDIUMBLOB".equals(paramString))
                || ("LONGBLOB".equals(paramString))
                || ("IMAGE".equals(paramString)) || ("OID".equals(paramString))
                || ("BINARY".equals(paramString))
                || ("VARBINARY".equals(paramString))
                || ("LONGVARBINARY".equals(paramString))
                || ("RAW".equals(paramString)) || ("BYTEA".equals(paramString)))
            return "byte[]";
        if (("TIMESTAMP".equals(paramString)) || ("TIME".equals(paramString))
                || ("DATE".equals(paramString))
                || ("DATETIME".equals(paramString))
                || ("SMALLDATETIME".equals(paramString)))
            return "java.util.Date";
        if (("BIGINT".equalsIgnoreCase(paramString))
                || ("INT8".equalsIgnoreCase(paramString))
                || ("IDENTITY".equalsIgnoreCase(paramString)))
            return "Long";
        if (("INTEGER".equalsIgnoreCase(paramString))
                || ("INT".equalsIgnoreCase(paramString))
                || ("MEDIUMINT".equalsIgnoreCase(paramString))
                || ("INT4".equalsIgnoreCase(paramString))
                || ("SIGNED".equalsIgnoreCase(paramString))
                || ("SMALLINT".equalsIgnoreCase(paramString))
                || ("YEAR".equalsIgnoreCase(paramString))
                || ("INT2".equalsIgnoreCase(paramString))
                || ("TINYINT".equalsIgnoreCase(paramString)))
            return "Integer";
        if (("DOUBLE".equalsIgnoreCase(paramString))
                || ("FLOAT".equalsIgnoreCase(paramString))
                || ("FLOAT4".equalsIgnoreCase(paramString))
                || ("FLOAT8".equalsIgnoreCase(paramString))
                || ("DECIMAL".equalsIgnoreCase(paramString))
                || ("NUMBER".equalsIgnoreCase(paramString))
                || ("DEC".equalsIgnoreCase(paramString))
                || ("NUMERIC".equalsIgnoreCase(paramString))
                || ("REAL".equalsIgnoreCase(paramString)))
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
