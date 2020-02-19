package com.codesroots.mac.cards.presentaion.Printer;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.codesroots.mac.cards.R;

import sunmi.sunmiui.dialog.DialogCreater;
import sunmi.sunmiui.dialog.EditTextDialog;

/**
 *
 * Created by Administrator on 2017/4/27.
 */

public abstract class BaseActivity extends AppCompatActivity {
    BaseApp baseApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseApp = (BaseApp) getApplication();
    }







}
