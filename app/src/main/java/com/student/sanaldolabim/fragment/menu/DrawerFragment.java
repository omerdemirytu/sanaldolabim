package com.student.sanaldolabim.fragment.menu;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.student.sanaldolabim.R;
import com.student.sanaldolabim.activity.create.CreateDrawerActivity;
import com.student.sanaldolabim.adapter.DrawerAdapter;
import com.student.sanaldolabim.database.source.DrawerSource;
import com.student.sanaldolabim.fragment.BaseFragment;

public class DrawerFragment extends BaseFragment {

    private DrawerSource drawerSource;

    public DrawerFragment() {
        super(R.layout.fragment_drawer);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        drawerSource = new DrawerSource(context);
    }

    @Override
    public String getTitle() {
        return getString(R.string.drawer);
    }

    @Override
    public void onClickBarCreate() {
        Intent intent = new Intent(getActivity(), CreateDrawerActivity.class);
        startActivity(intent);
    }

    @Override
    public ArrayAdapter getListAdapter() {
        return new DrawerAdapter(getContext(), drawerSource.fetch());
    }
}
