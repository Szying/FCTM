package com.liszying.fctm.common.entity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.liszying.fctm.common.proxy.GetInfo;
import com.liszying.fctm.common.proxy.IHttpCompleted;

public class MemberInfo extends Fragment implements IHttpCompleted {
    View view;
    private String _id;

    // NUMBER	TEXT	帳號（電話）
    private String number;

    // PASSWORD	TEXT	密碼
    private String password;

    // EMAIL	TEXT	電子信箱
    private String email;

    // PHONE	TEXT	連絡電話
    private String phone;

    // BIRTH	TEXT	外傭帳號
    private String Lab_Account;

    // EXP_DATA	TEXT	外傭密碼
    private String Lab_Password;

    // REG_DATE	TEXT	註冊日期
    private String regDate;

    // STATUS	TEXT	帳號狀態
    private String status;

    // RESERVED	TEXT	保留
    private String reserved;

    public void parse(String data) {
        if(GetInfo.result == null || GetInfo.result.length() < 5) return;
        Log.w("data", "parse: " + data);
        String[] datas = data.split("`");
        if(datas.length < 10) return;

        set_id(datas[0]);
        setNumber(datas[1]);
        setPassword(datas[2]);
        setEmail(datas[3]);
        setPhone(datas[4]);
        setLab_Account(datas[5]);
        setLab_Password(datas[6]);
        setRegDate(datas[7]);
        setStatus(datas[8]);
        setReserved(datas[9]);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(com.liszying.fctm.R.layout.userinfo, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        parse(GetInfo.result);

        final TextView txtAccount = (TextView)getActivity().findViewById(com.liszying.fctm.R.id.reg_account);
        final TextView txtPassword = (TextView) getActivity().findViewById(com.liszying.fctm.R.id.reg_password);
        final TextView txtEmail = (TextView) getActivity().findViewById(com.liszying.fctm.R.id.reg_email);
        final TextView txtPhone = (TextView)getActivity().findViewById(com.liszying.fctm.R.id.reg_phone);
        final TextView txtLabAccount = (TextView) getActivity().findViewById(com.liszying.fctm.R.id.reg_labAccount);
        final TextView txtLabPassword = (TextView) getActivity().findViewById(com.liszying.fctm.R.id.reg_labPassword);
        final TextView txtStatus = (TextView) getActivity().findViewById(com.liszying.fctm.R.id.reg_status);

        txtAccount.setText(number);
        txtPassword.setText(password);
        txtEmail.setText(email);
        txtPhone.setText(phone);
        txtLabAccount.setText(Lab_Account);
        txtLabPassword.setText(Lab_Password);
        txtStatus.setText(regDate);
    }

    @Override
    public void doNotify(String result) {
    //  Toast.makeText(getActivity(),"MemberInfo -result:"+result,Toast.LENGTH_LONG).show();
        TextView txtStatus = (TextView) getActivity().findViewById(com.liszying.fctm.R.id.reg_status);
        txtStatus.setText(result);
    }

    @Override
    public void doError(int resID) {
        Toast.makeText(getActivity(),"doNotify-member"+resID,Toast.LENGTH_LONG).show();
    }
//    @Override
//    public void doNotify(String result)
//    {
//        if(result.indexOf("false.") == 0)
//        {
//            UserInfo.isLogin = false;
//            TextView regStatus = (TextView)view.findViewById(R.id.reg_status);
//            regStatus.setText(result.replace("false.", ""));
//        }
//        else if(result.indexOf("true") == 0)
//        {
//            UserInfo.isLogin = true;
//            Toast.makeText(getActivity(), "註冊成功", Toast.LENGTH_SHORT).show();
//
//            // 取得該看護資料
//            //     TextView txtUsername = (TextView)view.findViewById(R.id.reg_account);
//            //    HttpAction.query("member", txtUsername.getText().toString(), "", new UserInfo((MainActivity)getActivity()));
////            DialogLogin dlg = new DialogLogin();
////            closeDialog(getDialog(), true);
////            dlg.show(getFragmentManager(), "DialogLogin");
////            dismiss();
//
//
//        }
//        else
//        {
//            UserInfo.isLogin = false;
//            TextView regStatus = (TextView)view.findViewById(R.id.reg_status);
//            regStatus.setText(result);
//        }
//    }
//
//    @Override
//    public void doError(int resID)
//    {
//        TextView loginStatus = (TextView)view.findViewById(R.id.reg_status);
//        loginStatus.setText(getText(resID));
//    }
//
//    private void closeDialog(DialogInterface dlg, boolean bClose)
//    {
//        try
//        {
//            Field field = dlg.getClass().getSuperclass().getDeclaredField("mShowing");
//            field.setAccessible(true);
//            field.set(dlg, bClose);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

    public String get_id() {
        return _id;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getLab_Account() {
        return Lab_Account;
    }

    public String getLab_Password() {
        return Lab_Password;
    }

    public String getRegDate() {
        return regDate;
    }

    public String getStatus() {
        return status;
    }

    public String getReserved() {
        return reserved;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLab_Account(String lab_Account) {
        Lab_Account = lab_Account;
    }

    public void setLab_Password(String lab_Password) {
        Lab_Password = lab_Password;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReserved(String reserved) {
        this.reserved = reserved;
    }
}
