package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DoubleGreaterCommand implements Command {

    File dir;
    String fileName,Content,baseCmd;
    public DoubleGreaterCommand(File dir,String fileName,String Content,String baseCmd){
        this.dir=dir;
        this.fileName=fileName;
        this.Content=Content;
        this.baseCmd=baseCmd;
    }
    public File execute() throws IOException {
        fileName=fileName.trim();
        int greater =fileName.indexOf(">");
        int doubleGreater=fileName.indexOf(">>");

        if(greater==doubleGreater && greater==-1){
            String []currFiles=fileName.split(" ");
            for(var i:currFiles){
                try (FileWriter writer = new FileWriter(dir.getPath()+'\\'+i,true)) {
                    writer.write(Content);
                }
                catch (IOException e){
                    System.out.println(String.format("cat: %s: No such file or directory\n",i));

                }
            }
        }
        else{
            int idx;
            if(greater==-1)idx=doubleGreater;
            else if(doubleGreater==-1)idx=greater;
            else idx=Math.min(greater,doubleGreater);
            String []currFiles=fileName.substring(0,idx).split(" ");

            for(var i:currFiles){
                try (FileWriter writer = new FileWriter(dir.getPath()+'\\'+i,true)) {
                    writer.write(Content);
                }
                catch (IOException e){
                    System.out.println(String.format("cat: %s: No such file or directory\n",i));

                }
            }
            Content="";
            for(var i:currFiles){
                try {
                    Content+= Files.readString(Path.of(dir.getAbsolutePath()+"\\"+i))+'\n';
                }
                catch (IOException e){


                }
            }


            if(greater==doubleGreater){
                DoubleGreaterCommand obj=new DoubleGreaterCommand(dir,fileName.substring(idx+2),Content,baseCmd);
                obj.execute();
            }
            else{

                GreaterCommand obj=new GreaterCommand(dir,fileName.substring(idx+1),Content,baseCmd);
                obj.execute();
            }
        }
        return dir;
    }

    @Override
    public List<String> getOutput() throws IOException {
        return List.of();
    }
}
