package com.student.sanaldolabim.fragment.menu;

import com.student.sanaldolabim.R;
import com.student.sanaldolabim.fragment.BaseFragment;

public class ActivityFragment extends BaseFragment {

    public ActivityFragment() {
        super(R.layout.fragment_activity);
    }
    @Override
    public String getTitle() {
        return getString(R.string.activity);
    }
}
