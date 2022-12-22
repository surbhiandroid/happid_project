package com.happid.app.Utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class is used to contain bitmap utils methods - getPath, getBitmap , change orientation.
 */

public class BitmapUtils {
    private static final String TAG = BitmapUtils.class.getSimpleName();
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    // get sd card path of image
    public static String getPath(Context context, Intent intent, Uri mCapturedImageURI) {
        Cursor cursor = null;
        String fileImagePath = null;
        try {
            Uri selectedImage;
            if (intent == null) {
                selectedImage = mCapturedImageURI;
            } else {
                if (intent.getData() == null) {
                    selectedImage = mCapturedImageURI;
                } else {
                    selectedImage = intent.getData();
                }
            }
            String[] filePathColumn = {MediaStore.MediaColumns.DATA};
            cursor = context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                fileImagePath = cursor.getString(columnIndex);
                cursor.close();
            }
        } catch (Exception ignored) {

        } finally {
            if (cursor != null)
                cursor.close();
        }
        return fileImagePath;
    }

    public static String getPathFromURI(Context context, Uri uri){
        String fileImagePath = null;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        Cursor cursor = context.getContentResolver().query(uri, filePathColumn, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            fileImagePath = cursor.getString(columnIndex);
            cursor.close();
        }

    return fileImagePath;
    }


    public static Bitmap imageOreintationValidator(Bitmap bitmap, String path) {

        ExifInterface ei;
        try {
            ei = new ExifInterface(path);
            int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    bitmap = rotateImage(bitmap, 90);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    bitmap = rotateImage(bitmap, 180);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    bitmap = rotateImage(bitmap, 270);
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    private static Bitmap rotateImage(Bitmap source, float angle) {
        Bitmap bitmap = null;
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        try {
            bitmap = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
        } catch (OutOfMemoryError err) {
            err.printStackTrace();
        }
        return bitmap;
    }

    public static Uri onOpenCameraImage(Activity context , int requestCode) {
        if(mCheckPermission(context)){
            Uri mCapturedImageURI = null;
            try {
                ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.TITLE, context.getPackageName());
                mCapturedImageURI = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);

//            context.startActivityForResult(cameraIntent, AppConstant.REQUEST_CODE.CAPTURE_IMAGE);
                context.startActivityForResult(cameraIntent, requestCode);

            } catch (ActivityNotFoundException e) {
            }

            return mCapturedImageURI;
        }else{
            Toast.makeText(context, "Please provide Camera & Storage Permission in Setting to access Camera", Toast.LENGTH_SHORT).show();

            return null;
        }

    }

    public static Uri onOpenCameraVideo(Activity context) {

        Uri mCapturedImageURI = null;
        try {
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.TITLE, context.getPackageName());
            mCapturedImageURI = context.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
            Intent cameraIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);
            cameraIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 60);
            context.startActivityForResult(cameraIntent, AppConstant.REQUEST_CODE.CAPTURE_VIDEO);

        } catch (ActivityNotFoundException e) {
        }

        return mCapturedImageURI;
    }


    public static Uri onOpenGallaryImage(Activity context , int requestCode) {
        if(mCheckPermission(context)){
            Uri mCapturedImageURI = null;
            if (Environment.getExternalStorageState().equals("mounted")) {

                ContentValues values = new ContentValues();
                values.put(MediaStore.MediaColumns.TITLE, context.getPackageName());
                mCapturedImageURI = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                Intent pickImageIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickImageIntent.setType("image/*");
                pickImageIntent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                // context.startActivityForResult(pickImageIntent, AppConstant.REQUEST_CODE.GALLARY_IMAGE);
                context.startActivityForResult(pickImageIntent, requestCode);
            }

            return mCapturedImageURI;
        }else{
            //Toast.makeText(context, "Please provide Storage Permission in Setting to access Camera", Toast.LENGTH_SHORT).show();

            return null;
        }

    }

    public static Uri onOpenGallaryVideo(Activity context) {
        Uri mCapturedImageURI = null;

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_PICK);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        context.startActivityForResult(intent,AppConstant.REQUEST_CODE.GALLARY_VIDEO);
//        if (Environment.getExternalStorageState().equals("mounted")) {
//
//            ContentValues values = new ContentValues();
//            values.put(MediaStore.MediaColumns.TITLE, context.getPackageName());
//            mCapturedImageURI = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
//
//            Intent pickVideoIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//            pickVideoIntent.setType("video/*");
//            pickVideoIntent.setAction(Intent.ACTION_GET_CONTENT);
//
//            context.startActivityForResult(pickVideoIntent, AppConstant.REQUEST_CODE.GALLARY_VIDEO);
//        }

        return mCapturedImageURI;
    }




    public static Bitmap getScaledBitmap(Bitmap myBitmap) {
        if (myBitmap!=null) {
            int height = myBitmap.getHeight();
            int width = myBitmap.getWidth();
            if (height > 2048) {
                width = width * 2048 / height;
                height = 2048;
            }
            if (width > 2048) {
                height = height * 2048 / width;
                width = 2048;
            }
            if (width == 2048 || height == 2048) {
                return Bitmap.createScaledBitmap(myBitmap, width, height, false);
            }
            return myBitmap;
        }else {
            return null;
        }
    }


    public static File saveBitmap(Bitmap bitmap) {
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + System.currentTimeMillis()+ "/cijsi_image";
        File dir = new File(file_path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String format = new SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.getDefault()).format(new Date());
        File file = new File(dir, format + ".jpeg");
        FileOutputStream fOut;
        try {
            fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
        }

        return file;
    }
    private static boolean mCheckPermission(Context context){
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }

    }
    public static void showDialog(String msg, final Context context, final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }
}
