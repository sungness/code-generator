package com.sungness.code.generate.db.impl;

import com.sungness.code.generate.db.DaoHelper;
import com.sungness.code.generate.db.IDbHelper;
import com.sungness.code.generate.db.MapCmd;
import com.sungness.code.generate.exception.CodegenException;
import com.sungness.code.generate.model.schema.ColumnSchema;
import com.sungness.code.generate.model.schema.TableSchema;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DB2Helper implements IDbHelper {
    private String sqlPk = "SELECT TABNAME TAB_NAME, COLNAME COL_NAME, KEYSEQ  FROM  SYSCAT.COLUMNS WHERE  TABSCHEMA='BPMX380' AND KEYSEQ>0 AND UPPER(TABNAME) = UPPER('%s')";

    private String sqlTableComment = "SELECT  TABNAME, REMARKS FROM  SYSCAT.TABLES WHERE TABSCHEMA IN (SELECT CURRENT SCHEMA FROM SYSIBM.DUAL) AND UPPER(TABNAME) = UPPER('%s') ";

    private String sqlColumn = "SELECT TABNAME, COLNAME, TYPENAME, REMARKS, NULLS, LENGTH, SCALE, KEYSEQ  FROM  SYSCAT.COLUMNS WHERE  TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) AND UPPER(TABNAME) = UPPER('%s') ";

    private String sqlAllTables = "SELECT  TABNAME, REMARKS FROM  SYSCAT.TABLES WHERE  TABSCHEMA IN (SELECT CURRENT SQLID FROM SYSIBM.DUAL) ";

    private String url;

    private String username;

    private String password;

    public DB2Helper() throws CodegenException {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
        } catch (ClassNotFoundException localClassNotFoundException) {
            throw new CodegenException("找不到DB2驱动!", localClassNotFoundException);
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
        List localList = localDaoHelper.queryForList(str, new DB2MapCmd());
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
                        return paramResultSet.getString("REMARKS");
                    }
                });
        return str2 == null ? paramString : str2;
    }

    public TableSchema getByTable(String paramString) throws CodegenException {
        paramString = paramString.toUpperCase();
        String str = getTableComment(paramString);
        TableSchema localTableSchema = new TableSchema();
        localTableSchema.setTableName(paramString);
        localTableSchema.setTableComment(str);
        List localList = getColumnsByTable(paramString);
        localTableSchema.setColumnList(localList);
        return localTableSchema;
    }

    public List<String> getAllTable() throws CodegenException {
        DaoHelper localDaoHelper = new DaoHelper(this.url, this.username,
                this.password);
        return localDaoHelper.queryForList(this.sqlAllTables, new MapCmd() {
            public String getObjecFromRs(ResultSet paramResultSet)
                    throws SQLException {
                return paramResultSet.getString("TABNAME");
            }
        });
    }

    public static void main(String[] paramArrayOfString)
            throws CodegenException {
        DB2Helper localDB2Helper = new DB2Helper();
        localDB2Helper.setUrl(
                "jdbc:db2://192.168.1.17:50000/bpmx:currentSchema=BPMX380;",
                "db2admin", "123456");
        System.out.println(localDB2Helper.getByTable("TEST1"));
    }
}