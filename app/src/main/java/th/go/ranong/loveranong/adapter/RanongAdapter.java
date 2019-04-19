package th.go.ranong.loveranong.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import th.go.ranong.loveranong.activity.RanongItem;
import th.go.ranong.loveranong.dao.RanongDao;

public class RanongAdapter extends BaseAdapter {

    RanongDao ranongDao;

    public void setRanongDao(RanongDao ranongDao) {
        this.ranongDao = ranongDao;
    }

    @Override
    public int getCount() {
        if(ranongDao == null) return 0;
        if(ranongDao.getData() == null) return 0;
        return ranongDao.getData().size();
    }

    @Override
    public Object getItem(int position) {
        return ranongDao.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RanongItem ranongItem;
        if (convertView != null) {
            ranongItem = (RanongItem) convertView;
        } else {
            ranongItem = new RanongItem(parent.getContext());

        }
        ranongItem.setPhotoUrl(ranongDao.getData().get(position));
        return ranongItem;
    }
}
