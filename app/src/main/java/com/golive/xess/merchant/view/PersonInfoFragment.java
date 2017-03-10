package com.golive.xess.merchant.view;

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
import com.golive.xess.merchant.base.BaseFragment;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerPersonalComponent;
import com.golive.xess.merchant.di.modules.PersonalModule;
import com.golive.xess.merchant.presenter.PersonalContract;
import com.golive.xess.merchant.presenter.PersonalPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

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
        presenter.submitEdit();
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
        Toast.makeText(activity," picture ",Toast.LENGTH_SHORT).show();
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
        Toast.makeText(activity," editAddress ",Toast.LENGTH_SHORT).show();
    }


    ////////////////////PersonalContract.View//////////////////////////
    @Override
    public void editOnFailure(Throwable throwable) {

    }

    @Override
    public void successEdit() {
        System.out.println("=======successEdit=======>");
    }
    ////////////////////PersonalContract.View//////////////////////////
}
