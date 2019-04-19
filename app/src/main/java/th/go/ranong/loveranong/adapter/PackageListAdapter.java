package th.go.ranong.loveranong.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;

import th.go.ranong.loveranong.R;
import th.go.ranong.loveranong.dao.PackageListCollectionDao;
import th.go.ranong.loveranong.dao.PackageListItemDao;
import th.go.ranong.loveranong.view.ListItem;

/**
 * Created by Kendo on 5/2/2561.
 */

public class PackageListAdapter extends BaseAdapter {

    PackageListCollectionDao packageListCollectionDao;
    int lastPosition = -1;


    public void setPackageListCollectionDao(PackageListCollectionDao packageListCollectionDao) {
        this.packageListCollectionDao = packageListCollectionDao;
    }

    @Override
    public int getCount() {
        if(packageListCollectionDao == null) {
            return 0;
        }
        if(packageListCollectionDao.getData() == null) {
            return 0;
        }
        return packageListCollectionDao.getData().size();
    }

    @Override
    public Object getItem(int i) {
        return packageListCollectionDao.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListItem listItem;
        if (view != null) {
            listItem = (ListItem)view;
        } else {
            listItem = new ListItem(viewGroup.getContext());

        }
        PackageListItemDao packageListItemDao = (PackageListItemDao) getItem(i);
        listItem.setTitleText(packageListItemDao.getPkTitle());
        //poiListItem.setSubtitleText(poiListItemDao.getPoiPhoto());
        listItem.setPhotoUrl(packageListItemDao.getPkPhoto());

        if(i > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(viewGroup.getContext(), R.anim.up_from_bottom);
            listItem.startAnimation(animation);
            lastPosition = i;
        }
        return  listItem;
    }
}
