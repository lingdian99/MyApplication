package com.example.zhihuribao.main.utils;

import android.util.Log;

import com.example.zhihuribao.main.bean.FileImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by banker_test on 2017/9/12.
 */

public class ContentDataControl {
    private static List<FileImage> fileImages;

    public static void addFiles(List<FileImage> imgs) {
        fileImages=new ArrayList<>();
        fileImages=imgs;
        for (int i=0;i<imgs.size();i++){
            Log.e("list_img", "getFileImages: "+imgs.get(i).getFileId());
        }
    }

    public static List<FileImage> getFileImages() {
        for (int i=0;i<fileImages.size();i++){
            Log.e("list", "getFileImages: "+fileImages.get(i).getFileId());
        }
        return fileImages;
    }
}
