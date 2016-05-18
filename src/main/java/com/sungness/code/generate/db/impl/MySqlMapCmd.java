package com.sungness.code.generate.db.impl;

import com.sungness.code.generate.db.MapCmd;
import com.sungness.code.generate.model.schema.ColumnSchema;
import com.sungness.code.generate.util.StringUtil;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySqlMapCmd implements MapCmd<ColumnSchema> {
    public ColumnSchema getObjecFromRs(ResultSet paramResultSet)
            throws SQLException {
        ColumnSchema localColumnSchema = new ColumnSchema();
        String str1 = paramResultSet.getString("column_name");
        String str2 = paramResultSet.getString("column_key");
        long l = paramResultSet.getLong("character_octet_length");
        String str3 = paramResultSet.getString("is_nullable");
        boolean bool1 = str3.equals("YES");
        int i = paramResultSet.getInt("numeric_precision");
        int j = paramResultSet.getInt("NUMERIC_scale");
        String str4 = paramResultSet.getString("column_comment");
        str4 = StringUtil.isEmpty(str4) ? str1 : str4;
        boolean bool2 = str2.equals("PRI");
        String str5 = paramResultSet.getString("data_type");
        String str6 = getJavaType(str5, i, j);
        String str7 = getDisplayDbType(str5, l, i, j);
        String[] arrayOfString = str4.split("\n");
        localColumnSchema.setColumnName(str1);
        localColumnSchema.setIsNotNull(!bool1);
        localColumnSchema.setPrecision(i);
        localColumnSchema.setScale(j);
        localColumnSchema.setLength(l);
        localColumnSchema.setComment(arrayOfString[0]);
        localColumnSchema.setIsPK(bool2);
        localColumnSchema.setColDbType(str5);
        localColumnSchema.setColType(str6);
        localColumnSchema.setDisplayDbType(str7);
        return localColumnSchema;
    }

    private String getDisplayDbType(String paramString, long paramLong,
            int paramInt1, int paramInt2) {
        if (paramString.equals("varchar"))
            return "varchar(" + paramLong + ")";
        if (paramString.equals("decimal"))
            return "decimal(" + paramInt1 + "," + paramInt2 + ")";
        return paramString;
    }

    private String getJavaType(String paramString, int paramInt1, int paramInt2) {
        if (paramString.equals("bigint"))
            return "Long";
        if (paramString.equals("int"))
            return "Integer";
        if ((paramString.equals("tinyint")) || (paramString.equals("smallint")))
            return "Short";
        if ((paramString.equals("varchar")) || (paramString.endsWith("text")))
            return "String";
        if ((paramString.equals("varchar")) || (paramString.endsWith("text")))
            return "String";
        if (paramString.equals("double"))
            return "Double";
        if (paramString.equals("float"))
            return "Float";
        if (paramString.endsWith("blob"))
            return "byte[]";
        if (paramString.equals("decimal")) {
            if (paramInt2 == 0) {
                if (paramInt1 <= 10)
                    return "Integer";
                return "Long";
            }
            return "Double";
        }
        if (paramString.startsWith("date"))
            return "java.util.Date";
        return paramString;
    }
}

/*
 * Location:
 * Z:\sungness\SungnessApp\java\java-bpm\bpmx3\bpmx\metadata\codegen\refLib
 * \codegen.jar Qualified Name: com.sungness.code.generate.db.impl.MySqlMapCmd
 * JD-Core Version: 0.6.0
 */