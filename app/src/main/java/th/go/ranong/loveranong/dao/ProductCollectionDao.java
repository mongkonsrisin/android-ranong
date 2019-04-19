package th.go.ranong.loveranong.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lapp on 28/2/2018 AD.
 */

public class ProductCollectionDao {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<ProductListItemDao> data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<ProductListItemDao> getData() {
        return data;
    }

    public void setData(List<ProductListItemDao> data) {
        this.data = data;
    }
}
