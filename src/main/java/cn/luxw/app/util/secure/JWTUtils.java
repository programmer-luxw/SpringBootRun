package cn.luxw.app.util.secure;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import cn.luxw.app.util.FileUtils;

public class JWTUtils {
	static final String HS256_SECRET = "secret";
	static final String HS256_ISSUER = "JULY";
	
	public static String HS256Encoded(Integer id,String name) {
		try {
			Date expiresAt = new Date(System.currentTimeMillis() + 60 * 1000L);
		    String token = JWT.create()
		        .withIssuer(HS256_ISSUER)
		        .withClaim("id", id)
                .withClaim("name", name)
                //.withExpiresAt(expiresAt)
		        .sign(Algorithm.HMAC256(HS256_SECRET));
		    return token;
		} catch (JWTCreationException e){
			e.printStackTrace();
		}
		return null;
	}

	public static DecodedJWT HS256Verify(String token) {
		try {
		    JWTVerifier verifier = JWT.require(Algorithm.HMAC256("xxx"))
		        .withIssuer(HS256_ISSUER)
		        //.acceptExpiresAt(Long.MAX_VALUE)
		        .build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token);
		    return jwt;
		} catch (JWTVerificationException e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String RS256Encoded(Integer id,String name) throws Exception {
				RSAPrivateKey privateKey = getPrivateKey();
				try {
				    Algorithm algorithm = Algorithm.RSA256(null, privateKey);
				    String token = JWT.create()
				        .withIssuer(HS256_ISSUER)
				        .withClaim("id", id)
		                .withClaim("name", name)
				        .sign(algorithm);
				    return token;
				} catch (JWTCreationException e){
					e.printStackTrace();
				}
				return null;
	}
	
	public static DecodedJWT RS256Verify(String token) throws Exception {
		RSAPublicKey publicKey = getPublicKey(); //Get the key instance
				RSAPrivateKey privateKey = null;//getPrivateKey();//Get the key instance
				try {
				    Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
				    JWTVerifier verifier = JWT.require(algorithm)
				        .withIssuer(HS256_ISSUER)
				        .build(); //Reusable verifier instance
				    DecodedJWT jwt = verifier.verify(token);
				    return jwt;
				} catch (JWTVerificationException e){
					System.err.println("000000000000");
				   e.printStackTrace();
				}
				return null;
	}
	
	static String pub = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwHNHqN7U47EaeTdb0KEC46apLDb+aF89xuHFu1Avf+Ju0QstSoTa0TLuOdTImufw4a76CrEWE+AfS83+5ZAiKeRzGi/3oaFNu/5osq9ogisnrIl17BW+tvD6LcbyjVB+6/cfgA0hL5wcsZTKvHuzThUBkBoMkxVmxYXBGhvDmMh2wjwG/OOtnn+rZ6yQU3uFDxPapRmyV/8MNf+gB3V3ZsRkwHCv2/eo53X0wmaPADirpPjLphMbc+/FuoL4uROz6QcGJZQYRsf5Nz8or1GfWYVza+YB6ZyO8JcpxyfPNermieMUSoHmww9FGArz3MoPB60qRBkhdfC4T2V9W76GTwIDAQAB";
	static String pri = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCkTRCVA5VtmGZ24yxRuLC9YRERHzxUGAY5j/XOS/RbgDsnO8EMycbfhnzYEO95Y7dMlV+wmDlFl0re1nmgo2y8GrfA5NrAZajAkFZLf5yN/FqTCN/pKlhZ81wbdN7dg6XGJnXZ8txzQHyrVy7lZ8rhBHLB0KX4Z7JDUPRn2YKDTgUj9roxjb3ePIJ23fyV9tQ8R2WegM7rB6wuXfg2qLD4srCGeSnU82TMnqMViqU4l6bZs9HnqikFZ2MhA6gxufBLGYyMe8MRjMFPUbuHN4kJUoMjJjaQXCKFODowBd/e2GirfCm15zUpnFO5vjKRKdrvV3mfxnnZmR4a/bzxebJTAgMBAAECggEASB+wVMmy8d5BtJS64tDhfPC8TjjZAc1eBizE6Z98ECK1VN6tOxDkZUEjUrH0feQhFdRIojvggEUOz810rOiwlc5gtaOAx46YypdR2miVb9rEY+E0caVuDWaAG2ltX+4nelltyJQM4+b+EzT9WCT88VRDkAMoqGfU4CoMb/4ShdKnDMBMuXgkunP1EGe8hSUwIFddb9KExaLTxh3D9nDY/j2PBqlwYyhio0IWMoL2cPoD6llTNtRbJCsQjs923vxR3N0CA6Lz8zqhBBRRYxTaMCX7x+l/Z5n78We3xysGewlJGl4G5rcfrkhiP+978aRkd/vBm5cUgtXJ5OC8mZ62gQKBgQDs8FMQOdTQqNJ93VVu0uuNKSH8OtN6Wa0+0WXK0XSxgxKbztuv+tetgKhIJoMVt0YFf6u613fALL/GYdqv3KLTaeYW40vUvvKZHbosaxS6VvEFOLPI7qHKE1Tz5dOh8qKzOixfNCBDUx6BpjEsCNra0MUeH0Jdc3BL1VZFAMuikwKBgQCxhMpL/jXlGMpiPnWZxp+zn1rbm8Fvgs7MNmGB/cBQX1j4jTf9ED+hzRu0+NNIUWWE8c09UP/W/uqTYLPeenQILiNoagtDmFWVTy9zowRNEqSWQQkV2zw5AMzZSyQooxNMEe/PTjXVd0b+DwPZCtia+eGepZJyCeXBi8J0DGDJQQKBgGssWaYhQo4Pjwa3swkqBroXPPQmd7oEDkU32l9fVvVkYGpxqxVy1VP5uiTIpsqP1/Bsy3T8OYKHu0c0xAKAPnZ6lEUOEyLsLDfuJmQpG3seEG9YMppUtoH9NJQOBShf5BBF5AQ8Q8uwMUBbuCo+5x/LBx6U7SPwL39dxGP59CJZAoGABRTcBZjdShWPlYhcLW62G5owK6ElxF9mNApKeEIfQy23ikXgmGniqGsKo/+LbMKHuWqCiYLMEAd0B4v5GiE/OQy5DsAaWnD94ixGG3VLJjmmZHawH+QKsUcIlh/EhTVF1COfTbvbZqTnb1ZWsdvRn34Pq07qBkGqRgQOKOxByQECgYB8oqkAiHDLZchiWuiOszO5ys5rREQPvEY+cWV6IBuO3PaG7NelkPJHILhTr264U1UmV66n4dicxD4kCqg/stANosWcUQaqQrzBtpLDhGu/3VLVZzJD8fWm5PY3nt+aVLZOkWlrLIfRkygCMfUBOFo/KPM4xZnpBZ4ZQXQWc3m8MQ==";
	static String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJKVUxZIiwiaWQiOiJhYmMiLCJhZ2UiOiJlZmQifQ.m_JUzfKStVuhud7A-Nxopzmf0CvvaU9bHV0mxsDfOSXGUnT022ruDWiP4WGNvS6z1V65mhiff1KLpHTE13j9HCB1CRkt_6Gk9dG-x3G_ynrk6B_HYFXUzcKcl4DGwr70pvQwzPSIJV8bRbdP1Xz_0VCngVwoL46Qa_vqaqoFfApKthRMHQHgy3Ms5bsQZlSExhDiLIq7r_9OJhUp1wdN4ANUehhvE8ISlzRGywTsDmYMrCswvcTwctjIvjxEPlqZu6mgwVCD5VUuolO-xNlJHAew9yivQnrO7X482jB_VsRlz0av-VKIdHAUcq-yI3_u3BQwiDuOVeN_C2pPYg2sIQ";
	public static void main(String[] args) throws Exception {
		//String str1 = HS256Encoded(1, "test");
		//System.out.println(str1);
		
//		HS256Verify(c);
//		getKeys();
//		FileUtils.writeStringToFile("C:/data/b.txt", "xxx");
//		getPublicKey();
		//String r = RS256Encoded(100, "xyz");
		//System.out.println(r);
		DecodedJWT c = RS256Verify(token);
		if(c!=null) {
			String rr = c.getIssuer();
			System.out.println(rr);
		}
		//getKeys();
		
	}
	
	
	/** 
     * 生成公钥和私钥 
     * @throws NoSuchAlgorithmException  
     * 
     */  
	 public static void getKeys() throws NoSuchAlgorithmException{  
        HashMap<String, Object> map = new HashMap<String, Object>();  
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");  
        keyPairGen.initialize(2048);  
        KeyPair keyPair = keyPairGen.generateKeyPair();  
        //RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        PublicKey publicKey =  keyPair.getPublic();
        String pubStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        
        //RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();  
        PrivateKey privateKey =  keyPair.getPrivate();  
        String priStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        map.put("public", pubStr);  
        map.put("private", priStr);  
        //System.err.println(pubStr);
        //System.err.println("###===================###");
       // System.err.println(priStr);
        FileUtils.writeStringToFile(map.toString(),"C:/data/a.json");
//        return map;  
    } 
    public static RSAPublicKey getPublicKey() throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(pub);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKey publicKey = (RSAPublicKey)keyFactory.generatePublic(keySpec);
        return publicKey;
  }
    
    
    public static RSAPrivateKey getPrivateKey() throws Exception {
    	//String key  = FileUtils.readFileToString("C:/Intel/private.pem", false);
        byte[] keyBytes = Base64.getDecoder().decode(pri);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        return privateKey;
  }
	
	
	
	
}
