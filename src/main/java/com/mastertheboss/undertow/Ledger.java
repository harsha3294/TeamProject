package com.mastertheboss.undertow;

import java.util.HashMap;

public class Ledger {

    private HashMap<String,Long> accountLedger;

    public Ledger(){
        accountLedger= new HashMap<>(  );
    }

    public void Deposit(String account,long amount){
        if(accountLedger.containsKey( account )){
            accountLedger.put(account,accountLedger.get(account)+amount);
        }else{
            this.accountLedger.put(account,amount);
        }
    }

    public boolean withDraw(String account,long amount){
        if(accountLedger.containsKey( account )){
            long previousBalance = accountLedger.get( account );
            if(previousBalance<amount) return false;
            else {
                accountLedger.put(account,previousBalance-amount);
                return true;
            }}else return false;

        }

}
