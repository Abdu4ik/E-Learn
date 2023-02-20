package com.tafakkoor.englishlearningplatform.utils;
import com.tafakkoor.englishlearningplatform.dao.UserDAO;
import com.tafakkoor.englishlearningplatform.utils.validator.UserValidator;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class AES {
    private static final String SECRET_KEY = "strongpassword";
    private static final String SALT_VALUE = "verystrongpassword";
    private static final byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static String encrypt(String strToEncrypt) {
        try {
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT_VALUE.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException |
                 InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }
    public static String decrypt(String strToDecrypt) {
        try {
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT_VALUE.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException |
                 InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

    }


    public static int findNumber(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length / 2];
    }
    // explain the above method called findNumber
    // it takes an array of integers as an argument
    // it sorts the array
    // it returns the middle element of the array
    // if the array has an even number of elements, it returns the first element of the middle two elements
    // for example, if the array is [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    // the middle two elements are 5 and 6
    // the method returns 5
    // if the array is [1, 2, 3, 4, 5, 6, 7, 8, 9]
    // the middle element is 5
    // the method returns 5
    // if the array is [1, 2, 3, 4, 5, 6, 7, 8]
    // the middle two elements are 4 and 5
    // the method returns 4
    // if the array is [1, 2, 3, 4, 5, 6, 7]
    // the middle element is 4
    // the method returns 4
    // if the array is [1, 2, 3, 4, 5, 6]
    // the middle two elements are 3 and 4
    // the method returns 3
    // if the array is [1, 2, 3, 4, 5]
    // the middle element is 3
    // the method returns 3
    // if the array is [1, 2, 3, 4]
    // the middle two elements are 2 and 3
    // the method returns 2
    // if the array is [1, 2, 3]
    // the middle element is 2


}