/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package awaball.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Awa Ball
 */
public class HachageVerifyHaches {
    public static byte[] hashDigest(String algorithme, String message, String cible) 
            throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        String path = cible +".txt";
        MessageDigest md = MessageDigest.getInstance(algorithme);       
        FileInputStream fis = new FileInputStream(message);
        FileOutputStream fos = new FileOutputStream(path);
        DigestInputStream dis = new DigestInputStream(fis, md);
        
        byte[] buffer = new byte[64];
        byte[] digest = null;
        while (dis.read(buffer) != -1){
            digest = md.digest();
            fos.write(digest);
            dis.read(buffer);
        }
        fis.close();
        fos.close(); 
        
        return digest;
    }
    public static boolean comparerHash(String algorithme, String hache, String message) 
            throws NoSuchAlgorithmException, FileNotFoundException, IOException{
        
        FileInputStream fis = new FileInputStream(message);
        File file = new File(hache);
        FileInputStream fis1 = new FileInputStream(file);        
        
        MessageDigest md = MessageDigest.getInstance(algorithme);
        DigestInputStream dis = new DigestInputStream(fis, md);
        
        byte[] empreinte = new byte[(int) file.length()];

        byte[] hash = new byte[64];
        byte[] digest = null;
        
        fis1.read(empreinte);
        
        while (dis.read(hash) != -1) {            
            digest = md.digest();
            dis.read(hash);
        }
        fis.close();
        fis1.close();
        
        return MessageDigest.isEqual(digest, empreinte);
    }
}
