package org.example;
import java.io.File;
import java.io.IOException;

public class TouchCommand implements Command{
    File dir;
    String fileName;
    TouchCommand(File dir,String fileName){
        this.dir=dir;
        this.fileName=fileName;
    }

    @Override
    public File execute() throws IOException {

           String absolutePath=dir.getPath()+'/'+fileName;
            File newFile=new File(absolutePath);
            try {
                if (newFile.createNewFile()) {
                    System.out.println("File created successfully at: " + newFile.getAbsolutePath());
                }
                else {

                }
            }
            catch(Exception e){

            }


        return dir;
    }
}
