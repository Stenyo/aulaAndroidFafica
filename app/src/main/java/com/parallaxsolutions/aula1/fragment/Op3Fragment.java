package com.parallaxsolutions.aula1.fragment;

import android.app.Fragment;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.parallaxsolutions.aula1.R;

public class Op3Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_op3, container, false);
        Button notificationButton = view.findViewById(R.id.notification_button);


        notificationButton.setOnClickListener(v->{

            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(v.getContext(), "main")
                    .setSmallIcon(R.drawable.ic_drawer)
                    .setSound(alarmSound)
                    .setColor(Color.parseColor("#3f51b5"))
                    .setContentTitle("Titulo da Notificação")
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Corpo da notificação"))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager mNotificationManager =
                    (NotificationManager) v.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(1, mBuilder.build());

        });
        return view;
    }

}
