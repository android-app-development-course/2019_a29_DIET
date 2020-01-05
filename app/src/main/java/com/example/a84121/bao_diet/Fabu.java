package com.example.a84121.bao_diet;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.solver.Cache;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by 84121 on 2019/12/11.
 */

public class Fabu extends AppCompatActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks{
    File tempImg1;
    ImageView photo,mPic1,Imagemmmmm;
    Button goback,publish;
    int mPhotoId;
    private Uri uri;
    private File cameraSavePath;
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fabu);
        mPic1 = (ImageView) findViewById(R.id.ImageView01);

        mPic1.setOnClickListener(mPhotoListener);
        goback=(Button)findViewById(R.id.btn_goback);
        publish=(Button)findViewById(R.id.btn_share);
        cameraSavePath = new File(Environment.getExternalStorageDirectory().getPath() + "/" + System.currentTimeMillis() + ".jpg");
        clickback();

    }
    @Override
    public void onClick(View v) {}
    public void clickback() {
           goback.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View mView) {
            Log.d("按返回了","");
            Intent mIntent=new Intent();//没有任何参数（意图），只是用来传递数据
            setResult(RESULT_OK,mIntent);
            finish();
        }
    });
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View mView) {
                Log.d("按返回了","");
                Log.d("导入数据库第0步","成功");
                database = SQLiteDatabase.openOrCreateDatabase(DBManager.DB_PATH + "/" + DBManager.DB_NAME, null);
                Log.d("导入数据库第一步","成功");
                ContentValues values = new ContentValues();
                Log.d("导入数据库第er步","成功");
                values.put("user", "派大星");
                values.put("community_text", "Great Picture");
                values.put("community_pic", "fabu1");
                values.put("head", "star");
                Log.d("导入数据库第san步","成功");
                database.insert("community2", null, values);
                Log.d("导入数据库第si步","成功");
                database.close();
                Intent mIntent=new Intent();//没有任何参数（意图），只是用来传递数据
                setResult(RESULT_OK,mIntent);
                finish();
            }
        });
    }
    private View.OnClickListener mPhotoListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.ImageView01)
                showPopueWindow();
        }
    };

        private void showPopueWindow(){
        View popView = View.inflate(this,R.layout.popupwindow_camera_need,null);
        Button bt_album = (Button) popView.findViewById(R.id.btn_pop_album);
        Button bt_camera = (Button) popView.findViewById(R.id.btn_pop_camera);
        Button bt_cancle = (Button) popView.findViewById(R.id.btn_pop_cancel);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels*1/3;

        final PopupWindow popupWindow = new PopupWindow(popView,weight,height);
//        popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);

        bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivity(i);
//                popupWindow.dismiss();
                        goPhotoAlbum();
                popupWindow.dismiss();

            }
        });
        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPermission();
                goCamera();
                popupWindow.dismiss();

            }
        });
        bt_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.dismiss();

            }
        });
        //popupWindow消失屏幕变为不透明
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1.0f;
                getWindow().setAttributes(lp);
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM,0,50);

    }
    private void getPermission() {
        Log.d("在这","");
        if (EasyPermissions.hasPermissions(this, permissions)) {
            //已经打开权限
          // Toast.makeText(this, "已经申请相关权限", Toast.LENGTH_SHORT).show();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相册、照相使用权限", 1, permissions);
        }

    }
    private void goCamera() {
        Log.d("来这里","goCamera1");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Log.d("来这里","goCamera2");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.d("来这里","goCamera3");
            uri = FileProvider.getUriForFile(Fabu.this, "com.example.a84121.bao_diet.fileprovider", cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            Log.d("来这里","goCamera4");
            uri = Uri.fromFile(cameraSavePath);
        }
        Log.d("来这里","goCamera5");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        Log.d("来这里","goCamera6");
        Fabu.this.startActivityForResult(intent, 1);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }

    //成功打开权限
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

        Toast.makeText(this, "相关权限获取成功", Toast.LENGTH_SHORT).show();
    }
    //用户未同意权限
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "请同意相关权限，否则功能无法使用", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String photoPath;
        if (requestCode == 1 && resultCode == RESULT_OK) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                photoPath = String.valueOf(cameraSavePath);
            } else {
                photoPath = uri.getEncodedPath();
            }
            Log.d("拍照返回图片路径:", photoPath);
            Glide.with(Fabu.this).load(photoPath).centerCrop().into(mPic1);
