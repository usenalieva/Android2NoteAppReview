package com.makhabatusen.android2noteapp.version2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.makhabatusen.android2noteapp.R;
import com.makhabatusen.android2noteapp.version2.App;
import com.makhabatusen.android2noteapp.version2.models.Note;
import com.makhabatusen.android2noteapp.version2.ui.form.FormFragment;

import java.util.List;


public class HomeFragment extends Fragment {
    NavController navController;
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private Note note;
    private boolean toAddNote;
    public static final String KEY_NOTE_HF = "note_hf";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NoteAdapter();
        setHasOptionsMenu(true);
        loadData();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_fragment_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       if (item.getItemId() == R.id.clear_prefs_item) {
           App.getPrefs().clearPrefs();
           openBoard();
       }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.fab).setOnClickListener(v -> {
            toAddNote = true;
            openForm();
        });
        recyclerView = view.findViewById(R.id.recycler_view);
        initList();

           setFragmentListener();

    }

    private void loadData() {
        List<Note> list = App.getAppDatabase().noteDao().getAll();
        adapter.addList(list);
    }


    private void initList() {
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        adapter.setListener(new OnItemOnCLickListener() {
            @Override
            public void onCLick(int pos) {
                toAddNote = false;

                /** OPTION #1 with Bundle **/
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(KEY_NOTE_HF, adapter.getItem(pos));
//                Toast.makeText(requireContext(), "Note position: " + pos, Toast.LENGTH_SHORT).show();
//                     navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
//                navController.navigate(R.id.formFragment, bundle);
                /** OPTION #2 with Safe Args **/
                navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigate(HomeFragmentDirections.actionNavigationHomeToFormFragment().setNote(adapter.getItem(pos)));
            }

            @Override
            public void onLongClick(int pos) {
                // TODO delete
            }
        });
    }

    private void setFragmentListener() {
        getParentFragmentManager().setFragmentResultListener(FormFragment.REQUEST_KEY_FF,
                getViewLifecycleOwner(),
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        note = (Note) result.getSerializable(FormFragment.KEY_NOTE_FF);
                        if (toAddNote)
                        adapter.addItem(note);
                        else adapter.updateItem(note);
                    }
                }
        );
    }

    private void openForm() {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.formFragment);
    }

    private void openBoard() {
        navController.navigate(R.id.boardFragment);
    }


}