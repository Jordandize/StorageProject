package ua.edu.ukma.gpd.storage.service.impl;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import ua.edu.ukma.gpd.storage.service.CryptService;


/*
 * AES implementation
 */

@Service
public class CryptServiceImpl implements CryptService {
	
    private static final String KEY = "aKDCgUoSR4uandcf";
    
    private static final String VECTOR = "v3s0WN5Z3AdokwfN";

	@Override
	public String encrypt(String value) {
		try {
    		IvParameterSpec ips = new IvParameterSpec(VECTOR.getBytes("UTF-8"));
    		SecretKeySpec sks = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
    		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    		cipher.init(Cipher.ENCRYPT_MODE, sks, ips);
    		return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
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
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
	}

}
