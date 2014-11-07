import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;

public class AES {
	final static int[] rcon = {
		0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 
		0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 
		0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 
		0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 
		0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 
		0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 
		0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 
		0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 
		0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 
		0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 
		0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 
		0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 
		0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 
		0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 
		0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 
		0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d
	};

	final static int[] sbox = {
	   0x63, 0x7C, 0x77, 0x7B, 0xF2, 0x6B, 0x6F, 0xC5, 0x30, 0x01, 0x67, 0x2B, 0xFE, 0xD7, 0xAB, 0x76,
	   0xCA, 0x82, 0xC9, 0x7D, 0xFA, 0x59, 0x47, 0xF0, 0xAD, 0xD4, 0xA2, 0xAF, 0x9C, 0xA4, 0x72, 0xC0,
	   0xB7, 0xFD, 0x93, 0x26, 0x36, 0x3F, 0xF7, 0xCC, 0x34, 0xA5, 0xE5, 0xF1, 0x71, 0xD8, 0x31, 0x15,
	   0x04, 0xC7, 0x23, 0xC3, 0x18, 0x96, 0x05, 0x9A, 0x07, 0x12, 0x80, 0xE2, 0xEB, 0x27, 0xB2, 0x75,
	   0x09, 0x83, 0x2C, 0x1A, 0x1B, 0x6E, 0x5A, 0xA0, 0x52, 0x3B, 0xD6, 0xB3, 0x29, 0xE3, 0x2F, 0x84,
	   0x53, 0xD1, 0x00, 0xED, 0x20, 0xFC, 0xB1, 0x5B, 0x6A, 0xCB, 0xBE, 0x39, 0x4A, 0x4C, 0x58, 0xCF,
	   0xD0, 0xEF, 0xAA, 0xFB, 0x43, 0x4D, 0x33, 0x85, 0x45, 0xF9, 0x02, 0x7F, 0x50, 0x3C, 0x9F, 0xA8,
	   0x51, 0xA3, 0x40, 0x8F, 0x92, 0x9D, 0x38, 0xF5, 0xBC, 0xB6, 0xDA, 0x21, 0x10, 0xFF, 0xF3, 0xD2,
	   0xCD, 0x0C, 0x13, 0xEC, 0x5F, 0x97, 0x44, 0x17, 0xC4, 0xA7, 0x7E, 0x3D, 0x64, 0x5D, 0x19, 0x73,
	   0x60, 0x81, 0x4F, 0xDC, 0x22, 0x2A, 0x90, 0x88, 0x46, 0xEE, 0xB8, 0x14, 0xDE, 0x5E, 0x0B, 0xDB,
	   0xE0, 0x32, 0x3A, 0x0A, 0x49, 0x06, 0x24, 0x5C, 0xC2, 0xD3, 0xAC, 0x62, 0x91, 0x95, 0xE4, 0x79,
	   0xE7, 0xC8, 0x37, 0x6D, 0x8D, 0xD5, 0x4E, 0xA9, 0x6C, 0x56, 0xF4, 0xEA, 0x65, 0x7A, 0xAE, 0x08,
	   0xBA, 0x78, 0x25, 0x2E, 0x1C, 0xA6, 0xB4, 0xC6, 0xE8, 0xDD, 0x74, 0x1F, 0x4B, 0xBD, 0x8B, 0x8A,
	   0x70, 0x3E, 0xB5, 0x66, 0x48, 0x03, 0xF6, 0x0E, 0x61, 0x35, 0x57, 0xB9, 0x86, 0xC1, 0x1D, 0x9E,
	   0xE1, 0xF8, 0x98, 0x11, 0x69, 0xD9, 0x8E, 0x94, 0x9B, 0x1E, 0x87, 0xE9, 0xCE, 0x55, 0x28, 0xDF,
	   0x8C, 0xA1, 0x89, 0x0D, 0xBF, 0xE6, 0x42, 0x68, 0x41, 0x99, 0x2D, 0x0F, 0xB0, 0x54, 0xBB, 0x16
	};

