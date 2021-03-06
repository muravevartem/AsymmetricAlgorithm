package com.semura.asymmetric.rsa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

public class RSAEncryptCipher {
	
	private Cipher cipher;
	
	public RSAEncryptCipher(PublicKey publicKey) {
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void encrypt(InputStream inputStream, OutputStream outputStream) throws IOException {
		CipherInputStream cipherInputStream = new CipherInputStream(inputStream, cipher);
		cipherInputStream.transferTo(outputStream);
	}
	
	public byte[] encrypt(byte[] bytes) {
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {
			encrypt(inputStream, outputStream);
			return outputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new byte[0];
	}
	
}
