package com.example.zhihuribao.main.bean;

/**
 * Created by banker_test on 2017/9/12.
 */

public class FileImage {
    private String fileId;
    private String fileName;
    private String filePath;

    public void setFileId(String fileId){
        this.fileId=fileId;
    }

    public void setFileName(String fileName){
        this.fileName=fileName;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileId(){
        return fileId;
    }

    public String getFileName(){
        return fileName;
    }

    public String getFilePath() {
        return filePath;
    }

}
