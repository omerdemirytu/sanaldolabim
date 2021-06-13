package com.student.sanaldolabim.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.student.sanaldolabim.R;

public class BaseFragment extends Fragment {

    public ListView listView = null;

    public BaseFragment(int layoutId) {
        super(layoutId);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView titleBarText = getView().findViewById(R.id.title_bar_text);
        titleBarText.setText(getTitle());

        TextView titleBarCreate = getView().findViewById(R.id.title_bar_create);
        titleBarCreate.setOnClickListener(view1 -> onClickBarCreate());

        listView = getView().findViewById(R.id.list);
        listView.setAdapter(getListAdapter());
    }

    public String getTitle() {
        return null;
    }

    public void onClickBarCreate() {
    }

    public void update() {
        listView.setAdapter(getListAdapter());
    }

    public ArrayAdapter getListAdapter() {
        return null;
    }

}
