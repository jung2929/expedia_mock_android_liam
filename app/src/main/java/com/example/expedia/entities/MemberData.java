package com.example.expedia.entities;

public class MemberData {
    private String Email;
    private String Pw;
    private String Name;

    public MemberData(String email, String pw, String name) {
        Email = email;
        Pw = pw;
        Name = name;
    }

    public MemberData(String email, String pw) {
        Email = email;
        Pw = pw;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPw() {
        return Pw;
    }

    public void setPw(String pw) {
        Pw = pw;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
