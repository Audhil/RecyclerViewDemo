package com.wordpress.smdaudhilbe.adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wordpress.smdaudhilbe.model.OnRecyclerViewItemClickListener;
import com.wordpress.smdaudhilbe.model.PaletteManager;
import com.wordpress.smdaudhilbe.model.ViewModel;
import com.wordpress.smdaudhilbe.mohammed_2284.recyclerviewdemo.R;

import java.util.List;

/**
 * Created by mohammed-2284 on 17/02/15.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static PaletteManager paletteManager = new PaletteManager();
    List<ViewModel> items;
    int itemLayout;
    private OnRecyclerViewItemClickListener<ViewModel> itemClickListener;

    public RecyclerAdapter(List<ViewModel> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        view.setOnClickListener(viewHolderClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ViewModel item = items.get(position);
        holder.itemView.setTag(item);
        holder.text.setText(item.getText());

        Picasso.with(holder.image.getContext()).load(item.getImage()).into(holder.image, new Callback() {
            @Override
            public void onSuccess() {
                holder.updatePalette(paletteManager);
            }

            @Override
            public void onError() {
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void add(ViewModel item, int position) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(ViewModel item) {
        int position = items.indexOf(item);
        items.remove(position);
        notifyItemRemoved(position);
    }

    View.OnClickListener viewHolderClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                ViewModel vModel = (ViewModel) v.getTag();
                itemClickListener.onItemClick(v, vModel);
            }
        }
    };

    public void setOnItemClickListener(OnRecyclerViewItemClickListener<ViewModel> listener) {
        this.itemClickListener = listener;
    }

    //  alpha color
    private static int setColorAlpha(int color, int alpha) {
        return (alpha << 24) | (color & 0x00ffffff);
    }

    //  ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;

        public ViewHolder(View itemViewIs) {
            super(itemViewIs);
            image = (ImageView) itemViewIs.findViewById(R.id.image);
            text = (TextView) itemViewIs.findViewById(R.id.text);
        }

        public void updatePalette(PaletteManager pManager) {
            String key = ((ViewModel) itemView.getTag()).getImage();
            Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();


            paletteManager.getPalette(key, bitmap, new PaletteManager.CallBack() {
                @Override
                public void onPaletteReady(Palette palette) {
                    int bgColor = palette.getDarkVibrantColor(0);
                    text.setBackgroundColor(setColorAlpha(bgColor, 192));
                    text.setTextColor(palette.getLightMutedColor(0));
                }
            });
        }
    }
}