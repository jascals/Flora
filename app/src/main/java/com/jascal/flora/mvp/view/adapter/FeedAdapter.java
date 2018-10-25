package com.jascal.flora.mvp.view.adapter;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jascal.flora.R;
import com.jascal.flora.net.Config;
import com.jascal.flora.net.bean.Feed;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private List<Feed> mList;

    public FeedAdapter(List<Feed> mList) {
        this.mList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_feed, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Feed feed = mList.get(position);

        if (feed.getImage_count() > 0) {
            Uri uri = Uri.parse(Config.BASE_IMAGE_PATH +
                    feed.getImages().get(0).getUser_id() +
                    Config.BASE_IMAGE_TAIL +
                    feed.getImages().get(0).getImg_id() +
                    ".jpg");
            Log.d("fresco", uri.toString());

            load(uri, holder.img, feed.getImages().get(0).getWidth(), feed.getImages().get(0).getHeight());

//            ResizeOptions resizeOptions = new ResizeOptions(200, 600);
//
//            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
//                    .setResizeOptions(resizeOptions)
//                    .build();
//
//            holder.img.setController(
//                    Fresco.newDraweeControllerBuilder()
//                            .setOldController(holder.img.getController())
//                            .setImageRequest(request)
//                            .build()
//            );

        }

        holder.text.setText(feed.getContent());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        SimpleDraweeView img;
        TextView text;

        ViewHolder(View itemView) {
            super(itemView);
//            cardView = (CardView) itemView;
            img = (SimpleDraweeView) itemView.findViewById(R.id.item_img);
            text = (TextView) itemView.findViewById(R.id.item_text);
        }
    }


    private void load(Uri uri, SimpleDraweeView draweeView, int width, int height) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, height))
                //缩放,在解码前修改内存中的图片大小, 配合Downsampling可以处理所有图片,否则只能处理jpg,
                // 开启Downsampling:在初始化时设置.setDownsampleEnabled(true)
                .setProgressiveRenderingEnabled(true)//支持图片渐进式加载
                .setAutoRotateEnabled(true) //如果图片是侧着,可以自动旋转
                .build();
        PipelineDraweeController controller =
                (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(draweeView.getController())
                        .setAutoPlayAnimations(true) //自动播放gif动画
                        .build();
        draweeView.setController(controller);
    }
}
