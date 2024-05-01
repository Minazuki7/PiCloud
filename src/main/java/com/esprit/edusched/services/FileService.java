package com.esprit.edusched.services;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Service
public class FileService {
    private final String ACCESS_TOKEN = "ACCESS"; // Replace with your Dropbox access token

    public String uploadPicture(String fileName, String pictureData) throws Exception {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
    
        byte[] bytes = pictureData.getBytes();
        try (InputStream in = new ByteArrayInputStream(bytes)) {
            FileMetadata metadata = client.files().uploadBuilder("/" + fileName)
                    .uploadAndFinish(in);
            

            String imageURL = client.sharing().createSharedLinkWithSettings(metadata.getPathLower()).getUrl();
            return imageURL;
        }
    }
    
    public String downloadPicture(String fileName) throws Exception {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (InputStream in = client.files().download("/" + fileName).getInputStream()) {
            byte[] buffer = new byte[4096];
            int n;
            while ((n = in.read(buffer)) != -1) {
                outputStream.write(buffer, 0, n);
            }
        }
        return outputStream.toString();
    }

}
