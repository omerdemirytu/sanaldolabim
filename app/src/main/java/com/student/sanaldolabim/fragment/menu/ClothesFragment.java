package com.student.sanaldolabim.fragment.menu;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.student.sanaldolabim.R;
import com.student.sanaldolabim.activity.create.CreateClothesActivity;
import com.student.sanaldolabim.adapter.ClothesAdapter;
import com.student.sanaldolabim.database.source.ClothesSource;
import com.student.sanaldolabim.fragment.BaseFragment;

public class ClothesFragment extends BaseFragment {

    private ClothesSource clothesSource;

    public ClothesFragment() {
        super(R.layout.fragment_clothes);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        clothesSource = new ClothesSource(context);
    }

    @Override
    public String getTitle() {
        return getString(R.string.clothes);
    }

    @Override
    public void onClickBarCreate() {
        Intent intent = new Intent(getActivity(), CreateClothesActivity.class);
        startActivity(intent);
    }

    @Override
    public ArrayAdapter getListAdapter() {
        return new ClothesAdapter(getContext(), clothesSource.fetch());
    }
}
