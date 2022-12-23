package Book_Api;

import java.io.*;
import java.net.*;

public class SaveImageFromUrl {
    public static byte[] saveImageToArray(String imageId) throws IOException {
        if (imageId.length() > 0) {
            String imageUrl = "http://covers.openlibrary.org/b/id/" + imageId + ".jpg";
            System.out.println("saveImageToArray: \n" + imageUrl + "\n");
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();

            return os.toByteArray();
        }
        return null;
    }
}