package bish.flickrdemo.view.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import bish.flickrdemo.R;
import bish.flickrdemo.model.ImageInfo;

/**
 * Created by BB045296 on 5/21/2016.
 */
public class MyGridAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ImageInfo> imageInfoArrayList = new ArrayList<>();
    private boolean[] bool;
    public MyGridAdapter(Context context, ArrayList<ImageInfo> imageInfoArrayList){
        this.context = context;
        this.imageInfoArrayList = imageInfoArrayList;
        bool = new boolean[imageInfoArrayList.size()];
    }
    @Override
    public int getCount() {
       return (imageInfoArrayList != null? imageInfoArrayList.size():0);

    }

    @Override
    public Object getItem(int position) {
        return imageInfoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        bool = new boolean[imageInfoArrayList.size()];
    }

    public static class ViewHolder
    {
         ImageView myImageView;
         TextView myTextView;
         ViewFlipper myViewFlipper;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder view;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            view = new ViewHolder();
            convertView = inflater.inflate(R.layout.grid_item,parent,false);
            view.myImageView = (ImageView)convertView.findViewById(R.id.iv_gridimage);
            view.myTextView = (TextView)convertView.findViewById(R.id.tv_imageinfo);
            view.myViewFlipper = (ViewFlipper)convertView.findViewById(R.id.view_flipper);
            convertView.setTag(view);
        }
        else
        {
            view = (ViewHolder) convertView.getTag();
            //if it is not clicked...restoring it to image view
            if(!bool[position])
                view.myViewFlipper.setDisplayedChild(0);
                //if it is already clicked...restoring to saved state... i.e. to text view
            else
                view.myViewFlipper.setDisplayedChild(1);

        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AnimatorSet  set = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.flip_right);
                set.setTarget(v);
                set.start();

                view.myViewFlipper.showNext();
                //used to keep track of state of each grid item...
                if(!bool[position])
                bool[position] = true;
                else
                    bool[position] = false;

            }
        });
        Glide.with(context).load(imageInfoArrayList.get(position).getUrl())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view.myImageView);

        view.myTextView.setText(imageInfoArrayList.get(position).getTitle());
        return convertView;
    }

}
