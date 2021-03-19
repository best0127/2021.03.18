import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES/CBC/PKCS5Padding 解密
 */
public class Crack {

    public static void main(String[] args) {

        //需解密字符串
        String str = "KOT/DkEPNpGBaaba7S/F/4ctrqIhc+iSV2wL+uihu" +
                "udVH1TnwDzkDeMb/ZWxtdi8CYUF7pON/7i/s1m2XkCK4Mjh/ohsT4L4nIB" +
                "YQK2oII3Y8irhaU95zsnoEDF6eZqIQj+ueHQfzATdZ14a7cmZ/2AAg2sue4xBkO5C3OS" +
                "V+TVIMZtN275T8VjHQSDbw6PdhaeA/Hltesj2LfvrR5XXSYCZDEm5wz0iuISSkV0UFHB9JCXjt" +
                "94iPSx5cwe9lZ0g9Cwbe/Dypx3FYxEzzRCUgmbCbS/mdRqRFidobsmrg4WVg9mdSxDATIKrtHqfTGpG5" +
                "Khd8rdUSyf9nypTVzPxD4EY0IIQ3Yu1pEbwiCsQcOsIDpWUATQ4/QHXtzAljkCm4wM92WoD4w51R3OvjkLLa" +
                "HKmW//hs3uWn4NK9GbUY6m0ZydRSijLbS7u22DfiXvw0Vj88B+Zr0IH9v5g/J3uxINk0CFyapLm4hDm5tPqnUL4y+" +
                "4vrT5TojkSfEvhY80pdlffNv+4XuXNN+grDD3Xo08lOreLdlI5wU2gTYZP8I13ng91UUZeLej2BdkNwuM50+uGSl7MCiE" +
                "bURC06qTp6C7KcZbowGcvWtED4C1BU5qlx+zBOOeGP78ttBSaEbWwcJN8bLd5Fr73KmyddChULpNH59/9wXxoZZQpm8EWmg3Zh" +
                "9uCVADF5rJB7sIKxfGM7WpWhWjh6W+aUTrha/QXEB3wpLSMmy6Ql5/L1fAMHV8eOSt8NeXw9f6D9VX/LvLH3vgSPOw5ENPudC0aFKxJ4TH3G" +
                "3smcXKAPU08fyxgzNW74WN/a63mtqD5jmHHkJa/uW1BacIdWMT1lC7WrWjy7Ld5mlEKs17YXKUvh0HwM/Q=";
        //密码->uid转MD5取前16位
        String key = "98874CA86AF6E937";
        String res = null;

        //获取解密结果
        res = Decrypt(str, key);
        //打印结果
        System.out.println(res);

    }

    // 解密
    public static String Decrypt(String str, String key) {
        try {

            // 判断Key是否正确
            if (key == null) {
                System.out.print("Key为空null");
                return null;
            }

            // 判断Key是否为16位
            if (key.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }

            byte[] raw = key.getBytes("utf-8");
            SecretKeySpec keySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //密码为16位，直接取16位偏移量
            IvParameterSpec iv = new IvParameterSpec(key.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, keySpec, iv);
            byte[] encrypted1 = new Base64().decode(str);//先用base64解密

            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
}
