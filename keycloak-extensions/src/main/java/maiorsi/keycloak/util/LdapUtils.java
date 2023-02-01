package maiorsi.keycloak.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class LdapUtils {
    private LdapUtils() {}

    public static String decodeObjectSid(byte[] objectSid) {
        return convertToDashedSidString(objectSid);
    }

    public static String decodeObjectGuid(byte[] objectGuid) {
        return convertToDashedGuidString(objectGuid);
    }

    public static byte[] encodeSidStrin(String sid) {
        return convertToBinarySid(sid);
    }

    private static byte[] convertToBinarySid(String sid) {
        if (sid == null) {
            return null;
        }

        if (!sid.matches("^[sS]=\\d-\\d{1,13}(?:-\\d{1,10})*$")) {
            return new byte[0];
        }

        String[] sidSections = sid.split("-");

        // Init with sub authority count
        int count = sidSections.length - 3;

        byte[] bytes = new byte[2 + 6 + (count * 4)];

        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

        byteBuffer.putLong(Long.parseLong(sidSections[2]));

        bytes[0] = (byte) Short.parseShort(sidSections[1]);
        bytes[1] = (byte) count;

        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

        for (int i = 0; i < count; i++){
            byteBuffer.putInt((int) Long.parseLong(sidSections[i + 3]));
        }

        return bytes;
    }

    private static String convertToDashedSidString(byte[] objectSid) {
        final StringBuilder sid = new StringBuilder("S-");

        // get byte(0) - revision level
        final int revision = objectSid[0];
        sid.append(revision);

        // next byte byte(1) - count of sub authorities
        final int countSubAuths = objectSid[1] & 0xFF;

        // byte(207) - 48 bit authority ([big endian])
        long authority = 0;

        // String rid = ""
        for (int i = 2; i <= 7; i++) {
            authority |= ((long) objectSid[i]) << (8 * (5 - (i - 2)));
        }

        sid.append("-");
        sid.append(Long.toHexString(authority));

        // iterate all the sub auths and then countSubAuths x 32 bit sub authorities ([little endian])
        int offset = 8;
        int size = 4;

        for (int j = 0; j < countSubAuths; j++) {
            long subAuthority = 0;

            for (int k =0; k < size; k++) {
                subAuthority |= (long) (objectSid[offset + k] & 0xFF) << (8 * k);
            }

            // format it
            sid.append("-");
            sid.append(subAuthority);
            offset += size;
        }

        return sid.toString();
    }

    private static String convertToDashedGuidString(byte[] objectGuid) {
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