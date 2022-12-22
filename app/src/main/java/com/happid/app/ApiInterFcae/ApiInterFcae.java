package com.happid.app.ApiInterFcae;


import com.happid.app.Model.UploadPrescriptionApi;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterFcae {
    @Multipart
    @POST("retail/website/updateclientprofile")
    Call<UploadPrescriptionApi> uploadprofileimage(@Part("LicenseNumber") RequestBody LicenseNumber,
                                                   @Part("ProsID") RequestBody ProsID,
                                                   @Part MultipartBody.Part Image);


}





