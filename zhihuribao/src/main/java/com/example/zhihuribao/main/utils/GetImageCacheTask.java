package com.example.zhihuribao.main.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.io.File;

/**
 * Created by banker_test on 2017/9/8.
 */

public class GetImageCacheTask extends AsyncTask<String,Void,File>{
    private Context context;
    private ImageView imageView;

    public GetImageCacheTask(Context context,ImageView imageView){
        this.context=context;
        this.imageView=imageView;
    }

    @Override
    protected File doInBackground(String... params) {
        String url =  params[0];
        try {
            return Glide
                    .with(context)
                    .load(url)
                    .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get() // needs to be called on background thread
                    ;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(File result) {
        if (result == null) {
            return;
        }
        //此path就是对应文件的缓存路径
        String path = result.getPath();
        Log.e("path", path);
        Bitmap bmp= BitmapFactory.decodeFile(path);
        imageView.setImageBitmap(bmp);
    }

    private void share(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Shared image");
        intent.putExtra(Intent.EXTRA_TEXT, "Look what I found!");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, "Share image"));
    }
}
