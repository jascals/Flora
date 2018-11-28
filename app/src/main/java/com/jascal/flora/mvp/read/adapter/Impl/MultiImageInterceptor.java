package com.jascal.flora.mvp.read.adapter.Impl;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
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
public class MultiImageInterceptor implements Interceptor {
    @Override
    public boolean checkType(News news) {
        return news.getImages().size() > 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mutli_image, viewGroup, false);
        return new MultiImageHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position, News news) {
        MultiImageHolder multiImageHolder = (MultiImageHolder) viewHolder;
        multiImageHolder.desc.setText(news.getDesc());
        multiImageHolder.info.setText(news.getWho() + " created on " + news.getCreatedAt());
        load(Uri.parse(news.getImages().get(0)), multiImageHolder.simpleDraweeView0, 300, 400);
        load(Uri.parse(news.getImages().get(0)), multiImageHolder.simpleDraweeView1, 300, 400);
        if (news.getImages().size() >= 3) {
            load(Uri.parse(news.getImages().get(0)), multiImageHolder.simpleDraweeView2, 300, 400);
        }
    }

    static class MultiImageHolder extends RecyclerView.ViewHolder {
        TextView desc;
        TextView info;
        SimpleDraweeView simpleDraweeView0;
        SimpleDraweeView simpleDraweeView1;
        SimpleDraweeView simpleDraweeView2;

        MultiImageHolder(@NonNull View itemView) {
            super(itemView);
            this.desc = itemView.findViewById(R.id.item_desc);
            this.info = itemView.findViewById(R.id.item_info);
            this.simpleDraweeView0 = itemView.findViewById(R.id.item_img0);
            this.simpleDraweeView1 = itemView.findViewById(R.id.item_img1);
            this.simpleDraweeView2 = itemView.findViewById(R.id.item_img2);
        }
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
}
