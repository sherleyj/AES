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
We finished all of this assignment.  

[Test Cases]
