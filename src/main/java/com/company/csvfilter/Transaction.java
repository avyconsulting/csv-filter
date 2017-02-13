package com.company.csvfilter;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {

    public static final String HEAD = "SOURCE,ID,TIMESTAMP,TYPE,VALUE,COMMISSION";
    private final String source;
    private final String id;
    private final LocalDateTime timestamp;
    private final String type;
    private final String value;
    private final String commission;

    public Transaction(String source, String id, String timestamp, String type, String value, String commission) {
        this.source = source;
        this.id = id;
        this.timestamp = LocalDateTime.parse(timestamp);
        this.type = type;
        this.value = value;
        this.commission = commission;
    }

    @Override
    public boolean equals(Object obj) {
        if (isNotNull(obj)) {
            Transaction thisObj = (Transaction) obj;
            return Objects.equals(this.timestamp, thisObj.timestamp);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.source, this.id, this.type);
    }

    private boolean isNotNull(Object obj) {
        return null != obj && this.getClass().equals(obj.getClass());
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public String getCommission() {
        return this.commission;
    }

    public String getValue() {
        return this.value;
    }

    public String getSource() {
        return this.source;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.source)
                        .append(",")
                        .append(this.id)
                        .append(",")
                        .append(this.timestamp)
                        .append(",")
                        .append(this.type)
                        .append(",")
                        .append(this.value)
                        .append(",")
                        .append(this.commission);

        return builder.toString();
    }
}
