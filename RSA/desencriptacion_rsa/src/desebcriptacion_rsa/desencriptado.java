
package desebcriptacion_rsa;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class desencriptado {
    	
    public static void main(String[] args) {
		
	try {
                Cipher cifrador = Cipher.getInstance("RSA");
                byte lecturaBytes[] = Files.readAllBytes(Paths.get("../fileEncrypted.dat"));
                
		FileInputStream clPrivada;
                clPrivada = new FileInputStream("../privateKey.dat");
                int numBtyesPri = clPrivada.available();
                byte[] Privatebytes = new byte[numBtyesPri];
                clPrivada.read(Privatebytes);
                clPrivada.close();
	    

		KeyFactory keyPrivada = KeyFactory.getInstance("RSA");
		KeySpec ks = new PKCS8EncodedKeySpec(Privatebytes);
		PrivateKey clavePrivada = keyPrivada.generatePrivate(ks);
		
		cifrador.init(Cipher.DECRYPT_MODE, clavePrivada);
		byte[] mensajeDescifrado = cifrador.doFinal(lecturaBytes);
		System.out.println("DESENCRIPTADO:  " + new String(mensajeDescifrado));
		      
		} catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException 
                        |  IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException e) {
      
		} 

	}
    
}