    final static int[] LogTable = {
	0,   0,  25,   1,  50,   2,  26, 198,  75, 199,  27, 104,  51, 238, 223,   3, 
	100,   4, 224,  14,  52, 141, 129, 239,  76, 113,   8, 200, 248, 105,  28, 193, 
	125, 194,  29, 181, 249, 185,  39, 106,  77, 228, 166, 114, 154, 201,   9, 120, 
	101,  47, 138,   5,  33,  15, 225,  36,  18, 240, 130,  69,  53, 147, 218, 142, 
	150, 143, 219, 189,  54, 208, 206, 148,  19,  92, 210, 241,  64,  70, 131,  56, 
	102, 221, 253,  48, 191,   6, 139,  98, 179,  37, 226, 152,  34, 136, 145,  16, 
	126, 110,  72, 195, 163, 182,  30,  66,  58, 107,  40,  84, 250, 133,  61, 186, 
	43, 121,  10,  21, 155, 159,  94, 202,  78, 212, 172, 229, 243, 115, 167,  87, 
	175,  88, 168,  80, 244, 234, 214, 116,  79, 174, 233, 213, 231, 230, 173, 232, 
	44, 215, 117, 122, 235,  22,  11, 245,  89, 203,  95, 176, 156, 169,  81, 160, 
	127,  12, 246, 111,  23, 196,  73, 236, 216,  67,  31,  45, 164, 118, 123, 183, 
	204, 187,  62,  90, 251,  96, 177, 134,  59,  82, 161, 108, 170,  85,  41, 157, 
	151, 178, 135, 144,  97, 190, 220, 252, 188, 149, 207, 205,  55,  63,  91, 209, 
	83,  57, 132,  60,  65, 162, 109,  71,  20,  42, 158,  93,  86, 242, 211, 171, 
	68,  17, 146, 217,  35,  32,  46, 137, 180, 124, 184,  38, 119, 153, 227, 165, 
	103,  74, 237, 222, 197,  49, 254,  24,  13,  99, 140, 128, 192, 247, 112,   7};

    final static int[] AlogTable = {
	1,   3,   5,  15,  17,  51,  85, 255,  26,  46, 114, 150, 161, 248,  19,  53, 
	95, 225,  56,  72, 216, 115, 149, 164, 247,   2,   6,  10,  30,  34, 102, 170, 
	229,  52,  92, 228,  55,  89, 235,  38, 106, 190, 217, 112, 144, 171, 230,  49, 
	83, 245,   4,  12,  20,  60,  68, 204,  79, 209, 104, 184, 211, 110, 178, 205, 
	76, 212, 103, 169, 224,  59,  77, 215,  98, 166, 241,   8,  24,  40, 120, 136, 
	131, 158, 185, 208, 107, 189, 220, 127, 129, 152, 179, 206,  73, 219, 118, 154, 
	181, 196,  87, 249,  16,  48,  80, 240,  11,  29,  39, 105, 187, 214,  97, 163, 
	254,  25,  43, 125, 135, 146, 173, 236,  47, 113, 147, 174, 233,  32,  96, 160, 
	251,  22,  58,  78, 210, 109, 183, 194,  93, 231,  50,  86, 250,  21,  63,  65, 
	195,  94, 226,  61,  71, 201,  64, 192,  91, 237,  44, 116, 156, 191, 218, 117, 
	159, 186, 213, 100, 172, 239,  42, 126, 130, 157, 188, 223, 122, 142, 137, 128, 
	155, 182, 193,  88, 232,  35, 101, 175, 234,  37, 111, 177, 200,  67, 197,  84, 
	252,  31,  33,  99, 165, 244,   7,   9,  27,  45, 119, 153, 176, 203,  70, 202, 
	69, 207,  74, 222, 121, 139, 134, 145, 168, 227,  62,  66, 198,  81, 243,  14, 
	18,  54,  90, 238,  41, 123, 141, 140, 143, 138, 133, 148, 167, 242,  13,  23, 
	57,  75, 221, 124, 132, 151, 162, 253,  28,  36, 108, 180, 199,  82, 246,   1};

