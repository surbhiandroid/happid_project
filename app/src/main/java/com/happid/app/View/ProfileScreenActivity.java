package com.happid.app.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.happid.app.ApiInterFcae.ApiClient;
import com.happid.app.ApiInterFcae.ApiInterFcae;
import com.happid.app.R;
import com.happid.app.Utils.GalleryCameraHandlingManager;
import com.happid.app.Utils.GpsTracker;
import com.happid.app.Utils.IDialogUploadListener;
import com.happid.app.Utils.ImageUriProvider;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import id.zelory.compressor.Compressor;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;

public class ProfileScreenActivity extends AppCompatActivity implements  GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    private String no="";
    private ImageView back;
    private String addressString="";
    private GpsTracker gpsTracker;
    private double latituded=0.0,longituded=0.0;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    PendingResult<LocationSettingsResult> result;
    final static int REQUEST_LOCATION = 199;
    private CardView picklocationcard;
    private TextView addresstxt;
    private EditText noeditbox,lastnameeditbox,nameeditbox;
    private RelativeLayout camerarylt;
    private File mSelectedFile;
    private Uri imageUri;
    private ImageView profileimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        mGoogleApiClient = new GoogleApiClient.Builder(ProfileScreenActivity.this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(ProfileScreenActivity.this)
                .addOnConnectionFailedListener(ProfileScreenActivity.this).build();
        if(getIntent().getExtras()!=null)
        {
            no=getIntent().getStringExtra("no");

        }
        finid();
        setvalue();
        picklocation();
        clicklinere();
    }

    private void setvalue() {
        noeditbox.setText(no);
    }

    private void clicklinere() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        camerarylt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileScreenActivity.this.imageUpload();
            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkParameterIsNotNull(permissions, "permissions");
        Intrinsics.checkParameterIsNotNull(grantResults, "grantResults");
        if (requestCode == 2 || requestCode == 3) {
            GalleryCameraHandlingManager.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }

    public final void imageUpload() {
        this.openDialogCameraGallary(false, (AppCompatActivity)this, true, (IDialogUploadListener)(new IDialogUploadListener() {
            public void onClick(boolean isCamera) {
                boolean mediaStatex = false;
                int mediaState = isCamera ? 0 : 1;
                GalleryCameraHandlingManager.getInstance().captureMedia((AppCompatActivity)ProfileScreenActivity.this, isCamera, mediaState, (ImageUriProvider)(new ImageUriProvider() {
                    public void getImagePathWithUri(@org.jetbrains.annotations.Nullable Object imagepath, @NotNull Uri uri) {
                        Intrinsics.checkParameterIsNotNull(uri, "uri");
                        if (imagepath != null) {
                            ProfileScreenActivity.this.mSelectedFile = new File(imagepath.toString());
                        }

                        File var10000 = ProfileScreenActivity.this.mSelectedFile;
                        if (var10000 == null) {
                            Intrinsics.throwNpe();
                        }

                        long size = var10000.length() / (long)1024;
                        if (size > 0L) {

                            Glide.with((FragmentActivity)ProfileScreenActivity.this).load(imagepath).apply(RequestOptions.circleCropTransform()).into((ImageView)ProfileScreenActivity.this.findViewById(R.id.profileimage));
                            ProfileScreenActivity.this.imageUri = uri;
                            MultipartBody.Part file = (MultipartBody.Part)null;

                            try {
                                ProfileScreenActivity.this.mSelectedFile = (new Compressor((Context)ProfileScreenActivity.this)).compressToFile(ProfileScreenActivity.this.mSelectedFile);
                            } catch (IOException var11) {
                                var11.printStackTrace();
                            }

                            if (ProfileScreenActivity.this.mSelectedFile != null) {
                                RequestBody var12 = RequestBody.create(MediaType.parse("image/jpeg"), ProfileScreenActivity.this.mSelectedFile);
                                RequestBody requestFile = var12;
                                File var10001 = ProfileScreenActivity.this.mSelectedFile;
                                if (var10001 == null) {
                                    Intrinsics.throwNpe();
                                }

                                file = MultipartBody.Part.createFormData("Image", var10001.getName(), requestFile);
                            }

                            if (file != null) {
                                boolean var7 = false;
                                boolean var8 = false;
                                boolean var10 = false;
                                ProfileScreenActivity.this.mGetImageURL(file);
                            }
                        }

                    }
                }));
            }

            public void onRemove() {
            }
        }));
    }


    private final void mGetImageURL(MultipartBody.Part  file) {
       /* String descriptionString = PharmaSharedPref.getLicencenumbe();
        RequestBody licence = RequestBody.create(MediaType.parse("text/plain"), descriptionString);
        String orderid = PharmaSharedPref.getUserId();
        if(!orderid.equalsIgnoreCase(""))
        {
            RequestBody OrderID = RequestBody.create(MediaType.parse("text/plain"), orderid);
            Retrofit retrofit = ApiClient.getClient();
            ApiInterFcae apiInterface = retrofit.create(ApiInterFcae.class);
            Call<UploadPrescriptionApi> call = apiInterface.uploadprofileimage(licence,OrderID,file);
            call.enqueue(new Callback<UploadPrescriptionApi>() {
                @Override
                public void onResponse(Call<UploadPrescriptionApi> call,
                                       Response<UploadPrescriptionApi> response) {
                    Log.v("Upload", "success");

                    // CommonUtils.hideProgressDialog(customProgressDialog);
                    if (response.isSuccessful()) {

                        if (response.body() != null) {
                            if(response.body().getApiResponse().equalsIgnoreCase("Success"))
                            {
                                callApifordetaili();
                         *//*   PharmaSharedPref.init(UploadPrescription.this);
                            if(response.body().getOrderNumber()!=null)
                            {
                                PharmaSharedPref.saveOrderno(response.body().getOrderNumber());
                                Toast.makeText(UploadPrescription.this, response.body().getOrderNumber(), Toast.LENGTH_SHORT).show();

                            }
*//*


                                Toast.makeText(EditprofileActivity.this, "Image added successfully", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(EditprofileActivity.this, response.body().getApiResponse(), Toast.LENGTH_SHORT).show();

                            }

                            // CommonUtils.showToast(getContext(),"Image added successfully");
                            // fetchProspectImage();
                        }

                    } else {

                        Toast.makeText(EditprofileActivity.this, "error image upload", Toast.LENGTH_SHORT).show();

                        //  CommonUtils.unsuccessfull(response.errorBody(), response.code(), getContext());
                    }
                }

                @Override
                public void onFailure(Call<UploadPrescriptionApi> call, Throwable t) {
                    //  GlobalCommon.mDismiss();
                    Toast.makeText(EditprofileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("exsec",t.getMessage());

                }
            });
        }
        else
        {
            Toast.makeText(this, "No order id", Toast.LENGTH_SHORT).show();
        }
*/

    }




    public final void openDialogCameraGallary(boolean showRemove, @Nullable AppCompatActivity activity, boolean isFromProfile, @NotNull final IDialogUploadListener listner) {


        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_camera_photo);
        TextView cameraPhoto=dialog.findViewById(R.id.tv_camera);
        TextView galleryPhoto=dialog.findViewById(R.id.tv_gallery);
        View v=dialog.findViewById(R.id.v);
        View v2=dialog.findViewById(R.id.v2);


                       /* if (showRemove) {
                            v2.setVisibility(0);
                        } else {
                            v2.setVisibility(8);
                        }*/

        cameraPhoto.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                listner.onClick(true);
                dialog.dismiss();
            }
        }));
        galleryPhoto.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                listner.onClick(false);
                dialog.dismiss();
            }
        }));
        dialog.show();
    }




    private void finid() {
        camerarylt=findViewById(R.id.camerarylt);
        noeditbox=findViewById(R.id.noeditbox);
        lastnameeditbox=findViewById(R.id.lastnameeditbox);
        nameeditbox=findViewById(R.id.nameeditbox);
        addresstxt=findViewById(R.id.addresstxt);
        picklocationcard=findViewById(R.id.picklocationcard);
        back=findViewById(R.id.back);
    }

    private void picklocation() {
        picklocationcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGoogleApiClient.disconnect();
                final LocationManager manager = (LocationManager) ProfileScreenActivity.this.getSystemService( Context.LOCATION_SERVICE );

                if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
                    Toast.makeText(ProfileScreenActivity.this, "Looks like your GPS is not enabled. Turn on your GPS in settings", Toast.LENGTH_SHORT).show();
                    mGoogleApiClient.connect();
                }
                else
                {

                    gpsTracker = new GpsTracker(ProfileScreenActivity.this);

                    if (gpsTracker.canGetLocation()) {
                        latituded = gpsTracker.getLatitude();
                        longituded = gpsTracker.getLongitude();
                        Log.d("addressString", ""+latituded+" "+""+longituded);
                        try {
                            Geocoder geocoder;
                            List<Address> addresses;
                            geocoder = new Geocoder(ProfileScreenActivity.this, Locale.getDefault());

                            addresses = geocoder.getFromLocation(latituded, longituded, 1);

                            addressString = addresses.get(0).getAddressLine(0);
                            Log.d("addressString", addressString);

                            addressString = addresses.get(0).getAddressLine(0);
                            Log.d("addressString", addressString);

                            String  citytxtcalue=addresses.get(0).getLocality();
                            String statevalue=addresses.get(0).getAdminArea();
                            String   pincodtxtval=addresses.get(0).getPostalCode();

                            Toast.makeText(ProfileScreenActivity.this, "Location picked", Toast.LENGTH_SHORT).show();
                           /* citytxt.setText(citytxtcalue);
                            statetxt.setText(statevalue);
                            pincodetxt.setText(pincodtxtval);*/
                            StringTokenizer tk = new StringTokenizer(addressString, ",");
                            String first=tk.nextToken();
                            if(!first.equalsIgnoreCase(""))
                            {
                                /*etComment1.setText(first);*/
                                /*addresstxt=first;*/
                                addresstxt.setText(first);

                            }
                            String second=tk.nextToken();
                            String third=tk.nextToken();
                            if(!second.equalsIgnoreCase("") && !third.equalsIgnoreCase(""))
                            {
                                String four=tk.nextToken();
                                if(!four.equalsIgnoreCase(""))
                                {
                                    if(citytxtcalue.equalsIgnoreCase(four))
                                    {
                                        addresstxt.setText(first+","+second+","+third);
                                    }
                                    else
                                    {
                                        addresstxt.setText(first+","+second+","+third+","+four);
                                    }
                                }
                                else
                                {


                                    addresstxt.setText(first+","+second+","+third);
                                }

                            }






                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else{
                        gpsTracker.showSettingsAlert();
                    }
                }



            }


        });

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = com.google.android.gms.location.LocationRequest.create();
        mLocationRequest.setPriority(com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(30 * 1000);
        mLocationRequest.setFastestInterval(5 * 1000);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);

        result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                //final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        //...


                        Log.i("final", "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(
                                    ProfileScreenActivity.this,
                                    REQUEST_LOCATION);
                            Log.d("final","ok");
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        //...
                        break;
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("onActivityResult()", Integer.toString(resultCode));
        if (requestCode == 0 || requestCode == 1) {
            GalleryCameraHandlingManager.getInstance().onActivityResult(requestCode, resultCode, data);
        }

        //final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case REQUEST_LOCATION:
                switch (resultCode) {
                    case Activity.RESULT_OK: {
                        // All required changes were successfully made
                        /* */

                        break;
                    }
                    case Activity.RESULT_CANCELED: {
                        // The user was asked to change settings, but chose not to

                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}