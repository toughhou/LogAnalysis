package com.tcl.log.persistent.model.hbase;

/**
 * Created by _think on 2015/1/10.
 */
public class CellEntity {
    private String row;
    private String family;
    private String qualifier;
    private String value;

    public CellEntity(String row, String family, String qualifier, String value) {
        this.row = row;
        this.family = family;
        this.qualifier = qualifier;
        this.value = value;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
