package com.jascal.flora.mvp.feed.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.jascal.flora.R;
import com.jascal.flora.net.Config;
import com.jascal.flora.net.bean.tc.Feed;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private List<Feed> mList;

    public FeedAdapter(List<Feed> mList) {
        this.mList = mList;
    }

    private void load(Uri uri, SimpleDraweeView draweeView, int width, int height) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, height))
                .setProgressiveRenderingEnabled(true)
                .setAutoRotateEnabled(true)
                .build();

        PipelineDraweeController controller =
                (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(draweeView.getController())
                        .setAutoPlayAnimations(true)
                        .build();
        draweeView.setController(controller);
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
            Uri uri = Uri.parse(Config.BASE_IMAGE_PATH + feed.getImages().get(0).getUser_id() +
                    Config.BASE_IMAGE_TAIL + feed.getImages().get(0).getImg_id() + ".jpg");
            load(uri, holder.img, feed.getImages().get(0).getWidth(), feed.getImages().get(0).getHeight());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView img;

        ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.item_img);
        }
    }
}
