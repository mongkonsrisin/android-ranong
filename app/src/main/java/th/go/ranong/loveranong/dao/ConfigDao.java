package th.go.ranong.loveranong.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Kendo on 3/8/2018.
 */

public class ConfigDao {
    @SerializedName("cfg_id")
    @Expose
    private Integer cfgId;
    @SerializedName("cfg_key")
    @Expose
    private String cfgKey;
    @SerializedName("cfg_value")
    @Expose
    private String cfgValue;

    public Integer getCfgId() {
        return cfgId;
    }

    public void setCfgId(Integer cfgId) {
        this.cfgId = cfgId;
    }

    public String getCfgKey() {
        return cfgKey;
    }

    public void setCfgKey(String cfgKey) {
        this.cfgKey = cfgKey;
    }

    public String getCfgValue() {
        return cfgValue;
    }

    public void setCfgValue(String cfgValue) {
        this.cfgValue = cfgValue;
    }
}
