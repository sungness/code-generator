package com.sungness.code.generate.db;

import com.sungness.code.generate.exception.CodegenException;
import com.sungness.code.generate.model.schema.TableSchema;
import java.util.List;

public abstract interface IDbHelper {
    public abstract void setUrl(String paramString1, String paramString2,
                                String paramString3);

    public abstract TableSchema getByTable(String paramString)
            throws CodegenException;

    public abstract List<String> getAllTable() throws CodegenException;
}