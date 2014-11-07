UTEID: sj8346; mdc2984;
FIRSTNAME: Sherley; Michael;
LASTNAME: Jones; Chin;
CSACCOUNT: sj8346; micdchin;
EMAIL: sherleyjones@.utexas.edu; mchin461@gmail.com;

[Program 4]
[Description]
There is one file, AES.java.  
To run encryption: java AES e key plaintext
To run decryption: java AES d key plaintext.enc  
We created globals for the state matrix and expanded key.  The tables needed throught the encryption are also globals.  We read the file one line at a time into the state matrix and perform the encryption/decryption on it, write to file, and do this until we read the end of file. We call our expand_key() funstion which expands our 4x4 key into a 4x44 key. From there we check the flag.  If we are encrypting we call our encrypt() function.  We created a function for each transformation algorithm and call them in encrypt().  If the flag is for decryption, we call out decrypt() function.  Here we call transformation functions as well.

[Finish]
We finished all of this assignment.  We encrypt at about 5.9 MB/sec and decrypt at about  4.8 MB/sec.

[Test Cases]

[Test Case 1]
inputFile: 
328831e0435a3137f6309807a88da234
key:
2b28ab097eaef7cf15d2154f16a6883c

inputFile.enc:
3902dc1925dc116a8409850b1dfb9732
inputFile.enc.dec:
328831e0435a3137f6309807a88da234

[Test Case 2]
inputFile: 
00000000000000000000000000000000
key:
00000000000000000000000000000000

inputFile.enc:
66ef88cae98a4c344b2cfa2bd43b592e
inputFile.enc.dec:
00000000000000000000000000000000

[Test Case 3]
inputFile: 
0
key:
00000000000000000000000000000000

inputFile.enc:
66ef88cae98a4c344b2cfa2bd43b592e
inputFile.enc.dec:
00000000000000000000000000000000