	public static byte[][] st = new byte[4][4];
	public static byte[][] expanded_key = new byte[4][44];

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

			//byte[][] st = new byte[4][4];
			// String buf = new String();
			// buf = inputReader.readLine();
			// st = format_input(buf);

			byte[][] cipher_key = new byte[4][4];
			String buf2 = new String();
			buf2 = keyReader.readLine();
			cipher_key = format_input(buf2);

			// for (int i = 0; i < 4; i++) {
			// 	for (int j = 0; j < 4; j++) {
			// 		System.out.print(st[i][j] & 0xff);
			// 	}
			// 	System.out.println();
			// }

			//byte[][] expanded_key = new byte[4][44];
			expanded_key = key_expansion(cipher_key);
			// for (int i = 0; i < 4; i++) {
			// 	for (int j = 0; j < 4; j++) {
			// 		System.out.print(cipher_key[i][j]);
			// 	}
			// 	System.out.println();
			// }

			if(option.equalsIgnoreCase("E")){
				String buf = new String();
				while ((buf = inputReader.readLine()) != null){
					st = format_input(buf);
					encrypt();
				}
			}

			// if(option.equalsIgnoreCase("D"))
			// 	decrypt();

		} catch(Exception e) {
			System.out.println("");
			System.out.println("Caught Exception: " + e);
		}
	}

	public static byte[][] format_input(String s){
		byte[][] st = new byte[4][4];
		String hexstring = "";
			int hexval = 0;
			for (int i = 0; i < 32; i++) {
				if (i < s.length()) {
					if (i % 2 == 0) {
						hexstring = s.substring(i,i+1);
						hexval = Integer.valueOf(hexstring, 16).intValue()*16;
					} else {
						hexstring = s.substring(i,i+1);
						hexval += Integer.valueOf(hexstring, 16).intValue();
					}
				} else {
					if (i % 2 == 0) {
						hexval = 0;
					} else 
						hexval += 0;

				}

				
				if (i % 2 == 1) {
					st[i/8][(i/2)%4] = (byte)hexval;
					hexval = 0;
				}
			} 
			return st;
	}

	public static void encrypt(){
		// round 0: addRoundKey
		// round 1 - 9: subBytes, shiftRows, MixColumns, addRoundKey
		// round 10: subBytes, shiftRows, addRoundKey
		int round = 0;
		if (round == 0){
			addRoundKey(round);
		}
		round++;
		while(round < 11){
			subBytes();
			shiftRows();
			if (round != 10){
				for(int i = 0; i < 4; i++){
					mixColumn2(i);
				}
			}
			addRoundKey(round);
			round++;

		}


		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				System.out.print(Integer.toHexString(st[i][j] & 0xff));
				System.out.print(" ");
			}
			System.out.println();
		}
		
	}

	public static void decrypt(){

		
	}

	public static void subBytes(){
		for (int i = 0;i < 4 ;i++ ) {
			for (int j = 0;j < 4 ;j++ ) {
				st[i][j] = (byte)sbox[st[i][j] & 0xff];
			}	
		}
	}
	// XOR the key with the state matrix
	public static void addRoundKey(int round){

		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				st[i][j] ^= expanded_key[i][(round*4) + j];
			}
	    }
	}

	public static void shiftRows(){
		byte[][] st_copy = new byte[4][4];
		for(int i = 0; i < 4; i++){
			st_copy[i] = st[i].clone();
		}


		for (int i = 1; i < 4; i++) {
			for (int j = 0; j < 4; j++ ) {

				st[i][j] = st_copy[i][(j+i)%4];	
			}
		}
	}

	private static byte mul (int a, byte b) {
		int inda = (a < 0) ? (a + 256) : a;
		int indb = (b < 0) ? (b + 256) : b;

		if ( (a != 0) && (b != 0) ) {
		    int index = (LogTable[inda] + LogTable[indb]);
		    byte val = (byte)(AlogTable[ index % 255 ] );
		    return val;
		}
		else 
		    return 0;
    } // mul

    // In the following two methods, the input c is the column number in
    // your evolving state matrix st (which originally contained 
    // the plaintext input but is being modified).  Notice that the state here is defined as an
    // array of bytes.  If your state is an array of integers, you'll have
    // to make adjustments. 

    public static void mixColumn2 (int c) {
		// This is another alternate version of mixColumn, using the 
		// logtables to do the computation.
		
		byte a[] = new byte[4];
		
		// note that a is just a copy of st[.][c]
		for (int i = 0; i < 4; i++) 
		    a[i] = st[i][c];
		
		// This is exactly the same as mixColumns1, if 
		// the mul columns somehow match the b columns there.
		st[0][c] = (byte)(mul(2,a[0]) ^ a[2] ^ a[3] ^ mul(3,a[1]));
		st[1][c] = (byte)(mul(2,a[1]) ^ a[3] ^ a[0] ^ mul(3,a[2]));
		st[2][c] = (byte)(mul(2,a[2]) ^ a[0] ^ a[1] ^ mul(3,a[3]));
		st[3][c] = (byte)(mul(2,a[3]) ^ a[1] ^ a[2] ^ mul(3,a[0]));
	} // mixColumn2

	public static void invMixColumn2 (int c) {
		byte a[] = new byte[4];
		
		// note that a is just a copy of st[.][c]
		for (int i = 0; i < 4; i++) 
		    a[i] = st[i][c];
		
		st[0][c] = (byte)(mul(0xE,a[0]) ^ mul(0xB,a[1]) ^ mul(0xD, a[2]) ^ mul(0x9,a[3]));
		st[1][c] = (byte)(mul(0xE,a[1]) ^ mul(0xB,a[2]) ^ mul(0xD, a[3]) ^ mul(0x9,a[0]));
		st[2][c] = (byte)(mul(0xE,a[2]) ^ mul(0xB,a[3]) ^ mul(0xD, a[0]) ^ mul(0x9,a[1]));
		st[3][c] = (byte)(mul(0xE,a[3]) ^ mul(0xB,a[0]) ^ mul(0xD, a[1]) ^ mul(0x9,a[2]));
     } // invMixColumn2


	/* This is the core key expansion, which, given a 4-byte value,
 	* does some scrambling */
	public static byte[] substitute_t(byte[] in, byte i) {
        byte a, c;
        /* Rotate the input 8 bits to the left */
        a = in[0];
        for(c = 0;c < 3; c++) {
            in[c] = in[c + 1];
        }
        in[3] = a;
        /* Apply Rijndael's s-box on all 4 bytes */
        for(a = 0; a < 4; a++) {
                in[a] = (byte)sbox[in[a] & 0xff];
        }
        /* On just the first byte, add 2^i to the byte */
        in[0] ^= rcon[i];
        return in;
	}

	public static byte[][] key_expansion(byte[][] key){
		byte[] t = new byte[4];
		byte[][] expanded_key = new byte[4][44];
		byte rcon_val = 1;
		//int current_col = 4;
	
		// Adding key to expanded key
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				expanded_key[i][j] = key[i][j];
			}
	    }

		for(int current_col = 4; current_col < 44;) {
			// last column of previous block
			
			for (int i = 0; i < 4; i++){
				t[i] = expanded_key[i][current_col - 1];
			}

			//Rotate, sub bytes (sbox), and RCON
			t = substitute_t(t, rcon_val);

			// set the first round column
			for(int i = 0; i < 4; i++){
				expanded_key[i][current_col] =  (byte)(t[i] ^ expanded_key[i][current_col - 4]);
			}

			current_col++;
			for(int i = 0; i < 3; i++){
				for (int j = 0; j < 4; j++){
					expanded_key[j][current_col] = (byte)(expanded_key[j][current_col - 4] ^ expanded_key[j][current_col - 1]);
				}
				current_col++;
			}
			rcon_val++;
		}

		// for (int i = 0; i < 4; i++){
		// 	System.out.print(expanded_key[i][43] & 0xFF);
		// }

		return expanded_key;
	}
}

// 2b28ab097eaef7cf15d2154f16a6883c