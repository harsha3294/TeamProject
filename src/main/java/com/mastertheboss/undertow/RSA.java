package com.mastertheboss.undertow;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigInteger;

;

/**
 *
 * @author manishsombansh
 */
public class RSA {
    
    public static long PRIVATE_KEY =  379666662787L;
      
    public static long encrypt(long senderAccountId,long hash){
        BigInteger senderBig = new BigInteger(String.valueOf(senderAccountId));
        BigInteger privateKeyBig = new BigInteger(String.valueOf(PRIVATE_KEY));
        BigInteger hashBig = new BigInteger(String.valueOf(hash));
        BigInteger bi = hashBig.modPow(privateKeyBig, senderBig);
       
        return bi.longValue();
      
    }
    
    private static String bytesToHex(byte[] hash) {
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < hash.length; i++) {
    String hex = Integer.toHexString(0xff & hash[i]);
    if(hex.length() == 1) hexString.append('0');
        hexString.append(hex);
    }
    return hexString.toString();
}
    
    public static String decrypt(long senderAccountId,long senderPublicKey,long txnSignature){
        BigInteger senderBig = new BigInteger(String.valueOf(senderAccountId));
        BigInteger publicKeyBig = new BigInteger(String.valueOf(senderPublicKey));
        BigInteger signatureBig = new BigInteger(String.valueOf(txnSignature));
        BigInteger bi = signatureBig.modPow(publicKeyBig, senderBig);
       
        return bi.toString(16);
      
    }
    
    
    
}
