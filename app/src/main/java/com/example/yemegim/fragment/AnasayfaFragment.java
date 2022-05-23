package com.example.yemegim.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yemegim.R;
import com.example.yemegim.adapter.YemeklerAdapter;

import com.example.yemegim.databinding.FragmentAnasayfaBinding;

import com.example.yemegim.viewmodel.AnasayfaFragmentViewModel;

public class AnasayfaFragment extends Fragment {

    private FragmentAnasayfaBinding tasarim;

    private AnasayfaFragmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_anasayfa, container, false);
        tasarim.setAnasayfaFragment(this);
        tasarim.setAnasayfaToolbarBaslik("YEMEKLER");

        viewModel.yemeklerListesi.observe(getViewLifecycleOwner(), liste -> {
            YemeklerAdapter adapter = new YemeklerAdapter(requireContext(), liste);
            tasarim.setYemeklerAdapter(adapter);
        });
        return tasarim.getRoot();
    }

    public void fabTikla(View view) {
        Navigation.findNavController(view).navigate(R.id.sepetGecis);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        viewModel = new ViewModelProvider(this).get(AnasayfaFragmentViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.yemekleriYukle();
    }
}