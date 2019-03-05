package com.mastertheboss.undertow;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author manishsombansh
 */
public class URLSafe {
    
    public static final long TEAM_ACCOUNT=1284110893049L;
    public static final String TEAM_ACCOUNT_STRING ="1284110893049";
    private static final String INVALID_STRING="TeamCloud"+","+"240549211245"+"\n"+"<INVALID|>";
    
    public HashMap<Integer,String> blockHash;
    
    public URLSafe(){
        blockHash = new HashMap<>();
    }

    private static boolean checkForDuplicateBlockHashesAnd0Checks(HashMap<Integer,String> blockHashMap){
        
        HashSet<String> blockSet = new HashSet<String>();
        for(Map.Entry<Integer,String> block: blockHashMap.entrySet()){
            String blockHashValue= block.getValue();
            if(!blockHashValue.startsWith( "0" )) return false;
                if(blockSet.contains( blockHashValue)) return false;
                else blockSet.add(blockHashValue);
        }
        return true;
    }

    public static boolean checkValidityOfTransaction(HashMap<String,Long> ledger,String sender,long amount){


        //System.out.println("Amount "+amount+"  balance "+ledger.get(sender));
        if(ledger.get( sender )!=null){
            //System.out.println("Amount "+amount+"  balance "+ledger.get(sender));
            return ledger.get( sender ) >= amount;
        }else return ledger.get( sender ) != null || amount == 0L;

    }

    public static String computeBlockHash(int blockId,String previousBlockHash,List<String> txnHashes,String pow){


            StringBuilder s = new StringBuilder();
            s.append( blockId +"|");
            s.append(previousBlockHash+"|");
            for(int i=0;i<txnHashes.size();i++){
                if(i==txnHashes.size()-1)
                    s.append(txnHashes.get(i));
                else
                    s.append(txnHashes.get(i)+"|");
            }
           String temp = s.toString();
           String shaValue = SHA256.computeSHA256Hash(temp);
           String toBeHashed = shaValue+pow;

           String hashedBlockValue = CCHash.computeCCHash(toBeHashed);

            return hashedBlockValue;

        
    }

    /**
     * @param args the command line arguments
     */

