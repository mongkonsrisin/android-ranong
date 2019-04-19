package th.go.ranong.loveranong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import th.go.ranong.loveranong.R;
import th.go.ranong.loveranong.dao.PoiListCollectionDao;
import th.go.ranong.loveranong.dao.PoiListItemDao;
import th.go.ranong.loveranong.view.ListItem;

/**
 * Created by Kendo on 5/2/2561.
 */

public class PoiListAdapter extends BaseAdapter implements Filterable {


    PoiListCollectionDao poiListCollectionDao;
    PoiListCollectionDao poiListCollectionDao2;

    int lastPosition = -1;



    public void setPoiListCollectionDao(PoiListCollectionDao poiListCollectionDao) {
        this.poiListCollectionDao = poiListCollectionDao;
        this.poiListCollectionDao2 = poiListCollectionDao;
    }

    @Override
    public int getCount() {
        if (poiListCollectionDao2 == null) {
            return 0;
        }
        if (poiListCollectionDao2.getData() == null) {
            return 0;
        }
        return poiListCollectionDao2.getData().size();

    }


    @Override
    public Object getItem(int i) {
        return poiListCollectionDao2.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListItem poiListItem;
        if (view != null) {
            poiListItem = (ListItem) view;
        } else {
            poiListItem = new ListItem(viewGroup.getContext());

        }
        PoiListItemDao poiListItemDao = (PoiListItemDao) getItem(i);
        poiListItem.setTitleText(poiListItemDao.getPoiName());
        //poiListItem.setSubtitleText(poiListItemDao.getPoiPhoto());
        poiListItem.setPhotoUrl(poiListItemDao.getPoiPhoto());

        if (i > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(viewGroup.getContext(), R.anim.up_from_bottom);
            poiListItem.startAnimation(animation);
            lastPosition = i;
        }
        return poiListItem;
    }

    @Override
    public Filter getFilter() {

    return null;
    }
}






