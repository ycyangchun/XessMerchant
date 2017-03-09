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

import com.golive.xess.merchant.R;
import com.golive.xess.merchant.base.BaseFragment;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerPersonalComponent;
import com.golive.xess.merchant.di.modules.PersonalModule;
import com.golive.xess.merchant.personter.PersonalContract;
import com.golive.xess.merchant.personter.PersonalPresenter;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        nicknameEt.setEnabled(isEdit);

    }

    @OnClick(R.id.imageView)
    void onClickImg(){
        if(isEdit){
            Logger.d("图片"+isEdit);
        }
    }

    @OnClick(R.id.address_tv)
    void onClickAddress(){
        if(isEdit){
            Logger.d("地址"+isEdit);
        }
    }



    ////////////////////PersonalContract.View//////////////////////////
    @Override
    public void editOnFailure(Throwable throwable) {

    }

    @Override
    public void successEdit() {

    }
    ////////////////////PersonalContract.View//////////////////////////
}
