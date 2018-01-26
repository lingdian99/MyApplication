package com.example.kaiyan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kaiyan.R;
import com.example.kaiyan.bean.HomepageBean;

import java.util.List;

/**
 * Created by wys10 on 17/12/12.
 */

public class MyRvAdapter extends RecyclerView.Adapter{
    private View mItemView;
    //头部banner
    private View headerView;
    private Context mContext;
    private List<HomepageBean.ItemListBean> mItemList;
    private MyOnClickListener myOnClickListener;
    //顶部header
    private static final int HEADER_VIEW_TYPE=1;

    public MyRvAdapter(Context context,List<HomepageBean.ItemListBean> mItemList){
        this.mContext=context;
        this.mItemList=mItemList;
    }

    public void setHeaderView(View view){
        this.headerView=view;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mItemView=LayoutInflater.from(mContext).inflate(R.layout.main_item_layout,parent,false);
        switch (viewType){
            case 0:
                return new MyViewHolder(mItemView);
            case HEADER_VIEW_TYPE:
                return new MyHeaderView(headerView);
            default:
                return new MyViewHolder(mItemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        if (holder instanceof MyViewHolder){
            String imgUrl=mItemList.get(position).getData().getCover().getFeed();
            String name=(mItemList.get(position).getData().getAuthor()==null?mItemList.get(position).getData().getDescription()
                    :mItemList.get(position).getData().getAuthor().getName());
            String title=mItemList.get(position).getData().getTitle();
            ((MyViewHolder) holder).topicText.setText(title);
            ((MyViewHolder) holder).usernameText.setText(name);
            Glide.with(mContext).load(imgUrl).into(((MyViewHolder) holder).homepageBg);
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return HEADER_VIEW_TYPE;
        }
        return super.getItemViewType(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView homepageBg;
        ImageView userImg;
        TextView topicText;
        TextView usernameText;

        public MyViewHolder(final View itemView) {
            super(itemView);
            homepageBg= (ImageView) itemView.findViewById(R.id.iv_thumbnail);
            userImg= (ImageView) itemView.findViewById(R.id.img_user);
            topicText= (TextView) itemView.findViewById(R.id.tv_topic);
            usernameText= (TextView) itemView.findViewById(R.id.tv_username);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myOnClickListener!=null){
                        myOnClickListener.onItemClick((Integer) itemView.getTag());
                    }
                }
            });
        }

    }

    class MyHeaderView extends RecyclerView.ViewHolder{

        public MyHeaderView(View itemView) {
            super(itemView);
        }
    }

    public interface MyOnClickListener{
        public void onItemClick(int position);
    }

    public void setMyOnClickListener(MyOnClickListener listener){
        this.myOnClickListener=listener;
    }
}
