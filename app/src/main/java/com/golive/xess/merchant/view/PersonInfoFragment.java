package com.golive.xess.merchant.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.golive.xess.merchant.R;
import com.golive.xess.merchant.XessConfig;
import com.golive.xess.merchant.base.BaseFragment;
import com.golive.xess.merchant.base.XessApp;
import com.golive.xess.merchant.di.components.DaggerPersonalComponent;
import com.golive.xess.merchant.di.modules.PersonalModule;
import com.golive.xess.merchant.model.api.body.LoginBody;
import com.golive.xess.merchant.model.api.body.StoreBody;
import com.golive.xess.merchant.model.entity.LoginEntity;
import com.golive.xess.merchant.presenter.PersonalContract;
import com.golive.xess.merchant.presenter.PersonalPresenter;
import com.golive.xess.merchant.utils.GlideImageLoader;
import com.golive.xess.merchant.utils.MerchantUtils;
import com.golive.xess.merchant.utils.PictureUtils;
import com.golive.xess.merchant.view.widget.AddressDialog;
import com.golive.xess.merchant.view.widget.DialogErr;
import com.golive.xess.merchant.view.widget.GlideRoundTransform;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;

import java.util.ArrayList;

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
    @BindView(R.id.edit_rl)
    RelativeLayout editRl;
    @BindView(R.id.girl_finish)
    RelativeLayout girlFinish;
    private ArrayList<ImageItem> imageItems;

    @Inject
    PersonalPresenter presenter;

    private String provinceCode = "", cityCode = "" ,headImg= "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, view);
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
        presenter.initViewData(new LoginBody(storeUid,
                MerchantUtils.getPassword(), deviceNo, onlineNo));
    }

    @OnClick(R.id.edit_per_bt)
    void onClickEdit() {
        String name = nicknameEt.getText().toString().trim();
        //
        StoreBody storeBody = new StoreBody();
        storeBody.setStoreUid(storeUid);
        storeBody.setDeviceNo(deviceNo);
        storeBody.setProvince(provinceCode);
        storeBody.setCity(cityCode);
        storeBody.setHeadImg(PictureUtils.bitmapToString(headImg));
        storeBody.setFileSuffix("jpg");
        storeBody.setFileType("I");
        storeBody.setName(name);
        presenter.updateStore(storeBody);
    }

    @OnClick(R.id.imageView)
    void onClickImg() {
        openPhotoAlbum();
    }

    @OnFocusChange(R.id.imageView)
    void onFocusImg(boolean b) {
        if (b) {
            openPhotoAlbum();
        }
    }

    // 打开相册
    void openPhotoAlbum() {
        new AlertDialog.Builder(activity)
                .setItems(new String[]{"拍照", "从相册获取"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ImagePicker imagePicker = ImagePicker.getInstance();
                        imagePicker.setImageLoader(new GlideImageLoader());
                        imagePicker.setMultiMode(true);   //多选
                        imagePicker.setShowCamera(true);  //显示拍照按钮
                        imagePicker.setSelectLimit(1);    //最多选择9张
                        imagePicker.setCrop(true);       //不进行裁剪
                        Intent intent = new Intent(activity, ImageGridActivity.class);
                        startActivityForResult(intent, 100);
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @OnClick(R.id.address_tv)
    void onClickAddress() {
        editAddress();
    }

    @OnFocusChange(R.id.address_tv)
    void onFocusAddress(boolean b) {
        if (b) {
            editAddress();
        }
    }

    // 编辑地址
    void editAddress() {
        AddressDialog mChangeAddressDialog = new AddressDialog(
                activity);
        mChangeAddressDialog.setAddress("北京市", "朝阳区");
        mChangeAddressDialog.show();
        mChangeAddressDialog
                .setAddresskListener(new AddressDialog.OnAddressCListener() {

                    @Override
                    public void onClick(String province, String city ,String pCode,String cCode) {
                        // TODO Auto-generated method stub
                        provinceCode = pCode;
                        cityCode = cCode;
                        addressTv.setText(province+" "+city);

                    }
                });
    }


    ////////////////////PersonalContract.View//////////////////////////

    @Override
    public void showOnFailure(Throwable throwable) {
        new DialogErr(activity,throwable.getMessage()).show();
    }

    @Override
    public void successLoad(LoginEntity loginEntity) {
        if (loginEntity != null) {
            if (XessConfig._VERSION == XessConfig._PERSONAL) {

            } else {
                idTv.setText(loginEntity.getStoreUid() + "");
                nicknameEt.setText(loginEntity.getName());
                provinceCode = loginEntity.getProvince();
                cityCode = loginEntity.getCity();
                addressTv.setText(loginEntity.getAddress());
                String head = loginEntity.getHeadImg();
                Glide.with(activity).load(head).error(R.drawable.ic_head).transform(new GlideRoundTransform(activity)).into(imageView);
            }
        }
    }

    @Override
    public void successUpload(String string) {
        girlFinish.setVisibility(View.VISIBLE);
        editRl.setVisibility(View.GONE);
        girlFinish.postDelayed(new Runnable() {
            @Override
            public void run() {
                editRl.setVisibility(View.VISIBLE);
                girlFinish.setVisibility(View.GONE);
            }
        },1500);

    }

    ////////////////////PersonalContract.View//////////////////////////
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == 100) {
                imageItems = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (imageItems != null && imageItems.size() > 0) {
                    headImg = imageItems.get(0).path;
                    System.out.println(headImg);
                    Glide.with(activity).load(headImg).error(R.drawable.ic_head).transform(new GlideRoundTransform(activity)).into(imageView);
                }
            } else {
                Toast.makeText(activity, "没有选择图片", Toast.LENGTH_SHORT).show();
            }
        } else {

        }
    }
}
