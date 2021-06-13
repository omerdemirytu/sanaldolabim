package com.student.sanaldolabim.activity.create;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.student.sanaldolabim.R;
import com.student.sanaldolabim.activity.BaseActivity;
import com.student.sanaldolabim.database.source.ClothesSource;
import com.student.sanaldolabim.database.source.DrawerSource;
import com.student.sanaldolabim.model.SDClothes;
import com.student.sanaldolabim.model.SDDrawer;
import com.student.sanaldolabim.util.Logger;
import com.student.sanaldolabim.util.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CreateClothesActivity extends BaseActivity {

    private ClothesSource clothesSource;
    private DrawerSource drawerSource;
    private Map<String, SDDrawer> drawers = new HashMap<>();
    public static final int PICK_IMAGE = 1;
    public String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_clothes);

        clothesSource = new ClothesSource(this);
        drawerSource = new DrawerSource(this);
        for (SDDrawer drawer : drawerSource.fetch()) {
            drawers.put(drawer.getName(), drawer);
        }

        init();
    }

    private void init() {
        Spinner drawerSpinner = findViewById(R.id.drawer);
        drawerSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, drawers.keySet().toArray()));

        Spinner typeSpinner = findViewById(R.id.type);
        typeSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, SDClothes.getClothTypes()));

        Spinner colorSpinner = findViewById(R.id.color);
        colorSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, SDClothes.getColors()));

        Spinner patternSpinner = findViewById(R.id.pattern);
        patternSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, SDClothes.getPatterns()));

        DatePicker purchaseDatePicker = findViewById(R.id.purchase_date);
        EditText priceEditText = findViewById(R.id.price);
        EditText nameEditText = findViewById(R.id.name);

        AppCompatButton createButton = findViewById(R.id.drawer_save);

        createButton.setOnClickListener(view -> {
            SDClothes clothe = new SDClothes();
            clothe.setName(nameEditText.getText().toString());
            clothe.setClotheType(SDClothes.ClotheType.valueOf((String) typeSpinner.getSelectedItem()));
            clothe.setColor(SDClothes.ClotheColor.valueOf((String) colorSpinner.getSelectedItem()));
            clothe.setPattern(SDClothes.ClothePattern.valueOf((String) patternSpinner.getSelectedItem()));
            clothe.setPurchaseDate(Utils.getDateFromDatePicker(purchaseDatePicker));
            clothe.setPhoto(imagePath);
            try {
                clothe.setPrice(Double.valueOf(priceEditText.getText().toString()));
            } catch (Exception e) {
                Logger.d("Error " + e);
            }
            clothe.setSdDrawer(drawers.get(drawerSpinner.getSelectedItem()));
            clothesSource.insert(clothe);
            showDialog("Kıyafet eklendi", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
        });

        AppCompatButton pickImageButton = findViewById(R.id.pick_image);
        pickImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {

            Uri selectedImage = data.getData();
            File finalFile = new File(getRealPathFromURI(selectedImage));
            imagePath = finalFile.getAbsolutePath();
        }
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }
}
