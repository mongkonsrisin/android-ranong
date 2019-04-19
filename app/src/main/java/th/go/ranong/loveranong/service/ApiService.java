package th.go.ranong.loveranong.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import th.go.ranong.loveranong.dao.ConfigCollectionDao;
import th.go.ranong.loveranong.dao.MapAllCollectionDao;
import th.go.ranong.loveranong.dao.PackageDetailCollectionDao;
import th.go.ranong.loveranong.dao.PackageListCollectionDao;
import th.go.ranong.loveranong.dao.PoiCollectionDao;
import th.go.ranong.loveranong.dao.PoiListCollectionDao;
import th.go.ranong.loveranong.dao.ProductCollectionDao;
import th.go.ranong.loveranong.dao.RanongDao;
import th.go.ranong.loveranong.dao.RoomListCollectionDao;

/**
 * Created by Kendo on 5/2/2561.
 */

public interface ApiService {
    @GET("list_poi_by_cat.php")
    Call<PoiListCollectionDao> loadAllPoi(@Query("poicatid") String poicatid);

    @GET("poi_detail.php")
    Call<PoiCollectionDao> viewPoi(@Query("poiid") String poiid);

    @GET("search_poi_by_cat.php")
    Call<PoiListCollectionDao> searchPoi(@Query("poicatid") String poicatid,@Query("q") String q);

    @GET("list_package.php")
    Call<PackageListCollectionDao> loadAllPackage();


    @GET("package_detail.php")
    Call<PackageDetailCollectionDao> loadPackage(@Query("pkid") String pkid);

    @GET("search_package.php")
    Call<PackageListCollectionDao> searchPackage(@Query("startdate") String dateStart,
                                                 @Query("enddate") String datEnd,
                                                 @Query("category") String category,
                                                 @Query("type") String type,
                                                 @Query("budget") String budget,
                                                 @Query("people") String people);

    @GET("room_detail.php")
    Call<RoomListCollectionDao> loadRoom(@Query("poiid") String poiid);

    @GET("app_version.php")
    Call<ConfigCollectionDao> checkVersion(@Query("platform") String platform);

    @GET("product_detail.php")
    Call<ProductCollectionDao> loadProduct(@Query("poiid") String poiid,
                                           @Query("cat") String cat);

    @GET("poi_location.php")
    Call<RoomListCollectionDao> loadMapAll(@Query("poiid") String poiid);

    @GET("image_gallery.php")
    Call<RanongDao> getPhotos(@Query("id") int id);

    @GET("list_poi_by_cat.php")
    Call<MapAllCollectionDao> loadAll(@Query("poicatid") String poicatid);
}
