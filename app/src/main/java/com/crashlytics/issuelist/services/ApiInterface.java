package com.crashlytics.issuelist.services;

import com.crashlytics.issuelist.models.IssueComments;
import com.crashlytics.issuelist.models.IssuesData;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Venkat-Tesark on 03-08-2017.
 */

public interface ApiInterface {

    @Headers("Accept:application/vnd.github.v3+json")
    @GET("repos/crashlytics/secureudid/issues")
    Call<List<IssuesData>> getIssueList(@QueryMap Map<String,String>map);

    @Headers("Accept:application/vnd.github.v3+json")
    @GET("repos/crashlytics/secureudid/issues/{no}/comments")
    Call<List<IssueComments>> getIssueCommentsList(@Path("no")String no);


/*


    @Headers("Content-Type:application/json")
    @POST("user/register")
    Call<LoginData> registerUser(@QueryMap Map<String, String> params);

    @Headers("Content-Type:application/json")
    @GET("equity/list")
    Call<PortfolioListReponse> getPortfolioList(@Header("Authorization") String token);

    @Headers("Content-Type:application/json")
    @POST("equity/search")
    Call<MarketSearchResponse> searchMartket(@Header("Authorization") String token, @QueryMap Map<String, String> map);

    @Headers("Content-Type:application/json")
    @PUT("equity/update")
    Call<UpdatePortfolioData> updatePortfolio(@Header("Authorization") String token, @Body UpdatePortfolioRequest params);

    @Headers("Content-Type:application/json")
    @POST("equity/delete")
    Call<RemovePortfolioResponse> removePortfolio(@Header("Authorization") String token, @Body UpdatePortfolioRequest params);

    @Headers("Content-Type:application/json")
    @POST("equity/add")
    Call<UpdatePortfolioData> addPortfolio(@Header("Authorization") String token, @Body UpdatePortfolioRequest params);

    @Headers("Content-Type:application/json")
    @GET("equity/detail")
    Call<CompanyDetailData> getCompnayDetails(@Header("Authorization") String token, @QueryMap Map<String, String> map);

    @Headers("Content-Type:application/json")
    @POST("equity/watchlist/add")
    Call<AddWatchListResponse> addWatchList(@Header("Authorization") String token, @QueryMap Map<String, String> map);

    @Headers("Content-Type:application/json")
    @GET("equity/watchlist/list")
    Call<WatchListData> getWatchList(@Header("Authorization") String token);

    @Headers("Content-Type:application/json")
    @GET("equity/summary")
    Call<PortfolioReportData> getReports(@Header("Authorization") String token);

    @Headers("Content-Type:application/json")
    @POST("equity/watchlist/delete")
    Call<RemoveWatchListResponse> removeWatchList(@Header("Authorization") String token, @QueryMap Map<String, String> map);
*/

}
