package com.golive.xess.merchant.personter;

import com.golive.xess.merchant.model.api.ApiService;
import com.orhanobut.logger.Logger;

import javax.inject.Inject;

/**
 * Created by YangChun .
 * on 2017/3/8.
 */

public class PersonalPresenter implements PersonalContract.Persenter {
    private PersonalContract.View view;
    private ApiService apiService;

    @Inject
    public PersonalPresenter(PersonalContract.View view, ApiService apiService) {
        this.view = view;
        this.apiService = apiService;
    }

    @Override
    public void submitEdit() {
        Logger.d("haha");
    }
}
