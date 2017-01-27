package imageviewer.persistence.files;

import imageviewer.model.Image;
import imageviewer.persistence.ImageLoader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileImageLoader implements ImageLoader{
   private static File[] files;
    private final String[] ImageExtensions = new String[]{"jpg","png","bmp"};
    
    public FileImageLoader(String folder) {
        files = new File(folder).listFiles(withImageExtension());
     }
    @Override
    public Image load(){
        return imageAt(0);
    }

        

    private Image imageAt(int index) {
        return new Image() {
            @Override
            public byte[] bitmap() {
                try {
                    FileInputStream is = new FileInputStream(files[index]);
                    return read(is);
                } catch (IOException ex) {
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
            @Override
            public Image next() {
                if(index==files.length-1) return imageAt(0);
                return imageAt(index+1);
            }

            @Override
            public Image prev() {
                if(index==0) return imageAt(files.length-1);
                return imageAt(index-1);
            }
          
        };
    }
    private FilenameFilter withImageExtension() {
         return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                for (String extension : ImageExtensions)
                    if(name.endsWith(extension)) return true;
                return false;
            }
        };
    }
}
