package com.liszying.fctm.common.proxy;

public interface IHttpCompleted
{
    public void doNotify(String result);
    public void doError(int resID);
}
