package com.example.yemegim.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yemegim.R;
import com.example.yemegim.databinding.CardTasarimiBinding;
import com.example.yemegim.entity.Yemekler;
import com.example.yemegim.fragment.AnasayfaFragmentDirections;
import com.squareup.picasso.Picasso;

import java.util.List;

public class YemeklerAdapter extends RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Yemekler> yemeklerListesi;

    public YemeklerAdapter(Context mContext, List<Yemekler> yemeklerListesi) {
        this.mContext = mContext;
        this.yemeklerListesi = yemeklerListesi;
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder {
        private CardTasarimiBinding tasarim;

        public CardTasarimTutucu(CardTasarimiBinding tasarim) {
            super(tasarim.getRoot());
            this.tasarim = tasarim;
        }
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        CardTasarimiBinding tasarim = DataBindingUtil.inflate(layoutInflater, R.layout.card_tasarimi, parent, false);
        return new CardTasarimTutucu(tasarim);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        Yemekler yemek = yemeklerListesi.get(position);
        CardTasarimiBinding t = holder.tasarim;
        t.setYemekNesnesi(yemek);

        Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/" + t.getYemekNesnesi()
                .getYemek_resim_adi()).into(t.imageViewYemekResim);

        t.satirCard.setOnClickListener(view -> {
            AnasayfaFragmentDirections.DetayGecis gecis = AnasayfaFragmentDirections.detayGecis(yemek);
            Navigation.findNavController(view).navigate(gecis);
        });
    }

    @Override
    public int getItemCount() {
        return yemeklerListesi.size();
    }

}
