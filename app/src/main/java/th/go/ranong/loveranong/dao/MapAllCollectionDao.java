package th.go.ranong.loveranong.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kendo on 5/2/2561.
 */

public class MapAllCollectionDao {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<MapAlltemDao> data = null;

    public Boolean isSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<MapAlltemDao> getData() {
        return data;
    }

    public void setData(List<MapAlltemDao> data) {
        this.data = data;
    }
}
