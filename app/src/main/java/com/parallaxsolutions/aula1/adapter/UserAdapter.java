package com.parallaxsolutions.aula1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parallaxsolutions.aula1.R;
import com.parallaxsolutions.aula1.models.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List<User> dataUser;


    public UserAdapter(List<User> dataUser) {
        this.dataUser = dataUser;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_user, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(dataUser.get(position));
    }

    @Override
    public int getItemCount() {
        return dataUser.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView nome;
        private final TextView email;
        private final TextView data;

        public ViewHolder(View itemView) {
            super(itemView);
            nome = (TextView) itemView.findViewById(R.id.textView_nome);
            email = (TextView) itemView.findViewById(R.id.textView_email);
            data = (TextView) itemView.findViewById(R.id.textView_data);

        }

        public void bind(final User user) {
            nome.setText(user.nome);
            email.setText(user.email);
            data.setText(user.diaCadastro);
        }
    }

}
