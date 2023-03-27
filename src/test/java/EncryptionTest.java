import org.hamcrest.Matcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import sample.TestObject;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.security.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class EncryptionTest {
    Encryptor encryptor = new Encryptor();

    @Test
    void givenObject_whenEncrypt_thenSuccess()
            throws NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, ClassNotFoundException {
        TestObject object = new TestObject("Test", 55);

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();

        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec spec = new IvParameterSpec(iv);

        String alg = "AES/CBC/PKCS5Padding";
        SealedObject sealedObject = encryptor.encryptData(alg, object, key, spec);

        TestObject newObject = (TestObject) encryptor.decryptData(alg, sealedObject, key, spec);

        assertThat(object, sameInstance(newObject));
    }
}
