package com.parallaxsolutions.aula1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parallaxsolutions.aula1.DAO.UserDAO;
import com.parallaxsolutions.aula1.activitys.CadastrarActivity;
import com.parallaxsolutions.aula1.activitys.MenuActivity;
import com.parallaxsolutions.aula1.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public static final int LOGIN_REQUEST = 5;
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText nome = findViewById(R.id.edit_nome);
        EditText email = findViewById(R.id.edit_email);
        EditText senha = findViewById(R.id.edit_senha);
        EditText calendar = findViewById(R.id.edit_nasc);
        TextView shareTxt = findViewById(R.id.share_textView);
        Button enviar = findViewById(R.id.button_enviar);
        Button btnCalen = findViewById(R.id.button_calendar);
        Button btnLigar = findViewById(R.id.button_call);
        Button btnMenu = findViewById(R.id.menu_button);


        enviar.setOnClickListener(v -> {
            new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog)
                    .setTitle("Tem certeza?")
                    .setMessage("Você confirma o envio das informações para a outra activity?")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            User user = new User();
                            user.nome = nome.getText().toString();
                            user.email = email.getText().toString();
                            user.senha = senha.getText().toString();
                            user.diaCadastro = "21/05/2019";

                            UserDAO crudUser = new UserDAO(v.getContext());
                            String result = crudUser.insereDado(user);
                            Toast.makeText(getBaseContext(),result,Toast.LENGTH_LONG).show();

                            Intent i = new Intent(v.getContext(), CadastrarActivity.class);
                            i.putExtra("user", user);
                            startActivityForResult(i, LOGIN_REQUEST);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(getBaseContext(),"Você cancelou o envio",Toast.LENGTH_LONG).show();
                        }
                    })
                    .show();

        });
        btnMenu.setOnClickListener( v->{
            Intent i = new Intent(v.getContext(), MenuActivity.class);
            startActivity(i);
        });
        shareTxt.setOnClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "AQUI FICA O TEXTO A SER COMPARTILHADO");
            sendIntent.setType("text/plain");
            v.getContext().startActivity(sendIntent);
        });

        btnLigar.setOnClickListener(v -> {
            Uri uri = Uri.parse("tel:12345");
            Intent callIntent = new Intent(Intent.ACTION_CALL, uri);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            v.getContext().startActivity(callIntent);
        });
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt", "BR"));
                String date = sdf.format(myCalendar.getTime());
                calendar.setText(date);
            }

        };

        btnCalen.setOnClickListener(v->{
            new DatePickerDialog(v.getContext(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == LOGIN_REQUEST && resultCode == RESULT_OK){
            User user = (User)data.getExtras().getSerializable("user");
            Toast.makeText(getBaseContext(),"Usuario " + user.nome + " logado em " + user.diaCadastro,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("debug","onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("debug","onResume");
    }
}
