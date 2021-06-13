package com.student.sanaldolabim.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.student.sanaldolabim.R;
import com.student.sanaldolabim.model.SDClothes;
import com.student.sanaldolabim.model.SDDrawer;
import com.student.sanaldolabim.util.Logger;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

public class ClothesAdapter extends ArrayAdapter<SDClothes> {

    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private final List<SDClothes> clothes;

    public ClothesAdapter(@NonNull Context context, List<SDClothes> clothes) {
        super(context, 0, clothes);
        this.context = context;
        this.clothes = clothes;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return clothes.size();
    }

    @Override
    public SDClothes getItem(int position) {
        return clothes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return clothes.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_clothes, null);

            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.name);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            holder.color = (TextView) convertView.findViewById(R.id.color);
            holder.pattern = (TextView) convertView.findViewById(R.id.pattern);
            holder.purchaseDate = (TextView) convertView.findViewById(R.id.purchase_date);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.drawerName = (TextView) convertView.findViewById(R.id.drawer_name);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SDClothes clothe = clothes.get(position);
        if (clothe != null) {
            holder.name.setText(clothe.getName());
            holder.type.setText(clothe.getClotheType().toString());
            holder.color.setText(clothe.getColor().toString());
            holder.pattern.setText(clothe.getPattern().toString());
            holder.purchaseDate.setText(clothe.getPurchaseDate().toString());
            holder.price.setText(String.valueOf(clothe.getPrice()));
            holder.drawerName.setText(clothe.getSdDrawer() != null ? clothe.getSdDrawer().getName() : null);
            if (clothe.getPhoto() != null) {
                File imgFile = new File(clothe.getPhoto());
                if (imgFile.exists()) {
                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), bmOptions);
                    holder.image.setImageBitmap(bitmap);
                }
            } else {
                holder.image.setImageBitmap(null);
            }
        }
        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView type;
        TextView color;
        TextView pattern;
        TextView purchaseDate;
        TextView price;
        TextView drawerName;
        ImageView image;
    }

}
