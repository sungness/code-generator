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

public class H2Helper implements IDbHelper {
    private String sqlTableComment = "SELECT TABLE_NAME, REMARKS FROM INFORMATION_SCHEMA.TABLES T WHERE T.TABLE_SCHEMA=SCHEMA() AND UPPER(TABLE_NAME) = UPPER('%s') ";
    private String sqlColumn = "SELECT A.TABLE_NAME, A.COLUMN_NAME, A.IS_NULLABLE, A.TYPE_NAME, A.CHARACTER_OCTET_LENGTH LENGTH, A.NUMERIC_PRECISION PRECISIONS, A.NUMERIC_SCALE SCALE, B.COLUMN_LIST, A.REMARKS FROM INFORMATION_SCHEMA.COLUMNS A  JOIN INFORMATION_SCHEMA.CONSTRAINTS B ON A.TABLE_NAME=B.TABLE_NAME WHERE  A.TABLE_SCHEMA=SCHEMA() AND B.CONSTRAINT_TYPE='PRIMARY KEY' AND UPPER(A.TABLE_NAME) = UPPER('%s') ";
    private String sqlAllTables = "SELECT TABLE_NAME, REMARKS FROM INFORMATION_SCHEMA.TABLES T WHERE T.TABLE_SCHEMA=SCHEMA() ";
    private String url;
    private String username;
    private String password;

    public H2Helper() throws CodegenException {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException localClassNotFoundException) {
            throw new CodegenException("找不到H2驱动!", localClassNotFoundException);
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
        List localList = localDaoHelper.queryForList(str, new H2MapCmd());
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
                return paramResultSet.getString("TABLE_NAME");
            }
        });
    }
}
