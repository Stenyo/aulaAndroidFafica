package com.parallaxsolutions.aula1.activitys;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.parallaxsolutions.aula1.DAO.UserDAO;
import com.parallaxsolutions.aula1.MainActivity;
import com.parallaxsolutions.aula1.R;
import com.parallaxsolutions.aula1.adapter.UserAdapter;
import com.parallaxsolutions.aula1.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CadastrarActivity extends AppCompatActivity {

    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
    public RecyclerView recUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        recUser = findViewById(R.id.rec_user_list);
        UserDAO userCrud = new UserDAO(this);
        UserAdapter userAdapter = new UserAdapter(userCrud.list());
        recUser.setAdapter(userAdapter);

        recUser.setLayoutManager(this.mLayoutManager);

        TextView text = findViewById(R.id.textView);
        Button next = findViewById(R.id.button_next);


        User user = (User) getIntent().getSerializableExtra("user");
        user.diaCadastro= "19/02/2019";
        text.setText("usuario: " + user.nome + " cadastrado!");

        next.setOnClickListener(v->{
            Intent resultIntent = new Intent();
            resultIntent.putExtra("user", user);
            setResult(RESULT_OK,resultIntent);
            finish();
        });



    }



}
