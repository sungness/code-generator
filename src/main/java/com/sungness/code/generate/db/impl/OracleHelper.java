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

public class OracleHelper implements IDbHelper {
    private String sqlPk = "select column_name from user_constraints c,user_cons_columns col where c.constraint_name=col.constraint_name and c.constraint_type='P' and c.table_name='%s'";
    private String sqlTableComment = "select * from user_tab_comments  where table_type='TABLE' AND table_name ='%s'";
    private String sqlColumn = "select    A.column_name NAME,A.data_type TYPENAME,A.data_length LENGTH,A.data_precision PRECISION,    A.Data_Scale SCALE,A.Data_default, A.NULLABLE, B.comments DESCRIPTION  from  user_tab_columns A,user_col_comments B where a.COLUMN_NAME=b.column_name and    A.Table_Name = B.Table_Name and A.Table_Name='%s' order by A.column_id";
    private String sqlAllTables = "select table_name from user_tables where status='VALID'";
    private String url;
    private String username;
    private String password;

    public OracleHelper() throws CodegenException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException localClassNotFoundException) {
            throw new CodegenException("找不到oracle驱动!",
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
                .format(this.sqlColumn, new Object[] { paramString });
        List localList = localDaoHelper.queryForList(str, new OracleMapCmd());
        return localList;
    }

    private String getTableComment(String paramString) throws CodegenException {
        paramString = paramString.toUpperCase();
        String str1 = String.format(this.sqlTableComment,
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
        String str1 = String.format(this.sqlPk, new Object[] { paramString });
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
        return localDaoHelper.queryForList(this.sqlAllTables, new MapCmd() {
            public String getObjecFromRs(ResultSet paramResultSet)
                    throws SQLException {
                return paramResultSet.getString("TABLE_NAME");
            }
        });
    }

    public static void main(String[] paramArrayOfString)
            throws CodegenException {
        OracleHelper localOracleHelper = new OracleHelper();
        localOracleHelper.setUrl("jdbc:oracle:thin:@localhost:1521:zyp", "zyp",
                "zyp");
        String str = localOracleHelper.getPk("TEST");
    }
}

/*
 * Location:
 * Z:\sungness\SungnessApp\java\java-bpm\bpmx3\bpmx\metadata\codegen\refLib
 * \codegen.jar Qualified Name: com.sungness.code.generate.db.impl.OracleHelper
 * JD-Core Version: 0.6.0
 */