package com.jascal.flora.mvp.read.adapter.Impl;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import com.jascal.flora.mvp.read.adapter.Interceptor;
import com.jascal.flora.net.bean.gank.News;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/28 2:47 PM
 * @email jascal@163.com
 */
public class SingleImageInterceptor implements Interceptor {
    @Override
    public boolean checkType(News news) {
        return news.getImages().size() == 1 | news.getType().equals("福利");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_single_image, viewGroup, false);
        return new SingleImageHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position, News news) {
        SingleImageHolder singleImageHolder = (SingleImageHolder) viewHolder;
        if (news.getType().equals("福利")) {
            load(Uri.parse(news.getUrl()), singleImageHolder.simpleDraweeView, 300, 400);
//            singleImageHolder.simpleDraweeView.setImageURI(news.getUrl());
        } else {
            load(Uri.parse(news.getImages().get(0)), singleImageHolder.simpleDraweeView, 300, 400);
//            singleImageHolder.simpleDraweeView.setImageURI(news.getImages().get(0));
        }
        singleImageHolder.desc.setText(news.getDesc());
        singleImageHolder.info.setText(news.getWho() + " created on " + news.getCreatedAt());
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

    static class SingleImageHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView desc;
        TextView info;

        SingleImageHolder(@NonNull View itemView) {
            super(itemView);
            this.simpleDraweeView = itemView.findViewById(R.id.item_img);
            this.desc = itemView.findViewById(R.id.item_desc);
            this.info = itemView.findViewById(R.id.item_info);
        }
    }
}
