package th.go.ranong.loveranong.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import th.go.ranong.loveranong.dao.PackageDetailListItemDao;
import th.go.ranong.loveranong.dao.PoiListItemDao;
import th.go.ranong.loveranong.dao.ProductCollectionDao;
import th.go.ranong.loveranong.dao.ProductListItemDao;
import th.go.ranong.loveranong.view.ListItemProduct;

/**
 * Created by Kendo on 5/2/2561.
 */

public class ProductAdapter extends BaseAdapter {

    ProductCollectionDao productCollectionDao;


    public void setProductCollectionDao(ProductCollectionDao productCollectionDao) {
        this.productCollectionDao = productCollectionDao;
    }

    @Override
    public int getCount() {
        if(productCollectionDao == null) {
            return 0;
        }
        if(productCollectionDao.getData() == null) {
            return 0;
        }
        return productCollectionDao.getData().size();
    }

    @Override
    public Object getItem(int i) {
        return productCollectionDao.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListItemProduct listItem;
        if (view != null) {
            listItem = (ListItemProduct) view;
        } else {
            listItem = new ListItemProduct(viewGroup.getContext());

        }
        ProductListItemDao productListItemDao = (ProductListItemDao) getItem(i);
        listItem.setNameText(productListItemDao.getName());
        listItem.setName2Text(productListItemDao.getPrice());
        listItem.setPhotoUrl(productListItemDao.getPhoto());

        return  listItem;
    }
}
