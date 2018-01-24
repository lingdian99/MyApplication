package com.example.zhihuribao.main.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.zhihuribao.R;
import com.example.zhihuribao.main.bean.Comment;
import com.example.zhihuribao.main.utils.DateUtil;

/**
 * Created by banker_test on 2017/8/25.
 */
public class CommentHolder extends RvHolder<Comment.CommentsBean>{
    private TextView tvAuther;
    private TextView tvComment;
    private TextView tvTime;
    private ImageView imageView;
    private Context context;

    public CommentHolder(View itemView, int viewType, final RvListener rvListener, Context context) {
        super(itemView,viewType,rvListener);
        this.context=context;
        tvAuther= (TextView) itemView.findViewById(R.id.tv_user);
        tvTime= (TextView) itemView.findViewById(R.id.tv_time);
        tvComment= (TextView) itemView.findViewById(R.id.tv_comment);
        imageView= (ImageView) itemView.findViewById(R.id.img_user);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               rvListener.onClick(getAdapterPosition());
            }
        });
    }

    @Override
    public void bindHolder(Comment.CommentsBean commentsBean, int position) {
        tvAuther.setText(commentsBean.getAuthor());
        tvComment.setText("\t\t"+commentsBean.getContent());
        tvTime.setText(DateUtil.times(commentsBean.getTime()));
        Glide.with(context).load(commentsBean.getAvatar()).asBitmap().into(new BitmapImageViewTarget(imageView){
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable drawable= RoundedBitmapDrawableFactory.create(context.getResources(),resource);
                drawable.setCircular(true);
                imageView.setImageDrawable(drawable);
            }
        });
    }
}