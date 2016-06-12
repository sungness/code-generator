package com.sungness.code.generate.model.schema.mysql;

import com.sungness.core.util.FieldNaming;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.StringTokenizer;

/**
 * Mysql信息架构（information_schema）-列对应的Bean
 * Created by wanghongwei on 11/15/15.
 */
public class InformationSchemaColumn implements Serializable {
    private static final long serialVersionUID = 5204275673133519108L;
    /**  */
    private String tableCatalog;
    /** 所属数据库名 */
    private String tableSchema;
    /** 表名 */
    private String tableName;
    /** 列名 */
    private String columnName;
    /** 顺序、位置 */
    private Integer ordinalPosition;
    /** 列默认值 */
    private Object columnDefault;
    /** 是否可空 NO-非空，YES-可空 */
    private String isNullable;
    /** 数据类型 */
    private String dataType;
    /** 字符串最大长度 */
    private Integer characterMaximumLength;
    /** 八位字节的字符长度 */
    private Integer characterOctetLength;
    /** 数值精度 */
    private Integer numericPrecision;
    /** 字符集 */
    private String characterSetName;
    /** 字符集 */
    private String collationName;
    /** 列类型 */
    private String columnType;
    /** key */
    private String columnKey;
    /** 附加信息（自增...） */
    private String extra;
    /** 注释 */
    private String columnComment;

    /** 字段名转换后的驼峰式名称（首字母小写） */
    private String camelCaseName;
    /** 字段名转换后的驼峰式名称（首字母大写） */
    private String upperCaseName;

    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
        this.camelCaseName = FieldNaming.toCamelCase(columnName);
        this.upperCaseName = FieldNaming.upperCaseFirstLetter(this.camelCaseName);
    }

    public Integer getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(Integer ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public Object getColumnDefault() {
        return columnDefault;
    }

    public void setColumnDefault(Object columnDefault) {
        this.columnDefault = columnDefault;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getCharacterMaximumLength() {
        return characterMaximumLength;
    }

    public void setCharacterMaximumLength(Integer characterMaximumLength) {
        this.characterMaximumLength = characterMaximumLength;
    }

    public Integer getCharacterOctetLength() {
        return characterOctetLength;
    }

    public void setCharacterOctetLength(Integer characterOctetLength) {
        this.characterOctetLength = characterOctetLength;
    }

    public Integer getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(Integer numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public String getCharacterSetName() {
        return characterSetName;
    }

    public void setCharacterSetName(String characterSetName) {
        this.characterSetName = characterSetName;
    }

    public String getCollationName() {
        return collationName;
    }

    public void setCollationName(String collationName) {
        this.collationName = collationName;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getColumnComment() {
        return columnComment;
    }

    public String getClearComment() {
        StringTokenizer token = new StringTokenizer(columnComment, ",，.:： ");
        if (token.countTokens() != 0) {
            while (token.hasMoreTokens()) {
                String str = token.nextToken();
                if (StringUtils.isNotBlank(str)) {
                    return str;
                }
            }
        }
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getCamelCaseName() {
        return camelCaseName;
    }

    public String getUpperCaseName() {
        return upperCaseName;
    }

    public String getUpperName() {
        return columnName.toUpperCase();
    }

    public String getEnComment() {
        return FieldNaming.separateCamelCase(getUpperCaseName(), " ");
    }
    public String getJavaType() {
        switch (dataType.toLowerCase()) {
            case "int":
                return "Integer";
            case "bigint":
                return "Long";
            case "decimal":
                return "BigDecimal";
            case "varchar":
            default:
                return "String";
        }
    }

    public static void main(String[] args) {
        StringTokenizer token = new StringTokenizer("关联省代码", ",，. ");
        if (token.countTokens() != 0) {
            while (token.hasMoreTokens()) {
                System.out.println(token.nextToken());
            }
        }
    }
}
