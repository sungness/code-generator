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

public class Sql2005Helper implements IDbHelper {
    private String url = "";
    private String username = "";
    private String password = "";
    private String sqlPk = "sp_pkeys N'%s'";
    private String sqlTableComment = "select cast(b.value as varchar) comment from sys.tables a, sys.extended_properties b where a.type='U' and a.object_id=b.major_id and b.minor_id=0 and a.name='%s'";
    private String sqlColumn = "select a.name, c.name typename, a.max_length length, a.is_nullable,a.precision,a.scale,(select count(*) from sys.identity_columns where sys.identity_columns.object_id = a.object_id and a.column_id = sys.identity_columns.column_id) as autoGen,(select cast(value as varchar) from sys.extended_properties where sys.extended_properties.major_id = a.object_id and sys.extended_properties.minor_id = a.column_id) as description from sys.columns a, sys.tables b, sys.types c where a.object_id = b.object_id and a.system_type_id=c.system_type_id and b.name='%s' and c.name<>'sysname' order by a.column_id";
    private String sqlAllTables = "select name from sys.tables where type='U' and name<>'sysdiagrams'";

    public Sql2005Helper() throws CodegenException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException localClassNotFoundException) {
            throw new CodegenException("找不到sqlserver驱动!",
                    localClassNotFoundException);
        }
    }

    public void setUrl(String paramString1, String paramString2,
            String paramString3) {
        this.url = paramString1;
        this.username = paramString2;
        this.password = paramString3;
    }

    public TableSchema getByTable(String paramString) throws CodegenException {
        String str1 = getComment(paramString);
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

    private void setPk(List<ColumnSchema> paramList, String paramString) {
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext()) {
            ColumnSchema localColumnSchema = (ColumnSchema) localIterator.next();
            if (paramString.toLowerCase().equals(
                    localColumnSchema.getColumnName().toLowerCase()))
                localColumnSchema.setIsPK(true);
        }
    }

    private List<ColumnSchema> getColumnsByTable(String paramString)
            throws CodegenException {
        DaoHelper localDaoHelper = new DaoHelper(this.url, this.username,
                this.password);
        String str = String
                .format(this.sqlColumn, new Object[] { paramString });
        List localList = localDaoHelper.queryForList(str, new Sql2005MapCmd());
        return localList;
    }

    public List<String> getAllTable() throws CodegenException {
        DaoHelper localDaoHelper = new DaoHelper(this.url, this.username,
                this.password);
        List localList = localDaoHelper.queryForList(this.sqlAllTables,
                new MapCmd() {
                    public String getObjecFromRs(ResultSet paramResultSet)
                            throws SQLException {
                        return paramResultSet.getString("name");
                    }
                });
        return localList;
    }

    private String getComment(String paramString) throws CodegenException {
        DaoHelper localDaoHelper = new DaoHelper(this.url, this.username,
                this.password);
        String str1 = String.format(this.sqlTableComment,
                new Object[] { paramString });
        String str2 = (String) localDaoHelper.queryForObject(str1,
                new MapCmd() {
                    public String getObjecFromRs(ResultSet paramResultSet)
                            throws SQLException {
                        return paramResultSet.getString("comment");
                    }
                });
        str2 = str2 == null ? paramString : str2;
        String[] arrayOfString = str2.split("\n");
        return arrayOfString[0];
    }

    private String getPk(String paramString) throws CodegenException {
        DaoHelper localDaoHelper = new DaoHelper(this.url, this.username,
                this.password);
        String str1 = String.format(this.sqlPk, new Object[] { paramString });
        System.out.println(str1);
        String str2 = (String) localDaoHelper.queryForObject(str1,
                new MapCmd() {
                    public String getObjecFromRs(ResultSet paramResultSet)
                            throws SQLException {
                        return paramResultSet.getString("column_name");
                    }
                });
        return str2;
    }

    public static void main(String[] paramArrayOfString)
            throws CodegenException {
        Sql2005Helper localSql2005Helper = new Sql2005Helper();
        localSql2005Helper.setUrl(
                "jdbc:sqlserver://192.168.1.111:1433; DatabaseName=gzrs", "sa",
                "sasa");
        List localList = localSql2005Helper
                .getColumnsByTable("Doc_ArchivesResource");
    }
}