//            ContentResolver cr =this.getContentResolver();
//            if (uri == null)
//                return;
//            Log.d("uri不空", photoPath);
////按 刚刚指定 的那个文件名，查询数据库，获得更多的 照片信息，比如 图片的物理绝对路径
//            Cursor cursor =cr.query(uri,null,null,null,null);
//            if(cursor!=null){
//                if(cursor.moveToNext()){
//                    String path=cursor.getString(1);
////获得图片
//                    Log.d("准备改图", photoPath);
//
//
//                    try {
//                        Bitmap bp= MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//                        Imagemmmmm.setImageBitmap(bp);
////                        ContentValues values = new ContentValues();
////                        values.put("user", "Toto");
////                        values.put("community_text", "Terrier");
////                        values.put("community_pic", "fabu1");
////                        values.put("head", "star");
////
////                      //  try {
////                            long newRowId = database.insert("community2", null, values);
//                      //  }catch (Exception e)
//                      //  {
//
//
//                      //  }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//
////写入到数据库
//                    //mBlobDAL.InsertImg(bp);
//                }
//                cursor.close();
//            }

        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            photoPath = getPhotoFromPhotoAlbum.getRealPathFromUri(this, data.getData());
            Glide.with(Fabu.this).load(photoPath).centerCrop().into(mPic1);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    public  Drawable getDrawable(String urlpath){
        Drawable drawable = null;
        try {
            URL url = new URL(urlpath);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            drawable = Drawable.createFromStream(in, "fabu.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drawable;
    }


    //    private void camera() {
//        try {
//            tempImg1 = new File(MainActivity.this.getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
//            if (!tempImg1.exists()) {
//                boolean b = tempImg1.createNewFile();
//                if (b) {
//
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempImg1));
//                    startActivityForResult(intent, 1);
//                }
//            }
//        } catch (IOException e) {
//
//        }
//    }
//public void onActivityResult(int requestCode, int resultCode, Intent data) {
//    photo = (ImageView)findViewById(mPhotoId);
//    String pfid=String.valueOf(BusinessDetailsFragment.getPosition(mPhotoId) + 1);
//    String gsid=String.valueOf(mBusinessId);
//    String cur_date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
//    switch (resultCode) {
//        case 1:
//            if (data != null) {
//                Uri mImageCaptureUri = (Uri) data.getExtras().get("uri");
//                if (mImageCaptureUri != null) {
//                    Bitmap image;
//                    try {
//                        //image = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), mImageCaptureUri);
//                        image = (Bitmap) data.getExtras().get("image");
//                        String fileFullPath = PhotoAPI.savePicsToSdcard(image, mFileLoc);
//                        PromptUtils.showProgressDialog(getActivity(), "正在上传照片");
//                        mResult = PhotoAPI.uploadFile(gsid, pfid, fileFullPath);
//                        Cache.addLastPhotoPath(pfid, fileFullPath);
//                        Cache.addLastPhotoDate(gsid, cur_date);
//                        PromptUtils.dismissProgressDialog();
//
//                        showDialog(mResult);
//                        if (image != null) {
//                            photo.setImageBitmap(image);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    Bundle extras = data.getExtras();
//                    if (extras != null) {
//                        Bitmap image = extras.getParcelable("data");
//                        String fileFullPath = PhotoAPI.savePicsToSdcard(image, mFileLoc);
//
//                        PromptUtils.showProgressDialog(getActivity(), "正在上传照片");
//                        mResult = PhotoAPI.uploadFile(gsid, pfid, fileFullPath);
//                        PromptUtils.dismissProgressDialog();
//                        Cache.addLastPhotoPath(pfid, fileFullPath);
//                        Cache.addLastPhotoDate(gsid, cur_date);
//                        showDialog(mResult);
//                        if (image != null) {
//                            photo.setImageBitmap(image);
//                        }
//                    }
//                }
//            }
//            break;
//        case 2:
//            if (data != null) {
//                Uri mImageCaptureUri = data.getData();
//                if (mImageCaptureUri != null) {
//                    Bitmap image;
//                    try {
//                        image = MediaStore.Images.Media.getBitmap(this.getActivity().getContentResolver(), mImageCaptureUri);
//                        String fileFullPath = getRealPathFromURI(this.getActivity(),mImageCaptureUri);
//                        PromptUtils.showProgressDialog(getActivity(), "正在上传照片");
//                        mResult = PhotoAPI.uploadFile(gsid, pfid, fileFullPath);
//                        PromptUtils.dismissProgressDialog();
//                        Cache.addLastPhotoPath(pfid, fileFullPath);
//                        Cache.addLastPhotoDate(gsid, cur_date);
//                        showDialog(mResult);
//                        if (image != null) {
//                            photo.setImageBitmap(image);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    Bundle extras = data.getExtras();
//                    if (extras != null) {
//                        String fileFullPath = getRealPathFromURI(this.getActivity(),mImageCaptureUri);
//                        PromptUtils.showProgressDialog(getActivity(), "正在上传照片");
//                        mResult = PhotoAPI.uploadFile(gsid, pfid, fileFullPath);
//                        PromptUtils.dismissProgressDialog();
//                        Cache.addLastPhotoPath(pfid, fileFullPath);
//                        Cache.addLastPhotoDate(gsid, cur_date);
//                        Bitmap image = extras.getParcelable("data");
//                        if (image != null) {
//                            photo.setImageBitmap(image);
//                        }
//                    }
//                }
//            }
//            break;
//        default:
//            break;
//
//    }
//}
    public void click() {
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View mView) {
//                Intent mIntent=new Intent();//没有任何参数（意图），只是用来传递数据
//                mIntent.putExtra("likeFlag",likeFlag);
//                mIntent.putExtra("position",position);
//                mIntent.putExtra("collectFlag",collectFlag);
//                setResult(RESULT_OK,mIntent);
//                finish();
//            }
//        });

    }



}
