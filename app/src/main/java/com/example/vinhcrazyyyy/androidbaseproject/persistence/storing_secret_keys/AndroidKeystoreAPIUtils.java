package com.example.vinhcrazyyyy.androidbaseproject.persistence.storing_secret_keys;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION_CODES;
import android.security.KeyPairGeneratorSpec;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.x500.X500Principal;

/*Android M and Higher*/
public class AndroidKeystoreAPIUtils {

    private static final String SHARED_PREFERENCE_NAME = "shared_preference_name";
    Context context;

    public AndroidKeystoreAPIUtils(Context context) {
        this.context = context;
    }

    private static final String AndroidKeyStore = "AndroidKeyStore";
    private static final String AES_MODE = "AES/GCM/NoPadding";
    private static final String KEY_ALIAS = "KeyAlias";
    private static final String XEALTH_KEY_ALIAS = "XealthKeyAlias";
    private static final String FIXED_IV = "FixedIV";

    KeyStore keyStore;

    @RequiresApi(api = VERSION_CODES.M)
    private SecretKey generateKey() {
        try {
            keyStore = KeyStore.getInstance(AndroidKeyStore);
            keyStore.load(null);

            if (!keyStore.containsAlias(KEY_ALIAS)) {
                KeyGenerator keyGenerator = KeyGenerator.getInstance(
                        KeyProperties.KEY_ALGORITHM_AES, AndroidKeyStore
                );
                keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_ALIAS,
                        KeyProperties.PURPOSE_ENCRYPT |KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setRandomizedEncryptionRequired(false)
                .build());
                SecretKey secretKey = keyGenerator.generateKey();
                return secretKey;
            }
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Key getSecretKey() {
        try {
            return keyStore.getKey(XEALTH_KEY_ALIAS, null);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequiresApi(api = VERSION_CODES.KITKAT)
    private String encryptingTheData() {
        Cipher c = null;
        try {
            c = Cipher.getInstance(AES_MODE);
            c.init(Cipher.ENCRYPT_MODE, getSecretKey(context),
                    new GCMParameterSpec(128, FIXED_IV.getBytes()));
            byte[] encodedBytes = c.doFinal(input);
            String encryptedBase64Encoded = Base64.encodeToString(encodedBytes, Base64.DEFAULT);
            return encryptedBase64Encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequiresApi(api = VERSION_CODES.KITKAT)
    private byte[] decryptingTheData() {
        try {
            Cipher c = Cipher.getInstance(AES_MODE);
            c.init(Cipher.DECRYPT_MODE, getSecretKey(context),
                    new GCMParameterSpec(128, FIXED_IV.getBytes()));
            byte[] decodedBytes = c.doFinal(encrypted);
            return decodedBytes;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*Pre Android */
    @RequiresApi(api = VERSION_CODES.JELLY_BEAN_MR2)
    private KeyPair generateRSAKeyPairs() {
        try {
            keyStore = KeyStore.getInstance(AndroidKeyStore);
            keyStore.load(null);

            //Generate the RSA key pairs
            if (!keyStore.containsAlias(KEY_ALIAS)) {
                //Generate a key pair for encryption
                Calendar start = Calendar.getInstance();
                Calendar end = Calendar.getInstance();
                end.add(Calendar.YEAR, 30);

                KeyPairGeneratorSpec spec = new KeyPairGeneratorSpec.Builder(context)
                        .setAlias(KEY_ALIAS)
                        .setSubject(new X500Principal("CN=" + KEY_ALIAS))
                        .setSerialNumber(BigInteger.TEN)
                        .setStartDate(start.getTime())
                        .setEndDate(end.getTime())
                        .build();

                KeyPairGenerator kpg = KeyPairGenerator
                        .getInstance(KeyProperties.KEY_ALGORITHM_RSA, AndroidKeyStore);
                kpg.initialize(spec);
                KeyPair keyPair = kpg.generateKeyPair();
                return keyPair;
            }
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        return null;
    }

    /*RSA Encryption and Decryption Routines*/
    private static final String RSA_MODE = "RSA/ECB/PKCS1Padding";

    private byte[] rsaEncrypt(byte[] secret) throws Exception {
        KeyStore.PrivateKeyEntry privateKeyEntry
                 = (KeyStore.PrivateKeyEntry) keyStore.getEntry(KEY_ALIAS, null);
        //Encrypt the text
        Cipher inputCipher = Cipher.getInstance(RSA_MODE, "AndroidOpenSSL");
        inputCipher.init(Cipher.ENCRYPT_MODE,
                privateKeyEntry.getCertificate().getPublicKey());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CipherOutputStream cipherOutputStream =
                new CipherOutputStream(outputStream, inputCipher);
        cipherOutputStream.write(secret);
        cipherOutputStream.close();

        byte[] vals = outputStream.toByteArray();
        return vals;
    }

    private byte[] rsaDecrypt(byte[] encrypted) throws Exception {
        KeyStore.PrivateKeyEntry privateKeyEntry =
                (PrivateKeyEntry) keyStore.getEntry(KEY_ALIAS, null);
        Cipher output = Cipher.getInstance(RSA_MODE, "AndroidOpenSSL");
        output.init(Cipher.DECRYPT_MODE, privateKeyEntry.getPrivateKey());
        CipherInputStream cipherInputStream = new CipherInputStream(
                new ByteArrayInputStream(encrypted),
                output
        );
        ArrayList<Byte> values = new ArrayList<>();
        int nextByte;
        while ((nextByte = cipherInputStream.read()) != -1) {
            values.add((byte) nextByte);
        }

        byte[] bytes = new byte[values.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = values.get(i).byteValue();
        }
        return bytes;
    }

    private void generateAndStoreAESKey() {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        String encryptedKeyB64 = pref.getString(ENCRYPTED_KEY, null);
        if (encryptedKeyB64 == null) {
            byte[] key = new byte[16];
            SecureRandom secureRandom = new SecureRandom();
            secureRandom.nextBytes(key);

            try {
                byte[] encryptedKey = rsaEncrypt(key);
                encryptedKeyB64 = Base64.encodeToString(encryptedKey, Base64.DEFAULT);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(ENCRYPTED_KEY, encryptedKeyB64);
                editor.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //And finally, our calls to encrypt and decrypt the data
    private static final String AES_MODE = "AES/ECB/PKCS7Padding";

    private Key getSecretKey(Context context) throws Exception {
        SharedPreferences pref = context.getSharedPreferences(SHARED_PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        String encryptedKeyB64 = pref.getString(ENCRYPTED_KEY, null);

        //need to check null, omitted here
        byte[] encryptedKey = Base64.decode(encryptedKeyB64, Base64.DEFAULT);
        byte[] key = rsaDecrypt(encryptedKey);

        return new SecretKeySpec(key, "AES");
    }

    private String encrypt(Context context, byte[] input) {
        try {
            Cipher c = Cipher.getInstance(AES_MODE, "BC");
            c.init(Cipher.ENCRYPT_MODE, getSecretKey(context));
            byte[] encodedBytes = c.doFinal(input);
            String encryptedBase64Encoded = Base64.encodeToString(encodedBytes, Base64.DEFAULT);
            return encryptedBase64Encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] decrypt(Context context, byte[] encrypted) {
        try {
            Cipher c = Cipher.getInstance(AES_MODE, "BC");
            c.init(Cipher.DECRYPT_MODE, getSecretKey(context));
            byte[] decodedBytes = c.doFinal(encrypted);
            return decodedBytes;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /*The simplest approach for storing secrets in to keep them as resource files
    that are simply not checked into source control. */
    /*Since your secrets are now in an individual file,
    they're simple to ignore in your source control system (for example, in Git,
    you would add this to the '.gitignore' file in your repository):
     */
    //echo "**/*/res/values/secrets.xml" > .gitignore*/
}
