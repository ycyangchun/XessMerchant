package com.golive.xess.merchant.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.XessConfig;
import com.golive.xess.merchant.base.BaseFragment;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerPersonalComponent;
import com.golive.xess.merchant.di.modules.PersonalModule;
import com.golive.xess.merchant.model.api.ApiService;
import com.golive.xess.merchant.model.api.body.LoginBody;
import com.golive.xess.merchant.model.api.body.UserBody;
import com.golive.xess.merchant.model.entity.LoginEntity;
import com.golive.xess.merchant.model.entity.UserInfo;
import com.golive.xess.merchant.presenter.PersonalContract;
import com.golive.xess.merchant.presenter.PersonalPresenter;
import com.golive.xess.merchant.utils.Base64Util;
import com.golive.xess.merchant.utils.Des3Util;
import com.golive.xess.merchant.utils.SharedPreferencesUtils;
import com.golive.xess.merchant.view.widget.ChangeAddressDialog;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by YangChun .
 * on 2017/3/7.
 * 个人信息
 */

public class PersonInfoFragment extends BaseFragment implements PersonalContract.View {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.id_tv)
    TextView idTv;
    @BindView(R.id.nickname_et)
    EditText nicknameEt;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.edit_per_bt)
    Button editBt;

    @Inject
    PersonalPresenter presenter;

    private boolean isEdit = false;// 可控件编辑

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DaggerPersonalComponent.builder()
                .netComponent(XessApp.get(activity).getNetComponent())
                .personalModule(new PersonalModule(this))
                .build().inject(this);
//        presenter.submitEdit();
        presenter.initViewData(new LoginBody(storeUid,
                SharedPreferencesUtils.getString("password"),deviceNo));
    }

    @OnClick(R.id.edit_per_bt)
    void onClickEdit(){
        if(!isEdit){
            editBt.setText(activity.getString(R.string.finish_s));
        } else {
            editBt.setText(activity.getString(R.string.editor_info_s));
        }
        isEdit = !isEdit;
        //控件是否可以 编辑
        nicknameEt.setEnabled(isEdit);
        imageView.setFocusable(isEdit);
        imageView.setFocusableInTouchMode(isEdit);
        addressTv.setFocusable(isEdit);
        addressTv.setFocusableInTouchMode(isEdit);

    }

    @OnClick(R.id.imageView)
    void onClickImg(){
        if(isEdit){
            openPhotoAlbum();
        }
    }

    @OnFocusChange(R.id.imageView)
    void onFocusImg(boolean b){
        if(isEdit && b){
            openPhotoAlbum();
        }
    }

    // 打开相册
    void openPhotoAlbum(){
        new AlertDialog.Builder(activity)
                .setItems(new String[]{"拍照", "从相册获取"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(activity," picture ",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @OnClick(R.id.address_tv)
    void onClickAddress(){
        if(isEdit){
            editAddress();
        }
    }

    @OnFocusChange(R.id.address_tv)
    void onFocusAddress(boolean b){
        if(isEdit && b){
            editAddress();
        }
    }
    // 编辑地址
    void editAddress(){
        ChangeAddressDialog mChangeAddressDialog = new ChangeAddressDialog(
                activity);
        mChangeAddressDialog.setAddress("北京", "朝阳区");
        mChangeAddressDialog.show();
        mChangeAddressDialog
                .setAddresskListener(new ChangeAddressDialog.OnAddressCListener() {

                    @Override
                    public void onClick(String province, String city) {
                        // TODO Auto-generated method stub
                        Toast.makeText(activity,
                                province + "-" + city,
                                Toast.LENGTH_LONG).show();
                    }
                });

    }


    ////////////////////PersonalContract.View//////////////////////////
    @Override
    public void editOnFailure(Throwable throwable) {

    }

    @Override
    public void successEdit() {
        System.out.println("=======successEdit=======>");
    }

    @Override
    public void showOnFailure(Throwable throwable) {

    }

    @Override
    public void successLoad(LoginEntity loginEntity) {
        if(loginEntity != null){
            if(XessConfig._VERSION == XessConfig._PERSONAL) {

            } else {
                idTv.setText(loginEntity.getStoreUid()+"");
                nicknameEt.setText(loginEntity.getStoreAlias());
                addressTv.setText(loginEntity.getProvince());
            }
        }
    }

    ////////////////////PersonalContract.View//////////////////////////
}
