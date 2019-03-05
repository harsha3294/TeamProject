package com.mastertheboss.undertow;

import java.math.BigInteger;

public class encry {



    public static void main(String[] args){

        long sender = 509015179679L;
        long publicKey = 15619;
        long singature = 463884077351L;

        BigInteger senderBig = new BigInteger( String.valueOf( sender ) );
        BigInteger publicKeyBig = new BigInteger( String.valueOf( publicKey ) );
        BigInteger signatureBig = new BigInteger( String.valueOf( singature ) );
        BigInteger bi = signatureBig.modPow( publicKeyBig,senderBig );

        System.out.println(bi.toString(16));


    }
}
