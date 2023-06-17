package com.driver;

public class SavingsAccount extends BankAccount{
    double rate;
    double maxWithdrawalLimit;

    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate) {
        // minimum balance is 0 by default
        super.setName(name);
        super.setBalance(balance);
        super.setMinBalance(0);
        this.maxWithdrawalLimit=maxWithdrawalLimit;
        this.rate=rate;

    }
    public void withdraw(double amount) throws Exception {
        // Might throw the following errors:
        // 1. "Maximum Withdraw Limit Exceed" : If the amount exceeds maximum withdrawal limit
        // 2. "Insufficient Balance" : If the amount exceeds balance
        if(amount>this.maxWithdrawalLimit) throw new Exception("Maximum Withdraw Limit Exceed");
        if(amount>getBalance()) throw new Exception("Insufficient Balance");
        this.setBalance(this.getBalance()-amount);


    }

    public double getSimpleInterest(int years){
        // Return the final amount considering that bank gives simple interest on current amount
        double interest=(getBalance()*years*this.rate)/100;
        return getBalance()+interest;

    }

    public double getCompoundInterest(int times, int years){
        // Return the final amount considering that bank gives compound interest on current amount given times per year
        double p=getBalance();
        double r=rate/100;
        int n=times;
        double factor=1+(r/n);
        double pdt=n*years;
        double ans=p* Math.pow(factor,pdt);
        return ans;

    }

}
