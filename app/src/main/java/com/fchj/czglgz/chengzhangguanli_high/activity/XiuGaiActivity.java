package com.fchj.czglgz.chengzhangguanli_high.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.fchj.czglgz.chengzhangguanli_high.R;
import com.fchj.czglgz.chengzhangguanli_high.adapter.QuAdapter;
import com.fchj.czglgz.chengzhangguanli_high.adapter.SchoolAdapter;
import com.fchj.czglgz.chengzhangguanli_high.adapter.ShengAdapter;
import com.fchj.czglgz.chengzhangguanli_high.adapter.ShiAdapter;
import com.fchj.czglgz.chengzhangguanli_high.manager.OkHttpClientManager;
import com.fchj.czglgz.chengzhangguanli_high.model.HeadModel;
import com.fchj.czglgz.chengzhangguanli_high.model.QuModel;
import com.fchj.czglgz.chengzhangguanli_high.model.SchoolModel;
import com.fchj.czglgz.chengzhangguanli_high.model.ShengModel;
import com.fchj.czglgz.chengzhangguanli_high.model.ShiModel;
import com.fchj.czglgz.chengzhangguanli_high.model.ShowInfoModel;
import com.fchj.czglgz.chengzhangguanli_high.model.XiuGaiModel;
import com.fchj.czglgz.chengzhangguanli_high.util.Url;
import com.fchj.czglgz.chengzhangguanli_high.view.CircleView;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.mixiaoxiao.smoothcompoundbutton.SmoothRadioButton;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class XiuGaiActivity extends Activity {
    private RadioButton nanRb, nvRb;
    private RadioGroup sexRg;
    private int SexNum = 0;
    private EditText jzNameEd, studentNameEd, ageEd, schoolEd, phoneEd, addressEd, nianjiEd, banjiEd;
    private RelativeLayout trueImg;
    private XiuGaiModel mXiuGaiModel;
    private Handler mHandler;
    private SharedPreferences username;
    private int userId;
    private ImageView returnImg;
    private ShowInfoModel mShowInfoModel;
    private Spinner mShengSp, mShiSp, mQuSp, mSchoolSp;
    // 头像
    private CircleView headImg;
    private HeadModel mHeadModel;
    private Button headBtn;
    // 图片
    private Uri imageUri;
    private static final int TAKE_PHOTO_REQUEST_TWO = 444;
    private static final int TAKE_PHOTO_REQUEST_THREE = 555;
    private String imgLiu;
    // 省
    private ShengModel mShengModel;
    private List<ShengModel.DataBean> mShengData;
    private ShengAdapter mShengAdapter;
    private String shengNum = "0";
    private String shiNum = "0";
    private String quNum = "0";
    private String schoolNum = "0";
    // 市
    private ShiAdapter mShiAdapter;
    private ShiModel mShiModel;
    private List<ShiModel.DataBean> mShiData;
    //区
    private QuAdapter mQuAdapter;
    private QuModel mQuModel;
    private List<QuModel.DataBean> mQuData;
    // 学校
    private SchoolAdapter mSchoolAdapter;
    private SchoolModel mSchoolModel;
    private List<SchoolModel.DataBean> mSchoolData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiugai);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
        mHandler = new Handler();
        username = getSharedPreferences("user_npt", MODE_PRIVATE);
        userId = username.getInt("userId", 0);
        initView();
        showInfo();
        getSheng();
