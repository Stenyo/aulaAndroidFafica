package com.parallaxsolutions.aula1.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.parallaxsolutions.aula1.R;

public class Op1Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_op1, container, false);
        Button enviar = view.findViewById(R.id.button_site);


        enviar.setOnClickListener(v->{
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setData(Uri.parse("http://google.com"));
            v.getContext().startActivity(i);
        });
        return view;
    }

}
