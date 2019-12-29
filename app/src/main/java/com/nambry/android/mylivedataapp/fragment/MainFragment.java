package com.nambry.android.mylivedataapp.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nambry.android.mylivedataapp.R;
import com.nambry.android.mylivedataapp.databinding.FragmentMainBinding;
import com.nambry.android.mylivedataapp.viewmodel.MainViewModel;

// FragmentMainBindingが出ない場合はBuild&実行で確認する
public class MainFragment extends Fragment {
    private MainViewModel viewModel;
    private FragmentMainBinding binding;

    // onCreateの処理は単方向なので本当は不要
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(MainFragment.this).get(MainViewModel.class);
        // データに変更があったらテキストに変更するように促す処理
        viewModel.getLiveData().observe(MainFragment.this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.d(getClass().getSimpleName(), s);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        binding.setLifecycleOwner(MainFragment.this);// 双方向データバインディングを行うために設定
        binding.setViewmodel(viewModel);
        viewModel.fetchText();
        return binding.getRoot();
    }
}
