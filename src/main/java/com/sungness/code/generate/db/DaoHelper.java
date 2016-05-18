package com.sungness.code.generate.db;

import com.sungness.code.generate.exception.CodegenException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoHelper<T> {
    private String url = "";

    private String userName = "";

    private String pwd = "";

    public void setUrl(String paramString) {
        this.url = paramString;
    }

    public void setUserName(String paramString) {
        this.userName = paramString;
    }

    public void setPwd(String paramString) {
        this.pwd = paramString;
    }

    public DaoHelper(String paramString1, String paramString2,
            String paramString3) {
        this.url = paramString1;
        this.userName = paramString2;
        this.pwd = paramString3;
    }

    public T queryForObject(String paramString, MapCmd<T> paramMapCmd)
            throws CodegenException {
        Connection localConnection = null;
        Statement localStatement = null;
        ResultSet localResultSet = null;
        try {
            localConnection = DriverManager.getConnection(this.url,
                    this.userName, this.pwd);
            localStatement = localConnection.createStatement();
            localResultSet = localStatement.executeQuery(paramString);
            if (localResultSet.next()) {
                T localObject1 = paramMapCmd.getObjecFromRs(localResultSet);
                return localObject1;
            }
            System.out.println("没有到数据:" + paramString);
        } catch (SQLException localSQLException1) {
            throw new CodegenException(localSQLException1);
        } finally {
            try {
                if (localResultSet != null)
                    localResultSet.close();
                if (localStatement != null)
                    localStatement.close();
                if (localConnection != null)
                    localConnection.close();
            } catch (SQLException localSQLException4) {
                throw new CodegenException(localSQLException4);
            }
        }
        return null;
    }

    public List<T> queryForList(String paramString, MapCmd<T> paramMapCmd)
            throws CodegenException {
        Connection localConnection = null;
        Statement localStatement = null;
        ResultSet localResultSet = null;
        ArrayList localArrayList = new ArrayList();
        try {
            localConnection = DriverManager.getConnection(this.url,
                    this.userName, this.pwd);
            localStatement = localConnection.createStatement();
            localResultSet = localStatement.executeQuery(paramString);
            while (localResultSet.next())
                localArrayList.add(paramMapCmd.getObjecFromRs(localResultSet));
        } catch (SQLException localSQLException2) {
            throw new CodegenException(localSQLException2);
        } finally {
            try {
                if (localResultSet != null)
                    localResultSet.close();
                if (localStatement != null)
                    localStatement.close();
                if (localConnection != null)
                    localConnection.close();
            } catch (SQLException localSQLException3) {
                throw new CodegenException(localSQLException3);
            }
        }
        return localArrayList;
    }
}