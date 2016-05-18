package com.sungness.code.generate.db.impl;

import com.sungness.code.generate.db.DaoHelper;
import com.sungness.code.generate.db.IDbHelper;
import com.sungness.code.generate.db.MapCmd;
import com.sungness.code.generate.exception.CodegenException;
import com.sungness.code.generate.model.schema.ColumnSchema;
import com.sungness.code.generate.model.schema.TableSchema;
import com.sungness.code.generate.util.StringUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class DMHelper implements IDbHelper {
    private String SQL_GET_PK = "SELECT  CONS_C.COLUMN_NAME FROM \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE  CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1  AND   CONS.TABLE_NAME='%s'";
    private String SQL_GET_TABLE_COMMENT = "SELECT TABLE_NAME,COMMENTS FROM (SELECT A.TABLE_NAME AS TABLE_NAME,DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS COMMENTS FROM \"SYS\".\"USER_TABLES\" A LEFT JOIN \"SYS\".\"SYSTABLECOMMENTS\" B ON  A.TABLE_NAME=B.TVNAME) WHERE  TABLE_NAME ='%s'";
    private final String SQL_GET_COLUMNS = "SELECT T.TABLE_NAME TABLE_NAME, T.NAME NAME,T.TYPENAME TYPENAME, T.LENGTH LENGTH,  T.PRECISION PRECISION,T.SCALE SCALE,T.DATA_DEFAULT DATA_DEFAULT,T.NULLABLE NULLABLE,T.DESCRIPTION DESCRIPTION,  (SELECT  COUNT(*)   FROM    \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE  CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1  AND   CONS.TABLE_NAME=T.TABLE_NAME  AND CONS_C.COLUMN_NAME= T.NAME) AS  IS_PK FROM (SELECT A.COLUMN_ID COLUMN_ID, A.TABLE_NAME TABLE_NAME, A.COLUMN_NAME NAME, A.DATA_TYPE TYPENAME, A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE, A.DATA_DEFAULT, A.NULLABLE, DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS DESCRIPTION  FROM \"SYS\".\"USER_TAB_COLUMNS\" A LEFT JOIN \"SYS\".\"SYSCOLUMNCOMMENTS\" B ON  A.COLUMN_NAME=B.COLNAME AND  A.TABLE_NAME=B.TVNAME  AND B.SCHNAME=user() ) T  WHERE TABLE_NAME='%S'  ORDER BY COLUMN_ID ";
    private String SQL_GET_ALL_TABLES = "SELECT TABLE_NAME,COMMENTS FROM (SELECT A.TABLE_NAME AS TABLE_NAME,DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS COMMENTS FROM \"SYS\".\"USER_TABLES\" A LEFT JOIN \"SYS\".\"SYSTABLECOMMENTS\" B ON  A.TABLE_NAME=B.TVNAME) WHERE 1=1";
    private String url;
    private String username;
    private String password;

    public DMHelper() throws CodegenException {
        try {
            Class.forName("dm.jdbc.driver.DmDriver");
        } catch (ClassNotFoundException localClassNotFoundException) {
            throw new CodegenException("找不到 达梦 驱动!",
                    localClassNotFoundException);
        }
    }

    public void setUrl(String paramString1, String paramString2,
            String paramString3) {
        this.url = paramString1;
        this.username = paramString2;
        this.password = paramString3;
    }

    private List<ColumnSchema> getColumnsByTable(String paramString)
            throws CodegenException {
        paramString = paramString.toUpperCase();
        DaoHelper localDaoHelper = new DaoHelper(this.url, this.username,
                this.password);
        String str = String
                .format("SELECT T.TABLE_NAME TABLE_NAME, T.NAME NAME,T.TYPENAME TYPENAME, T.LENGTH LENGTH,  T.PRECISION PRECISION,T.SCALE SCALE,T.DATA_DEFAULT DATA_DEFAULT,T.NULLABLE NULLABLE,T.DESCRIPTION DESCRIPTION,  (SELECT  COUNT(*)   FROM    \"SYS\".\"USER_CONSTRAINTS\" CONS, \"SYS\".\"USER_CONS_COLUMNS\" CONS_C    WHERE  CONS.CONSTRAINT_NAME=CONS_C.CONSTRAINT_NAME  AND CONS.CONSTRAINT_TYPE='P'  AND CONS_C.POSITION=1  AND   CONS.TABLE_NAME=T.TABLE_NAME  AND CONS_C.COLUMN_NAME= T.NAME) AS  IS_PK FROM (SELECT A.COLUMN_ID COLUMN_ID, A.TABLE_NAME TABLE_NAME, A.COLUMN_NAME NAME, A.DATA_TYPE TYPENAME, A.DATA_LENGTH LENGTH, A.DATA_PRECISION PRECISION, A.DATA_SCALE SCALE, A.DATA_DEFAULT, A.NULLABLE, DECODE(B.COMMENT$,NULL, A.TABLE_NAME,B.COMMENT$) AS DESCRIPTION  FROM \"SYS\".\"USER_TAB_COLUMNS\" A LEFT JOIN \"SYS\".\"SYSCOLUMNCOMMENTS\" B ON  A.COLUMN_NAME=B.COLNAME AND  A.TABLE_NAME=B.TVNAME  AND B.SCHNAME=user() ) T  WHERE TABLE_NAME='%S'  ORDER BY COLUMN_ID ",
                        new Object[] { paramString });
        List localList = localDaoHelper.queryForList(str, new DMMapCmd());
        return localList;
    }

    private String getTableComment(String paramString) throws CodegenException {
        paramString = paramString.toUpperCase();
        String str1 = String.format(this.SQL_GET_TABLE_COMMENT,
                new Object[] { paramString });
        DaoHelper localDaoHelper = new DaoHelper(this.url, this.username,
                this.password);
        String str2 = (String) localDaoHelper.queryForObject(str1,
                new MapCmd() {
                    public String getObjecFromRs(ResultSet paramResultSet)
                            throws SQLException {
                        return paramResultSet.getString("COMMENTS");
                    }
                });
        if (str2 == null)
            str2 = paramString;
        String[] arrayOfString = str2.split("\n");
        return arrayOfString[0];
    }

    private String getPk(String paramString) throws CodegenException {
        paramString = paramString.toUpperCase();
        String str1 = String.format(this.SQL_GET_PK,
                new Object[] { paramString });
        DaoHelper localDaoHelper = new DaoHelper(this.url, this.username,
                this.password);
        String str2 = "";
        try {
            str2 = (String) localDaoHelper.queryForObject(str1, new MapCmd() {
                public String getObjecFromRs(ResultSet paramResultSet)
                        throws SQLException {
                    return paramResultSet.getString("COLUMN_NAME");
                }
            });
        } catch (Exception localException) {
            throw new CodegenException("从表中取得主键出错,请检查表是否设置主键");
        }
        return str2;
    }

    private void setPk(List<ColumnSchema> paramList, String paramString) {
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
            ColumnSchema localColumnSchema = (ColumnSchema) localIterator.next();
            if (paramString.toLowerCase().equals(
                    localColumnSchema.getColumnName().toLowerCase()))
                localColumnSchema.setIsPK(true);
        }
    }

    public TableSchema getByTable(String paramString) throws CodegenException {
        paramString = paramString.toUpperCase();
        String str1 = getTableComment(paramString);
        String str2 = getPk(paramString);
        TableSchema localTableSchema = new TableSchema();
        localTableSchema.setTableName(paramString);
        localTableSchema.setTableComment(str1);
        List localList = getColumnsByTable(paramString);
        if (StringUtil.isNotEmpty(str2))
            setPk(localList, str2);
        localTableSchema.setColumnList(localList);
        return localTableSchema;
    }

    public List<String> getAllTable() throws CodegenException {
        DaoHelper localDaoHelper = new DaoHelper(this.url, this.username,
                this.password);
        return localDaoHelper.queryForList(this.SQL_GET_ALL_TABLES,
                new MapCmd() {
                    public String getObjecFromRs(ResultSet paramResultSet)
                            throws SQLException {
                        return paramResultSet.getString("TABLE_NAME");
                    }
                });
    }

    public static void main(String[] paramArrayOfString)
            throws CodegenException {
        DMHelper localDMHelper = new DMHelper();
        localDMHelper.setUrl("jdbc:oracle:thin:@localhost:1521:zyp", "zyp",
                "zyp");
        String str = localDMHelper.getPk("TEST");
    }
}
