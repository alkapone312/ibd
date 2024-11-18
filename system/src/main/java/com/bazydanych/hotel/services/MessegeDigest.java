package main.java.com.bazydanych.hotel.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessegeDigest {
    public static String md5(String message) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(message.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Handle the exception appropriately in your application
        }
    }
}
