package com.makhabatusen.android2noteapp.version2.ui.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.makhabatusen.android2noteapp.R;
import com.makhabatusen.android2noteapp.version2.models.Note;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class FormFragment extends Fragment {

    private EditText etNote;
    private Note note;
    public static final String REQUEST_KEY_FF = "rk_form";
    public static final String KEY_NOTE_FF = "note";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etNote = view.findViewById(R.id.et_note);
        view.findViewById(R.id.btn_save).setOnClickListener(v -> {
            save();
        });
    }

    private void save() {
        String textNote = etNote.getText().toString().trim();

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm yyyy/MM/dd", Locale.ROOT);
        String date = dateFormat.format(System.currentTimeMillis());

        note = new Note(textNote,date);


        /** OPTION #1 with Bundle and parentFragmentManager **/

        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_NOTE_FF, note);
        getParentFragmentManager().setFragmentResult(REQUEST_KEY_FF,bundle);

        close();

        /** OPTION #2 with Safe Args **/
//        FormFragmentDirections.actionFormFragmentToNavigationHome().setNote(note);
//        close();
//        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
//        navController.navigate(FormFragmentDirections.actionFormFragmentToNavigationHome().setNote(note));

    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
        navController.navigateUp();
    }
}