package th.go.ranong.loveranong.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by lapp on 28/2/2018 AD.
 */

public class PackageDetailCollectionDao {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private PackageDetailListItemDao data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public PackageDetailListItemDao getData() {
        return data;
    }

    public void setData(PackageDetailListItemDao data) {
        this.data = data;
    }
}
