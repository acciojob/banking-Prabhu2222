package com.driver;

import java.util.PriorityQueue;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        setName(name);
        setBalance(balance);
        setMinBalance(5000);
        if(getBalance()<5000) throw new Exception("Insufficient Balance");
        this.tradeLicenseId=tradeLicenseId;

    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
            String str=this.tradeLicenseId;
            if(str.length()==1) return ;
            if(str.length()<=0) throw new Exception("Valid License can not be generated");

            for(int i=1;i<str.length();i++){
                if(str.charAt(i)!=str.charAt(i-1)){
                    continue;
                }else{
                   String newString=generateValidLicenseId(str);
                   if(newString.length()==0) throw new Exception("Valid License can not be generated");
                   this.tradeLicenseId=newString;
                   break;
                }

            }
    }

    private  String generateValidLicenseId(String str) {
        int arr[]=new int [26];//creating freq map
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            arr[ch-'A']++;
        }
        PriorityQueue<pair> pq=new PriorityQueue<>((a,b)->{
            if(a.freq>b.freq) return -1;
            else if(a.freq<b.freq) return 1;
            else return 0;
        });
        for(int i=0;i<26;i++){
            if(arr[i]>0){
                pq.add(new pair((char)('A'+i),arr[i]));
            }
        }
        String ansStr="";
        pair block_pair=pq.remove();
        ansStr+=block_pair.ch;
        block_pair.freq--;
        while(pq.size()>0){
            pair temp_pair=pq.remove();
            ansStr+=temp_pair.ch;
            temp_pair.freq--;
            if(block_pair.freq>0){
                pq.add(block_pair);
            }
            block_pair=temp_pair;
        }
        if(block_pair.freq>0) return"";
        return ansStr;


    }
    class pair{
        char ch;
        int freq;
        pair(){}
        pair(char ch,int freq ){
            this.ch=ch;
            this.freq=freq;
        }
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }
}
