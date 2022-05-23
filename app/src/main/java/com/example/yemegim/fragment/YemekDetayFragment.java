package com.example.yemegim.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yemegim.R;
import com.example.yemegim.databinding.FragmentYemekDetayBinding;
import com.example.yemegim.entity.Yemekler;
import com.example.yemegim.viewmodel.YemekDetayFragmentViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class YemekDetayFragment extends Fragment {
    private FragmentYemekDetayBinding tasarim;
    private YemekDetayFragmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_yemek_detay, container, false);

        tasarim.setYemekDetayFragment(this);

        tasarim.setYemekDetayToolbarBaslik("YEMEK DETAY");

        YemekDetayFragmentArgs bundle = YemekDetayFragmentArgs.fromBundle(getArguments());
        Yemekler gelenYemek = bundle.getYemek();
        tasarim.setYemekNesnesi(gelenYemek);
        imageViewYemek(tasarim.getYemekNesnesi().getYemek_resim_adi());
        tasarim.setKullaniciAdi("muammer_agtas");
        return tasarim.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(YemekDetayFragmentViewModel.class);
    }

    public void buttonEkle(int siparisAdedi, int birimFiyat) {
        siparisAdedi++;
        tasarim.setSiparisAdedi(siparisAdedi);
        tasarim.setSiparisAdetliFiyat(siparisAdedi * birimFiyat);
    }

    public void buttonSil(int siparisAdedi, int birimFiyat) {
        if (siparisAdedi > 0) {
            siparisAdedi--;
        } else {
            Snackbar.make(getView(), "Eklenen Adetiniz Yoktur.", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.GRAY).setTextColor(Color.WHITE).show();
        }
        tasarim.setSiparisAdedi(siparisAdedi);
        tasarim.setSiparisAdetliFiyat(siparisAdedi * birimFiyat);
    }

    public void imageViewYemek(String resimUrl) {
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + resimUrl;
        Picasso.get().load(url).into(tasarim.imageViewYemek);
    }

    public void buttonSepeteEkle(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi) {
        if (yemek_siparis_adet > 0) {
            viewModel.sepeteYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi);
            Snackbar.make(getView(), yemek_siparis_adet + " Adet " + yemek_adi + " Sepete Eklendi.", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.GRAY).setTextColor(Color.WHITE).show();
        } else {
            Snackbar.make(getView(), "Adet Seçimi Gerçekleştirmediniz...", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.GRAY).setTextColor(Color.WHITE).show();
        }
    }
}