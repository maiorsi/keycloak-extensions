package maiorsi.keycloak.util;

/**
 * Pulled (copied) from
 * https://github.com/spring-projects/spring-ldap/blob/main/core/src/main/java/org/springframework/ldap/support/LdapUtils.java.
 */
public final class GuidUtils {
    private GuidUtils() {
    }

    public static String convertBinaryGuidToString(byte[] objectGuid) {
        return prefixZeros((int) objectGuid[3] & 0xFF)
                + prefixZeros((int) objectGuid[2] & 0xFF)
                + prefixZeros((int) objectGuid[1] & 0xFF)
                + prefixZeros((int) objectGuid[0] & 0xFF)
                + "-"
                + prefixZeros((int) objectGuid[5] & 0xFF)
                + prefixZeros((int) objectGuid[4] & 0xFF)
                + "-"
                + prefixZeros((int) objectGuid[7] & 0xFF)
                + prefixZeros((int) objectGuid[6] & 0xFF)
                + "-"
                + prefixZeros((int) objectGuid[8] & 0xFF)
                + prefixZeros((int) objectGuid[9] & 0xFF)
                + "-"
                + prefixZeros((int) objectGuid[10] & 0xFF)
                + prefixZeros((int) objectGuid[11] & 0xFF)
                + prefixZeros((int) objectGuid[12] & 0xFF)
                + prefixZeros((int) objectGuid[13] & 0xFF)
                + prefixZeros((int) objectGuid[14] & 0xFF)
                + prefixZeros((int) objectGuid[15] & 0xFF);
    }

    private static String prefixZeros(int value) {
        if (value <= 0xF) {
            return "0" + Integer.toHexString(value);
        }

        return Integer.toHexString(value);
    }
}
