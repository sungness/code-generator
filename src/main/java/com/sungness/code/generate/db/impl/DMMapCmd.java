package com.sungness.code.generate.db.impl;

import com.sungness.code.generate.db.MapCmd;
import com.sungness.code.generate.model.schema.ColumnSchema;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DMMapCmd implements MapCmd<ColumnSchema> {
    public ColumnSchema getObjecFromRs(ResultSet paramResultSet)
            throws SQLException {
        ColumnSchema localColumnSchema = new ColumnSchema();
        String str1 = paramResultSet.getString("NAME");
        String str2 = paramResultSet.getString("TYPENAME");
        int i = Integer.parseInt(paramResultSet.getString("LENGTH"));
        int j = paramResultSet.getInt("PRECISION");
        int k = paramResultSet.getInt("SCALE");
        String str3 = paramResultSet.getString("DESCRIPTION");
        str3 = str3 == null ? str1 : str3;
        String str4 = paramResultSet.getString("NULLABLE");
        String str5 = getDisplayDbType(str2, i, j, k);
        String str6 = getJavaType(str2, j, k);
        boolean bool = "N".equals(str4);
        localColumnSchema.setColumnName(str1);
        localColumnSchema.setColDbType(str2);
        localColumnSchema.setComment(str3);
        localColumnSchema.setIsNotNull(bool);
        localColumnSchema.setLength(i);
        localColumnSchema.setPrecision(j);
        localColumnSchema.setScale(k);
        localColumnSchema.setDisplayDbType(str5);
        localColumnSchema.setColType(str6);
        return localColumnSchema;
    }

    private String getDisplayDbType(String paramString, long paramLong,
            int paramInt1, int paramInt2) {
        String str = paramString.trim().toUpperCase();
        if (str.indexOf("CHAR") > -1)
            return str + "(" + paramLong + ")";
        if (str.equals("NUMBER")) {
            if ((paramInt2 == 0) && (paramInt1 > 0))
                return "NUMBER(" + paramInt1 + ")";
            return "NUMBER(" + paramInt1 + "," + paramInt2 + ")";
        }
        return str;
    }

    private String getJavaType(String paramString, int paramInt1, int paramInt2) {
        String str = paramString.trim().toUpperCase();
        if (str.indexOf("BLOB") != -1)
            return "byte[]";
        if ((str.indexOf("CHAR") != -1) || (str.indexOf("CLOB") != -1))
            return "String";
        if (str.indexOf("DATE") != -1)
            return "java.util.Date";
        if (str.equals("NUMBER")) {
            if (paramInt2 > 0)
                return "Float";
            if (paramInt1 < 10)
                return "Integer";
            return "Long";
        }
        return "String";
    }
}
