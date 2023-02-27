package maiorsi.keycloak.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public final class LdapUtils {
    private LdapUtils() {
    }

    public static String decodeObjectSid(byte[] objectSid) {
        return convertToDashedSidString(objectSid);
    }

    public static String decodeObjectGuid(byte[] objectGuid) {
        return convertToDashedGuidString(objectGuid);
    }

    public static byte[] encodeSidString(String sid) {
        return convertToBinarySid(sid);
    }

    /**
	 * Converts a binary SID to its String representation, according to the
	 * algorithm described <a
	 * href="https://blogs.msdn.com/oldnewthing/archive/2004/03/15/89753.aspx"
	 * >here</a>. Thanks to <a href=
	 * "https://www.jroller.com/eyallupu/entry/java_jndi_how_to_convert">Eyal
	 * Lupu</a> for algorithmic inspiration.
	 * 
	 * <pre>
	 * If you have a SID like S-a-b-c-d-e-f-g-...
	 * 
	 * Then the bytes are
	 * a	(revision)
	 * N	(number of dashes minus two)
	 * bbbbbb	(six bytes of &quot;b&quot; treated as a 48-bit number in big-endian format)
	 * cccc	(four bytes of &quot;c&quot; treated as a 32-bit number in little-endian format)
	 * dddd	(four bytes of &quot;d&quot; treated as a 32-bit number in little-endian format)
	 * eeee	(four bytes of &quot;e&quot; treated as a 32-bit number in little-endian format)
	 * ffff	(four bytes of &quot;f&quot; treated as a 32-bit number in little-endian format)
	 * etc.	
	 * 
	 * So for example, if your SID is S-1-5-21-2127521184-1604012920-1887927527-72713, then your raw hex SID is
	 * 
	 * 010500000000000515000000A065CF7E784B9B5FE77C8770091C0100
	 * 
	 * This breaks down as follows:
	 * 01	S-1
	 * 05	(seven dashes, seven minus two = 5)
	 * 000000000005	(5 = 0x000000000005, big-endian)
	 * 15000000	(21 = 0x00000015, little-endian)
	 * A065CF7E	(2127521184 = 0x7ECF65A0, little-endian)
	 * 784B9B5F	(1604012920 = 0x5F9B4B78, little-endian)
	 * E77C8770	(1887927527 = 0X70877CE7, little-endian)
	 * 091C0100	(72713 = 0x00011c09, little-endian)
	 * 
	 * S-1-	version number (SID_REVISION)
	 * -5-	SECURITY_NT_AUTHORITY
	 * -21-	SECURITY_NT_NON_UNIQUE
	 * -...-...-...-	these identify the machine that issued the SID
	 * 72713	unique user id on the machine
	 * </pre>
	 * 
	 * @param sid binary SID in byte array format
	 * @return String version of the given sid
	 * @since 1.3.1
	 */
	public static String convertBinarySidToString(byte[] sid) {
		// Add the 'S' prefix
		StringBuffer sidAsString = new StringBuffer("S-");

		// bytes[0] : in the array is the version (must be 1 but might
		// change in the future)
		sidAsString.append(sid[0]).append('-');

		// bytes[2..7] : the Authority
		StringBuffer sb = new StringBuffer();
		for (int t = 2; t <= 7; t++) {
			String hexString = Integer.toHexString(sid[t] & 0xFF);
			sb.append(hexString);
		}
		sidAsString.append(Long.parseLong(sb.toString(), HEX));

		// bytes[1] : the sub authorities count
		int count = sid[1];

		// bytes[8..end] : the sub authorities (these are Integers - notice
		// the endian)
		for (int i = 0; i < count; i++) {
			int currSubAuthOffset = i * 4;
			sb.setLength(0);
			sb.append(toHexString((byte) (sid[11 + currSubAuthOffset] & 0xFF)));
			sb.append(toHexString((byte) (sid[10 + currSubAuthOffset] & 0xFF)));
			sb.append(toHexString((byte) (sid[9 + currSubAuthOffset] & 0xFF)));
			sb.append(toHexString((byte) (sid[8 + currSubAuthOffset] & 0xFF)));

			sidAsString.append('-').append(Long.parseLong(sb.toString(), HEX));
		}

		// That's it - we have the SID
		return sidAsString.toString();
	}

    private static byte[] convertToBinarySid(String sid) {
        if (sid == null) {
            return null;
        }

        if (!sid.matches("^[sS]=\\d-\\d{1,13}(?:-\\d{1,10})*$")) {
            return null;
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

        for (int i = 0; i < count; i++) {
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

        // iterate all the sub auths and then countSubAuths x 32 bit sub authorities
        // ([little endian])
        int offset = 8;
        int size = 4;

        for (int j = 0; j < countSubAuths; j++) {
            long subAuthority = 0;

            for (int k = 0; k < size; k++) {
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
