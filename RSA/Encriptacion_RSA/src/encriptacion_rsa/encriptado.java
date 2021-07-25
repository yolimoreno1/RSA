
package encriptacion_rsa;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class encriptado {

    private static String txt;
    private static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    
    
   public static void main(String[] args) throws Exception {
    
      KeyPairGenerator kgp = KeyPairGenerator.getInstance("RSA");
      KeyPair kp = kgp.generateKeyPair();
      
      PublicKey clavePublica = kp.getPublic();
      PrivateKey clavePrivada = kp.getPrivate();

      guardar(clavePublica, "../publicKey.dat");

      guardar(clavePrivada, "../privateKey.dat");

      Cipher cifrador = Cipher.getInstance("RSA");

      System.out.println("Escribe un mensaje: ");
      txt= bf.readLine();
      byte textoPlano[] = txt.getBytes();

      cifrador.init(Cipher.ENCRYPT_MODE, clavePublica);
      byte[] textoCifrado = cifrador.doFinal(textoPlano);
      System.out.println(new String(textoCifrado));
      
      
      FileOutputStream fileEncrypted = new FileOutputStream ("../fileEncrypted.dat");
      fileEncrypted.write(textoCifrado);
      fileEncrypted.close(); 

   }

   private static void guardar(Key clave, String fichero) throws Exception {
       
      byte[] publicKeyBytes = clave.getEncoded();
      FileOutputStream fileOS = new FileOutputStream(fichero);
      fileOS.write(publicKeyBytes);
      fileOS.close();
      
   }
}
