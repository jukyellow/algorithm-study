package com.juk.algo.A0917;

import java.util.Hashtable;
import java.util.Random;

public class TinyURL {    
    public static void main(String[] args) {
        String originUrl = "https://leetcode.com/problems/design-tinyurl";
        Codec codec = new Codec();
        String tinyURL = codec.encode(originUrl);
        String decodeUrl = codec.decode(tinyURL);
        
        System.out.println("Origin URL:" + originUrl);
        System.out.println("tinyURL URL:" + tinyURL);
        System.out.println("Decode URL:" + decodeUrl);
    }
}

class Codec {
    private static Hashtable urlTable = new Hashtable(); //thread safe
    private final String baseUrl = "http://tinyurl.com/";
    
    // Encodes a URL to a shortened URL.
    public synchronized String encode(String longUrl) {
        StringBuffer rndKeySB = new StringBuffer();
        Random rnd = new Random();
        
        for (int i = 0; i < 6; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
            case 0: // a-z
                rndKeySB.append((char) ((int) (rnd.nextInt(26)) + 97));
                break;
            case 1:// A-Z
                rndKeySB.append((char) ((int) (rnd.nextInt(26)) + 65));
                break;
            case 2:// 0-9
                rndKeySB.append((rnd.nextInt(10)));
                break;
            }
        }
        
        String tinyUrl = baseUrl + rndKeySB.toString();
        urlTable.put(tinyUrl, longUrl);
        return tinyUrl;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return (String) urlTable.get(shortUrl);
    }
}