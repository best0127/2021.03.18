import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crack {

    public static void main(String[] args) {

        //需解密字符串
        String str = "zaLybnHFi79MIhf0H/r8pwpDUqhwYM/X0bAblqPzujqSHjQ4Lhb1RZ3Qg4WWkUb1Xc8nr3mWZODk4CH2kUG28h4cd4qdF0vhevqqUt++Hp4bWImfWIPj+0bMSZg0ePLnHtYleMoCgWZioMEEa10upnghtutMP8KGqJqKkMeOJT0SISNSDGzRKec0DIn3bBMwQzXRYHTwQCOrPfDyrXobFMTixy3pTJH20+PhRx/5cbLisaZpfcCcg5EDlqkxq3T5HHY0R/UkjcFDIkuIt2gMdMtxXV87CrHbLwv3DbgOHhPdRm8Ey8rg1XfTMGmNPMLhEsYMqIVip/j2ehtaaoUYFjpKx2YYnDQCL3qjzu4Rszrc1LVg/JfNNXIX+JBO/pAopwHEh0rkqxKoSoLDlHhUlWStX2UNxyyRkD+b+77Bbd2g3HXMy/x+Fx8CsDNbsNlWW3uoqeMID5XoCZd4lJKj6HnegLVGWtv8C+dbIV8pnNyCs4Zg6v/EPp+Xt1Jk/RQla8B0ekZHvdQK8Pmiv/xk0Q5MbnMc7adasBMr38ilNvne2usrIns4Sh1I2xh9vdF5c9xpXBmBNsNL/HWzU3fJ+miobgdUvMwk92aUpdEc+T2HAiIYG67wfO4fBU+1m9Q7cIlAhsXzuSzFqdgJBzt3UwJHQDVxOr/CgBoL1QZN3BYcPqQn3aoEw9vgVF58zQ6JgeEt31xsawyuCaTLKJ5G2PcFGrYyyv/7tesABbT2jky1YkqLLKosO3KTgFtggKtoIu8uNW9Lj8LMg6L/6/p5VQK1QvN3UGCWD2P9tYzsKceeCM2HzAHd5uCO3GX1qcLeesnmH9TDdkTmI5HyVfJ8/U4OACVCUjzi4FpCOlL1I34ant/dvfKoglT9qfKyv6gscX/fxCzdA6ffRr44yk5v2nnAHy0tLVVTxNgNkbZWPKgiPlH6aNz4fn5VEeIw4qHx";
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
