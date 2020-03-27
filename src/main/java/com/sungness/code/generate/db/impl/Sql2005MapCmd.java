package com.sungness.code.generate.db.impl;

import com.sungness.code.generate.db.MapCmd;
import com.sungness.code.generate.model.schema.ColumnSchema;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Sql2005MapCmd implements MapCmd<ColumnSchema> {
    public ColumnSchema getObjecFromRs(ResultSet paramResultSet)
            throws SQLException {
        ColumnSchema localColumnSchema = new ColumnSchema();
        String str1 = paramResultSet.getString("name");
        String str2 = paramResultSet.getString("typename");
        long l = paramResultSet.getLong("length");
        int i = paramResultSet.getInt("is_nullable");
        int j = paramResultSet.getInt("precision");
        int k = paramResultSet.getInt("scale");
        int m = paramResultSet.getInt("autoGen");
        String str3 = paramResultSet.getString("description");
        str3 = str3 == null ? str1 : str3;
        boolean bool = i == 0;
        String str4 = getDisplayDbType(str2, l, j, k);
        String str5 = getJavaType(str2, j, k);
        localColumnSchema.setColumnName(str1);
        localColumnSchema.setComment(str3);
        localColumnSchema.setAutoGen(m);
        localColumnSchema.setIsNotNull(bool);
        localColumnSchema.setColDbType(str2);
        localColumnSchema.setLength(l);
        localColumnSchema.setPrecision(j);
        localColumnSchema.setScale(k);
        localColumnSchema.setDisplayDbType(str4);
        localColumnSchema.setColType(str5);
        return localColumnSchema;
    }

    private String getJavaType(String paramString, int paramInt1, int paramInt2) {
        if (paramString.equals("int"))
            return "Integer";
        if (paramString.equals("bigint"))
            return "Long";
        if ((paramString.equals("smallint")) || (paramString.equals("tinyint")))
            return "Short";
        if (paramString.equals("bit"))
            return "Boolean";
        if ((paramString.indexOf("char") != -1)
                || (paramString.endsWith("text"))
                || (paramString.equals("xml")))
            return "String";
        if ((paramString.equals("double")) || (paramString.equals("money"))
                || (paramString.equals("real")))
            return "Double";
        if (paramString.equals("float"))
            return "Float";
        if (paramString.endsWith("image"))
            return "byte[]";
        if ((paramString.equals("decimal")) || (paramString.equals("numeric"))) {
            if (paramInt2 == 0) {
                if (paramInt1 <= 10)
                    return "Integer";
                return "Long";
            }
            return "Double";
        }
        if (paramString.startsWith("datetime"))
            return "java.util.Date";
        return paramString;
    }

    private String getDisplayDbType(String paramString, long paramLong,
            int paramInt1, int paramInt2) {
        if (paramString.equals("xml"))
            return "xml";
        if (paramString.indexOf("char") != -1) {
            if (paramLong == -1L)
                return paramString + "(max)";
            return paramString + "(" + paramLong + ")";
        }
        if (paramString.equals("decimal"))
            return "decimal(" + paramInt1 + "," + paramInt2 + ")";
        return paramString;
    }
}
