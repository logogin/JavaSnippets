package logogin.util;

import java.security.Key;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import com.google.common.base.Charsets;

/**
 * CryptoUtil.java
 *
 * @created Apr 25, 2013
 * @author logogin
 */
public class CryptoUtil {

    public static String hmacSha1(String text, String key) {
        try {
            byte[] keyBytes = key.getBytes(Charsets.UTF_8);
            byte[] textBytes = text.getBytes(Charsets.UTF_8);

            // Get an hmac_sha1 key from the raw key bytes
            Key signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(textBytes);
            // Convert raw bytes to Hex
            return Hex.encodeHexString(rawHmac);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
