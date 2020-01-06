package com.krry.entity;

import java.io.Serializable;
import java.util.Arrays;

/**
 * date: 2020/1/3 13:06 <br>
 * author: Administrator <br>
 */
public class AlarmDetail implements Serializable{

    private static final long serialVersionUID = -8472740255819060445L;

    private String state;
    private String details;
    private String tierCode;
    private TierInfo[] siteTier;
    private String operationState;
    private String level;

    @Override
    public String toString() {
        return "AlarmDetail{" +
                "state='" + state + '\'' +
                ", details='" + details + '\'' +
                ", tierCode='" + tierCode + '\'' +
                ", siteTier=" + Arrays.toString(siteTier) +
                ", operationState='" + operationState + '\'' +
                ", level='" + level + '\'' +
                '}';
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTierCode() {
        return tierCode;
    }

    public void setTierCode(String tierCode) {
        this.tierCode = tierCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public TierInfo[] getSiteTier() {
        return siteTier;
    }

    public void setSiteTier(TierInfo[] siteTier) {
        this.siteTier = siteTier;
    }

    public String getOperationState() {
        return operationState;
    }

    public void setOperationState(String operationState) {
        this.operationState = operationState;
    }
}
