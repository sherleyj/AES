import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;

public class AES {
	public static void main (String args[]) throws IOException {
		String option = args[0];
		// File keyFile = new File(args[1]);

		try{
		// remember: check for extension and remove it.
			InputStream inputFile = new FileInputStream(args[2]);
			InputStream keyFile = new FileInputStream(args[1]);

		// create new input stream reader
			InputStreamReader inputStream = new InputStreamReader(inputFile); 
			InputStreamReader keyStream = new InputStreamReader(keyFile); 

        // create new buffered reader
			BufferedReader inputReader = new BufferedReader(inputStream);
			BufferedReader keyReader = new BufferedReader(keyStream);

		// char buffer
			char[] cbuf = new char[inputFile.available()];
			inputReader.read(cbuf, 0, inputFile.available());
			char[] cipher_key = new char[keyFile.available()];
			keyReader.read(cipher_key, 0, keyFile.available());

			if(option.equalsIgnoreCase("E"))
				encrypt(cbuf, cipher_key);

			if(option.equalsIgnoreCase("D"))
				decrypt(cbuf);

		} catch(Exception e) {
			System.out.println("");
			System.out.println("Caught Exception: " + e);
		}
	}

	public static void encrypt(char[] cbuf, char[] cipher_key){
		for (int i = 0; i < cbuf.length; i++){
			System.out.print(cbuf[i]);
		}
		for (int i = 0; i < cipher_key.length; i++){
			System.out.print(cipher_key[i]);
		}
	}
	public static void decrypt(char[] cbuf){
		for (int i = 0; i < cbuf.length; i++){
			System.out.println(cbuf[i]);
		}
	}
}