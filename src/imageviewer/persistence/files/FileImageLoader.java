package imageviewer.persistence.files;

import imageviewer.model.Image;
import imageviewer.persistence.ImageLoader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileImageLoader implements ImageLoader{
    private final String filename;

    public FileImageLoader(String filename) {
        this.filename = filename;
    }
    @Override
    public Image load(){
        return new Image(){
            @Override
            public byte[] bitmap(){
                FileInputStream is;
                try {
                    is = new FileInputStream(filename);
                    return read(is);
                } catch (FileNotFoundException ex) {
                    return new byte[0];
                }
            }

            private byte[] read(FileInputStream is) throws IOException {
                byte[] buffer=new byte[4096];
                ByteArrayOutputStream os=new ByteArrayOutputStream();
                int length;
                do{
                    length=is.read(buffer);
                    if(length<1)break;
                    os.write(buffer, 0, length);
                }while(true);
                return os.toByteArray();
            }
        };
    }
}
