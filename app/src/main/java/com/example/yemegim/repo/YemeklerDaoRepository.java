package com.example.yemegim.repo;



import androidx.lifecycle.MutableLiveData;

import com.example.yemegim.entity.CRUDCevap;
import com.example.yemegim.entity.SepetYemeklerCevap;
import com.example.yemegim.entity.SepetYemekler;
import com.example.yemegim.entity.Yemekler;
import com.example.yemegim.entity.YemeklerCevap;
import com.example.yemegim.retrofit.ApiUtils;
import com.example.yemegim.retrofit.YemeklerDaoInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YemeklerDaoRepository {
    private MutableLiveData<List<Yemekler>> yemeklerListesi;
    private MutableLiveData<List<SepetYemekler>> sepetYemeklerListesi;
    private YemeklerDaoInterface ydao;

    public YemeklerDaoRepository() {
        ydao = ApiUtils.getYemeklerDaoInterface();
        yemeklerListesi = new MutableLiveData();
        sepetYemeklerListesi = new MutableLiveData();
    }

    public void sepeteYemekEkle(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi) {
        ydao.sepeteYemekEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {
            }
        });
    }


    public MutableLiveData<List<Yemekler>> yemekleriGetir() {
        return yemeklerListesi;
    }

    public MutableLiveData<List<SepetYemekler>> sepettekiYemekleriGetir() {
        return sepetYemeklerListesi;
    }

    public void sepettenYemekSil(int sepet_yemek_id, String kullanici_adi) {
        ydao.sepettenYemekSil(sepet_yemek_id, kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                sepettekiYemekleriAl(kullanici_adi);
            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {
            }
        });
    }

    public void tumYemekleriAl() {
        ydao.tumYemekler().enqueue(new Callback<YemeklerCevap>() {
            @Override
            public void onResponse(Call<YemeklerCevap> call, Response<YemeklerCevap> response) {
                List<Yemekler> liste = response.body().getYemekler();
                yemeklerListesi.setValue(liste);
            }

            @Override
            public void onFailure(Call<YemeklerCevap> call, Throwable t) {
            }
        });
    }

    public void sepettekiYemekleriAl(String kullanici_adi) {

            ydao.sepettekiYemekleriAl(kullanici_adi).enqueue(new Callback<SepetYemeklerCevap>() {

                @Override
                public void onResponse(Call<SepetYemeklerCevap> call, Response<SepetYemeklerCevap> response) {
                    List<SepetYemekler> sliste = response.body().getSepetYemekler();
                    sepetYemeklerListesi.setValue(sliste);
                }

                @Override
                public void onFailure(Call<SepetYemeklerCevap> call, Throwable t) {
                }
            });
    }
}
