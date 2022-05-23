package com.example.yemegim.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yemegim.entity.Yemekler;
import com.example.yemegim.repo.YemeklerDaoRepository;

import java.util.List;

public class AnasayfaFragmentViewModel extends ViewModel {
    private YemeklerDaoRepository yrepo = new YemeklerDaoRepository();
    public MutableLiveData<List<Yemekler>> yemeklerListesi;

    public AnasayfaFragmentViewModel() {
        yemekleriYukle();
        yemeklerListesi = yrepo.yemekleriGetir();
    }

    public void yemekleriYukle() {
        yrepo.tumYemekleriAl();
    }
}
