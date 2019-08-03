package com.esragungor.biletalma.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.esragungor.biletalma.R;
import com.esragungor.biletalma.adapters.BiletAdapter;
import com.esragungor.biletalma.model.Bilet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BiletlerimFragment extends Fragment {
    ListView lv_biletler;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentbiletlerim, container, false);

        lv_biletler = view.findViewById(R.id.lv_alınan_biletler);


        final ArrayList<Bilet> filmListesi = new ArrayList();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("biletler");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot sh : dataSnapshot.getChildren()) {

                    Bilet movieTicket = sh.getValue(Bilet.class);

                    filmListesi.add(movieTicket);
                   // System.out.println(movieTicket.getFilmAdi());

                    BiletAdapter adapter = new BiletAdapter(getActivity(), filmListesi, inflater);
                    lv_biletler.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;
    }

}

