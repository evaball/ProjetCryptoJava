/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package awaball.classes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

/**
 *
 * @author Awa Ball
 */
public class ChiffrementDechiffrementAsymetrique {
    public static void encrypt(String algorithme, String key, String fichier, String path) 
            throws Exception{
        String filePath = path +".txt";
        FileInputStream fis = new FileInputStream(fichier);
        FileOutputStream fos = new FileOutputStream(filePath);
        PublicKey publicKey = KeyGenerate.getPubKey(key);
        
        Cipher cipher = Cipher.getInstance(algorithme);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        CipherInputStream cis = new CipherInputStream(fis, cipher);
        
        byte[] buf = new byte[8];
        int i = cis.read(buf);
        while (i != -1) {            
            fos.write(buf, 0, i);
            i = cis.read(buf);
        }
        fis.close();
        fos.close();
    }

    public static void decrypt(String algo, String key, String chiffre, String filePath) 
            throws FileNotFoundException, Exception{
        String path = filePath +".txt";
        FileInputStream fis2 = new FileInputStream(chiffre);
        FileOutputStream fos2 = new FileOutputStream(path);
        PrivateKey privateKey = KeyGenerate.getPrivKey(key);
        
        Cipher decipher = Cipher.getInstance(algo);
        decipher.init(Cipher.DECRYPT_MODE, privateKey);
        CipherInputStream cis1 = new CipherInputStream(fis2, decipher);
        byte[] buf = new byte[8];
        int j = cis1.read(buf);
        while (j != -1 ) {            
            fos2.write(buf, 0, j);
            j = cis1.read(buf);
        }
        fis2.close();
        fos2.close();
    }
    
}
