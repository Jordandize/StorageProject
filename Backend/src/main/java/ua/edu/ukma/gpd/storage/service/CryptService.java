package ua.edu.ukma.gpd.storage.service;

public interface CryptService {
	
	public String encrypt(String value);
	
	public String decrypt(String crypt);

}
