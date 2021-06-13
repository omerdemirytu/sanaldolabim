package com.student.sanaldolabim.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.FileProvider;

import com.student.sanaldolabim.R;
import com.student.sanaldolabim.database.source.CabinSource;
import com.student.sanaldolabim.model.SDCabin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CabinAdapter extends ArrayAdapter<SDCabin> {

    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private final List<SDCabin> cabins;
    private CabinSource cabinSource;

    public CabinAdapter(@NonNull Context context, List<SDCabin> cabins) {
        super(context, 0, cabins);
        this.context = context;
        this.cabins = cabins;
        this.cabinSource = new CabinSource(context);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cabins.size();
    }

    @Override
    public SDCabin getItem(int position) {
        return cabins.get(position);
    }

    @Override
    public long getItemId(int position) {
        return cabins.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_cabin, null);

            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.delete = (AppCompatButton) convertView.findViewById(R.id.deleteButton);
            holder.share = convertView.findViewById(R.id.shareButton);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cabinSource.delete(cabins.get(position));
                cabins.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.share.setOnClickListener(view -> {
            ArrayList<Uri> files = new ArrayList<Uri>();

            for(String path : cabins.get(position).getFilePaths()) {
                if(path!=null){
                    File file = new File(path);
                    Uri photoURI = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);
                    files.add(photoURI);
                }
            }

            final Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
            intent.setType("*/*");
            intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, files);
            context.startActivity(Intent.createChooser(intent, "Paylas"));
        });

        SDCabin cabin = cabins.get(position);
        if (cabin != null) {
            holder.title.setText(cabin.getName());
        }
        return convertView;
    }

    //View Holder Pattern for better performance
    private static class ViewHolder {
        TextView title;
        AppCompatButton delete;
        AppCompatButton share;
    }
}
