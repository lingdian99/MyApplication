package com.example.zhihuribao.main.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhihuribao.R;
import com.example.zhihuribao.main.bean.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by banker_test on 2017/8/25.
 */

public class CommentAdapter extends RvAdapter<Comment.CommentsBean> {
    private Context context;
    private RvListener rvListener;
    private List<Comment.CommentsBean> dataList;
    private List<Integer> headPositions;
    private final int HEAD_POSITION = 1;
    private List<String> sectionString;
    private Map<Integer, String> sections;

    public CommentAdapter(Context context, List<Comment.CommentsBean> dataList, List<Integer> headPositions, RvListener rvListener) {
        super(context, dataList, rvListener);
        this.context = context;
        this.rvListener = rvListener;
        this.dataList = dataList;
        this.headPositions = headPositions;
        headPositions = new ArrayList<>();
        sectionString = new ArrayList<>();
        sectionString.add("长评论");
        sectionString.add("短评论");
    }

    @Override
    public RvHolder getViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD_POSITION) {
            View view = LayoutInflater.from(context).inflate(R.layout.comment_head, parent, false);
            return new MyHolder(view, viewType, rvListener);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.comment_layout, parent, false);
            return new CommentHolder(view, viewType, rvListener, context);
        }
    }


    @Override
    public void onBindViewHolder(RvHolder holder, int position) {
        if (getItemViewType(position) == HEAD_POSITION) {
            for (int i = 0; i < headPositions.size(); i++) {
                if (position == headPositions.get(i)) {
                    holder.bindHolder(sectionString.get(i), position);
                    return;
                }
            }
            return;
        } else if (getItemViewType(position) == 0) {
//            if (position < headPositions.get(headPositions.size() - 1)) {
//                holder.bindHolder(dataList.get(position - 1), position);
//            } else {
//                holder.bindHolder(dataList.get(position - 2), position);
//            }

            for (int i=headPositions.size()-1;i>=0;i--){
                if (position>headPositions.get(i)){
                    holder.bindHolder(dataList.get(position-(i+1)),position);
                    return;
                }
            }
            Log.e("position", "onBindViewHolder: " + position);
        }

//        for (int i=headPositions.size()-1;i>=0;i--){
//            if (position==headPositions.get(i)){
//                Log.e("headPosition", "onBindViewHolder: "+headPositions.get(i));
//                Log.e("size", "onBindViewHolder: "+headPositions.size());
//                return;
//            }else {
////                holder.bindHolder(dataList.get(position),position);
//                Log.e("item", "onBindViewHolder: "+position);
//                return;
//            }
//
////            Log.e("headPosition", "onBindViewHolder: "+headPositions.get(i));
//        }

//
//        for (int i = 0; i < headPositions.size(); i++) {
//            if (i != headPositions.size() - 1) {
//                if (headPositions.get(i) < position&&position < headPositions.get(i + 1)){
//                    holder.bindHolder(dataList.get(position - 1), position);
//                }
//            }
//        }


//        if (position>headPositions.get(headPositions.size()-1)){
//            holder.bindHolder(dataList.get(position - headPositions.get(headPositions.size()-1)-1), position);
//            Log.e("holder", "position: "+position+"  "+(position - headPositions.get(headPositions.size()-1)));
//        }
//        Log.e("position", "onBindViewHolder: " + position);
    }


    @Override
    public int getItemViewType(int position) {
        for (int i = 0; i < headPositions.size(); i++) {
            if (position == headPositions.get(i)) {
                Log.e("headPosition", "getItemViewType: " + headPositions.get(i));
                return HEAD_POSITION;
            }
        }


//        for (int i = 0; i < sections.size(); i++) {
//            if (position == sections.) {
//                return HEAD_POSITION;
//            }
//        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + headPositions.size();
    }

    class MyHolder extends RvHolder<String> {
        TextView textView;

        public MyHolder(View itemView, int viewType, RvListener rvListener) {
            super(itemView, viewType, rvListener);
            textView = (TextView) itemView.findViewById(R.id.comment_header);
        }

        @Override
        public void bindHolder(String s, int position) {
            textView.setText(s);
        }


    }

}
