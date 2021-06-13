package com.student.sanaldolabim.fragment.menu;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.student.sanaldolabim.R;
import com.student.sanaldolabim.activity.create.CreateCabinActivity;
import com.student.sanaldolabim.adapter.CabinAdapter;
import com.student.sanaldolabim.database.source.CabinSource;
import com.student.sanaldolabim.fragment.BaseFragment;

public class CabinFragment extends BaseFragment {

    private CabinSource cabinSource;

    public CabinFragment() {
        super(R.layout.fragment_cabin);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        cabinSource = new CabinSource(context);
    }

    @Override
    public String getTitle() {
        return getString(R.string.cabin);
    }


    @Override
    public void onClickBarCreate() {
        Intent intent = new Intent(getActivity(), CreateCabinActivity.class);
        startActivity(intent);
    }

    @Override
    public ArrayAdapter getListAdapter() {
        return new CabinAdapter(getContext(), cabinSource.fetch());
    }
}
