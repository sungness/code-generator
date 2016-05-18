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
import java.util.List;

public class MySqlHelper implements IDbHelper {
    String sqlColumns = "select * from information_schema.columns where table_schema=DATABASE() and table_name='%s' ";
    String sqlComment = "select table_name,table_comment  from information_schema.tables t where t.table_schema=DATABASE() and table_name='%s' ";
    String sqlAllTable = "select table_name,table_comment from information_schema.tables t where t.table_schema=DATABASE()";
    private String url = "";
    private String username = "";
    private String password = "";

    public MySqlHelper() throws CodegenException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException localClassNotFoundException) {
            throw new CodegenException("找不到mysql驱动!",
                    localClassNotFoundException);
        }
    }

    public void setUrl(String paramString1, String paramString2,
            String paramString3) {
        this.url = paramString1;
        this.username = paramString2;
        this.password = paramString3;
    }

    public TableSchema getByTable(String tableName) throws CodegenException {
        TableSchema tableSchema = getTableModel(tableName);
        List<ColumnSchema> columnSchemaList = getColumnsByTable(tableName);
        tableSchema.setColumnList(columnSchemaList);
        return tableSchema;
    }

    private List<ColumnSchema> getColumnsByTable(String paramString)
            throws CodegenException {
        DaoHelper localDaoHelper = new DaoHelper(this.url, this.username,
                this.password);
        String str = String.format(this.sqlColumns,
                new Object[] { paramString });
        List localList = localDaoHelper.queryForList(str, new MySqlMapCmd());
        return localList;
    }

    private TableSchema getTableModel(String paramString)
            throws CodegenException {
        TableSchema localTableSchema = new TableSchema();
        DaoHelper localDaoHelper = new DaoHelper(this.url, this.username,
                this.password);
        String str1 = String.format(this.sqlComment,
                new Object[] { paramString });
        String str2 = (String) localDaoHelper.queryForObject(str1,
                new MapCmd() {
                    public String getObjecFromRs(ResultSet paramResultSet)
                            throws SQLException {
                        String str = paramResultSet.getString("table_comment");
                        return str;
                    }
                });
        localTableSchema.setTableName(paramString);
        if (StringUtil.isEmpty(str2))
            str2 = paramString;
        if (str2.startsWith("InnoDB free")) {
            str2 = paramString;
            localTableSchema.setTableComment(str2);
        } else {
            if (str2.indexOf(";") != -1) {
                int i = str2.indexOf(";");
                str2 = str2.substring(0, i);
            }
            String[] arrayOfString = str2.split("\n");
            localTableSchema.setTableComment(arrayOfString[0]);
        }
        return localTableSchema;
    }

    public List<String> getAllTable() throws CodegenException {
        DaoHelper localDaoHelper = new DaoHelper(this.url, this.username,
                this.password);
        List localList = localDaoHelper.queryForList(this.sqlAllTable,
                new MapCmd() {
                    public String getObjecFromRs(ResultSet paramResultSet)
                            throws SQLException {
                        return paramResultSet.getString("table_name");
                    }
                });
        return localList;
    }

    public static void main(String[] paramArrayOfString)
            throws CodegenException {
        MySqlHelper localMySqlHelper = new MySqlHelper();
        localMySqlHelper
                .setUrl("jdbc:mysql://192.168.1.8:3306/bpm?useUnicode=true&amp;characterEncoding=utf-8",
                        "root", "root");
        TableSchema localTableSchema = localMySqlHelper.getByTable("act_ru_task");
        System.out.println("ok");
    }
}
