import javax.crypto.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        // Khởi tạo cặp khóa RSA
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        keyPairGenerator.initialize(512);
//        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//
//        PublicKey publicKey = keyPair.getPublic();
//        PrivateKey privateKey = keyPair.getPrivate();
//        byte[] encodedPublicKey = publicKey.getEncoded();
//        String base64PublicKey = Base64.getEncoder().encodeToString(encodedPublicKey);
//        byte[] encodedPrivateKey = privateKey.getEncoded();
//        String base64PrivateKey = Base64.getEncoder().encodeToString(encodedPrivateKey);
//        System.out.println("b64PublicKey: "+b64PublicKey);
//        System.out.println("\n\n\n");
//        System.out.println("b64PrivateKey: "+b64PrivateKey);
        String base64PublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJaCtDh1OUVWYKnp30lF76C4+rV70m1DcYNXQTT4EPQ+8ZjYCiloGSwa83x0gXnPM1fKLjoLaZ1OP3VXnJuM3XMCAwEAAQ==";
        String base64PrivateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAloK0OHU5RVZgqenfSUXvoLj6tXvSbUNxg1dBNPgQ9D7xmNgKKWgZLBrzfHSBec8zV8ouOgtpnU4/dVecm4zdcwIDAQABAkBQEoUyrBql6shOXR/vaYeMqBypRJC0lHblexwzw+2xIqCx09W5WH4wjFVEqqkeC4xaHrSnhYJySwePhHyh/bwRAiEA4QbTolbPS0VwPFLByA/JPhyyBrYKCPg6d6DDdUSTI/0CIQCrOi7+p/rjL7SGuZ/Tl4qPBvDd6e5rcIn3AjQ7Q3TqLwIhAKOk/0tTrjSjLtfvpxUCxerxm1XeFLYpLvvIYdxqHkdRAiBxcqV8eJPUPuFfelGZAgj95t1kSwB7Ex92F3G/6lvmZQIgcaTU/l6zTn3N0Z+5A0YS3gfCZiGPMBWAS4ywFAX+Xvc=";

        String text = "Duc da ma hoa rsa";
        PublicKey publicKeyRecover = (PublicKey) base64ToKey(base64PublicKey,"PublicKey");
        PrivateKey privateKeyRecover = (PrivateKey) base64ToKey(base64PrivateKey,"PrivateKey");
        byte[] encryted = encryte(text,publicKeyRecover);
        byte[] decryted = decryte(encryted,privateKeyRecover);

        System.out.println("thong tin duoc ma hoa: " + new String(encryted));
        System.out.println();
        System.out.println();
        System.out.println(new String(decryted));
//        // Dữ liệu cần được mã hóa
//        String plainText = "Hello, RSA!";
//
//        // Mã hóa dữ liệu bằng khóa công khai
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
//
//        // Giải mã dữ liệu bằng khóa riêng
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
//
//        // In kết quả
//        System.out.println("Dữ liệu gốc: " + plainText);
//        System.out.println("Dữ liệu đã mã hóa: " + new String(encryptedBytes));
//        System.out.println("Dữ liệu đã giải mã: " + new String(decryptedBytes));
    }

    public static byte[] encryte(String  plainText,PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plainText.getBytes());
    }

    public static byte[] decryte(byte[] encryptedBytes, PrivateKey privateKey) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encryptedBytes);
    }
    public static Key base64ToKey(String base64Key,String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if("PrivateKey".equals(key)){
            try{
                return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64Key)));
            }catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }
        }else if("PublicKey".equals(key)){
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(base64Key)));
        }else{
            return null;
        }

    }
}
