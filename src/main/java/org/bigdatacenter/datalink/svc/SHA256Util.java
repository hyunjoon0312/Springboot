package org.bigdatacenter.datalink.svc;

import java.security.MessageDigest;

public class SHA256Util {

	
	private String secretKey;
	
	public SHA256Util(String secretKey){
		this.secretKey = secretKey;
	}
	
	
	
	public String SHA256Encode(String base) {

		byte[] salt = new byte[secretKey.length()];
		salt = secretKey.getBytes();

		try {

			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();

			digest.update(salt);
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
	
				for (int i = 0; i < hash.length; i++) {
					String hex = Integer.toHexString(0xff & hash[i]);
					if (hex.length() == 1)
						hexString.append('0');
					hexString.append(hex);
				}

			
			return hexString.toString();
			
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}// end

}
