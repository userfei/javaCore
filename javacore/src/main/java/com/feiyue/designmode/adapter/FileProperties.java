package com.feiyue.designmode.adapter;

import java.io.*;
import java.util.Properties;

public class FileProperties extends Properties implements FileIO{

    public FileProperties() {
       this.defaults = new Properties(this);
    }

    @Override
    public synchronized void load(InputStream inStream) throws IOException {
        super.load(inStream);
    }

    @Override
    public void readFromFile(String fileName) throws IOException{
        InputStream inputStream = null;
        try{
            inputStream = new BufferedInputStream(new FileInputStream(fileName));
            this.defaults.load(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(inputStream != null)
                    inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void writeToFile(String fileName) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(fileName));
            this.defaults.store(outputStream, "written by FeiYue");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(outputStream != null){
                    outputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setValue(String key, String value) {
        this.defaults.setProperty(key, value);
    }

    @Override
    public String getValue(String key) {
        return (String)this.defaults.get(key);
    }
}
