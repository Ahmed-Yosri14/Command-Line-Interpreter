package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TouchCommandTest {
    @Test
    void execute() throws IOException {
       File CurrentDir=new File(System.getProperty("user.dir"));
       LsCommand objs=new LsCommand(CurrentDir,false);
       ArrayList<String> lst=(ArrayList<String>) objs.getOutput();
       TouchCommand cmd=new TouchCommand(CurrentDir,"1.txt");
       cmd.execute();
       boolean exist=false;
       for(var i:lst){
           if(i.equals("1.txt")){
               exist=true;
               break;
           }
       }
       assertEquals(true,exist);
    }

}
