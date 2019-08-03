package com.esragungor.biletalma.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.esragungor.biletalma.R;
import com.esragungor.biletalma.model.Bilet;

import java.util.ArrayList;

public class BiletAdapter extends BaseAdapter {

    Context context;
    ArrayList<Bilet> biletList;
    LayoutInflater inflater;

    public BiletAdapter(Context context, ArrayList<Bilet> biletList, LayoutInflater inflater) {
        this.context = context;
        this.biletList = biletList;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return biletList.size();
    }

    @Override
    public Object getItem(int position) {
       return biletList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(R.layout.listitem_biletlerim,null);
        ImageView iv_film=view.findViewById(R.id.iv_film_resmi);
        TextView tv_filmAdi=view.findViewById(R.id.tv_film_adi);
        TextView tv_filmNo=view.findViewById(R.id.tv_film_no);
        TextView tv_filmUcret=view.findViewById(R.id.tv_film_ucret);

        Bilet film_Bilet= biletList.get(position);
       //final String film_gorsel=film_Bilet.getFilmGorsel();
        final String film_adi=film_Bilet.getFilmAdi();
        final String film_no=String.valueOf(film_Bilet.getBiletNumarasi());
        final String film_ucret=String.valueOf(film_Bilet.getBiletFiyati());

        Glide.with(context).load(film_Bilet.getFilmGorsel()).into(iv_film);

      //iv_film.setImageResource(film_gorsel);
       tv_filmAdi.setText(film_adi);
        tv_filmNo.setText(String.valueOf(film_no));
        tv_filmUcret.setText(String.valueOf(film_ucret));
        return view;
    }
}
