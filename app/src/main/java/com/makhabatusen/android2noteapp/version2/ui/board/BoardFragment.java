package com.makhabatusen.android2noteapp.version2.ui.board;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.makhabatusen.android2noteapp.R;
import com.makhabatusen.android2noteapp.version2.App;
import com.makhabatusen.android2noteapp.version2.utils.Prefs;


public class BoardFragment extends Fragment {

    private ViewPager2 viewPager;
    private BoardAdapter adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        backPress();
    }

    private void initView(View view) {
        viewPager = view.findViewById(R.id.view_pager_board);
        adapter = new BoardAdapter(getContext());
        viewPager.setAdapter(adapter);
        view.findViewById(R.id.btn_skip).setOnClickListener(v-> {
           close();
        });
        adapter.setButtonClickListener(this::close);
    }

    private void close() {
//        Prefs prefs = new Prefs(requireContext());
//        prefs.saveBoardStatus();
        App.getPrefs().saveBoardStatus();
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
        navController.navigateUp();
    }

    private void backPress() {
        requireActivity().getOnBackPressedDispatcher()
                .addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().finish();
                    }
                });

    }
}