//        if (shengNum != -1) {
//            getShi();
//        } else if (shiNum != -1) {
//            getQu();
//        } else if (quNum != -1) {
//            getSchool();
//        }
    }

    // 选择图片的window
    private void showPopueWindow() {
        final View popView = View.inflate(this, R.layout.popwindow_head, null);
        Button bt_album = (Button) popView.findViewById(R.id.btn_pop_album);
        Button bt_camera = (Button) popView.findViewById(R.id.btn_pop_camera);
        Button bt_cancle = (Button) popView.findViewById(R.id.btn_pop_cancel);
        //获取屏幕宽高
        int weight = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels * 1 / 3;

        final PopupWindow popupWindow = new PopupWindow(popView, weight, height);
        //   popupWindow.setAnimationStyle(R.style.anim_popup_dir);
        popupWindow.setFocusable(true);
        //点击外部popueWindow消失
        popupWindow.setOutsideTouchable(true);
        bt_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    imageUri = takePhoto(XiuGaiActivity.this, TAKE_PHOTO_REQUEST_THREE);

                    Log.d("imgUri", String.valueOf(imageUri));
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
//        bt_album.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_PICK);
//                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(intent, 222);
//                popupWindow.dismiss();
//            }
//        });
        bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 222);
                popupWindow.dismiss();
            }
        });
        //popupWindow出现屏幕变为半透明
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 50);

    }

    public Uri takePhoto(Activity mActivity, int flag) {

        //指定拍照intent
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri imageUri = null;
        if (takePictureIntent.resolveActivity(mActivity.getPackageManager()) != null) {
            String sdcardState = Environment.getExternalStorageState();
            File outputImage = null;
            if (Environment.MEDIA_MOUNTED.equals(sdcardState)) {
                outputImage = createImageFile(mActivity);
            } else {
                Toast.makeText(mActivity.getApplicationContext(), "内存异常", Toast.LENGTH_SHORT).show();
            }
            try {
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                outputImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (outputImage != null) {
                if (Build.VERSION.SDK_INT >= 24) {

                    imageUri = FileProvider.getUriForFile(mActivity.getApplicationContext(), "com.fchj.czglgz.chengzhangguanli_high.fileprovider", outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);

                }
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                mActivity.startActivityForResult(takePictureIntent, flag);
            }
        }

        return imageUri;
    }

    public File image;

    public File createImageFile(Activity mActivity) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp;//创建以时间命名的文件名称
        File storageDir = getOwnCacheDirectory(mActivity, "takephoto");//创建保存的路径
        image = new File(storageDir.getPath(), imageFileName + ".jpg");
        if (!image.exists()) {
            try {
                //在指定的文件夹中创建文件
                image.createNewFile();
            } catch (Exception e) {
            }
        }

        return image;
    }

    public File getOwnCacheDirectory(Context context, String cacheDir) {
        File appCacheDir = null;
        //判断sd卡正常挂载并且拥有权限的时候创建文件
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && hasExternalStoragePermission(context)) {
            appCacheDir = new File(Environment.getExternalStorageDirectory(), cacheDir);
        }
        if (appCacheDir == null || !appCacheDir.exists() && !appCacheDir.mkdirs()) {
            appCacheDir = context.getCacheDir();
        }
        return appCacheDir;
    }

    /**
     * 检查是否有权限
     *
     * @param context
     * @return
     */
    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
        return perm == 0;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode) {
            case 111:
                if (data == null) {
                    return;
                }//剪裁后的图片
                Bundle extras = data.getExtras();
                if (extras == null) {
                    return;
                }
                Bitmap bm = extras.getParcelable("data");
                headImg.setImageBitmap(bm);
            case 222:
                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(XiuGaiActivity.this, "点击取消从相册选择", Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    Uri imageUri = data.getData();
                    startPhotoZoom(imageUri);
                    String bitmap = getRealPathFromUri(XiuGaiActivity.this, imageUri);
                    headImg.setImageURI(imageUri);

                    String[] proj = {MediaStore.Images.Media.DATA};

                    Cursor actualimagecursor = managedQuery(imageUri, proj, null, null, null);

                    int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                    actualimagecursor.moveToFirst();

                    String img_path = actualimagecursor.getString(actual_image_column_index);

                    image = new File(img_path);

                    Log.d("imgUri1", getRealPathFromUri(XiuGaiActivity.this, imageUri));


                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case TAKE_PHOTO_REQUEST_TWO:
                if (resultCode == RESULT_CANCELED) {
                    delteImageUri(XiuGaiActivity.this, imageUri);
                    return;
                }
                Bitmap photo = data.getParcelableExtra("data");
                headImg.setImageBitmap(photo);

                break;
            case TAKE_PHOTO_REQUEST_THREE:
                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(XiuGaiActivity.this, "点击取消从相册选择", Toast.LENGTH_LONG).show();
                    return;
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        //    Bitmap to = compressImage(bitmap);
                        Bitmap bit = compressScale(bitmap);
                        imgLiu = bitmapToBase64(bit);
                        headImg.setImageBitmap(bit);
                        Log.d("3456", imgLiu);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }


                } else {
                    // Android 6.0及以下设备
                    Bitmap bitmap = getSmallBitmap(imageUri.getPath());
                    imgLiu = bitmapToString(imageUri.getPath());
                    headImg.setImageBitmap(bitmap);
                    Log.d("345", imgLiu);

                }
                break;
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 111);
    }

    // 格式化图片地址
    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static void delteImageUri(Context context, Uri uri) {
        context.getContentResolver().delete(uri, null, null);

    }

    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }


    // 根据路径获得图片并压缩，返回bitmap用于显示
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = computeInitialSampleSize(options, 480, 800);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;


        return BitmapFactory.decodeFile(filePath, options);
    }

    //计算图片的缩放值
    public static int computeInitialSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap compressScale(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 80, baos);// 这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap;
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 512f;
        float ww = 512f;
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) { // 如果高度高的话根据高度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be; // 设置缩放比例
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        // return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
        return zoomImage(bitmap, 100, 180);
    }

    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
