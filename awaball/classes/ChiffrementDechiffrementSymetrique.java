/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package awaball.classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author Awa Ball
 */
public class ChiffrementDechiffrementSymetrique {
    
    public static void encrypterFichier(String algorithme, String key, String source, String pathCipher)
        throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, Exception {
        
        SecretKey sk = KeyGenerate.getKey(key);        
        Cipher cipher = Cipher.getInstance(algorithme);      
        cipher.init(Cipher.ENCRYPT_MODE, sk);
        
        FileInputStream fis = null;
        FileOutputStream fos = null;
        CipherInputStream cis = null;
        String path = pathCipher + ".txt";

        try {
            fis = new FileInputStream(source);
            cis = new CipherInputStream(fis, cipher);
            fos = new FileOutputStream(path);
            byte[] b = new byte[8];
            int i = cis.read(b);
            while (i != -1) {
                fos.write(b, 0, i);
                i = cis.read(b);
            }
        } catch (IOException ioe) {
            if (fis != null) {
                try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
              }
        }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void decrypterFichier(String algoritme, String cle, String source, String pathDecipher)
        throws NoSuchAlgorithmException, NoSuchPaddingException,
        InvalidKeyException, Exception {
        
        SecretKey sk = KeyGenerate.getKey(cle);        
        Cipher cipher = Cipher.getInstance(algoritme);
        cipher.init(Cipher.DECRYPT_MODE, sk);
        
        FileInputStream fis = null;
        FileOutputStream fos = null;
        CipherInputStream cis = null;
        String path = pathDecipher +".txt";
        
        try {
            fis = new FileInputStream(source);
            cis = new CipherInputStream(fis, cipher);
            fos = new FileOutputStream(path);
            byte[] b = new byte[8];
            int i = cis.read(b);
            while (i != -1) {
                fos.write(b, 0, i);
                i = cis.read(b);
            }
        } catch (IOException ioe) {
            if (fis != null) {
                try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
              }
        }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
