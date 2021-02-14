package com.makhabatusen.android2noteapp.version2.ui.board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.button.MaterialButton;
import com.makhabatusen.android2noteapp.R;

public class  BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {
    private OnButtonClickListener buttonClickListener;

    public void setButtonClickListener(OnButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

    private Context mContext;

    private String[] titles = {"Note 27", "Simple", "Powerful", "Secure", "Cloud-Based"};

    private String[] descriptions = {
            "The world's best notepad app. It is free and secure.",
            "Note 27  is a simple and awesome notepad app.",
            "Note 27 has no limits on the size of your notes.",
            "Note 27 keeps your notes safe from hacker attacks.",
            "Note 27 lets you access your notes from multiple devices."
    };

    private int[] icons = {
            R.raw.a_notepad,
            R.raw.a_simple,
            R.raw.a_infinity,
            R.raw.a_security_scan,
            R.raw.a_cloud};



    public BoardAdapter(Context mContext) {
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pager_board,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LottieAnimationView lottieAnimationView;
        private TextView tvTitle;
        private TextView tvtDesc;
        private MaterialButton btnLogin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lottieAnimationView = itemView.findViewById(R.id.av_lottie);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvtDesc = itemView.findViewById(R.id.tv_desc);
            btnLogin = itemView.findViewById(R.id.btn_login);
            btnLogin.setOnClickListener(v->{
                buttonClickListener.onCLick();
            });

        }


        public void bind(int position) {

            lottieAnimationView.setAnimation(icons[position]);
            tvTitle.setText(titles[position]);
            tvtDesc.setText(descriptions[position]);

            if (position == (titles.length-1))
                btnLogin.setVisibility(View.VISIBLE);
            else btnLogin.setVisibility(View.GONE);
        }
    }
}
