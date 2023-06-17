package com.driver;

public class StudentAccount extends BankAccount{

    String  institutionName;

    public StudentAccount(String name, double balance, String  institutionName) {
        //minimum balance is 0 by default
        super.setName(name);
        super.setBalance(balance);
        super.setMinBalance(0);
        this.institutionName=institutionName;

    }

}
