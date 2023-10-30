/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package awaball.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 *
 * @author Awa Ball
 */
public class SignatureVerifySign {
    public static void signatureFichier(String path, String signPath, String algo, String cle) 
            throws FileNotFoundException, Exception{
        String filePath = signPath +".txt";
        FileInputStream fis = new FileInputStream(path);
        FileOutputStream fos = new FileOutputStream(filePath);
        
        PrivateKey pk = KeyGenerate.getPrivKey(cle);
        
        byte[] b = new byte[128];
        fis.read(b);
        Signature sign = Signature.getInstance(algo);
        sign.initSign(pk);
        sign.update(b);
        byte[] signature = sign.sign();
        fos.write(signature);
        
        fis.close();
        fos.close();
    }  
    
    public static boolean verifierSignature(String path, String signe, String algo, String cle) 
            throws FileNotFoundException, Exception{
        FileInputStream fis = new FileInputStream(path);
        File file = new File(signe);
        
        FileInputStream fis1 = new FileInputStream(file);
        
        PublicKey publicKey = KeyGenerate.getPubKey(cle);
                
        byte[] signature = new  byte[(int) file.length()];
        byte[] empreinte = new byte[128];
        
        fis1.read(signature);
        fis.read(empreinte);
        
        Signature sign = Signature.getInstance(algo);
        sign.initVerify(publicKey);
        sign.update(empreinte);
        fis.close();
        fis1.close();
        
        return sign.verify(signature);
    }
    
}
