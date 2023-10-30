/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package awaball.classes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Awa Ball
 */
public class KeyGenerate {
    
    public static SecretKey genKeySymetrique(String algo, int taille) throws Exception{
        KeyGenerator kg = KeyGenerator.getInstance(algo);
        kg.init(taille);
        SecretKey sk = kg.generateKey();
        return sk;
    }
    
    public static void saveKey(SecretKey sk, String path) throws Exception{
        String pathKey = path +".txt";
        FileOutputStream fos = new FileOutputStream(pathKey);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(sk);
        oos.close();
        fos.close();
    }
    
    public static SecretKey getKey(String path) throws Exception{
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        SecretKey sk = (SecretKey) ois.readObject();
        ois.close();
        fis.close();
        return sk;
    }
    
    public static KeyPair genKeyAsymetrique(String algo, int taille) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(algo);
        kpg.initialize(taille, new SecureRandom());
        return kpg.generateKeyPair();
    }

    public static void saveKey(KeyPair keypair, String path) throws Exception{
        String pathPub = path +"pub.txt";
        String pathPriv = path +"priv.txt";
        
        FileOutputStream fos = new FileOutputStream(pathPub);       
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(keypair.getPublic());
        oos.close();
        fos.close();

        fos = new FileOutputStream(pathPriv);
        oos = new ObjectOutputStream(fos);
        oos.writeObject(keypair.getPrivate());
        oos.close();
        fos.close();
    }

    public static PublicKey getPubKey(String path) throws Exception{
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        PublicKey pubkey = (PublicKey) ois.readObject();
        ois.close();
        fis.close();
        return pubkey;
    }

    public static PrivateKey getPrivKey(String path) throws Exception{
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        PrivateKey privkey = (PrivateKey) ois.readObject();
        ois.close();
        fis.close();
        return privkey;
    }
    
    
}
