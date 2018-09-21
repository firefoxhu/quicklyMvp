package com.quickly.module.mvp.base;

import java.lang.ref.WeakReference;

public abstract class MvpBasePresenterImpl<V extends IMvpBaseView> implements IMvpBasePresenter {

    protected WeakReference<V> mVWeakReference;

    public MvpBasePresenterImpl(V view){
        mVWeakReference = new WeakReference<>(view);
        view.setPresenter(this);
    }

    protected boolean isViewActive(){
        return mVWeakReference != null && mVWeakReference.get().isActive();
    }

    public void detaView(){
        if(mVWeakReference != null) {
            mVWeakReference.clear();
            mVWeakReference = null;
        }
    }





}
