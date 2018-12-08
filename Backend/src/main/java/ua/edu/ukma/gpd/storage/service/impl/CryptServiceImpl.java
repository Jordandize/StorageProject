package ua.edu.ukma.gpd.storage.service.impl;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ua.edu.ukma.gpd.storage.service.CryptService;


/*
 * AES implementation
 */

@Service
public class CryptServiceImpl implements CryptService {
	
	@Value("${crypt.key}")
    private String KEY;
	
	@Value("${crypt.vector}")
    private String VECTOR;

	@Override
	public String encrypt(String value) {
		try {
    		IvParameterSpec ips = new IvParameterSpec(VECTOR.getBytes("UTF-8"));
    		SecretKeySpec sks = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
    		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    		cipher.init(Cipher.ENCRYPT_MODE, sks, ips);
    		return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
    	} catch (Exception ignore) { }
    	return null;
	}

	@Override
	public String decrypt(String crypt) {
		try {
    		IvParameterSpec ips = new IvParameterSpec(VECTOR.getBytes("UTF-8"));
    		SecretKeySpec sks = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
    		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    		cipher.init(Cipher.DECRYPT_MODE, sks, ips);
    		return new String(cipher.doFinal(Base64.getDecoder().decode(crypt)));
    	} catch (Exception ignore) {	}
    	return null;
	}

}
