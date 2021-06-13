package com.student.sanaldolabim.activity.create;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatButton;

import com.student.sanaldolabim.R;
import com.student.sanaldolabim.activity.BaseActivity;
import com.student.sanaldolabim.database.source.DrawerSource;
import com.student.sanaldolabim.model.SDDrawer;

public class CreateDrawerActivity extends BaseActivity {

    private EditText nameEditText;

    private DrawerSource drawerSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_drawer);
        drawerSource = new DrawerSource(this);
        init();
    }

    private void init() {
        AppCompatButton createButton = findViewById(R.id.drawer_save);
        nameEditText = findViewById(R.id.nameEditText);

        createButton.setOnClickListener(view -> {
            SDDrawer drawer = new SDDrawer(nameEditText.getText().toString());
            drawerSource.insert(drawer);

            showDialog("Çekmece eklendi", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
        });
    }

}