  public static void main(String[] args) throws JSONException{
       //not working
       //String a = "eJyVktFOwzAMRX9lyjMPdmLHCb-CEOralBVt7UQLQ5r677jbVFJQkZan6DqRT058NuWuaFrzuHk6m2K_fxm-rvv3VH7qjiECMkr0Eh82pjgMl_C2NBmaQ9LIIDOIRT0nEp23DshoeVf0u6lcMSS23pvxWdOm0gyyMtgQ4zbE6cqxO03RseuHY9cmMz5slmh9aqu_aDdgCgRMji16nIHR-kAhktekTumSUAir-BGFFvhc2C1VglPWN69TH--CthJxjBfCW3-0gRAhRBVwhzGypNW8pURLJXPKjGFurC4TY4mZsbr7aIfpM9eM_Wb7TxkH4cgRZ2MMtCqMSIAW9Fty4qSof4RpX4oCQF5syI05QusYdGTcHb6EUPxiwgQ4CeW-bO7Lece1SObrLRVtfz3eptNV1kwV3DQH-qwQZcYiB56sfvoqVtDXgRnHbykf0R8=";
       
       URLSafe ob = new URLSafe();
       


       String a="eJyVldFu2zAMRX-l8HMfSIqiyP3KUAyyLbcB2qRo0nZDkX8fnWSJVDRplieDknOPLq_oj254yItl9-Pm50eXHx9_bX7vn1_K8OZPSMqIoBaA7famy08brxoJGWtQr2wWT8VLHcYIidAkWSQlApTOlx_y-mFe1n406XvotndeXYxeg2oZYk5T38f5lefV-1xav-c_3fb2puVal-X4BdcBl72sIZCopSNtEjY1iMErUykH5a-5GTlRzZ3HOERHm2vrxf0s7buBLDCa7vAuIyGJAJEGTnhichkQp7wCSRSssVLGJAHHXCERCwQQtAQtUqt9salIwfWYr0AyxNAgYTQeRCqX5v_SpJgM0w7poE2RzYQJIOJROsLhd07REEKrOJZQJpRS5QnrPHFv06R9lad876bks4n6FJ1_uCiaHMEc5ojrO_0AtsP9xirzzrSBspFk0nGorGKhFNHtSrG2SgN4O5xJ8H-s4qCtovZlSP00VFZRbZVRmHLWyqphtbx_yZvXx7xZrJZnLfvUy0uW-TF8c8QrAm8xMUMTeOMpBqwsI55V1PPN4Yo7yBLBL4Ii1kPMgQj1miYm8zM2TSzjiJDG6gqCekZN0XW4RvrUxQORMZAzpiR26q1IJGCI3xIF779JQ4R-3TDuZ-6eSMxHgs8F82Hc3MCv-vNdrAKgBWsn48DCw5CrWIU6VoNa8UxXsXp-KW-L1et6_8ayvO8DdfrYJI2ofgWtmg3oowsjneXySZyk227_AmPbo80=";
       a="eJzNld1u2zAMhV-l8HUv-CeR3KsMw6DYchugTbsmbQcUfffRaRPLQ7Ku2M18ZZCS9fHwUH7p-uuy3nRfLr6-dOXm5vvu59v7Q-2f4g3JBBHMGcQvL7pyu4togvcnIrv1bY1QhymBEnpWNQJKStZF-rpsr6d0Hcaxp1y7128RXQ8RgyYNVanayqct93fPU6j0Px4DbVc2fey6vFjybetmOMF3wE4M6qqQGY_UyIAiqByRsU7QnJ3sbA2JwHNbgwMXHoWn2HZ9FSFjyoaK4LwnPFAtj3-HYmbjLNOeWUohURaQI5NS8rNIZiILWdkhrRKnBgniVPMkRi3Sb4cfdIpGieeclXTWKdqnmvMRCWVfxkmkWIxOLVKCOlCPPiOhJ0iSKJHgnulPp3_oLZSkyxOjLVaGZI23sE27e1RvjbeGh3L1aU8BZg4lohpuaF1D2tlRkPbZ0-Q5ky7ILY_Uk2Kj1TQ5ml1I2RZinbL0x2I52NIxVqQUqKtGLGrFis77KveNWNvH-_u77edn8FR3mdFimc5-xyxyjj4GEEgW9DaORCKzYEI5euACsXYxg_8l1ZkxPHnLkk5f4BnqbJeJo2mLq6pkkOqVmnshhTOJWRYiLT19wIlZRcgx2rm5PUEt29_AROXL-WTquZCWGUYtLo3MbpD_3eOhE-aFxwcj7zFJ43FuPd4LFTRoPP5Qn9bb95_Tpj6_WfzIFaw-lSVKc3vQYlbh_P-DzDFR9_r6C0yUwxo=";
       a="eJzVlNtu2zAMhl8l8HUveBTJvcowDD6lMZBD0QTthiDvPrnJYnmD2wK7mq_knxD1kfylc9Vu6mFffVl9PVf1dvv99OO6fu7bl7ziAA1D0wT8sKrq3SmLCrcvK6dh12epQlUwwkhmjkThJFUOb-rjZgy71rom1eryLatDlzUowsDcc9v4uOXp8DpKT8-HzdAMp-rysJqjHft99zfab2B2TuGeUGICDgw2HYV1P-KSh_IivZqRl_SsTo7YjdpxeBwTmKqzAbKkN8B3oSgETI0FEO9QxCIIHnynclFfhMopHEqoBNEJiU1QbGYIrBH_AdQfx9-gEFBZSB3QJ8MROYBTpDvVovWYlJFKJOMmrY1iQkI2iVxkqOob0rve-cjsLJZ_yhObXBb12hZmx9LsLRC3IIXZX4ZTvcvXcNHrc7Ibb24SOyqGTANUJM6wJJ9oVcKUZre0a5K1lspW5eSejU4p8GNLReaThB4QdicSyZ5So08Aucq8k2shgL7H0k6ukAsGJfn32QnkQmYtqMXXVNdezI7K2VGNALOHqhkO28Pjz-uGff96nd25cLThOCfzCWwZCAkiVZfLLxIHVhw=";
       a="eJzNlMtu2zAQRX_F0DqLeXE47K8UQaEHVQvwo5DcpIDhf-8odmwqhdCgq0ob4pIUD--90Llqt_VwqL5svp6rerf7dvp1HY-5ffERqsUUEgKn-LSp6v3JxQC3x5XTsM8uVRgCRMKkMZpIIEha-fS2nrbzNFknNVhfXZ5dHTrXoJiGrHWLTT9v-XF8naX9z6nd5erytFmCTfnQ_Qn2jksmiGCJQdKdF9WXEcbgSp9nXEwBbZXegMKCXkTYeqZZm4bv8wcAI7AyRZU3xH_AIvS1rIZ3rKS66mnAaGYlFXZNEm3ig8pAQ_Q3Gf8dKjErCZGT4cMqQCEm5TsThVWjfCwAJVJsYmud1qVRETWwqpG-Md1OJxZCsRCI0ueLFaJ-iIZbaZSyFsXCRbGMu5hjUaz2eDiNx5c8TkO9W-3XB3du1AIiMZLjwMMzl4CQ7o4F0rh6Ac9BFykmQz9DCss8JTAWX6yxjPF_QnovNjP4dgcz_nyKSgjLFDH0mhsrU6QyRW169W4XKeZ9U49jPU3XLYf8ek3wXPjid1BIUvy5vHUBkZBX0RgNrLpcfgNZrjo-";
       a="eJzNlN1u2zAMhV8l8HUvSJEUqb3KUAz-UZoMqVM42TqgyLuPTlJbyeahvZsBA8aRZX4-POJb1W7qbV99WX19q-rd7tvx1-V5yO1Pf2JQNBMjVXxYVfXz0UWB6-XKcfucXapQBDRgiqpGDCkFqHx5Ux824zIjSDbM1enR1W3nGhTL0Jmg6XnLy_51lNp6l_uuHqrTw-oW7eD6n2jvwMoxRFECSRMwa1IEs7HkOo-8iGGZnhQklvTZOm5C24zaYfvkEqH4u5HQEM-A1_JEFC2Jswl9wq-ocutXakJUcG32C0u_cszr3HDh1_f9j6Gvd4t23ZG928Vs_gsGMcx2mTobnv27uBWEhRfhTZW5hO841tqJzXYFTaQRg7clnQH_CWXgvgKK3xMTASIxskxQyfcuMQmmO6akXpysLVoY0KMCSQ3_X6ZrdWR1mvFcCX7iHHrbPPVlSdKu80-U5zCUuYKMRK0WuTq09ZAXU3UPNo0NxKgxmtIcq2CcIkjgya5lcEkcbrxqMjWtCM9eITJ7pGIQjFg2cJHpb6PMk4nK-AGkMek3XsKaQOuAM5LzRWD_a0x20z6wZEQQyFA_3r4IgSmUJaXWmlPHRfuoJPKEJRMpx-h-GPLhZd932_7psq3Pr5c2TnxmiVKwmCLNU2sZC31aOMLpN-4tf8A=";

       a="eJzFkttu2kAURX8F-TkP537pr1RRZfC4WCKkAqupGvHvGQMB04oqeaqfRvtotJfPmtdmtW6HbfNl8fW1aTebb-Ov03lXVj_ryVXNxD2B_GHRtE9jDRXOX03G4anUqEFVcMI091BXpLSmjtftfn0clyw9kzaHx5oOXc1gNoalZ5tK05Ufzy9TtNyV8rs0h4fFLdi-bLu_wc64jOxsbGqYF1xR1Bo51aQvEy1H2l12T9Eb9jYFoetyyvbD96lcJEIJHUCOgP-GCuMMkyTDCxTVJsO4IJEo30MKrPQ3SFC7ezS_IhFxENV-VjgincuxspuTMqvixw2G8B9bSMWQAjYziHOiltOi4Mxgt2v78bMCARPFJ9qrQAQmB_bLspBE7pLXZfMNuUBvS13JfFlChBJaf_ID_v4P07tACkGESAbJTwjM-jZjXumrnjyX3UwgzQUydSa9zgSWbhifd0O7OV3ZlpeTxAtbAFmCCANf3xaqWHBA3kOrZBTRHA5vBYgUcw==";
       System.out.println(ob.functionHandler(ob,a));
      for(Integer i : ob.blockHash.keySet()){
          System.out.println(i + "       "+ob.blockHash.get(i));
      }
   }


