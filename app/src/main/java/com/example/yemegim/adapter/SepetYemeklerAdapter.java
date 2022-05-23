package com.example.yemegim.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yemegim.R;
import com.example.yemegim.databinding.SepetCardTasarimiBinding;
import com.example.yemegim.entity.SepetYemekler;
import com.example.yemegim.viewmodel.YemekSepetFragmentViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SepetYemeklerAdapter extends RecyclerView.Adapter<SepetYemeklerAdapter.SepetCardTasarimTutucu> {
    private Context mContext;
    private List<SepetYemekler> sepetYemeklerListesi;
    private YemekSepetFragmentViewModel viewModel;

    public SepetYemeklerAdapter(Context mContext, List<SepetYemekler> sepetYemeklerListesi, YemekSepetFragmentViewModel viewModel) {
        this.mContext = mContext;
        this.sepetYemeklerListesi = sepetYemeklerListesi;
        this.viewModel = viewModel;
    }

    public class SepetCardTasarimTutucu extends RecyclerView.ViewHolder {
        private SepetCardTasarimiBinding tasarim;

        public SepetCardTasarimTutucu(SepetCardTasarimiBinding tasarim) {
            super(tasarim.getRoot());
            this.tasarim = tasarim;
        }
    }

    @NonNull
    @Override
    public SepetCardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        SepetCardTasarimiBinding tasarim = DataBindingUtil.inflate(layoutInflater, R.layout.sepet_card_tasarimi, parent, false);
        return new SepetCardTasarimTutucu(tasarim);
    }

    @Override
    public void onBindViewHolder(@NonNull SepetCardTasarimTutucu holder, int position) {
        SepetYemekler sepetYemek = sepetYemeklerListesi.get(position);
        SepetCardTasarimiBinding t = holder.tasarim;
        t.setSepetYemekNesnesi(sepetYemek);

        Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/" + t.getSepetYemekNesnesi()
                .getYemek_resim_adi()).into(t.imageViewSepetYemekResim);

        t.imageViewSil.setOnClickListener(view -> {
            String silinenName = sepetYemek.getYemek_adi();
            Snackbar.make(view, silinenName + " Silinsin Mi?", Snackbar.LENGTH_LONG)
                    .setAction("EVET", v1 -> {
                        viewModel.sepettenYemekSil(sepetYemek.getSepet_yemek_id(), sepetYemek.getKullanici_adi());
                        Snackbar.make(v1, silinenName + " Silindi.", Snackbar.LENGTH_SHORT).setBackgroundTint(Color.GRAY)
                                .setActionTextColor(Color.WHITE).show();
                    }).setActionTextColor(Color.WHITE).setBackgroundTint(Color.GRAY).setTextColor(Color.WHITE).show();
        });
    }

    @Override
    public int getItemCount() {
        return sepetYemeklerListesi.size();
    }

}
