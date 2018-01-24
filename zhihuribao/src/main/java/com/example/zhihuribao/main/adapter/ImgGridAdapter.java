package com.example.zhihuribao.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.zhihuribao.R;
import com.example.zhihuribao.main.bean.FileImage;

import java.util.List;

/**
 * Created by banker_test on 2017/9/12.
 */

public class ImgGridAdapter extends RvAdapter<FileImage>{
    private Context context;
    private RvListener rvListener;
    private List<FileImage> imageList;


    public ImgGridAdapter(Context context, List<FileImage> dataList, RvListener rvListener) {
        super(context, dataList, rvListener);
        this.context=context;
        this.rvListener=rvListener;
        this.imageList=dataList;

    }

    @Override
    public RvHolder getViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.local_img_layout,parent,false);
        return new ImgGridHolder(view,viewType,rvListener);
    }

    @Override
    public void onBindViewHolder(RvHolder holder, int position) {
        super.onBindViewHolder(holder, position);
//        if (holder instanceof ImgGridHolder){
//            Glide.with(context).load(imageList.get(position).getFileName()).asBitmap().centerCrop().into(((ImgGridHolder) holder).localImg);
//        }

    }

    class ImgGridHolder extends RvHolder<FileImage>{
        protected ImageView localImg;

        public ImgGridHolder(View itemView, int viewType, RvListener rvListener) {
            super(itemView, viewType, rvListener);
            localImg= (ImageView) itemView.findViewById(R.id.img_local);
        }

        @Override
        public void bindHolder(FileImage fileImage, int position) {
            Glide.with(context).load(fileImage.getFilePath()).asBitmap().centerCrop().into(localImg);
        }
    }
}
