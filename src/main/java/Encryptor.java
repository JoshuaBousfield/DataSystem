import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

class Encryptor {

    public Encryptor() {
    }

    public SealedObject encryptData(String algorithm, Serializable object, SecretKey key, IvParameterSpec spec)
        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IOException, IllegalBlockSizeException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        SealedObject sealedObject = new SealedObject(object, cipher);
        return sealedObject;
    }

    public Serializable decryptData(String algorithm, SealedObject object, SecretKey key, IvParameterSpec spec)
        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            ClassNotFoundException, BadPaddingException, IllegalBlockSizeException, IOException {

        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        Serializable unsealedObject = (Serializable) object.getObject(cipher);
        return unsealedObject;
    }
}
