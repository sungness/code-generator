package com.sungness.code.generate.model;


public class Database {
    private String dbHelperClass;

    private String url;

    private String username;

    private String password;

    public Database(String dbHelperClass, String url,
            String username, String password) {
        this.dbHelperClass = dbHelperClass;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public String getDbHelperClass() {
        return this.dbHelperClass;
    }

    public void setDbHelperClass(String paramString) {
        this.dbHelperClass = paramString;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String paramString) {
        this.url = paramString;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String paramString) {
        this.username = paramString;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String paramString) {
        this.password = paramString;
    }
}
