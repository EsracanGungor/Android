package com.esragungor.biletalma.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esragungor.biletalma.R;
import com.esragungor.biletalma.activities.MainActivity;
import com.esragungor.biletalma.model.Bilet;

import java.util.ArrayList;
import java.util.Random;


public class BiletSatinAlmaFragment extends Fragment {
    Button btn_satinal;
    ImageView movie_image ;
    TextView tv_movie_Adi;
    TextView tv_movie_saat ;
    TextView tv_movie_ucret;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bilet_satinal, container, false);

         movie_image = view.findViewById(R.id.iv_biletsatinal_film);
         tv_movie_Adi = view.findViewById(R.id.tv_biletsatinal_adi);
         tv_movie_saat = view.findViewById(R.id.tv_biletsatinal_no);
         tv_movie_ucret = view.findViewById(R.id.tv_biletsatinal_ucreti);
         btn_satinal=view.findViewById(R.id.btn_satinalma);



        if (getActivity()!=null) {
            ArrayList<Bilet> biletlerim = new ArrayList<>();
            final ArrayList<Bilet> satinalinanBiletlerim=((MainActivity) getActivity()).satinAlinanBiletlerList;
            final Bilet bilet=GetRandomBilet(biletlerim);

      // movie_image.setImageResource(bilet.getFilmGorsel());
       tv_movie_Adi.setText(String.valueOf(bilet.getFilmAdi()));
       tv_movie_saat.setText(String.valueOf(bilet.getBiletNumarasi()));
       tv_movie_ucret.setText(String.valueOf(bilet.getBiletFiyati()));

            btn_satinal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((MainActivity) getActivity()).satinAlinanBiletlerList.add(bilet);
                    Toast.makeText(getActivity(), "Biletiniz oluşturulmuştur.", Toast.LENGTH_LONG).show();

                }
            });
        }
        return view;
    }

    public Bilet GetRandomBilet(ArrayList<Bilet> biletArrayList){
    return biletArrayList.get(new Random().nextInt(biletArrayList.size()));

}}




