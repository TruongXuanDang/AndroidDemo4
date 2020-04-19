package com.truongdx8.androidproject4;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    Activity activity;
    List<UserItem> userItems;

    public UserAdapter(Activity activity, List<UserItem> userItems) {
        this.activity = activity;
        this.userItems = userItems;
    }

    @Override
    public int getCount() {
        return userItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = activity.getLayoutInflater().inflate(R.layout.activity_user_list_item,parent,false);

            UserHolder holder = new UserHolder();
            holder.tvId = convertView.findViewById(R.id.tvId);
            holder.tvName = convertView.findViewById(R.id.tvName);
            holder.tvGender = convertView.findViewById(R.id.tvGender);

            convertView.setTag(holder);
        }
        UserHolder holder = (UserHolder) convertView.getTag();

        UserItem user = userItems.get(position);

        holder.tvName.setText(user.getName());
        holder.tvGender.setText(user.getGender());
        holder.tvId.setText(String.valueOf(user.getId()));

        return convertView;
    }
}
