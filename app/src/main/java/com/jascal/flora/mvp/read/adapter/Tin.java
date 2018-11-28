package com.jascal.flora.mvp.read.adapter;

import com.jascal.flora.net.bean.gank.News;

import java.util.List;

/**
 * @author ihave4cat
 * @describe TODO
 * @data on 2018/11/28 2:56 PM
 * @email jascal@163.com
 */
public class Tin {
    private List<News> news;

    Tin(Builder builder) {
        this.news = builder.news;
    }

    public List<News> getNews() {
        return news;
    }

    public static class Builder {
        private List<News> news;

        public Builder setDate(List<News> data) {
            this.news = data;
            return this;
        }

        public Builder append(List<News> data) {
            if(data.size()==0){
                return this;
            }
            if (this.news == null) {
                this.news = data;
            } else {
                for (int i = 0; i < data.size(); i++) {
                    this.news.add(data.get(i));
                }
            }
            return this;
        }

        public Tin build() {
            return new Tin(this);
        }
    }
}
