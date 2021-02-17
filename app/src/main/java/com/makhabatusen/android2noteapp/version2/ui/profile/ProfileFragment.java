package com.makhabatusen.android2noteapp.version2.ui.profile;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.makhabatusen.android2noteapp.R;


public class ProfileFragment extends Fragment {
    private ImageView ivAvatar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    ivAvatar = view.findViewById(R.id.iv_avatar);

        ivAvatar.setOnClickListener(v -> {
            getContent.launch("image/*");
        });

    }

    ActivityResultLauncher<String> getContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            (Uri result) -> {
                Glide.with(requireActivity())
                        .load(result)
                        .centerCrop()
                        .circleCrop()
                        //   .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                        .into(ivAvatar);

            }
    );

}