//                String oneBaseEncoder = Base64.encode(msg.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    //把bitmap转换成String
    public static String bitmapToString(String filePath) {

        Bitmap bm = getSmallBitmap(filePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }


    private void initView() {
        returnImg = (ImageView) findViewById(R.id.title_fanhui);
        returnImg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        //头像
//        headBtn = (Button) findViewById(R.id.xiugai_test_btn);
//        headBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                pushImg();
//                try {
//                    test();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        headImg = (CircleView) findViewById(R.id.xiugai_head);
        headImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopueWindow();
            }
        });
        jzNameEd = (EditText) findViewById(R.id.msg_jiazhangname);
        jzNameEd.setGravity(Gravity.RIGHT);
        studentNameEd = (EditText) findViewById(R.id.msg_studentname);
        studentNameEd.setGravity(Gravity.RIGHT);
        ageEd = (EditText) findViewById(R.id.msg_age);
        ageEd.setGravity(Gravity.RIGHT);
        ageEd.setInputType(EditorInfo.TYPE_CLASS_PHONE);
//        schoolEd = (EditText) findViewById(R.id.msg_school);
        phoneEd = (EditText) findViewById(R.id.msg_tel);
        phoneEd.setGravity(Gravity.RIGHT);
        phoneEd.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        addressEd = (EditText) findViewById(R.id.msg_address);
        addressEd.setGravity(Gravity.RIGHT);
        nianjiEd = (EditText) findViewById(R.id.msg_nianji);
        nianjiEd.setGravity(Gravity.RIGHT);
        nianjiEd.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        banjiEd = (EditText) findViewById(R.id.msg_banji);
        banjiEd.setGravity(Gravity.RIGHT);
        banjiEd.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        // RadioButton
        nanRb = (RadioButton) findViewById(R.id.xiugai_rb_nan);
        nvRb = (RadioButton) findViewById(R.id.xiugai_rb_nv);
        sexRg = (RadioGroup) findViewById(R.id.rg_sex);
        sexRg.setOnCheckedChangeListener(new MyRadioButtonListener());
        // 确定
        trueImg = (RelativeLayout) findViewById(R.id.xiugai_true_img);
        trueImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (image != null) {
                    try {
                        test();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
//                else if (!TextUtils.isEmpty(phoneEd.getText().toString())) {
//                    if (isMobileNO(phoneEd.getText().toString())) {
//                        setInfo();
//                    } else {
//                        Toast.makeText(XiuGaiActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
//
//                    }
//                }
                else if (!TextUtils.isEmpty(phoneEd.getText().toString())
                        && !TextUtils.isEmpty(jzNameEd.getText().toString())
                        && !TextUtils.isEmpty(studentNameEd.getText().toString())
                        && !TextUtils.isEmpty(ageEd.getText().toString())
                        && !TextUtils.isEmpty(studentNameEd.getText().toString())
                        && !TextUtils.isEmpty(nianjiEd.getText().toString())
                        && !TextUtils.isEmpty(banjiEd.getText().toString())
                        && !TextUtils.isEmpty(addressEd.getText().toString())) {
                    if (isMobileNO(phoneEd.getText().toString())) {
                        setInfo();
                    } else {
                        Toast.makeText(XiuGaiActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(XiuGaiActivity.this, "请输入完整的信息", Toast.LENGTH_SHORT).show();
                }


            }
        });
        // 省
        mShengSp = (Spinner) findViewById(R.id.xiugai_sheng_sp);
        mShengSp.setPrompt("省份");
        mShengSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                shengNum = mShengData.get(position).getProvinceid();
                getShi();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mShiSp = (Spinner) findViewById(R.id.xiugai_shi_sp);
        mShiSp.setPrompt("城市");
        mShiSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shiNum = mShiData.get(position).getCityid();
                getQu();
//                shiNum = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mQuSp = (Spinner) findViewById(R.id.xiugai_qu_sp);
        mQuSp.setPrompt("区域");
        mQuSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quNum = mQuData.get(position).getAreaid();
                getSchool();
//                quNum = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSchoolSp = (Spinner) findViewById(R.id.xiugai_school_sp);
        mSchoolSp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                schoolNum = mSchoolData.get(position).getSchoolforareas();

//                quNum = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 判断字符串是否符合手机号码格式
     * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
     * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
     * 电信号段: 133,149,153,170,173,177,180,181,189
     *
     * @return 待检测的字符串
     */
    public static boolean isMobileNO(String mobileNums) {
        /**
         * 判断字符串是否符合手机号码格式
         * 移动号段: 134,135,136,137,138,139,147,150,151,152,157,158,159,170,178,182,183,184,187,188
         * 联通号段: 130,131,132,145,155,156,170,171,175,176,185,186
         * 电信号段: 133,149,153,170,173,177,180,181,189
         * @param str
         * @return 待检测的字符串
         */
        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    private void setInfo() {
        Log.d("xuexiao", String.valueOf(schoolNum));
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("userId", String.valueOf(userId) + "");
        builder.add("parentname", String.valueOf(jzNameEd.getText().toString()) + "");
        builder.add("username", String.valueOf(studentNameEd.getText().toString()) + "");
        builder.add("age", String.valueOf(ageEd.getText().toString()) + "");
        builder.add("sex", String.valueOf(SexNum) + "");
        builder.add("school", String.valueOf(schoolNum) + "");
        builder.add("grade", String.valueOf(nianjiEd.getText().toString()) + "");
        builder.add("classes", String.valueOf(banjiEd.getText().toString()) + "");
        builder.add("userphone", String.valueOf(phoneEd.getText().toString()) + "");
        builder.add("address", String.valueOf(addressEd.getText().toString()) + "");
        Request request = new Request.Builder().url(Url.xiugaiUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        Log.d("setinfo", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mXiuGaiModel = gson.fromJson(reader, XiuGaiModel.class);

                        if (mXiuGaiModel.getCode() != 2) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
//                            if (isMobileNO(phoneEd.getText().toString())) {
                                    if (mXiuGaiModel.getMsg().equals("修改成功")) {
                                        Toast.makeText(XiuGaiActivity.this, mXiuGaiModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else if (mXiuGaiModel.getCode() == 2) {
                                        Toast.makeText(XiuGaiActivity.this, mXiuGaiModel.getMsg() + "", Toast.LENGTH_SHORT).show();

                                    } else {

                                        Toast.makeText(XiuGaiActivity.this, mXiuGaiModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });
                        } else {
                            Log.d("没有", "数据");
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(XiuGaiActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void showInfo() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("userId", String.valueOf(userId));
        Request request = new Request.Builder().url(Url.showInfoUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        Log.d("showinfo", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mShowInfoModel = gson.fromJson(reader, ShowInfoModel.class);
                        if (mShowInfoModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    jzNameEd.setText(mShowInfoModel.getData().getParentname());
                                    studentNameEd.setText(mShowInfoModel.getData().getUsername());
                                    ageEd.setText(String.valueOf(mShowInfoModel.getData().getAge()));
//                            schoolEd.setText(String.valueOf(mShowInfoModel.getData().getSchool()));
                                    Glide.with(XiuGaiActivity.this).load(mShowInfoModel.getData().getHeadimgurl()).placeholder(R.mipmap.boy).into(headImg);
                                    phoneEd.setText(String.valueOf(mShowInfoModel.getData().getUserphone()));
                                    addressEd.setText(String.valueOf(mShowInfoModel.getData().getAddress()));
                                    nianjiEd.setText(String.valueOf(mShowInfoModel.getData().getGrade()));
                                    banjiEd.setText(String.valueOf(mShowInfoModel.getData().getClasses()));
                                    if (mShowInfoModel.getData().getSex() == 0) {
                                        nanRb.setSelected(false);
                                        nvRb.setSelected(true);
                                    } else if (mShowInfoModel.getData().getSex() == 1) {
                                        nvRb.setSelected(false);
                                        nanRb.setSelected(true);
                                    }
//                            mShengSp.setSelection(3);
                                }
                            });
                        } else if (mShowInfoModel.getCode() == 2) {
                            Looper.prepare();
                            Toast.makeText(XiuGaiActivity.this, mShowInfoModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        } else {
                            Looper.prepare();
                            Toast.makeText(XiuGaiActivity.this, mShowInfoModel.getMsg() + "", Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(XiuGaiActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    class MyRadioButtonListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            // 选中状态改变时被触发
            switch (checkedId) {
                case R.id.xiugai_rb_nv:
                    // 当用户选择女性时
                    nanRb.setSelected(false);
                    nvRb.setSelected(true);
                    SexNum = 0;
                    break;
                case R.id.xiugai_rb_nan:
                    // 当用户选择男性时
                    nvRb.setSelected(false);
                    nanRb.setSelected(true);
                    SexNum = 1;
                    break;
            }
        }
    }

    //    private void getSchool() {
//        FormEncodingBuilder builder = new FormEncodingBuilder();
//        builder.add("userId", String.valueOf(userId));
//        Request request = new Request.Builder().url(Url.showInfoUrl).post(builder.build()).build();
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                String body = response.body().string();
//                Log.d("sdadsadas", body);
//                if (.getCode()!=2) {
//                    Gson gson = new Gson();
//                    JsonReader reader = new JsonReader(new StringReader(body));
//                    mShowInfoModel = gson.fromJson(reader, ShowInfoModel.class);
//                    mHandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            jzNameEd.setText(mShowInfoModel.getData().getParentname());
//                            studentNameEd.setText(mShowInfoModel.getData().getUsername());
//                            ageEd.setText(String.valueOf(mShowInfoModel.getData().getAge()));
//                            schoolEd.setText(String.valueOf(mShowInfoModel.getData().getSchool()));
//                            phoneEd.setText(String.valueOf(mShowInfoModel.getData().getUserphone()));
//                            addressEd.setText(String.valueOf(mShowInfoModel.getData().getAddress()));
//                            nianjiEd.setText(String.valueOf(mShowInfoModel.getData().getGrade()));
//                            banjiEd.setText(String.valueOf(mShowInfoModel.getData().getClasses()));
//                        }
//                    });
//                } else {
//                    Log.d("没有", "数据");
//                }
//
//
//            }
//        });
//    }


    private void getSheng() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("", "");
        Request request = new Request.Builder().url(Url.shengUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        Log.d("getsheng", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mShengModel = gson.fromJson(reader, ShengModel.class);
                        if (mShengModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mShengData = mShengModel.getData();
                                    mShengAdapter = new ShengAdapter(mShengData);
                                    mShengSp.setAdapter(mShengAdapter);
//                            if (shengNum != -1) {
//
//                                getShi();
//                            }
                                }
                            });
                        } else {
                            Log.d("没有", "数据");
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(XiuGaiActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getShi() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("provinceid", shengNum);
        Request request = new Request.Builder().url(Url.shiUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        Log.d("getshi", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mShiModel = gson.fromJson(reader, ShiModel.class);
                        if (mShiModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mShiData = mShiModel.getData();
                                    mShiAdapter = new ShiAdapter(mShiData);
                                    mShiSp.setAdapter(mShiAdapter);
//                            getQu();
                                }
                            });
                        } else {
                            Log.d("没有", "数据");
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(XiuGaiActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void getQu() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("cityid", shiNum);
        Request request = new Request.Builder().url(Url.quUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        Log.d("getqu", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mQuModel = gson.fromJson(reader, QuModel.class);
                        if (mQuModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mQuData = mQuModel.getData();
                                    mQuAdapter = new QuAdapter(mQuData);
                                    mQuSp.setAdapter(mQuAdapter);
//                            getSchool();
                                }
                            });
                        } else {
                            Log.d("没有", "数据");
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(XiuGaiActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void getSchool() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("schooltype", "0");
        builder.add("forareas", quNum);
        Request request = new Request.Builder().url(Url.schoolUrl).post(builder.build()).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) {
                try {
                    if (response.isSuccessful()) {
                        String body = response.body().string();
                        Log.d("getschool", body);
                        Gson gson = new Gson();
                        JsonReader reader = new JsonReader(new StringReader(body));
                        mSchoolModel = gson.fromJson(reader, SchoolModel.class);
                        if (mSchoolModel.getCode() == 1) {

                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mSchoolData = mSchoolModel.getData();
                                    mSchoolAdapter = new SchoolAdapter(mSchoolData);
                                    mSchoolSp.setAdapter(mSchoolAdapter);
                                }
                            });
                        } else {
                            Log.d("没有", "数据");
                        }


                    } else {
                        Looper.prepare();
                        Toast.makeText(XiuGaiActivity.this, "服务器异常", Toast.LENGTH_SHORT).show(); //请求失败
                        Looper.loop();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    private void test() {
        OkHttpClientManager.postAsyn(Url.headPicUrl,//
                new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        Log.d("aaa", "fail");
                        Toast.makeText(XiuGaiActivity.this, "头像修改失败", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String result) {
                        Log.d("asdasdas", "chenggong");
                        Toast.makeText(XiuGaiActivity.this, "头像修改成功", Toast.LENGTH_SHORT).show();
                        finish();

                    }
                },//
                image,//
                "imgFileName",//
                new OkHttpClientManager.Param("userId", String.valueOf(userId)));
    }


}
