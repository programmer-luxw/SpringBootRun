package cn.luxw.app.util;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

import com.google.common.base.Charsets;
import com.google.common.collect.Maps;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

public class Tests {
	static String pub = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwHNHqN7U47EaeTdb0KEC46apLDb+aF89xuHFu1Avf+Ju0QstSoTa0TLuOdTImufw4a76CrEWE+AfS83+5ZAiKeRzGi/3oaFNu/5osq9ogisnrIl17BW+tvD6LcbyjVB+6/cfgA0hL5wcsZTKvHuzThUBkBoMkxVmxYXBGhvDmMh2wjwG/OOtnn+rZ6yQU3uFDxPapRmyV/8MNf+gB3V3ZsRkwHCv2/eo53X0wmaPADirpPjLphMbc+/FuoL4uROz6QcGJZQYRsf5Nz8or1GfWYVza+YB6ZyO8JcpxyfPNermieMUSoHmww9FGArz3MoPB60qRBkhdfC4T2V9W76GTwIDAQAB";
	static String token = "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJKVUxZIiwiaWQiOiJhYmMiLCJhZ2UiOiJlZmQifQ.m_JUzfKStVuhud7A-Nxopzmf0CvvaU9bHV0mxsDfOSXGUnT022ruDWiP4WGNvS6z1V65mhiff1KLpHTE13j9HCB1CRkt_6Gk9dG-x3G_ynrk6B_HYFXUzcKcl4DGwr70pvQwzPSIJV8bRbdP1Xz_0VCngVwoL46Qa_vqaqoFfApKthRMHQHgy3Ms5bsQZlSExhDiLIq7r_9OJhUp1wdN4ANUehhvE8ISlzRGywTsDmYMrCswvcTwctjIvjxEPlqZu6mgwVCD5VUuolO-xNlJHAew9yivQnrO7X482jB_VsRlz0av-VKIdHAUcq-yI3_u3BQwiDuOVeN_C2pPYg2sIQ";
	static final String HS256_ISSUER = "JULY";
	public static void main(String[] args) throws Exception{
	/*	KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
		String pub = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
		String pri = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
		
		FileUtils.writeStringToFile(pub,"C:/data/pub.json");
		FileUtils.writeStringToFile(pri,"C:/data/pri.json");*/
		
		
		/* String jws = Jwts.builder()
				    .setSubject(HS256_ISSUER)
				    .claim("id", "abc")
				    .claim("age", "efd")
				    .signWith(keyPair.getPrivate())
				    .compact(); 
		 
		 System.out.println(jws);*/
		 
		Jwts.parser()
		   .requireSubject(HS256_ISSUER)
		  .setSigningKey(getPublicKey()) // <---- publicKey, not privateKey
		  .parseClaimsJws(token);
		
		System.err.println("===========");
		
	}
	
	 public static RSAPublicKey getPublicKey() throws Exception {
	        byte[] keyBytes = Base64.getDecoder().decode(pub);
	        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
	        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        RSAPublicKey publicKey = (RSAPublicKey)keyFactory.generatePublic(keySpec);
	        return publicKey;
	  }

}
