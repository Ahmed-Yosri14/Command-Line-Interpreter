package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;


public class CatCommandTest {
    @Test
    void execute()throws IOException{
        File CurrentDir=new File(System.getProperty("user.dir"));
        String from= Files.readString(Paths.get("from.txt"));
        CatCommand cmd=new CatCommand(CurrentDir,"from.txt");
        String temp="";
        for(var i:cmd.getOutput()){
            temp+=i;
        }
        temp=temp.substring(0,Math.max(0,temp.length()-1));
        assertEquals(temp,from);
    }
}
