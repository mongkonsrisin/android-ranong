package th.go.ranong.loveranong.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lapp on 28/2/2018 AD.
 */

public class PackageDetailListItemDao {
    @SerializedName("pk_id")
    @Expose
    private Integer pkId;
    @SerializedName("pk_title")
    @Expose
    private String pkTitle;
    @SerializedName("pk_description")
    @Expose
    private String pkDescription;
    @SerializedName("pk_startdate")
    @Expose
    private String pkStartdate;
    @SerializedName("pk_enddate")
    @Expose
    private String pkEnddate;
    @SerializedName("pk_budget")
    @Expose
    private Integer pkBudget;
    @SerializedName("pk_car")
    @Expose
    private Integer pkCar;
    @SerializedName("pk_motorcycle")
    @Expose
    private Integer pkMotorcycle;
    @SerializedName("pk_bicycle")
    @Expose
    private Integer pkBicycle;
    @SerializedName("pk_category")
    @Expose
    private Integer pkCategory;
    @SerializedName("pk_route")
    @Expose
    private String pkRoute;
    @SerializedName("pk_routedetail")
    @Expose
    private List<PoiListItemDao> pkRoutedetail = null;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public String getPkTitle() {
        return pkTitle;
    }

    public void setPkTitle(String pkTitle) {
        this.pkTitle = pkTitle;
    }

    public String getPkDescription() {
        return pkDescription;
    }

    public void setPkDescription(String pkDescription) {
        this.pkDescription = pkDescription;
    }

    public String getPkStartdate() {
        return pkStartdate;
    }

    public void setPkStartdate(String pkStartdate) {
        this.pkStartdate = pkStartdate;
    }

    public String getPkEnddate() {
        return pkEnddate;
    }

    public void setPkEnddate(String pkEnddate) {
        this.pkEnddate = pkEnddate;
    }

    public Integer getPkBudget() {
        return pkBudget;
    }

    public void setPkBudget(Integer pkBudget) {
        this.pkBudget = pkBudget;
    }

    public Integer getPkCar() {
        return pkCar;
    }

    public void setPkCar(Integer pkCar) {
        this.pkCar = pkCar;
    }

    public Integer getPkMotorcycle() {
        return pkMotorcycle;
    }

    public void setPkMotorcycle(Integer pkMotorcycle) {
        this.pkMotorcycle = pkMotorcycle;
    }

    public Integer getPkBicycle() {
        return pkBicycle;
    }

    public void setPkBicycle(Integer pkBicycle) {
        this.pkBicycle = pkBicycle;
    }

    public Integer getPkCategory() {
        return pkCategory;
    }

    public void setPkCategory(Integer pkCategory) {
        this.pkCategory = pkCategory;
    }

    public String getPkRoute() {
        return pkRoute;
    }

    public void setPkRoute(String pkRoute) {
        this.pkRoute = pkRoute;
    }

    public List<PoiListItemDao> getPkRoutedetail() {
        return pkRoutedetail;
    }

    public void setPkRoutedetail(List<PoiListItemDao> pkRoutedetail) {
        this.pkRoutedetail = pkRoutedetail;
    }
}
