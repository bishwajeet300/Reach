package com.review.sc;

import android.content.Context;

import dagger.Module;
import dagger.Provides;


@Module
public class ContextModule {

    private Context myContext;


    public ContextModule(Context context){
        this.myContext = context;
    }


    @Provides
    Context context() {
        return myContext;
    }
}
