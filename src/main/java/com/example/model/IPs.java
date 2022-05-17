package com.example.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ip")
public class IPs {
    @Id
    private String ip;

    private int connectionAttempts = 0;

    private long timeOut = 0;

    public void iterateConnectionAttempts(){
        connectionAttempts++;
    }

    public void resetConnectionAttempts(){
        connectionAttempts = 0;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }
}
