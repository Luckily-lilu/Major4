package com.ll.core;

public abstract class Repository <M extends IModel>  {
    /**
     * 数据业务
     */
    protected M mModel;

    /**
     * 创建业务实体
     */
    protected abstract void createModel();

    public Repository() {
        createModel();
    }

    /**
     * 释放业务实体
     */
    protected void releaseModel(){
        if (mModel != null){
            mModel = null;
        }
    }
}
