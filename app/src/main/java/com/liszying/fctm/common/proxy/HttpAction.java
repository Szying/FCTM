package com.liszying.fctm.common.proxy;

import com.liszying.fctm.common.proxy.AsyncHttpConnection;
import com.liszying.fctm.common.proxy.IHttpCompleted;

public class HttpAction
{
    public static void login(String type, String account, String password, IHttpCompleted callback, String URLMode)
    {
        try
        {
            new AsyncHttpConnection(callback, "login",type, account, password,URLMode).execute().get();
        }
        catch (Exception e)
        {
            callback.doError(com.liszying.fctm.R.string.login_err);
        }
    }

    public static void login1(String account, String password, IHttpCompleted callback,String URLMode)
    {
        try
        {
            new AsyncHttpConnection(callback, "login", "member", account, password,URLMode).execute().get();
        }
        catch (Exception e)
        {
            callback.doError(com.liszying.fctm.R.string.login_err);
        }
    }

    public synchronized static void logout(String account, IHttpCompleted callback,String URLMode)
    {
        try
        {
            new AsyncHttpConnection(callback, "logout", "member", account, "",URLMode).execute().get();
        }
        catch (Exception e)
        {
            callback.doError(com.liszying.fctm.R.string.logout_err);
        }
    }
    public synchronized static void insert(String type, String data, IHttpCompleted callback,String URLMode)
    {
        try
        {
            new AsyncHttpConnection(callback, "insert", type, "", data,URLMode).execute().get();
        }
        catch (Exception e)
        {
            callback.doError(com.liszying.fctm.R.string.insert_err);
        }
    }

    public synchronized static void update(String type, String number, String data, IHttpCompleted callback,String URLMode)
    {
        try
        {
            new AsyncHttpConnection(callback, "update", type, number, data,URLMode).execute().get();
        }
        catch (Exception e)
        {
            callback.doError(com.liszying.fctm.R.string.update_err);
        }
    }

    public synchronized static void query(String type, String number, String data, IHttpCompleted callback,String URLMode)
    {
        try
        {
            new AsyncHttpConnection(callback, "query", type, number, data,URLMode).execute().get();
        }
        catch (Exception e)
        {
            callback.doError(com.liszying.fctm.R.string.query_err);
        }
    }

    public synchronized static void delete(String type, String number, IHttpCompleted callback,String URLMode)
    {
        try
        {
            new AsyncHttpConnection(callback, "delete", type, number, "",URLMode).execute().get();
        }
        catch (Exception e)
        {
            callback.doError(com.liszying.fctm.R.string.delete_err);
        }
    }

}



