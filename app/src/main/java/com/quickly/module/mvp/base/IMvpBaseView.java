package com.quickly.module.mvp.base;

public interface IMvpBaseView<P extends IMvpBasePresenter> {

    void setPresenter(P presenter);

    /**
     * Presenter中处理子线程任务完成后，
     * 一般会回到主线程调用View的方法刷新UI，
     * 但如果此时activity已经销毁，
     * 并且没有取消子线程的任务（例如网络请求），
     * 此时去调用View的方法很容易发生空指针，
     * 应该在调用之前判断一下，
     * 因此建议view增加一个isActive()方法，
     * 用于判断当前view是否可见
     * @return
     */
    boolean isActive();
}
