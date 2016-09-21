package com.shiro.util;

import java.security.Key;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.H64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.StringUtils;

import com.shiro.pojo.User;

/**
 * ���ܹ�����
 * @author �⸣��
 *
 */
public class EndecryptUtils { 
	
    /** 
     * base64���Ƽ��� 
     * 
     * @param password 
     * @return 
     */ 
    public static String encrytBase64(String password) { 
    	 byte[] bytes = password.getBytes(); 
         return Base64.encodeToString(bytes); 
    } 
    
    /** 
     * base64���ƽ��� 
     * @param cipherText 
     * @return 
     */ 
    public static String decryptBase64(String cipherText) { 
    	return Base64.decodeToString(cipherText);
    } 
    
    /** 
     * 16���Ƽ��� 
     * 
     * @param password 
     * @return 
     */ 
    public static String encrytHex(String password) { 
		byte[] bytes = password.getBytes(); 
        return Hex.encodeToString(bytes); 
    } 
    
    /** 
     * 16���ƽ��� 
     * @param cipherText 
     * @return 
     */ 
    public static String decryptHex(String cipherText) { 
    	return new String(Hex.decode(cipherText)); 
    } 
    
    
    public static String generateKey(){ 
        AesCipherService aesCipherService=new AesCipherService(); 
        Key key=aesCipherService.generateNewKey(); 
        return Base64.encodeToString(key.getEncoded()); 
    } 
    
    /** 
     * ���������md5����,���������ĺ�salt��������User������ 
     * @param username �û��� 
     * @param password ���� 
     * @return ���ĺ�salt 
     */ 
    public static User md5Password(String username,String password){ 
        SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator(); 
        String salt= secureRandomNumberGenerator.nextBytes().toHex(); 
        //���username,���ε�������������м��� 
        String password_cipherText= new Md5Hash(password,username+salt,2).toBase64(); 
        User user=new User(); 
        user.setPassword(password_cipherText); 
        user.setSalt(salt); 
        user.setUsername(username); 
        return user;
    } 
    
    /** 
     * ���������md5����,����������
     * @param username �û��� 
     * @param password ���� 
     * @param salt  ��
     * @return ���ĺ�salt 
     */ 
    public static String md5Password(String username,String password,String salt){
    	return new Md5Hash(password,username+salt,2).toBase64();
    }
    
    /** 
     * ͨ��username,password,salt,У�������Ƿ�ƥ�� ��У�������ʵ�������ļ��У�����Ϊ��������д���� 
     * @param username �û��� 
     * @param password ԭ���� 
     * @param salt  �� 
     * @param md5cipherText ���� 
     * @return 
     */ 
    public static boolean checkMd5Password(String username,String password,String salt,String md5cipherText) {
    	if(StringUtils.hasText(password)&&StringUtils.hasText(username)&&StringUtils.hasText(md5cipherText)){
    		 //���username,���ε�������������м��� 
            String password_cipherText= new Md5Hash(password,username+salt,2).toHex(); 
            return md5cipherText.equals(password_cipherText); 
    	}
    	return false;
    }
    
    public static void main(String[] args) { 
        String password = "niwg"; 
        String username = "niwg";
        String cipherText = encrytHex(password); 
        System.out.println(password + "hex����֮��������ǣ�" + cipherText); 
        String decrptPassword=decryptHex(cipherText); 
        System.out.println(cipherText + "hex����֮��������ǣ�" + decrptPassword); 
        String cipherText_base64 = encrytBase64(password); 
        System.out.println(password + "base64����֮��������ǣ�" + cipherText_base64); 
        String decrptPassword_base64=decryptBase64(cipherText_base64); 
        System.out.println(cipherText_base64 + "base64����֮��������ǣ�" + decrptPassword_base64); 
        String h64=  H64.encodeToString(password.getBytes()); 
        System.out.println(h64); 
        String salt="7road"; 
        String cipherText_md5= new Md5Hash(password,username+salt,2).toHex(); 
        System.out.println(password+"ͨ��md5����֮��������ǣ�"+cipherText_md5); 
        System.out.println("checkMd5Password:"+checkMd5Password(username, password, salt, cipherText_md5));
        System.out.println(generateKey()); 
        System.out.println("=========================================================="); 
        AesCipherService aesCipherService=new AesCipherService(); 
        aesCipherService.setKeySize(128); 
        Key key=aesCipherService.generateNewKey(); 
        String aes_cipherText= aesCipherService.encrypt(password.getBytes(),key.getEncoded()).toHex(); 
        System.out.println(password+" aes���ܵ������ǣ�"+aes_cipherText); 
        String aes_mingwen=new String(aesCipherService.decrypt(Hex.decode(aes_cipherText),key.getEncoded()).getBytes()); 
        System.out.println(aes_cipherText+" aes���ܵ������ǣ�"+aes_mingwen); 
    } 
}