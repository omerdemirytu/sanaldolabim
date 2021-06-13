package com.student.sanaldolabim.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.student.sanaldolabim.R;
import com.student.sanaldolabim.database.source.DrawerSource;
import com.student.sanaldolabim.model.SDDrawer;

import java.util.ArrayList;
import java.util.List;

public class DrawerAdapter extends ArrayAdapter<SDDrawer> {

    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private final List<SDDrawer> drawers;
    private DrawerSource drawerSource;

    public DrawerAdapter(@NonNull Context context, List<SDDrawer> drawers) {
        super(context, 0, drawers);
        this.context = context;
        this.drawers = drawers;
        this.drawerSource = new DrawerSource(context);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return drawers.size();
    }

    @Override
    public SDDrawer getItem(int position) {
        return drawers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return drawers.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_drawer, null);

            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.delete = (AppCompatButton) convertView.findViewById(R.id.deleteButton);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerSource.delete(drawers.get(position));
                drawers.remove(position);
                notifyDataSetChanged();
            }
        });

        SDDrawer drawer = drawers.get(position);
        if (drawer != null) {
            holder.title.setText(drawer.getName());
        }
        return convertView;
    }

    //View Holder Pattern for better performance
    private static class ViewHolder {
        TextView title;
        AppCompatButton delete;

    }
}
