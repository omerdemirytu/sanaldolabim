package com.student.sanaldolabim.activity.create;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.widget.AppCompatButton;

import com.student.sanaldolabim.R;
import com.student.sanaldolabim.activity.BaseActivity;
import com.student.sanaldolabim.database.source.CabinSource;
import com.student.sanaldolabim.database.source.ClothesSource;
import com.student.sanaldolabim.model.SDCabin;
import com.student.sanaldolabim.model.SDClothes;
import com.student.sanaldolabim.model.SDDrawer;
import com.student.sanaldolabim.util.Logger;
import com.student.sanaldolabim.util.Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class CreateCabinActivity extends BaseActivity {

    private ClothesSource clothesSource;
    private CabinSource cabinSource;
    private Map<String, SDClothes> clothes = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cabin);

        cabinSource = new CabinSource(this);
        clothesSource = new ClothesSource(this);

        for (SDClothes clothe : clothesSource.fetch()) {
            clothes.put(clothe.getName(), clothe);
        }

        init();
    }

    private void init() {
        EditText nameEditText = findViewById(R.id.name);

        ImageView imageSectionOne = findViewById(R.id.image_section_one);
        ImageView imageSectionTwo = findViewById(R.id.image_section_two);
        ImageView imageSectionThree = findViewById(R.id.image_section_three);
        ImageView imageSectionFour = findViewById(R.id.image_section_four);
        ImageView imageSectionFive = findViewById(R.id.image_section_five);

        Spinner spinnerSectionOne = findViewById(R.id.spinner_section_one);
        spinnerSectionOne.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, clothes.keySet().toArray()));
        spinnerSectionOne.setOnItemSelectedListener(listener(imageSectionOne));

        Spinner spinnerSectionTwo = findViewById(R.id.spinner_section_two);
        spinnerSectionTwo.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, clothes.keySet().toArray()));
        spinnerSectionTwo.setOnItemSelectedListener(listener(imageSectionTwo));

        Spinner spinnerSectionThree = findViewById(R.id.spinner_section_three);
        spinnerSectionThree.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, clothes.keySet().toArray()));
        spinnerSectionThree.setOnItemSelectedListener(listener(imageSectionThree));

        Spinner spinnerSectionFour = findViewById(R.id.spinner_section_four);
        spinnerSectionFour.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, clothes.keySet().toArray()));
        spinnerSectionFour.setOnItemSelectedListener(listener(imageSectionFour));

        Spinner spinnerSectionFive = findViewById(R.id.spinner_section_five);
        spinnerSectionFive.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, clothes.keySet().toArray()));
        spinnerSectionFive.setOnItemSelectedListener(listener(imageSectionFive));

        AppCompatButton createButton = findViewById(R.id.drawer_save);

        createButton.setOnClickListener(view -> {
            SDCabin cabin = new SDCabin();
            cabin.setName(nameEditText.getText().toString());
            cabin.setSectionOne(clothes.get(spinnerSectionOne.getSelectedItem()));
            cabin.setSectionTwo(clothes.get(spinnerSectionTwo.getSelectedItem()));
            cabin.setSectionThree(clothes.get(spinnerSectionThree.getSelectedItem()));
            cabin.setSectionFour(clothes.get(spinnerSectionFour.getSelectedItem()));
            cabin.setSectionFive(clothes.get(spinnerSectionFive.getSelectedItem()));

            cabinSource.insert(cabin);
            showDialog("Kabin eklendi", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
        });
    }

    private AdapterView.OnItemSelectedListener listener(final ImageView imageView) {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SDClothes c = clothes.get(clothes.keySet().toArray()[i]);
                displayImage(c, imageView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
    }

    private void displayImage(SDClothes clothe, ImageView image) {
        if (clothe.getPhoto() != null) {
            File imgFile = new File(clothe.getPhoto());
            if (imgFile.exists()) {
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), bmOptions);
                image.setImageBitmap(bitmap);
            }
        } else {
            image.setImageBitmap(null);
        }
    }

}
