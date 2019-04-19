package th.go.ranong.loveranong.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import th.go.ranong.loveranong.dao.PackageDetailListItemDao;
import th.go.ranong.loveranong.dao.PoiListItemDao;
import th.go.ranong.loveranong.view.ListItemPackage;

/**
 * Created by Kendo on 5/2/2561.
 */

public class PackageDetailAdapter extends BaseAdapter {

    PackageDetailListItemDao packageDetailListItemDao;


    public void setPackageListItemDao(PackageDetailListItemDao packageDetailListItemDao) {
        this.packageDetailListItemDao = packageDetailListItemDao;
    }

    @Override
    public int getCount() {
        if(packageDetailListItemDao == null) {
            return 0;
        }
        if(packageDetailListItemDao.getPkRoutedetail() == null) {
            return 0;
        }
        return packageDetailListItemDao.getPkRoutedetail().size();
    }

    @Override
    public Object getItem(int i) {
        return packageDetailListItemDao.getPkRoutedetail().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListItemPackage listItem;
        if (view != null) {
            listItem = (ListItemPackage) view;
        } else {
            listItem = new ListItemPackage(viewGroup.getContext());

        }
        PoiListItemDao poiListItemDao = (PoiListItemDao) getItem(i);
        listItem.setNameText(poiListItemDao.getPoiName());
        listItem.setPhotoUrl(poiListItemDao.getPoiPhoto());
        listItem.setCountText((i+1)+"");

        return  listItem;
    }
}
