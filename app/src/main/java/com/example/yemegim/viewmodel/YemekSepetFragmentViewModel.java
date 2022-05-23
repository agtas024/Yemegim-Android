package com.example.yemegim.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yemegim.entity.SepetYemekler;
import com.example.yemegim.repo.YemeklerDaoRepository;

import java.util.List;

public class YemekSepetFragmentViewModel extends ViewModel {
    private YemeklerDaoRepository yrepo = new YemeklerDaoRepository();
    public MutableLiveData<List<SepetYemekler>> sepettekiYemeklerListesi;

    public YemekSepetFragmentViewModel() {
        sepettekiYemeklerListesi = yrepo.sepettekiYemekleriGetir();
    }

    public void sepettenYemekSil(int sepet_yemek_id, String kullanici_adi) {
        yrepo.sepettenYemekSil(sepet_yemek_id, kullanici_adi);
    }

    public void sepettekiYemekleriAl(String kullanici_adi) {
        yrepo.sepettekiYemekleriAl(kullanici_adi);
    }
}
