package com.example.zhihuribao.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhihuribao.R;
import com.example.zhihuribao.main.utils.BeforeNewsList;
import com.example.zhihuribao.main.utils.ThemeNewsUntil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by banker_test on 2017/4/26.
 */

public class ThemeNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private View mHeaderView;
    private View mFooterView;
    private ProgressBar progressBar;
    private static final int TYPE_HEAD = 1;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_FOOTER = 2;
    private static final int TYPE_DATE = 3;
    private List<ThemeNewsUntil.StoriesBean> storiesBeen = new ArrayList<>();
    private List<BeforeNewsList.StoriesBean> beforeBeen = new ArrayList<>();
    private boolean isFooterView = false;
    private List<Integer> datePosition = new ArrayList<>();
    private List<String> dateList;
    private List<Boolean> isClicks=new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public ThemeNewsAdapter(Context context, List<ThemeNewsUntil.StoriesBean> storiesBeen) {
        this.context = context;
        this.storiesBeen = storiesBeen;
        datePosition.add(1);
        addDates(storiesBeen.size());
    }

    public void setHeaderView(View headerView) {
        this.mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void setBeforeBeen(List<BeforeNewsList.StoriesBean> beforeBeen, List<String> dateList) {
        this.dateList = dateList;
        for (int i = 0; i < beforeBeen.size(); i++) {
            this.beforeBeen.add(beforeBeen.get(i));
        }
        addDates(beforeBeen.size());
        Log.e("beforeBeenSize", ":" + this.beforeBeen.size());
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        View dateView = LayoutInflater.from(context).inflate(R.layout.news_item_date, parent, false);
        mFooterView = LayoutInflater.from(context).inflate(R.layout.news_list_footer, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        if (mHeaderView != null && viewType == TYPE_HEAD) {
            return new MyViewHolder(mHeaderView);
        }
        if (viewType == TYPE_FOOTER) {
            Log.e("initFooterView", "footerView");
            return new MyViewHolder(mFooterView);
        }
        if (viewType == TYPE_DATE) {
            return new MyViewHolder(dateView);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        if (getItemViewType(position) == TYPE_HEAD) {
            return;
        }
        if (getItemViewType(position) == TYPE_FOOTER) {
                return;
        }
        if (getItemViewType(position) == TYPE_DATE) {
            if (holder instanceof MyViewHolder) {
                for (int i = 1; i < datePosition.size(); i++) {
                    if (position == 1) {
                        ((MyViewHolder) holder).dateText.setText("今日");
                    }
                    if (position == datePosition.get(i)) {
                        ((MyViewHolder) holder).dateText.setText(dateList.get(i));
                    }
                }
            }
        }

        if (holder instanceof MyViewHolder) {
            if (mHeaderView != null) {
                position = position - 1;
            }

            if (position < storiesBeen.size()) {
                if (storiesBeen.get(position).getImages()!=null){
                    Glide.with(context).load(storiesBeen.get(position).getImages().get(0)).into(((MyViewHolder) holder).itemImg);
                }else {
                    ((MyViewHolder) holder).itemImg.setVisibility(View.GONE);
                }
                ((MyViewHolder) holder).itemText.setText(storiesBeen.get(position).getTitle());
            } else {
                Glide.with(context).load(beforeBeen.get(position - storiesBeen.size()).getImages().get(0)).into(((MyViewHolder) holder).itemImg);
                ((MyViewHolder) holder).itemText.setText(beforeBeen.get(position - storiesBeen.size()).getTitle());
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ((MyViewHolder) holder).itemText.setTextColor(Color.parseColor("#ff0000"));
                    onItemClickListener.setOnClick((Integer) holder.itemView.getTag());
                }
            });

        }


    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) {
            Log.e("viewType", ":normal");
            return TYPE_NORMAL;
        }
        if (position == 0) {
            Log.e("viewType", ":header");
            return TYPE_HEAD;
        }
        if (position == getItemCount() - 1) {
            Log.e("viewType", ":Footer");
            return TYPE_FOOTER;
        }
        for (int i = 0; i < datePosition.size(); i++) {
            if (position == datePosition.get(i)) {
                Log.e("position+datePosition", ":" + position + "," + datePosition.get(i));
                return TYPE_DATE;
            }
        }
        Log.e("viewType", ":normal");
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
//        if (mHeaderView == null && mFooterView == null) {
//            Log.e("itemCount","itemCount:"+storiesBeen.size());
//            return storiesBeen.size();
//        }
//        if (mHeaderView != null && mFooterView == null) {
//            Log.e("itemCount","mFooterView:"+storiesBeen.size()+1);
//            return storiesBeen.size() + 1;
//        }
//        if (mFooterView != null && mHeaderView == null) {
//            Log.e("itemCount","mHeaderView:"+storiesBeen.size()+1);
//            return storiesBeen.size() + 1;
//        }
        if (mHeaderView == null) {
            return storiesBeen.size() + beforeBeen.size() + 1;
        }
        Log.e("itemCount", "itemCount:" + storiesBeen.size() + 2);
        return storiesBeen.size() + beforeBeen.size() + 2;

    }

    private void addDates(int prePosition) {
        int position = datePosition.get(datePosition.size() - 1) + prePosition;
        datePosition.add(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemText;
        ImageView itemImg;
        ProgressBar progressBar;
        TextView dateText;

        public MyViewHolder(final View itemView) {
            super(itemView);
            if (itemView == mHeaderView) {
                return;
            }
            if (itemView == mFooterView) {
                progressBar = (ProgressBar) itemView.findViewById(R.id.footer_progress);
            }
            dateText = (TextView) itemView.findViewById(R.id.item_date_text);
            itemText = (TextView) itemView.findViewById(R.id.item_text);
            itemImg = (ImageView) itemView.findViewById(R.id.item_img);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    itemText.setTextColor(Color.parseColor("#ff0000"));
//                    onItemClickListener.setOnClick((Integer) itemView.getTag());
//                }
//            });
        }
    }

    public interface OnItemClickListener {
        public void setOnClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
