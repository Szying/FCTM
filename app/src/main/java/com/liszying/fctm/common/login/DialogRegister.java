package com.liszying.fctm.common.login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.liszying.fctm.common.proxy.HttpAction;
import com.liszying.fctm.common.proxy.IHttpCompleted;
import com.liszying.fctm.common.entity.UserInfo;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DialogRegister extends DialogFragment implements IHttpCompleted
{
    View view;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // 定義對話盒物件建構器
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 取得介面實體化物件
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(com.liszying.fctm.R.layout.dialog_register, null);

        final TextView txtAccount = (TextView)view.findViewById(com.liszying.fctm.R.id.reg_account);
        final TextView txtPassword = (TextView)view.findViewById(com.liszying.fctm.R.id.reg_password);
        final TextView txtEmail = (TextView)view.findViewById(com.liszying.fctm.R.id.reg_email);
        final TextView txtPhone = (TextView)view.findViewById(com.liszying.fctm.R.id.reg_phone);
        final TextView txtLabAccount = (TextView)view.findViewById(com.liszying.fctm.R.id.reg_labAccount);
        final TextView txtLabPassword = (TextView)view.findViewById(com.liszying.fctm.R.id.reg_labPassword);

//        外傭管理資料欄位
//        NUMBER	TEXT	帳號
//        PASSWORD	TEXT	密碼
//        EMAIL   TEXT    Email
//        PHONE   TEXT     行動電話
//        LAB_NUMBER TEXT  外傭帳號
//        LAB_PASSWORD TEXT 外傭密碼
//        REG_DATE     TEXT 註冊日期
//        STATUS      TEXT  帳號狀態
//        RESERVED     TEXT 保留

        builder.setPositiveButton(com.liszying.fctm.R.string.register, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //定義好時間字串的格式
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH);

                // 取得現在時間
                String datetime = sdf.format(Calendar.getInstance().getTime());

                String data = txtAccount.getText().toString() + "`" +
                              txtPassword.getText() + "`" +
                              txtEmail.getText() + "`" +
                              txtPhone.getText() + "`" +
                              txtLabAccount.getText() + "`" +
                              txtLabPassword.getText() + "`" +
                              datetime + "`" +
                              "normal`" +
                              "NULL`"
                              ;

                // 將註冊資料傳送給Server
                try
                {
                    // 必須將字串轉成URL編碼，否則空白字元或中文會變成亂碼
                    data = URLEncoder.encode(data, "UTF-8");
                   // Toast.makeText(view.getContext(),data,Toast.LENGTH_LONG).show();
                    HttpAction.insert("member", data, DialogRegister.this,"Users");
                    closeDialog(dialog, false);

                }
                catch (UnsupportedEncodingException e) {}
            }
        });

        builder.setNegativeButton(com.liszying.fctm.R.string.close, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                closeDialog(dialog, true);
            }
        });

        // 設定對話盒介面
        builder.setView(view);
        builder.setCancelable(false);
        return builder.create(); // 產生對話盒實體並回傳
    }

    @Override
    public void doNotify(String result)
    {
        if(result.indexOf("false.") == 0)
        {
            UserInfo.isLogin = false;
            TextView regStatus = (TextView)view.findViewById(com.liszying.fctm.R.id.reg_status);
            regStatus.setText(result.replace("false.", ""));
        }
        else if(result.indexOf("true") == 0)
        {
            UserInfo.isLogin = true;
            Toast.makeText(getActivity(), "註冊成功", Toast.LENGTH_SHORT).show();

            // 取得該看護資料
       //     TextView txtUsername = (TextView)view.findViewById(R.id.reg_account);
        //    HttpAction.query("member", txtUsername.getText().toString(), "", new UserInfo((MainActivity)getActivity()));
            DialogLogin dlg = new DialogLogin();
            closeDialog(getDialog(), true);
            dlg.show(getFragmentManager(), "DialogLogin");
            dismiss();
        }
        else
        {
            UserInfo.isLogin = false;
            TextView regStatus = (TextView)view.findViewById(com.liszying.fctm.R.id.reg_status);
            regStatus.setText(result);
        }
    }

    @Override
    public void doError(int resID)
    {
        TextView loginStatus = (TextView)view.findViewById(com.liszying.fctm.R.id.reg_status);
        loginStatus.setText(getText(resID));
    }

    private void closeDialog(DialogInterface dlg, boolean bClose)
    {
        try
        {
            Field field = dlg.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(dlg, bClose);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
