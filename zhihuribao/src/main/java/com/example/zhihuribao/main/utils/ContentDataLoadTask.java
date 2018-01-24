package com.example.zhihuribao.main.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;

import com.example.zhihuribao.main.bean.FileImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by banker_test on 2017/9/12.
 */

public class ContentDataLoadTask extends AsyncTask<Void,Void,Void> {
    private FileImage fileImage;
    private List<FileImage> fileImages;
    private Context context;
    private ContentResolver contentResolver;

    public ContentDataLoadTask(Context context){
        this.context=context;
    }

    private OnContentDataListener onContentDataListener;

    public void setOnContentDataListener(OnContentDataListener onContentDataListener) {
        this.onContentDataListener = onContentDataListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (onContentDataListener!=null){
            onContentDataListener.startLoad();
        }
        contentResolver=context.getContentResolver();
    }

    @Override
    protected Void doInBackground(Void... params) {
        ContentDataControl.addFiles(addImg());

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (onContentDataListener!=null){
            onContentDataListener.finishLoad();
        }
    }

    //添加图片列表
    private List<FileImage> addImg(){
        fileImages=new ArrayList<>();
        String[] projection=new String[]{MediaStore.Images.ImageColumns._ID,MediaStore.Images.ImageColumns.DATA,MediaStore.Images.ImageColumns.DISPLAY_NAME};
        String fileId,fileName,filePath;
        //asc 按升序排列
//    desc 按降序排列
        //projection 是定义返回的数据，selection 通常的sql 语句，例如  selection=MediaStore.Images.ImageColumns.MIME_TYPE+"=? " 那么 selectionArgs=new String[]{"jpg"};
        Cursor cursor=contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,projection,null,null,MediaStore.Images.ImageColumns.DATE_MODIFIED+"  desc");
        while (cursor.moveToNext()){
            fileImage=new FileImage();
            fileId=cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID));
            fileName=cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME));
            filePath=cursor.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA));
            fileImage.setFileId(fileId);
            fileImage.setFileName(fileName);
            fileImage.setFilePath(filePath);
            fileImages.add(fileImage);
//            Log.e("ContentProvider", "fileId:"+fileImage.getFileId()+"  fileName:"+fileImage.getFileName()+"  filePath:"+fileImage.getFilePath());
        }
        cursor.close();
        for (int i=0;i<fileImages.size();i++){
            Log.e("ContentProvider----", "fileId:"+fileImages.get(i).getFileId()+"  fileName:"+fileImage.getFileName()+"  filePath:"+fileImage.getFilePath());

        }
        return fileImages;
    }

    public interface OnContentDataListener{
        public void startLoad();
        public void finishLoad();
    }
}
