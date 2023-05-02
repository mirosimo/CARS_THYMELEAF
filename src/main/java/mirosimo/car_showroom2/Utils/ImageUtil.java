package mirosimo.car_showroom2.Utils;

import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class ImageUtil {
    public String getImgData(byte[] byteData) {
        return Base64.getMimeEncoder().encodeToString(byteData);
    }
}