    public String functionHandler(URLSafe object,String input) throws JSONException{

       String a= input;
       
       Base64.Decoder d = Base64.getUrlDecoder();
       byte[] dc = d.decode(a);
       Compressor c = new Compressor();
       byte[] bs = c.decompress(dc);
       String payLoad = new String(bs);
       JSONObject obj = new JSONObject(payLoad);
       //System.out.println(obj);


       JSONArray blocks = obj.getJSONArray("chain");
       Ledger BalanceLedger = new Ledger();
       BalanceLedger.Deposit( TEAM_ACCOUNT_STRING,0 );

       int i;

       HashMap<Integer,String> blockHashGiven= new HashMap<Integer, String>(  );

        BigInteger lasttimestempcheck= new BigInteger( "0" );

        //loop through blocks and get the information while performing checks
        String previousBlockHash="00000000";
       for(i=0;i<blocks.length();i++){

           List<String> txnHashes = new ArrayList<String>();
           JSONObject temp = blocks.getJSONObject(i);
           JSONArray txnArray = temp.getJSONArray("all_tx");
           String givenHash= temp.get("hash").toString();
           int blockidgiven = Integer.valueOf( temp.get( "id" ).toString() );
           blockHashGiven.put(blockidgiven,givenHash);


           int blockId = Integer.valueOf(temp.get("id").toString());
           for(int j=0;j<txnArray.length();j++){
               JSONObject txnObject = txnArray.getJSONObject(j);
               String receiver = txnObject.get("recv").toString();
               int amountInt = Integer.parseInt(txnObject.get("amt").toString());
               long amountLong = Long.parseLong( txnObject.get("amt").toString() );
               if(amountLong>amountInt){
                   System.out.println("Amount overflowed");
               }
               if(amountInt<0 ){
                        return INVALID_STRING;
               }
               String timeStamp = txnObject.get("time").toString();
               String amount = txnObject.get("amt").toString();

               String fee = "";
               if(txnObject.has("fee"))
                   fee = txnObject.get("fee").toString();

               // Check the validity of transaction by making sure the sender has enough money to send
               String fee2;
               if(txnObject.has( "fee" )){
               fee2 = txnObject.get("fee").toString();}
               else fee2="0";
               String sender = "";
               if(txnObject.has("send")) {
                   sender = txnObject.get( "send" ).toString();
                   long amountToWithdraw= amountLong+ Long.parseLong( fee2 );
                   if(!BalanceLedger.withDraw( sender,amountToWithdraw )) return INVALID_STRING;
               }

               BalanceLedger.Deposit( receiver,amountLong );

               // Check if time stamps are not in proper order
               if(new BigInteger( timeStamp ).compareTo(lasttimestempcheck)<=0){

                  // System.out.println("Time stamp of transactions are not in non descending order");
                   return INVALID_STRING;
               }else {
                   lasttimestempcheck = new BigInteger( timeStamp );
               }

               String toBeComputed = timeStamp+"|"+sender+"|"+receiver+"|"+amount+"|"+fee;
               
               String hashValueOfTxn = CCHash.computeCCHash(toBeComputed);
               String givenTxnHash = txnObject.get("hash").toString();

               // Check if given hash is equal to computed hash
               if(!hashValueOfTxn.equals( givenTxnHash )){
                   //System.out.println("Txn hash mismatch");
                   return INVALID_STRING;
               }
               // Check if signature is correct
               if(!sender.equals("")){
                   if(!checkValidSignature(txnObject)){
                       return INVALID_STRING;
                   }
               }else{
                   if(amountLong>500000000L)
                    {
                       //System.out.println("Reward transaction hsa more than max for recvr"+receiver+"block "+blockId);
                       return INVALID_STRING;
                   }
               }
               txnHashes.add(hashValueOfTxn);
           }

           //Compute the block hash from txnHashes and add it blockHash
           String pow = temp.get("pow").toString();
           previousBlockHash=computeBlockHash(blockId,previousBlockHash,txnHashes,pow);
           blockHash.put(blockId,previousBlockHash);
       }

        // check if the block hashes are correct
        if(!checkBlockHashes( blockHash,blockHashGiven )){
            //System.out.println("BLOCK HASH MISMATCH");
            return INVALID_STRING;
        }

        //check for zero starts with for given hashes
        if(!checkForDuplicateBlockHashesAnd0Checks( blockHashGiven )){
            //System.out.println("invalid hash");
            return INVALID_STRING;
        }

        //Check for any duplicate blocks and if the block hash starts with 0
        if(!checkForDuplicateBlockHashesAnd0Checks(blockHash)){
            return INVALID_STRING;
        }


        JSONObject newTxnInPayload = obj.getJSONObject("new_tx");
        long receiverNewTx = Long.valueOf(newTxnInPayload.get("recv").toString());
        long amountNewTx = Long.valueOf(newTxnInPayload.get("amt").toString());
        String time = newTxnInPayload.get("time").toString();
        try {

            if(new BigInteger( time ).compareTo(lasttimestempcheck)<=0){
                //System.out.println("Time mistmatch");
                return INVALID_STRING;
            }else{
                //System.out.println("last "+lasttimestempcheck+" current "+new BigInteger( time ) );
            }

            JSONObject temp = UtilClass.createNewBlock( i, receiverNewTx, amountNewTx, time, BalanceLedger, object);
            JSONArray array = temp.getJSONArray("all_tx");
            String sig = array.getJSONObject(0).get("sig").toString();
            String pow = temp.get("pow").toString();
            return "TeamCloud,240549211245\n"+"<"+sig+"|"+pow+">";

        }catch (BalanceInSufficientException e){
            return INVALID_STRING;
        }
        
    }


    private boolean checkBlockHashes(HashMap<Integer,String> blockHash,HashMap<Integer,String> blockhashgiven){

       if(blockHash.size() != blockhashgiven.size()){
           //System.out.println("the length of block hash maps not equal");
           return false;
       }
        for(Integer k:blockHash.keySet()){
            if(!blockhashgiven.get(k).equals( blockHash.get( k ) )){
                //System.out.println("Block Hash Mismatch for "+k+" given:"+blockhashgiven.get( k )+" computed:"+blockHash.get( k ));
                return false;
            }
        }
        return true;
    }

    private boolean checkValidSignature(JSONObject txnObject){
        long signature = Long.parseLong(txnObject.get("sig").toString());
        String hashInPayLoad = txnObject.get("hash").toString();
        String sender = txnObject.get("send").toString();
        String decrypted = RSA.decrypt(Long.parseLong(sender), 15619L, signature);
        Long decryptedLong = Long.parseLong( decrypted ,16);
        Long hashInPayLoadLong = Long.parseLong( hashInPayLoad,16 );

        return decryptedLong.equals( hashInPayLoadLong );
    }

    
}

