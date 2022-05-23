package com.example.yemegim.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yemegim.R;

import com.example.yemegim.adapter.SepetYemeklerAdapter;
import com.example.yemegim.databinding.FragmentYemekSepetBinding;
import com.example.yemegim.viewmodel.YemekSepetFragmentViewModel;

public class YemekSepetFragment extends Fragment {
    private FragmentYemekSepetBinding tasarim;
    private YemekSepetFragmentViewModel viewModel;
    String kullanici_adi = "muammer_agtas";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_yemek_sepet, container, false);
        tasarim.setYemekSepetFragment(this);
        tasarim.setYemekSepetToolbarBaslik("YEMEK SEPET");
        viewModel.sepettekiYemeklerListesi.observe(getViewLifecycleOwner(), liste -> {
            SepetYemeklerAdapter sAdapter = new SepetYemeklerAdapter(requireContext(), liste, viewModel);
            tasarim.setSepetYemeklerAdapter(sAdapter);
        });

        return tasarim.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(YemekSepetFragmentViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.sepettekiYemekleriAl(kullanici_adi);
    }
}