package com.mastertheboss.undertow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class UtilClass {
    

    public static final long TEAM_ACCOUNT=1284110893049L;
    
     public static JSONObject createNewTx(long recv, long amt, String time,Ledger ledger) throws JSONException,BalanceInSufficientException{

 
        JSONObject newTxn = new JSONObject();
        newTxn.put("send",TEAM_ACCOUNT);
        newTxn.put("recv",recv );
        if(amt<0L){
            throw new BalanceInSufficientException();
        }
        newTxn.put("amt",amt );
        newTxn.put("fee",0);
        newTxn.put("time",time );
        
        String timeStamp = time;
        String sender = String.valueOf(TEAM_ACCOUNT);
        String fee = String.valueOf(0);
        String amount = Long.toString(amt);
        String receiver = Long.toString(recv);

        if(!ledger.withDraw( URLSafe.TEAM_ACCOUNT_STRING,amt )) throw new BalanceInSufficientException();


        String toBeComputed = timeStamp+"|"+sender+"|"+receiver+"|"+amount+"|"+fee;
        String hashValueOfTxn = CCHash.computeCCHash(toBeComputed);
        newTxn.put("hash",hashValueOfTxn);
        
        newTxn.put("sig",RSA.encrypt(Long.parseLong(sender), Long.parseLong(hashValueOfTxn,16)) );
        
        return newTxn;

    }
     
       static String findSum(String str1, String str2)  
{  
    // Before proceeding further, make sure length  
    // of str2 is larger.  
    if (str1.length() > str2.length()){  
        String t = str1; 
        str1 = str2; 
        str2 = t; 
    } 
  
    // Take an empty String for storing result  
    String str = "";  
  
    // Calculate lenght of both String  
    int n1 = str1.length(), n2 = str2.length();  
  
    // Reverse both of Strings 
    str1=new StringBuilder(str1).reverse().toString(); 
    str2=new StringBuilder(str2).reverse().toString(); 
  
    int carry = 0;  
    for (int i = 0; i < n1; i++)  
    {  
        // Do school mathematics, compute sum of  
        // current digits and carry  
        int sum = ((int)(str1.charAt(i) - '0') +  
                    (int)(str2.charAt(i) - '0') + carry);  
        str += (char)(sum % 10 + '0');  
  
        // Calculate carry for next step  
        carry = sum / 10;  
    }  
  
    // Add remaining digits of larger number  
    for (int i = n1; i < n2; i++)  
    {  
        int sum = ((int)(str2.charAt(i) - '0') + carry);  
        str += (char)(sum % 10 + '0');  
        carry = sum / 10;  
    }  
  
    // Add remaining carry  
    if (carry > 0)  
        str += (char)(carry + '0');  
  
    // reverse resultant String 
    str = new StringBuilder(str).reverse().toString(); 
  
    return str;  
}    
     
     

    public static JSONObject createNewBlock(int id, long recv, long amt, String time, Ledger ledger,URLSafe object) throws JSONException,BalanceInSufficientException{
        JSONObject newBlock = new JSONObject();
        JSONArray newAllTx = new JSONArray();
        newAllTx.put(createNewTx(recv,amt,time,ledger));
        newAllTx.put(createRewardTxn(time,ledger));
        newBlock.put("all_tx",newAllTx);
        newBlock.put("id",id);
        //TODO : Cal hash
        List<String> txnHashes = new ArrayList<String>();

        for(int i=0;i<newAllTx.length();i++){

            JSONObject js = newAllTx.getJSONObject(i);
            txnHashes.add(js.get("hash").toString());


        }
        
        String pow = givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect();

        String previousBlockHash = object.blockHash.get(id-1);
        while(!URLSafe.computeBlockHash(id,previousBlockHash, txnHashes, pow).startsWith("0")){
            pow = givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect();
            
        }

        String computedNewBlockHash = URLSafe.computeBlockHash(id, previousBlockHash, txnHashes,pow);
        object.blockHash.put(id,computedNewBlockHash);
        newBlock.put("hash",computedNewBlockHash);
        newBlock.put("pow",pow);

        return newBlock;
        
    }

    //TODO : LEPA FUNCTION CHANGE CHEYI
    public static String givenUsingPlainJava_whenGeneratingRandomStringBounded_thenCorrect() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                                                       (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();



        return generatedString;
    }

    public static JSONObject createRewardTxn(String time, Ledger ledger ) throws JSONException,BalanceInSufficientException{
        
        //long timeLong = Long.valueOf( time );
     
        String difference = "600000000000";
        String newTime = findSum(time, difference);

        
        long rewTXtime = Long.valueOf(newTime);

        JSONObject newRewTxn = new JSONObject();
        newRewTxn.put("recv",TEAM_ACCOUNT);
        newRewTxn.put("amt",500000000 );
        newRewTxn.put("time", Long.toString( rewTXtime ) );
        
        String timeStamp = Long.toString( rewTXtime );
        String sender = "";
        String fee = "";
        String receiver = Long.toString(TEAM_ACCOUNT);
        String amount = Long.toString(500000000L);

        
        String toBeComputed = timeStamp+"|"+sender+"|"+receiver+"|"+amount+"|"+fee;

        String hashValueOfTxn = CCHash.computeCCHash(toBeComputed);
        newRewTxn.put("hash",hashValueOfTxn);
        return newRewTxn;
    }

}
