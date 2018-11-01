package com.jascal.flora.mvp.binding;

import com.jascal.flora.mvp.PhotoContract;

import dagger.Module;
import dagger.Provides;

@Module
public class PhotoModule {
    private PhotoContract.View view;

    public PhotoModule(PhotoContract.View view) {
        this.view = view;
    }

    @Provides
    public PhotoContract.View inject(){
        return view;
    }
}
