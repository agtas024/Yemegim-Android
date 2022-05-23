package com.example.yemegim.retrofit;

import com.example.yemegim.entity.CRUDCevap;
import com.example.yemegim.entity.SepetYemeklerCevap;
import com.example.yemegim.entity.YemeklerCevap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface YemeklerDaoInterface {

    @POST("sepeteYemekEkle.php")
    @FormUrlEncoded
    Call<CRUDCevap> sepeteYemekEkle(@Field("yemek_adi") String yemek_adi, @Field("yemek_resim_adi") String yemek_resim_adi,
                                    @Field("yemek_fiyat") int yemek_fiyat, @Field("yemek_siparis_adet") int yemek_siparis_adet,
                                    @Field("kullanici_adi") String kullanici_adi);

    @GET("tumYemekleriGetir.php")
    Call<YemeklerCevap> tumYemekler();

    @POST("sepettenYemekSil.php")
    @FormUrlEncoded
    Call<CRUDCevap> sepettenYemekSil(@Field("sepet_yemek_id") int sepet_yemek_id, @Field("kullanici_adi") String kullanici_adi);
/*
    @GET("sepettekiYemekleriGetir.php")
    Call<SepetYemeklerCevap> sepettekiYemekleriAl(@Query("kullanici_adi") String kullanici_adi);
*/
    @POST("sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    Call<SepetYemeklerCevap> sepettekiYemekleriAl(@Field("kullanici_adi") String kullanici_adi);
}
