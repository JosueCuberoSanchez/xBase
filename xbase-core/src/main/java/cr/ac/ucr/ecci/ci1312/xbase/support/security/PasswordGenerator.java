package cr.ac.ucr.ecci.ci1312.xbase.support.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Support class that generates random password of <tt>PASSWORD_LENGTH</tt>
 * characters.
 *
 * @author Rodrigo A. Bartels
 */
public class PasswordGenerator {

    /**
     * String used to generate the random alphabet characters.
     */
    private static final char[] NEW_PASSWORD_CHAR_OPTIONS = {'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e',
            'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
            's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', '0', '!'};

    /**
     * Length of the generated password.
     */
    public static final int PASSWORD_LENGTH = 9;

    /**
     * Generates a random password from a set of defined chars.
     *
     * @return a random password.
     */
    public static String generatePassword() {
        Random randomGen = new Random();
        StringBuilder passCode = new StringBuilder();
        for (int index = 0; index < PASSWORD_LENGTH; index++) {
            int randNum = randomGen.nextInt(NEW_PASSWORD_CHAR_OPTIONS.length);
            passCode.append(NEW_PASSWORD_CHAR_OPTIONS[randNum]);
        }
        return passCode.toString();
    }

    public static String hashString(String message, String algorithm){

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

            return convertByteArrayToHexString(hashedBytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            return null;
        }
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuilder stringBuffer = new StringBuilder();
        for (byte arrayByte : arrayBytes) {
            stringBuffer.append(Integer.toString((arrayByte & 0xff) + 0x100, 16)
                    .substring(1));
        }
        return stringBuffer.toString();
    }

}
