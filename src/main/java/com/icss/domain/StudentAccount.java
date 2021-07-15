package com.icss.domain;

public class StudentAccount {
    private  Integer s_id;
    private  Integer s_studentid;
    private  String  s_password;
    private  String  s_phone;

    public Integer getS_id() {
        return s_id;
    }

    public void setS_id(Integer s_id) {
        this.s_id = s_id;
    }

    public Integer getS_studentid() {
        return s_studentid;
    }

    public void setS_studentid(Integer s_studentid) {
        this.s_studentid = s_studentid;
    }

    public String getS_password() {
        return s_password;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }

    public String getS_phone() {
        return s_phone;
    }

    public void setS_phone(String s_phone) {
        this.s_phone = s_phone;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public Double getS_account() {
        return s_account;
    }

    public void setS_account(Double s_account) {
        this.s_account = s_account;
    }

    public String getS_payway() {
        return s_payway;
    }

    public void setS_payway(String s_payway) {
        this.s_payway = s_payway;
    }

    public Double getS_debt() {
        return s_debt;
    }

    public void setS_debt(Double s_debt) {
        this.s_debt = s_debt;
    }

    private  String s_name;
    private  Double  s_account;
    private  String  s_payway;
    private  Double  s_debt;
}
