package com.example.zhihuribao.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhihuribao.R;
import com.example.zhihuribao.main.utils.BeforeNewsList;
import com.example.zhihuribao.main.utils.TodayNewsList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by banker_test on 2017/4/26.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private View mHeaderView;
    private View mFooterView;
    private ProgressBar progressBar;
    private static final int TYPE_HEAD = 1;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_FOOTER = 2;
    private static final int TYPE_DATE = 3;
    private List<TodayNewsList.StoriesBean> storiesBeen = new ArrayList<>();
    private List<BeforeNewsList.StoriesBean> beforeBeen = new ArrayList<>();
    private boolean isFooterView = false;
    //日期的position
    private List<Integer> datePosition = new ArrayList<>();
    private List<String> dateList;
    private List<Boolean> isClicks = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    public MyFooterViewHolder footerHolder;

    public HomeAdapter(Context context, List<TodayNewsList.StoriesBean> storiesBeen) {
        this.context = context;
        this.storiesBeen = storiesBeen;
    }

    public void setHeaderView(View headerView) {
        this.mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void setBeforeBeen(List<BeforeNewsList.StoriesBean> beforeBeen) {
//        this.dateList = dateList;
        for (int i = 0; i < beforeBeen.size(); i++) {
            this.beforeBeen.add(beforeBeen.get(i));
        }
        Log.e("beforeBeenSize", ":" + this.beforeBeen.size());
        notifyDataSetChanged();
    }

    public void setDateList(List<String> dateList,List<Integer> datePosition) {
        this.dateList = dateList;
        this.datePosition=datePosition;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false);
        View dateView = LayoutInflater.from(context).inflate(R.layout.news_item_date, parent, false);
        View sectionView = LayoutInflater.from(context).inflate(R.layout.section_main, parent, false);
        mFooterView = LayoutInflater.from(context).inflate(R.layout.news_list_footer, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        if (mHeaderView != null && viewType == TYPE_HEAD) {
            return new MyViewHolder(mHeaderView);
        }
        if (viewType == TYPE_FOOTER) {
            Log.e("initFooterView", "footerView");
            footerHolder = new MyFooterViewHolder(mFooterView);
            return footerHolder;
        }
        if (viewType == TYPE_DATE) {
            return new MySectionHolder(sectionView);
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
//            if (holder instanceof MyViewHolder) {
//                for (int i = 1; i < datePosition.size(); i++) {
//                    if (position == 1) {
//                        ((MyViewHolder) holder).dateText.setText("今日");
//                    }
//                    if (position == datePosition.get(i)) {
//                        ((MyViewHolder) holder).dateText.setText(dateList.get(i));
//                    }
//                }
//            }
            if (holder instanceof MySectionHolder) {

                for (int i = 1; i < datePosition.size(); i++) {
                    if (position == 1) {
                        ((MySectionHolder) holder).textView.setText("今日");
                    }

                    if (position == datePosition.get(i)) {
                        Log.e("position+datePosition", ":" + i + "," + dateList.size());
                        ((MySectionHolder) holder).textView.setText(dateList.get(i));
                        return;
                    }
                }

            }
        }


        if (getItemViewType(position) == TYPE_NORMAL) {
            if (holder instanceof MyViewHolder) {
                ((MyViewHolder) holder).menuText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPopupMenu(((MyViewHolder) holder).menuText);
                    }
                });

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                    ((MyViewHolder) holder).itemText.setTextColor(Color.parseColor("#ff0000"));
                        onItemClickListener.setOnClick((Integer) holder.itemView.getTag());
                    }
                });
//                if (mHeaderView != null) {
//                    position = position - 1;
//                }

//                if (position < storiesBeen.size()+datePosition.size()) {
//                    Glide.with(context).load(storiesBeen.get(position).getImages().get(0)).into(((MyViewHolder) holder).itemImg);
//                    ((MyViewHolder) holder).itemText.setText(storiesBeen.get(position).getTitle());
//                } else {
//                    Glide.with(context).load(beforeBeen.get(position - storiesBeen.size()).getImages().get(0)).into(((MyViewHolder) holder).itemImg);
//                    ((MyViewHolder) holder).itemText.setText(beforeBeen.get(position - storiesBeen.size()).getTitle());
//                }
                if (position <= storiesBeen.size() -1 + 2) {
                    int realPosition = position - 2;
                    Glide.with(context).load(storiesBeen.get(realPosition).getImages().get(0)).into(((MyViewHolder) holder).itemImg);
                    ((MyViewHolder) holder).itemText.setText(storiesBeen.get(realPosition).getTitle());
                    return;
                }else {
                    for (int i = datePosition.size() - 1; i >= 0; i--) {
                        int realPosition=position-storiesBeen.size()-i-1-1;
                        if (position > datePosition.get(i)) {
                            Glide.with(context).load(beforeBeen.get(realPosition).getImages().get(0)).into(((MyViewHolder) holder).itemImg);
                            ((MyViewHolder) holder).itemText.setText(beforeBeen.get(realPosition).getTitle());
                            return;
                        }
                    }
                }




            }
        }


    }

    private void showPopupMenu(TextView textView){
        PopupMenu popupMenu=new PopupMenu(context,textView);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_share:
                        Intent shareIntent=new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_TEXT,"http://www.baidu.com");
                        context.startActivity(Intent.createChooser(shareIntent,"分享到:"));
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    @Override
    public int getItemViewType(int position) {
        Log.e("getViewType", "getItemViewType: " + position);
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
        Log.e("itemCount", "itemCount:" + (storiesBeen.size() + beforeBeen.size() + 2 + datePosition.size()));
        return storiesBeen.size() + beforeBeen.size() + 2 + datePosition.size();

    }

    private void addDates(int prePosition) {
        int position = datePosition.get(datePosition.size() - 1) + prePosition;
        datePosition.add(position);
        Log.e("datePosition.size", "addDates: "+datePosition.size() );
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemText;
        ImageView itemImg;
        ProgressBar progressBar;
        TextView dateText;
        TextView menuText;

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
            menuText= (TextView) itemView.findViewById(R.id.tv_menu);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    itemText.setTextColor(Color.parseColor("#ff0000"));
//                    onItemClickListener.setOnClick((Integer) itemView.getTag());
//                }
//            });
        }
    }

    public class MyFooterViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        TextView textView;

        public MyFooterViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.footer_progress);
            textView = (TextView) itemView.findViewById(R.id.footer_text);
            progressBar.setVisibility(View.GONE);
        }

        public void setData(int type) {
            switch (type) {
                case 1:
                    textView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case 0:
                    textView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    break;
            }
        }
    }

    class MySectionHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MySectionHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_section_date);
        }
    }

    public interface OnItemClickListener {
        public void setOnClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
