package maiorsi.keycloak;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Base64;
import maiorsi.keycloak.util.LdapUtils;
import org.junit.jupiter.api.Test;

public class LdapTests {
    private static String BINARY_OBJECT_SID = "AQQAAAAAAAUVAAAAwdFKz9WmazDFb3Y8";
    private static String BINARY_OBJECT_GUID = "9eLQGLjeDE6kgxMT1ghj+g==";
    private static String SID_STRING = "S-1-5-21-3477787073-812361429-1014394821";
    private static String GUID_STRING = "18d0e2f5-deb8-4e0c-a483-1313d60863fa";

    @Test
    public void testDecodeGuid() {
        byte[] bytes = Base64.getDecoder().decode(BINARY_OBJECT_GUID);
        String decoded = LdapUtils.decodeObjectGuid(bytes);

        assertThat(decoded).isEqualTo(GUID_STRING);
    }

    @Test
    public void testDecodeSid() {
        byte[] bytes = Base64.getDecoder().decode(BINARY_OBJECT_GUID);
        String decoded = LdapUtils.decodeObjectSid(bytes);

        assertThat(decoded).isEqualTo(SID_STRING);
    }

    @Test
    public void testEncodeSid() {
        byte[] bytes = LdapUtils.encodeSidStrin(SID_STRING);
        String bytesBase64 = Base64.getEncoder().encodeToString(bytes);

        assertThat(bytesBase64).isEqualTo(BINARY_OBJECT_SID);
    }
}
