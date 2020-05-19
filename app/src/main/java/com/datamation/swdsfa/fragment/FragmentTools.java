package com.datamation.swdsfa.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.datamation.swdsfa.OtherUploads.UploadAttendance;
import com.datamation.swdsfa.OtherUploads.UploadDebtorCordinates;
import com.datamation.swdsfa.OtherUploads.UploadDebtorImges;
import com.datamation.swdsfa.OtherUploads.UploadFirebaseTokenKey;
import com.datamation.swdsfa.OtherUploads.UploadSalRef;
import com.datamation.swdsfa.R;
import com.datamation.swdsfa.api.ApiCllient;
import com.datamation.swdsfa.api.ApiInterface;
import com.datamation.swdsfa.controller.AttendanceController;
import com.datamation.swdsfa.controller.BankController;
import com.datamation.swdsfa.controller.CompanyDetailsController;
import com.datamation.swdsfa.controller.CustomerController;
import com.datamation.swdsfa.controller.DayExpHedController;
import com.datamation.swdsfa.controller.DayNPrdHedController;
import com.datamation.swdsfa.controller.DiscdebController;
import com.datamation.swdsfa.controller.DiscdetController;
import com.datamation.swdsfa.controller.DischedController;
import com.datamation.swdsfa.controller.DiscslabController;
import com.datamation.swdsfa.controller.ExpenseController;
import com.datamation.swdsfa.controller.FInvhedL3Controller;
import com.datamation.swdsfa.controller.FItenrDetController;
import com.datamation.swdsfa.controller.FItenrHedController;
import com.datamation.swdsfa.controller.FinvDetL3Controller;
import com.datamation.swdsfa.controller.FirebaseMediaController;
import com.datamation.swdsfa.controller.FreeDebController;
import com.datamation.swdsfa.controller.FreeDetController;
import com.datamation.swdsfa.controller.FreeHedController;
import com.datamation.swdsfa.controller.FreeItemController;
import com.datamation.swdsfa.controller.FreeMslabController;
import com.datamation.swdsfa.controller.FreeSlabController;
import com.datamation.swdsfa.controller.InvDetController;
import com.datamation.swdsfa.controller.ItemController;
import com.datamation.swdsfa.controller.ItemLocController;
import com.datamation.swdsfa.controller.ItemPriceController;
import com.datamation.swdsfa.controller.LocationsController;
import com.datamation.swdsfa.controller.NearCustomerController;
import com.datamation.swdsfa.controller.NewCustomerController;
import com.datamation.swdsfa.controller.OrderController;
import com.datamation.swdsfa.controller.OutstandingController;
import com.datamation.swdsfa.controller.ReasonController;
import com.datamation.swdsfa.controller.ReceiptDetController;
import com.datamation.swdsfa.controller.ReferenceDetailDownloader;
import com.datamation.swdsfa.controller.ReferenceSettingController;
import com.datamation.swdsfa.controller.RouteController;
import com.datamation.swdsfa.controller.RouteDetController;
import com.datamation.swdsfa.controller.SalRepController;
import com.datamation.swdsfa.controller.TownController;
import com.datamation.swdsfa.customer.UploadEditedDebtors;
import com.datamation.swdsfa.customer.UploadNewCustomer;
import com.datamation.swdsfa.dialog.CustomProgressDialog;
import com.datamation.swdsfa.dialog.StockInquiryDialog;
import com.datamation.swdsfa.expense.UploadExpenses;
import com.datamation.swdsfa.helpers.NetworkFunctions;
import com.datamation.swdsfa.helpers.SharedPref;
import com.datamation.swdsfa.helpers.UploadTaskListener;
import com.datamation.swdsfa.model.Attendance;
import com.datamation.swdsfa.model.Bank;
import com.datamation.swdsfa.model.CompanyBranch;
import com.datamation.swdsfa.model.CompanySetting;
import com.datamation.swdsfa.model.Control;
import com.datamation.swdsfa.model.DayExpHed;
import com.datamation.swdsfa.model.DayNPrdDet;
import com.datamation.swdsfa.model.DayNPrdHed;
import com.datamation.swdsfa.model.Debtor;
import com.datamation.swdsfa.model.Discdeb;
import com.datamation.swdsfa.model.Discdet;
import com.datamation.swdsfa.model.Disched;
import com.datamation.swdsfa.model.Discslab;
import com.datamation.swdsfa.model.Expense;
import com.datamation.swdsfa.model.FInvhedL3;
import com.datamation.swdsfa.model.FItenrDet;
import com.datamation.swdsfa.model.FItenrHed;
import com.datamation.swdsfa.model.FddbNote;
import com.datamation.swdsfa.model.FinvDetL3;
import com.datamation.swdsfa.model.FirebaseData;
import com.datamation.swdsfa.model.FreeDeb;
import com.datamation.swdsfa.model.FreeDet;
import com.datamation.swdsfa.model.FreeHed;
import com.datamation.swdsfa.model.FreeItem;
import com.datamation.swdsfa.model.FreeMslab;
import com.datamation.swdsfa.model.FreeSlab;
import com.datamation.swdsfa.model.Item;
import com.datamation.swdsfa.model.ItemLoc;
import com.datamation.swdsfa.model.ItemPri;
import com.datamation.swdsfa.model.Locations;
import com.datamation.swdsfa.model.NearDebtor;
import com.datamation.swdsfa.model.NewCustomer;
import com.datamation.swdsfa.model.Order;
import com.datamation.swdsfa.model.Reason;
import com.datamation.swdsfa.model.Route;
import com.datamation.swdsfa.model.RouteDet;
import com.datamation.swdsfa.model.SalRep;
import com.datamation.swdsfa.model.Town;
import com.datamation.swdsfa.model.User;
import com.datamation.swdsfa.model.apimodel.TaskType;
import com.datamation.swdsfa.nonproductive.UploadNonProd;
import com.datamation.swdsfa.presale.UploadPreSales;
import com.datamation.swdsfa.utils.NetworkUtil;
import com.datamation.swdsfa.utils.UtilityContainer;
import com.datamation.swdsfa.view.DayExpenseActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.helper.HttpConnection;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.datamation.swdsfa.model.apimodel.TaskType.UPLOADPRESALES;

/***@Auther - rashmi**/

public class FragmentTools extends Fragment implements View.OnClickListener, UploadTaskListener {

    private Context context = getActivity();
    User loggedUser;
    View view;
    int count = 0;
    Animation animScale;
    ImageView imgSync, imgUpload, imgPrinter, imgDatabase, imgStockDown, imgStockInq, imgSalesRep, imgTour, imgDayExp, imgImage, imgVideo;
    NetworkFunctions networkFunctions;
    SharedPref pref;
    List<String> resultList;
    List<String> resultListExpense;
    LinearLayout layoutTool;
    private long timeInMillis;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    ArrayList<FirebaseData> imgList, vdoList;
    private Handler mHandler;
    ArrayList<FirebaseData> imgUrlList;
    ArrayList<FirebaseData> vdoUrlList;
    FirebaseData fd;
    DatabaseReference rootRef;
    FirebaseMediaController fmc;

    boolean isAnyActiveImages = false;
    boolean isAnyActiveVideos = false;

    boolean isImageFitToScreen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_management_tools, container, false);
        pref = SharedPref.getInstance(getActivity());

        animScale = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_scale);
        layoutTool = (LinearLayout) view.findViewById(R.id.layoutTool);
        imgTour = (ImageView) view.findViewById(R.id.imgTourInfo);
        imgStockInq = (ImageView) view.findViewById(R.id.imgStockInquiry);
        imgSync = (ImageView) view.findViewById(R.id.imgSync);
        imgUpload = (ImageView) view.findViewById(R.id.imgUpload);
        imgStockDown = (ImageView) view.findViewById(R.id.imgDownload);
        imgPrinter = (ImageView) view.findViewById(R.id.imgPrinter);
        imgDatabase = (ImageView) view.findViewById(R.id.imgSqlite);
        imgSalesRep = (ImageView) view.findViewById(R.id.imgSalrep);
        imgDayExp = (ImageView) view.findViewById(R.id.imgDayExp);
        imgImage = (ImageView) view.findViewById(R.id.imgImage);
        imgVideo = (ImageView) view.findViewById(R.id.imgVideo);
        mHandler = new Handler(Looper.getMainLooper());
        fmc = new FirebaseMediaController(getActivity());

        imgList = new ArrayList<FirebaseData>();
        vdoList = new ArrayList<FirebaseData>();

        rootRef = FirebaseDatabase.getInstance().getReference();

       // getImgDataFromFirebase(rootRef);
        getVdoDataFromFirebase(rootRef);

        isAnyActiveImages = new InvDetController(getActivity()).isAnyActiveOrders();
        isAnyActiveVideos = new ReceiptDetController(getActivity()).isAnyActiveReceipt();

        if (isAnyActiveImages) {
            imgImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_image));
        } else {
            imgImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_image));
        }

        if (isAnyActiveVideos) {
            imgVideo.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_video));
        } else {
            imgVideo.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_video));
        }
        networkFunctions = new NetworkFunctions(getActivity());
        imgTour.setOnClickListener(this);
        imgStockInq.setOnClickListener(this);
        imgSync.setOnClickListener(this);
        imgUpload.setOnClickListener(this);
        imgStockDown.setOnClickListener(this);
        imgPrinter.setOnClickListener(this);
        imgDatabase.setOnClickListener(this);
        imgSalesRep.setOnClickListener(this);
        imgDayExp.setOnClickListener(this);
        imgImage.setOnClickListener(this);
        imgVideo.setOnClickListener(this);
        resultList = new ArrayList<>();
        resultListExpense = new ArrayList<>();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        timeInMillis = System.currentTimeMillis();

        Log.d("FRAGMENT_TOOL", "IMAGE_FLAG: " + pref.getImageFlag());


        if (fmc.getAllMediaforCheckIfIsExist("IMG") > 0) {
            imgImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_img_notification));
        } else {
            imgImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_image));
        }

        if (fmc.getAllMediaforCheckIfIsExist("VDO") > 0) {
            imgVideo.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_video_notification));
        } else {
            imgVideo.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_video));
        }

        return view;
    }

    private void getVdoDataFromFirebase(DatabaseReference rootRef) {
        DatabaseReference chatSpaceRef = rootRef.child("Videos");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    try
                    {
                        int flag = ds.child("FLAG").getValue(Integer.class);
                        String mType = ds.child("M_TYPE").getValue(String.class);
                        List<Object> repCodeList = (List<Object>) ds.child("REPCODE").getValue();
                        String url = ds.child("URL").getValue(String.class);
                        if(repCodeList.size()>0)
                            if (repCodeList.contains(pref.getLoginUser().getCode()) && (flag == 0)) {
                                FirebaseData fd = new FirebaseData();
                                fd.setMEDIA_FLAG(flag + "");
                                fd.setMEDIA_URL(url);
                                fd.setMEDIA_TYPE(mType);
                                vdoList.add(fd);
                                Log.d("*TAG", url + "," + flag + "," + repCodeList + "" + pref.getLoginUser().getCode() + ", " + mType);
                            }
                    }
                    catch (Exception ex)
                    {
                        Toast.makeText(getActivity(),"Video Media Problem....",Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("*ERR", databaseError + "");
            }
        };
        chatSpaceRef.addListenerForSingleValueEvent(eventListener);
//        return vdoList;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.imgTourInfo:
                imgTour.startAnimation(animScale);
                UtilityContainer.mLoadFragment(new FragmentMarkAttendance(), getActivity());
                break;

            case R.id.imgStockInquiry:
                imgStockInq.startAnimation(animScale);//
                //mDevelopingMessage("Still under development", "Stock Inquiry");
                new StockInquiryDialog(getActivity());
                break;

            case R.id.imgSync:
                imgSync.startAnimation(animScale);

                //new DashboardController(getActivity()).subtractDay(new Date());
                Log.d("Validate Secondary Sync", ">>Mac>> " + pref.getMacAddress().trim() + " >>URL>> " + pref.getBaseURL() + " >>DB>> " + pref.getDistDB());
                new Validate(pref.getMacAddress().trim(), pref.getBaseURL(), pref.getDistDB()).execute();
                break;

            case R.id.imgUpload:
                imgUpload.startAnimation(animScale);
                //removeActives();
                syncDialog(getActivity());
                // mUploadDialog();
                break;

            case R.id.imgDownload:
                imgStockDown.startAnimation(animScale);
                UtilityContainer.mLoadFragment(new FragmentCategoryWiseDownload(), getActivity());
                //UtilityContainer.mLoadFragment(new CompetitorDetailsFragment(), getActivity());
                break;

            case R.id.imgPrinter:
                imgPrinter.startAnimation(animScale);
                UtilityContainer.mPrinterDialogbox(getActivity());
                break;

            case R.id.imgSqlite:
                imgDatabase.startAnimation(animScale);
                UtilityContainer.mSQLiteDatabase(getActivity());
                break;

            case R.id.imgSalrep:
                imgSalesRep.startAnimation(animScale);
                ViewRepProfile();
                break;

            case R.id.imgDayExp:
                imgDayExp.startAnimation(animScale);
//                UtilityContainer.mLoadFragment(new ExpenseDetail(), getActivity());
                Intent intent = new Intent(getActivity(), DayExpenseActivity.class);
                startActivity(intent);
                break;

            case R.id.imgImage:
                imgImage.startAnimation(animScale);
                imgUrlList = fmc.getAllMediafromDb("IMG");
                ViewImageList();
                break;

            case R.id.imgVideo:
                imgVideo.startAnimation(animScale);
                vdoUrlList = fmc.getAllMediafromDb("VDO");
                ViewVideoList();
                break;

        }

    }


    public void ViewImageList() {
        final Dialog imageDialog = new Dialog(getActivity());
        imageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        imageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        imageDialog.setCancelable(false);
        imageDialog.setCanceledOnTouchOutside(false);
        //imageDialog.setContentView(R.layout.test_constraint);
        imageDialog.setContentView(R.layout.whatsapp_image_layout);

        LinearLayout parentLayout = (LinearLayout) imageDialog.findViewById(R.id.image_layout);


        if (imgUrlList != null) {
            for (FirebaseData fd : imgUrlList) {
//            for (int i = 0; i < imgUrlList.size(); i++) {


                try {

                    File folder = new File(Environment.getExternalStorageDirectory() + "/" + "SWDIMAGES");
                    boolean success = true;
                    if (!folder.exists()) {
                        success = folder.mkdir();
                    }
                    if (success) {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                        Date date = new Date();
                        System.out.println(dateFormat.format(date));

                        File sd = Environment.getExternalStorageDirectory();
                     //   File data = Environment.getDataDirectory();

                        if (sd.canWrite()) {

                            String pathName = "" + fd.getMEDIA_URL();
                            URL url = new URL(fd.getMEDIA_URL());
                            URLConnection conection = url.openConnection();
                            conection.connect();
                            int lenghtOfFile = conection.getContentLength();
                            String backupImgesPath = "//SWDIMAGES//backupname_"+dateFormat.format(date).toString()+".PNG"; // From SD directory.
                            File mypath = new File(new File(""), pathName);

                            // input stream to read file - with 8k buffer
                            InputStream input = new BufferedInputStream(url.openStream(), 1000);

                            // Output stream to write file
                            OutputStream output = new FileOutputStream(mypath);
                            byte data[] = new byte[1024];

                            long total = 0;
                            while ((count = input.read(data)) != -1) {
                               total += count;
                                // writing data to file
                                output.write(data, 0, count);
//                                onProgressUpdate("" + (int) ((total * 100) / lenghtOfFile), "" + count, "" + i);
                            }
                            // flushing output
                            output.flush();

                            // closing streams
                            output.close();
                          //  input.close();
                            Toast.makeText(getActivity(), "Images Saved Successful!,Check your gallery",Toast.LENGTH_SHORT).show();




                        }
                    }else {
                        // Do something else on failure
                        Toast.makeText(getActivity(), "Images Saved Failed!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    Log.e("error", e.toString());
                    Toast.makeText(getActivity(), "Images Saved Failed!", Toast.LENGTH_SHORT).show();

                }




//                try {
//                    final ImageView imageButton = new ImageView(getActivity());
//                    final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(400, 400);
//                    lp.setMargins(20, 20, 20, 20);
//                    imageButton.setLayoutParams(lp);
//
//                    Glide.with(this)
//                            .load(fd.getMEDIA_URL())
//                            .into(imageButton);
//
//                    imageButton.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            if (isImageFitToScreen) {
//                                isImageFitToScreen = false;
//                                imageButton.setLayoutParams(lp);
//                                imageButton.setAdjustViewBounds(true);
//                            } else {
//                                isImageFitToScreen = true;
//                                imageButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//                                imageButton.setScaleType(ImageView.ScaleType.FIT_XY);
//                            }
//                        }
//                    });
//
//                    parentLayout.addView(imageButton);
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
            }
        } else {
            Toast.makeText(getActivity(), "No Images to show!", Toast.LENGTH_SHORT).show();
        }

        imageDialog.findViewById(R.id.got_it).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int result = fmc.createOrUpdateFirebaseData(imgList, 1);
                if (result > 0) {
                    Toast.makeText(getActivity(), "Image flag updated", Toast.LENGTH_SHORT).show();
                }

                if (fmc.getAllMediaforCheckIfIsExist("IMG") > 0) {
                    imgImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_img_notification));
                } else {
                    imgImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_image));
                }
                imageDialog.dismiss();
            }
        });

        imageDialog.show();
    }


    public void ViewVideoList() {
        final Dialog videoDialog = new Dialog(getActivity());
        videoDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        videoDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        videoDialog.setCancelable(false);
        videoDialog.setCanceledOnTouchOutside(false);
        //videoDialog.setContentView(R.layout.whatsapp_video_responsive_layout);
        videoDialog.setContentView(R.layout.whatsapp_video_layout);

        LinearLayout parentLayout = (LinearLayout) videoDialog.findViewById(R.id.video_layout);

        for (FirebaseData fd : vdoUrlList) {
            try {
                final fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard videoView = new fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard(getActivity());
                final LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(475, 250);
                lp.setMargins(20, 20, 20, 20);
                videoView.setLayoutParams(lp);

                String pathName = "" + fd.getMEDIA_URL();

                videoView.setUp(pathName, "SWADESHI");
                videoView.ivThumb.setImageDrawable(getResources().getDrawable(R.drawable.video));

                parentLayout.addView(videoView);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        videoDialog.findViewById(R.id.got_it).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int vdoresult = fmc.createOrUpdateFirebaseData(vdoList, 1);
                if (vdoresult > 0) {
                    Toast.makeText(getActivity(), "Video flag updated", Toast.LENGTH_SHORT).show();
                }

                if (fmc.getAllMediaforCheckIfIsExist("VDO") > 0) {
                    imgVideo.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_video_notification));
                } else {
                    imgVideo.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_video));
                }
                videoDialog.dismiss();
            }
        });

        videoDialog.show();
    }


    public void ViewRepProfile() {
        final Dialog repDialog = new Dialog(getActivity());
        repDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        repDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        repDialog.setCancelable(false);
        repDialog.setCanceledOnTouchOutside(false);
        repDialog.setContentView(R.layout.rep_profile);

        //initializations
        TextView repname = (TextView) repDialog.findViewById(R.id.repname);
        final TextView repcode = (TextView) repDialog.findViewById(R.id.repcode);
        final TextView repPrefix = (TextView) repDialog.findViewById(R.id.repPrefix);
        // final TextView locCode = (TextView) repDialog.findViewById(R.id.target);
        final EditText repemail = (EditText) repDialog.findViewById(R.id.email);
        final TextView areaCode = (TextView) repDialog.findViewById(R.id.areaCode);
        final TextView dealCode = (TextView) repDialog.findViewById(R.id.dealclode);
        //  areaCode.setText(loggedUser.getRoute());
        final SalRep rep = new SalRepController(getActivity()).getSaleRepDet(new SalRepController(getActivity()).getCurrentRepCode());

        repname.setText(rep.getNAME());
        repcode.setText(rep.getRepCode());
        repPrefix.setText(rep.getPREFIX());
        //  locCode.setText("0");

        repemail.setText(rep.getEMAIL());
        dealCode.setText(rep.getDEALCODE());


        //close
        repDialog.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (repemail.length() > 0) {
                    if (isEmailValid(repemail.getText().toString())) {
                        ArrayList<SalRep> salRepslist = new ArrayList<>();
                        rep.setEMAIL(repemail.getText().toString().trim());
                        rep.setISSYNC("0");
                        salRepslist.add(rep);

                        new SalRepController(getActivity()).createOrUpdateSalRep(salRepslist);

                        repDialog.dismiss();

                    } else {
                        Toast.makeText(getActivity(), "Invalid email address, Please Try Again", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    repDialog.dismiss();
                }

            }
        });


        repDialog.show();
    }

    public boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }

    private void syncDialog(final Context context) {

        MaterialDialog materialDialog = new MaterialDialog.Builder(getActivity())
                .content("Are you sure, Do you want to Upload Data?")
                .positiveColor(ContextCompat.getColor(getActivity(), R.color.material_alert_positive_button))
                .positiveText("Yes")
                .negativeColor(ContextCompat.getColor(getActivity(), R.color.material_alert_negative_button))
                .negativeText("No, Exit")
                .callback(new MaterialDialog.ButtonCallback() {

                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);


                        boolean connectionStatus = NetworkUtil.isNetworkAvailable(context);
                        if (connectionStatus == true) {
///////////////////////************************************************/////////////////////////////////////////////////////////////////
                            OrderController orderHed = new OrderController(getActivity());
                            final ArrayList<Order> ordHedList = orderHed.getAllUnSyncOrdHed();//1
                            DayNPrdHedController npHed = new DayNPrdHedController(getActivity());
                            final ArrayList<DayNPrdHed> npHedList = npHed.getUnSyncedData();//2
                            SalRepController salRepController = new SalRepController(getActivity());//3
                            ArrayList<SalRep> saleRepList = salRepController.getAllUnsyncSalrep(new SalRepController(context).getCurrentRepCode());
                            AttendanceController attendanceController = new AttendanceController(getActivity());//4
                            ArrayList<Attendance> attendList = attendanceController.getUnsyncedTourData();
                            CustomerController customerDS = new CustomerController(getActivity());
                            ArrayList<Debtor> debtorlist = customerDS.getAllDebtorsToCordinatesUpdate();//5
                            ArrayList<Debtor> updExistingDebtors = customerDS.getAllUpdatedDebtors();//6
                            ArrayList<Debtor> imgDebtorList = customerDS.getAllImagUpdatedDebtors();//7
                            DayExpHedController exHed = new DayExpHedController(getActivity());
                            final ArrayList<DayExpHed> exHedList = exHed.getUnSyncedData();//8
                            NewCustomerController customerNwDS = new NewCustomerController(getActivity());
                            ArrayList<NewCustomer> newCustomersList = customerNwDS.getAllNewCustomersForSync();//9
                            // firebasetokenid - 10
//                    /* If records available for upload then */
                                if (ordHedList.size() <= 0 && npHedList.size() <= 0 && saleRepList.size() <= 0 && attendList.size()<= 0 && debtorlist.size()<=0 && updExistingDebtors.size() <= 0 && imgDebtorList.size()<= 0 && exHedList.size()<=0)
                                {
                                    Toast.makeText(getActivity(), "No Records to upload !", Toast.LENGTH_LONG).show();
                                }else {
                                    if(ordHedList.size()>0){
                                        Toast.makeText(getActivity(), "Presale data upload completed..!", Toast.LENGTH_LONG).show();
                                    }
//                                   List<String> testList = new ArrayList<>();
//                                    for(final Order order :ordHedList) {
                                        new UploadPreSales(getActivity(), FragmentTools.this, UPLOADPRESALES).execute(ordHedList);
//                                        try {
//                                            String content_type = "application/json";
//                                            ApiInterface apiInterface = ApiCllient.getClient(context).create(ApiInterface.class);
//                                            JsonParser jsonParser = new JsonParser();
//                                            String orderJson = new Gson().toJson(order);
//                                            JsonObject objectFromString = jsonParser.parse(orderJson).getAsJsonObject();
//                                            JsonArray jsonArray = new JsonArray();
//                                            jsonArray.add(objectFromString);
//                                            Call<String> resultCall = apiInterface.uploadOrder(jsonArray, content_type);
//
//                                            resultCall.enqueue(new Callback<String>() {
//                                                @Override
//                                                public void onResponse(Call<String> call, Response<String> response) {
//
//
//                                                    int status = response.code();
//                                                    Log.d(">>>response code", ">>>res " + status);
//                                                    Log.d(">>>response message", ">>>res " + response.message());
//                                                    Log.d(">>>response body", ">>>res " + response.body().toString());
//                                                    String resmsg = ""+response.body().toString();
//                                                    int resLength = response.body().toString().trim().length();
//                                                    Log.d(">>>resLength", ">>>resLength " +resLength);
//                                                    //  Log.d(">>>resrefno", ">>>res " + response.body().toString().trim().substring(3,resLength));
//
//
//                                                    if (status == 200 && !resmsg.equals("") && !resmsg.equals(null)) {
//                                                        mHandler.post(new Runnable() {
//                                                            @Override
//                                                            public void run() {
//
//                                                                // resultListNonProduct.add(np.getNONPRDHED_REFNO()+ "--->SUCCESS");
//                                                                //    addRefNoResults_Non(np.getNONPRDHED_REFNO() + " --> Success\n",RCSList.size());
//                                                                //  Log.d( ">>response"+status,""+c.getORDER_REFNO() );
//                                                                order.setORDER_IS_SYNCED("1");
//                                                                // new OrderController(context).updateIsSynced(c);
//                                                                new OrderController(context).updateIsSynced(order.getORDER_REFNO(),"1");
//                                                                //  Toast.makeText(context,np.getNONPRDHED_REFNO()+"-Non-productive uploded Successfully" , Toast.LENGTH_SHORT).show();
//
//                                                            }
//                                                        });
//                                                        //addRefNoResults(c.getORDER_REFNO() +" --> Success\n",RCSList.size());
//
//                                                        //  Toast.makeText(context, c.getORDER_REFNO()+" - Order uploded Successfully", Toast.LENGTH_SHORT).show();
//                                                    } else {
//                                                        Log.d( ">>response"+status,""+order.getORDER_REFNO() );
//                                                        mHandler.post(new Runnable() {
//                                                            @Override
//                                                            public void run() {
//
//                                                                // resultListNonProduct.add(np.getNONPRDHED_REFNO()+ "--->SUCCESS");
//                                                                //    addRefNoResults_Non(np.getNONPRDHED_REFNO() + " --> Success\n",RCSList.size());
//                                                                //  Log.d( ">>response"+status,""+c.getORDER_REFNO() );
//                                                                order.setORDER_IS_SYNCED("0");
//                                                                new OrderController(context).updateIsSynced(order.getORDER_REFNO(),"0");
//                                                                //  Toast.makeText(context,np.getNONPRDHED_REFNO()+"-Non-productive uploded Successfully" , Toast.LENGTH_SHORT).show();
//
//                                                            }
//                                                        });
//                                                        //  addRefNoResults(c.getORDER_REFNO() +" --> Success\n",RCSList.size());
//                                                        //   Toast.makeText(context, c.getORDER_REFNO()+" - Order uplod Failed", Toast.LENGTH_SHORT).show();
//                                                    }
//
//                                                }
//
//                                                @Override
//                                                public void onFailure(Call<String> call, Throwable t) {
//
//                                                    Toast.makeText(context, "Error response "+t.toString(), Toast.LENGTH_SHORT).show();
//
//                                                }
//                                            });
//
//
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                    onTaskCompleted(UPLOADPRESALES,testList);
                                }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            try { // upload Non productive 04-03-2020 kaveesha


                            } catch (Exception e) {
                                Log.v("Exception in nonProduct", e.toString());
                            }


                        } else
                            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                        dialog.dismiss();
                    }
                })
                .build();
        materialDialog.setCanceledOnTouchOutside(false);
        materialDialog.show();
    }

    public void mDevelopingMessage(String message, String title) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setIcon(R.drawable.info);


        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
        alertD.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

    }

    private void syncMasterDataDialog(final Context context) {

        MaterialDialog materialDialog = new MaterialDialog.Builder(getActivity())
                .content("Are you sure, Do you want to Sync Master Data?")
                .positiveColor(ContextCompat.getColor(getActivity(), R.color.material_alert_positive_button))
                .positiveText("Yes")
                .negativeColor(ContextCompat.getColor(getActivity(), R.color.material_alert_negative_button))
                .negativeText("No, Exit")
                .callback(new MaterialDialog.ButtonCallback() {

                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);

                        boolean connectionStatus = NetworkUtil.isNetworkAvailable(getActivity());
                        if (connectionStatus == true) {

                            if (isAllUploaded()) {
                                dialog.dismiss();
                                try {
                                    new secondarySync(SharedPref.getInstance(getActivity()).getLoginUser().getCode()).execute();
                                    SharedPref.getInstance(getActivity()).setGlobalVal("SyncDate", dateFormat.format(new Date(timeInMillis)));
                                } catch (Exception e) {
                                    Log.e("## ErrorIn2ndSync ##", e.toString());
                                }
                            } else {
                                Toast.makeText(context, "Please Upload All Transactions", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(context, "No Internet Connection", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                        dialog.dismiss();
                    }
                })
                .build();
        materialDialog.setCanceledOnTouchOutside(false);
        materialDialog.show();
    }

    private boolean isAllUploaded() {
        Boolean allUpload = false;

        OrderController orderHed = new OrderController(getActivity());
        ArrayList<Order> ordHedList = orderHed.getAllUnSyncOrdHed();

        DayNPrdHedController npHed = new DayNPrdHedController(getActivity());
        ArrayList<DayNPrdHed> npHedList = npHed.getUnSyncedData();

        DayExpHedController exHed = new DayExpHedController(getActivity());
        ArrayList<DayExpHed> exHedList = exHed.getUnSyncedData();

        ArrayList<Debtor> imgUpdDebtors = new CustomerController(getActivity()).getAllImagUpdatedDebtors();
        // ArrayList<ReceiptHed> rcptHedList = receipts.getAllCompletedRecHed();

       // if (ordHedList.isEmpty() && npHedList.isEmpty() && exHedList.isEmpty() && imgUpdDebtors.isEmpty()) {
        if (ordHedList.isEmpty() && npHedList.isEmpty()) {
            allUpload = true;
        } else {
            allUpload = false;
        }

        return allUpload;
    }

//    private void getImgDataFromFirebase(DatabaseReference rootRef) {
//        DatabaseReference chatSpaceRef = rootRef.child("Images");
//        ValueEventListener eventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//
//                    try
//                    {
//                        int flag = ds.child("FLAG").getValue(Integer.class);
//                        String mType = ds.child("M_TYPE").getValue(String.class);
//                        List<String> repCodeList = (List<String>) ds.child("REPCODE").getValue();
//                        String url = ds.child("URL").getValue(String.class);
//                        if(repCodeList.size()>0)
//                            if (repCodeList.contains(pref.getLoginUser().getCode()) && (flag == 0)) {
//                                fd = new FirebaseData();
//                                fd.setMEDIA_FLAG(flag + "");
//                                fd.setMEDIA_URL(url);
//                                fd.setMEDIA_TYPE(mType);
//                                imgList.add(fd);
//                               // Log.d("*TAG", url + "," + flag + "," + repCodeList + "" + pref.getLoginUser().getCode() + " " + mType);
//                            }
//                    }
//                    catch (Exception ex)
//                    {
//                        Toast.makeText(getActivity(),"Image Media Problem....",Toast.LENGTH_SHORT).show();
//                    }
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.d("*ERR", databaseError + "");
//            }
//        };
//        chatSpaceRef.addListenerForSingleValueEvent(eventListener);
////        return imgList;
//    }


    @Override
    public void onTaskCompleted(TaskType taskType, List<String> list) {
        resultList.addAll(list);
        switch (taskType) {
            case UPLOADPRESALES: {
                    DayNPrdHedController npHed = new DayNPrdHedController(getActivity());
                    final ArrayList<DayNPrdHed> npHedList = npHed.getUnSyncedData();
                    if(npHedList.size()>0){
                    Toast.makeText(getActivity(), "Nonproductive data upload completed..!", Toast.LENGTH_LONG).show();
                }
                    new UploadNonProd(getActivity(), FragmentTools.this, TaskType.UPLOAD_NONPROD).execute(npHedList);

                    Log.v(">>upload>>", "Upload non productive execute finish");
            }
            break;
            case UPLOAD_NONPROD:{
                SalRepController salRepController = new SalRepController(getActivity());
                ArrayList<SalRep> saleRep = salRepController.getAllUnsyncSalrep(new SalRepController(getActivity()).getCurrentRepCode());
                new UploadSalRef(getActivity(), FragmentTools.this,saleRep, TaskType.UPLOAD_SALREP).execute(saleRep);
                Log.v(">>upload>>", "Upload repemail execute finish");
            }
            break;
            case UPLOAD_SALREP:{
                AttendanceController attendanceController = new AttendanceController(getActivity());//4
                ArrayList<Attendance> attendList = attendanceController.getUnsyncedTourData();
                new UploadAttendance(getActivity(), FragmentTools.this,attendList, TaskType.UPLOAD_ATTENDANCE).execute(attendList);
                Log.v(">>upload>>", "Upload attendance execute finish");
            }
            break;
            case UPLOAD_ATTENDANCE:{
                DayExpHedController exHed = new DayExpHedController(getActivity());
                final ArrayList<DayExpHed> exHedList = exHed.getUnSyncedData();//8
                if(exHedList.size()>0){
                    Toast.makeText(getActivity(), "Expense data upload completed..!", Toast.LENGTH_LONG).show();
                }
                new UploadExpenses(getActivity(), FragmentTools.this, TaskType.UPLOAD_EXPENSE).execute(exHedList);
                Log.v(">>upload>>", "Upload expense execute finish");
            }
            break;
            case UPLOAD_EXPENSE:{
                CustomerController customerDS = new CustomerController(getActivity());
                ArrayList<Debtor> debtorlist = customerDS.getAllDebtorsToCordinatesUpdate();//5
                new UploadDebtorCordinates(getActivity(), FragmentTools.this,debtorlist, TaskType.UPLOAD_COORDINATES).execute(debtorlist);
                Log.v(">>upload>>", "Upload DebtorCordinates execute finish");
            }
            break;
            case UPLOAD_COORDINATES:{
                CustomerController customerDS = new CustomerController(getActivity());
                ArrayList<Debtor> imgDebtorList = customerDS.getAllImagUpdatedDebtors();//7
                new UploadDebtorImges(getActivity(), FragmentTools.this, imgDebtorList,TaskType.UPLOAD_IMAGES).execute();
                Log.v(">>upload>>", "Upload imgDebtor execute finish");
            }
            break;
            case UPLOAD_IMAGES:{
                NewCustomerController customerDS = new NewCustomerController(getActivity());
                ArrayList<NewCustomer> newCustomersList = customerDS.getAllNewCustomersForSync();
                new UploadNewCustomer(getActivity(), FragmentTools.this, newCustomersList,TaskType.UPLOAD_NEWCUS).execute();
                Log.v(">>upload>>", "Upload NewCustomer execute finish");
            }
            break;
            case UPLOAD_NEWCUS:{
                CustomerController customerDS = new CustomerController(getActivity());
                ArrayList<Debtor> updExistingDebtors = customerDS.getAllUpdatedDebtors();
                new UploadEditedDebtors(getActivity(), FragmentTools.this, updExistingDebtors,TaskType.UPLOAD_EDTCUS).execute();
                Log.v(">>upload>>", "Upload EditedDebtors execute finish");
            }
            break;
            case UPLOAD_EDTCUS:{
                ArrayList<SalRep> fblist = new ArrayList<>();

                SalRep salRep = new SalRep();
                salRep.setCONSOLE_DB(SharedPref.getInstance(context).getConsoleDB().trim());
                salRep.setDIST_DB(SharedPref.getInstance(context).getDistDB().trim());
                salRep.setRepCode(SharedPref.getInstance(context).getLoginUser().getCode());
                salRep.setFirebaseTokenID(SharedPref.getInstance(context).getFirebaseTokenKey());
                fblist.add(salRep);
                new UploadFirebaseTokenKey(getActivity(), FragmentTools.this, fblist,TaskType.UPLOAD_TKN).execute();
                Log.v(">>upload>>", "Upload FirebaseToken execute finish");

            }
            break;
            case UPLOAD_TKN:{
                Log.v(">>upload>>", "all upload finish");

               // resultList.addAll(list);
                String msg = "";
                for (String s : resultList) {
                    msg += s;
                }
                resultList.clear();
                mUploadResult(msg);
            }
            break;
            default:
                break;
        }
    }
    @Override
    public void onTaskCompleted(List<String> list) {

    }

    //**********************secondary sysnc start***********************************************/
    private class secondarySync extends AsyncTask<String, Integer, Boolean> {
        int totalRecords = 0;
        CustomProgressDialog pdialog;
        private String repcode;
        private List<String> errors = new ArrayList<>();

        public secondarySync(String repCode) {
            this.repcode = repCode;
            this.pdialog = new CustomProgressDialog(getActivity());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdialog = new CustomProgressDialog(getActivity());
            pdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            pdialog.setMessage("Authenticating...");
            pdialog.show();
        }

        @Override
        protected Boolean doInBackground(String... arg0) {


            int totalBytes = 0;

            try {
                if (SharedPref.getInstance(getActivity()).getLoginUser() != null && SharedPref.getInstance(getActivity()).isLoggedIn()) {

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Downloading firebase media data...");
                        }
                    });
                    Log.d("**$#*", "getImgDataFromFirebase: " + imgList);

                    if (imgList.size() > 0) {
                        int existImgRes = fmc.getAllIfIsExist(imgList);
                        if (existImgRes > 0) {
                            fmc.createOrUpdateFirebaseData(imgList, 0);
                        } else {
                            fmc.deleteAll("IMG");
                            fmc.createOrUpdateFirebaseData(imgList, 0);
                        }
                    }
/*****************controls**********************************************************************/

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Downloading company control data...");
                        }
                    });

                    String controls = "";
                    try {
                        controls = networkFunctions.getCompanyDetails(repcode);
                        // Log.d(LOG_TAG, "OUTLETS :: " + outlets);
                    } catch (IOException e) {
                        e.printStackTrace();

                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Downloading firebase media data...");
                        }
                    });
                    Log.d("*newvdoList", "doInBackground: " + vdoList);

                    if (vdoList.size() > 0) {
                        int existVdoRes = fmc.getAllIfIsExist(vdoList);
                        if (existVdoRes > 0) {
                            fmc.createOrUpdateFirebaseData(vdoList, 0);
                        } else {
                            fmc.deleteAll("VDO");
                            fmc.createOrUpdateFirebaseData(vdoList, 0);
                        }
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (Company details)...");
                        }
                    });

                    // Processing controls
                    try {
                        JSONObject controlJSON = new JSONObject(controls);
                        JSONArray controlJSONArray = controlJSON.getJSONArray("fControlResult");
                        ArrayList<Control> controlList = new ArrayList<Control>();
                        CompanyDetailsController companyController = new CompanyDetailsController(getActivity());
                        for (int i = 0; i < controlJSONArray.length(); i++) {
                            controlList.add(Control.parseControlDetails(controlJSONArray.getJSONObject(i)));
                        }
                        companyController.createOrUpdateFControl(controlList);
                    } catch (JSONException | NumberFormatException e) {

                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
/*****************outlets**********************************************************************/
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Downloading Customers...");
                        }
                    });


                    String outlets = "";
                    try {
                        outlets = networkFunctions.getCustomer(repcode);
                        // Log.d(LOG_TAG, "OUTLETS :: " + outlets);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (customer details)...");
                        }
                    });

                    // Processing outlets
                    try {
                        JSONObject customersJSON = new JSONObject(outlets);
                        JSONArray customersJSONArray = customersJSON.getJSONArray("FdebtorResult");
                        ArrayList<Debtor> customerList = new ArrayList<Debtor>();
                        CustomerController customerController = new CustomerController(getActivity());
                        customerController.deleteAll();
                        for (int i = 0; i < customersJSONArray.length(); i++) {
                            customerList.add(Debtor.parseOutlet(customersJSONArray.getJSONObject(i)));
                        }

                        customerController.InsertOrReplaceDebtor(customerList);
                        Log.d("InsertOrReplaceDebtor", "succes");

                    } catch (JSONException | NumberFormatException e) {

                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Customers downloaded\nDownloading Near Customers...");
                        }
                    });
                    /*****************end Customers**********************************************************************/

                    // ----------------Near Customer-------------------- Nuwan ------------- 17/10/2019--------------------------

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Downloading Near Customers...");
                        }
                    });

                    String nearOutlets = "";
                    try {
                        nearOutlets = networkFunctions.getNearCustomer();

                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (near customer details)...");
                        }
                    });

                    // Processing outlets
                    try {
                        JSONObject nCustomersJSON = new JSONObject(nearOutlets);
                        JSONArray nCustomersJSONArray = nCustomersJSON.getJSONArray("FnearDebtorResult");
                        ArrayList<NearDebtor> nDebList = new ArrayList<NearDebtor>();
                        NearCustomerController nCustomerController = new NearCustomerController(getActivity());
                        for (int i = 0; i < nCustomersJSONArray.length(); i++) {
                            nDebList.add(NearDebtor.parseNearOutlet(nCustomersJSONArray.getJSONObject(i)));
                        }

                        if (nCustomerController.deleteAll() > 0) {
                            nCustomerController.InsertOrReplaceNearDebtor(nDebList);
                        }

                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
                        throw e;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Near Customers downloaded\nDownloading Company Settings...");
                        }
                    });

                    // --------------------------------------------------------------------------------------------------
                    /*****************Settings*****************************************************************************/

                    String comSettings = "";
                    try {
                        comSettings = networkFunctions.getReferenceSettings();
                        // Log.d(LOG_TAG, "OUTLETS :: " + outlets);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (setting details)...");
                        }
                    });

                    // Processing company settings
                    ReferenceSettingController settingController = new ReferenceSettingController(getActivity());
                    settingController.deleteAll();
                    try {
                        JSONObject settingJSON = new JSONObject(comSettings);
                        JSONArray settingsJSONArray = settingJSON.getJSONArray("fCompanySettingResult");
                        ArrayList<CompanySetting> settingList = new ArrayList<CompanySetting>();

                        for (int i = 0; i < settingsJSONArray.length(); i++) {
                            settingList.add(CompanySetting.parseSettings(settingsJSONArray.getJSONObject(i)));
                        }
                        settingController.createOrUpdateFCompanySetting(settingList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }

                    /*****************end Settings**********************************************************************/
/*****************Branches*****************************************************************************/

                    String comBranches = "";
                    try {
                        comBranches = networkFunctions.getReferences(repcode);
                        // Log.d(LOG_TAG, "OUTLETS :: " + outlets);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (setting details)...");
                        }
                    });

                    // Processing Branches
                    ReferenceDetailDownloader branchController = new ReferenceDetailDownloader(getActivity());
                    branchController.deleteAll();
                    try {
                        JSONObject settingJSON = new JSONObject(comBranches);
                        JSONArray settingsJSONArray = settingJSON.getJSONArray("FCompanyBranchResult");
                        ArrayList<CompanyBranch> settingList = new ArrayList<CompanyBranch>();

                        for (int i = 0; i < settingsJSONArray.length(); i++) {
                            settingList.add(CompanyBranch.parseSettings(settingsJSONArray.getJSONObject(i)));
                        }
                        branchController.createOrUpdateFCompanyBranch(settingList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }

                    /*****************end Branches**********************************************************************/
                    /*****************Item Loc*****************************************************************************/

                    String itemLocs = "";
                    try {
                        itemLocs = networkFunctions.getItemLocations(repcode);
                        // Log.d(LOG_TAG, "OUTLETS :: " + outlets);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (item location details)...");
                        }
                    });

                    ItemLocController itemlocController = new ItemLocController(getActivity());
                    itemlocController.deleteAllItemLoc();
                    // Processing itemLocations
                    try {
                        JSONObject itemLocJSON = new JSONObject(itemLocs);
                        JSONArray settingsJSONArray = itemLocJSON.getJSONArray("fItemLocResult");
                        ArrayList<ItemLoc> itemLocList = new ArrayList<ItemLoc>();
                        for (int i = 0; i < settingsJSONArray.length(); i++) {
                            itemLocList.add(ItemLoc.parseItemLocs(settingsJSONArray.getJSONObject(i)));
                        }
                        itemlocController.InsertOrReplaceItemLoc(itemLocList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }

                    /*****************end Item Loc**********************************************************************/
                    /*****************Locations*****************************************************************************/

                    String locations = "";
                    try {
                        locations = networkFunctions.getLocations(repcode);
                        // Log.d(LOG_TAG, "OUTLETS :: " + outlets);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (location details)...");
                        }
                    });
                    LocationsController locController = new LocationsController(getActivity());
                    locController.deleteAll();
                    // Processing itemLocations
                    try {
                        JSONObject locJSON = new JSONObject(locations);
                        JSONArray locJSONArray = locJSON.getJSONArray("fLocationsResult");
                        ArrayList<Locations> locList = new ArrayList<Locations>();

                        for (int i = 0; i < locJSONArray.length(); i++) {
                            locList.add(Locations.parseLocs(locJSONArray.getJSONObject(i)));
                        }
                        locController.createOrUpdateFLocations(locList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************itemPrices*****************************************************************************/

                    String itemPrices = "";
                    try {
                        itemPrices = networkFunctions.getItemPrices(repcode);
                        // Log.d(LOG_TAG, "OUTLETS :: " + outlets);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (item price details)...");
                        }
                    });
                    ItemPriceController priceController = new ItemPriceController(getActivity());
                    priceController.deleteAllItemPri();
                    // Processing itemPrices
                    try {
                        JSONObject itemPriceJSON = new JSONObject(itemPrices);
                        JSONArray itemPriceJSONArray = itemPriceJSON.getJSONArray("fItemPriResult");
                        ArrayList<ItemPri> itemPriceList = new ArrayList<ItemPri>();

                        for (int i = 0; i < itemPriceJSONArray.length(); i++) {
                            itemPriceList.add(ItemPri.parseItemPrices(itemPriceJSONArray.getJSONObject(i)));
                        }
                        priceController.InsertOrReplaceItemPri(itemPriceList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end item prices**********************************************************************/
                    /*****************Items*****************************************************************************/

                    String item = "";
                    try {
                        item = networkFunctions.getItems(repcode);
                        // Log.d(LOG_TAG, "OUTLETS :: " + outlets);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (item details)...");
                        }
                    });

                    ItemController itemController = new ItemController(getActivity());
                    itemController.deleteAll();
                    // Processing Items
                    try {
                        JSONObject itemJSON = new JSONObject(item);
                        JSONArray itemJSONArray = itemJSON.getJSONArray("fItemsResult");
                        ArrayList<Item> itemList = new ArrayList<Item>();
                        for (int i = 0; i < itemJSONArray.length(); i++) {
                            itemList.add(Item.parseItem(itemJSONArray.getJSONObject(i)));
                        }
                        itemController.InsertOrReplaceItems(itemList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end Items **********************************************************************/
                    //                    /*****************reasons**********************************************************************/
//
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Items downloaded\nDownloading reasons...");
                        }
                    });
                    String reasons = "";
                    try {
                        reasons = networkFunctions.getReasons();
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (reasons)...");
                        }
                    });

                    ReasonController reasonController = new ReasonController(getActivity());
                    reasonController.deleteAll();
                    // Processing reasons
                    try {
                        JSONObject reasonJSON = new JSONObject(reasons);
                        JSONArray reasonJSONArray = reasonJSON.getJSONArray("fReasonResult");
                        ArrayList<Reason> reasonList = new ArrayList<Reason>();
                        for (int i = 0; i < reasonJSONArray.length(); i++) {
                            reasonList.add(Reason.parseReason(reasonJSONArray.getJSONObject(i)));
                        }
                        Log.d("befor add reason tbl>>>", reasonList.toString());
                        reasonController.createOrUpdateReason(reasonList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end reasons**********************************************************************/
                    /*****************fddbnote*****************************************************************************/
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Reason downloaded\nDownloading outstanding details...");
                        }
                    });
                    String fddbnote = "";
                    try {
                        fddbnote = networkFunctions.getFddbNotes(repcode);
                        // Log.d(LOG_TAG, "OUTLETS :: " + outlets);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (outstanding details)...");
                        }
                    });

                    // Processing fddbnote

                    OutstandingController outstandingController = new OutstandingController(getActivity());
                    outstandingController.deleteAll();
                    try {
                        JSONObject fddbnoteJSON = new JSONObject(fddbnote);
                        JSONArray fddbnoteJSONArray = fddbnoteJSON.getJSONArray("fDdbNoteWithConditionResult");
                        ArrayList<FddbNote> fddbnoteList = new ArrayList<FddbNote>();
                        for (int i = 0; i < fddbnoteJSONArray.length(); i++) {
                            fddbnoteList.add(FddbNote.parseFddbnote(fddbnoteJSONArray.getJSONObject(i)));
                        }
                        outstandingController.createOrUpdateFDDbNote(fddbnoteList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("outstanding downloaded\nDownloading banks...");
                        }
                    });
//
//
//                    /*****************expenses**********************************************************************/
//
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            pdialog.setMessage("Reasons downloaded\nDownloading expense details...");
//                        }
//                    });


                    /*****************Banks**********************************************************************/
                    String banks = "";
                    try {
                        banks = networkFunctions.getBanks();
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (banks)...");
                        }
                    });
                    BankController bankController = new BankController(getActivity());
                    bankController.deleteAll();
                    // Processing route
                    try {
                        JSONObject bankJSON = new JSONObject(banks);
                        JSONArray bankJSONArray = bankJSON.getJSONArray("fbankResult");
                        ArrayList<Bank> bankList = new ArrayList<Bank>();

                        for (int i = 0; i < bankJSONArray.length(); i++) {
                            bankList.add(Bank.parseBank(bankJSONArray.getJSONObject(i)));
                        }
                        bankController.createOrUpdateBank(bankList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end banks**********************************************************************/
                    String expenses = "";
                    try {
                        expenses = networkFunctions.getExpenses();
                    } catch (IOException e) {
                        errors.add(e.toString());
                        e.printStackTrace();
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (expenses)...");
                        }
                    });

                    ExpenseController expenseController = new ExpenseController(getActivity());
                    expenseController.deleteAll();
                    // Processing expense
                    try {
                        JSONObject expenseJSON = new JSONObject(expenses);
                        JSONArray expenseJSONArray = expenseJSON.getJSONArray("fExpenseResult");
                        ArrayList<Expense> expensesList = new ArrayList<Expense>();
                        for (int i = 0; i < expenseJSONArray.length(); i++) {
                            expensesList.add(Expense.parseExpense(expenseJSONArray.getJSONObject(i)));
                        }
                        expenseController.createOrUpdateFExpense(expensesList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end expenses**********************************************************************/
                    /*****************Route**********************************************************************/

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Expenses downloaded\nDownloading route details...");
                        }
                    });

                    String route = "";
                    try {
                        route = networkFunctions.getRoutes(repcode);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (routes)...");
                        }
                    });

                    // Processing route

                    RouteController routeController = new RouteController(getActivity());
                    routeController.deleteAll();
                    try {
                        JSONObject routeJSON = new JSONObject(route);
                        JSONArray routeJSONArray = routeJSON.getJSONArray("fRouteResult");
                        ArrayList<Route> routeList = new ArrayList<Route>();
                        for (int i = 0; i < routeJSONArray.length(); i++) {
                            routeList.add(Route.parseRoute(routeJSONArray.getJSONObject(i)));
                        }
                        routeController.createOrUpdateFRoute(routeList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end route**********************************************************************/
                    /*****************last 3 invoice heds**********************************************************************/
                    String last3InvHeds = "";
                    try {
                        last3InvHeds = networkFunctions.getLastThreeInvHed(repcode);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (last invoices)...");
                        }
                    });

                    // Processing lastinvoiceheds

                    FInvhedL3Controller invoiceHedController = new FInvhedL3Controller(getActivity());
                    invoiceHedController.deleteAll();
                    try {
                        JSONObject invoiceHedJSON = new JSONObject(last3InvHeds);
                        JSONArray invoiceHedJSONJSONArray = invoiceHedJSON.getJSONArray("RepLastThreeInvHedResult");
                        ArrayList<FInvhedL3> invoiceHedList = new ArrayList<FInvhedL3>();
                        for (int i = 0; i < invoiceHedJSONJSONArray.length(); i++) {
                            invoiceHedList.add(FInvhedL3.parseInvoiceHeds(invoiceHedJSONJSONArray.getJSONObject(i)));
                        }
                        invoiceHedController.createOrUpdateFinvHedL3(invoiceHedList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end lastinvoiceheds**********************************************************************/
                    /*****************last 3 invoice dets**********************************************************************/
                    String last3InvDets = "";
                    try {
                        last3InvDets = networkFunctions.getLastThreeInvDet(repcode);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (invoices)...");
                        }
                    });

                    FinvDetL3Controller invoiceDetController = new FinvDetL3Controller(getActivity());
                    invoiceDetController.deleteAll();
                    // Processing lastinvoiceheds
                    try {
                        JSONObject invoiceHedJSON = new JSONObject(last3InvDets);
                        JSONArray invoiceHedJSONJSONArray = invoiceHedJSON.getJSONArray("RepLastThreeInvDetResult");
                        ArrayList<FinvDetL3> invoiceDetList = new ArrayList<FinvDetL3>();
                        for (int i = 0; i < invoiceHedJSONJSONArray.length(); i++) {
                            invoiceDetList.add(FinvDetL3.parseInvoiceDets(invoiceHedJSONJSONArray.getJSONObject(i)));
                        }
                        invoiceDetController.createOrUpdateFinvDetL3(invoiceDetList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }

                    /*****************end lastinvoicedets**********************************************************************/

                    /*****************Route det**********************************************************************/

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Last invoices downloaded\nDownloading route details...");
                        }
                    });

                    String routedet = "";
                    try {
                        routedet = networkFunctions.getRouteDets(repcode);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (routes)...");
                        }
                    });

                    RouteDetController routeDetController = new RouteDetController(getActivity());
                    routeDetController.deleteAll();
                    // Processing route
                    try {
                        JSONObject routeJSON = new JSONObject(routedet);
                        JSONArray routeJSONArray = routeJSON.getJSONArray("fRouteDetResult");
                        ArrayList<RouteDet> routeList = new ArrayList<RouteDet>();
                        for (int i = 0; i < routeJSONArray.length(); i++) {
                            routeList.add(RouteDet.parseRoute(routeJSONArray.getJSONObject(i)));
                        }
                        routeDetController.InsertOrReplaceRouteDet(routeList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end route det**********************************************************************/

                    /*****************towns**********************************************************************/

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Expenses downloaded\nDownloading town details...");
                        }
                    });

                    String town = "";
                    try {
                        town = networkFunctions.getTowns(repcode);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (towns)...");
                        }
                    });

                    TownController townController = new TownController(getActivity());
                    townController.deleteAll();
                    // Processing towns
                    try {
                        JSONObject townJSON = new JSONObject(town);
                        JSONArray townJSONArray = townJSON.getJSONArray("fTownResult");
                        ArrayList<Town> townList = new ArrayList<Town>();
                        for (int i = 0; i < townJSONArray.length(); i++) {
                            townList.add(Town.parseTown(townJSONArray.getJSONObject(i)));
                        }
                        townController.createOrUpdateFTown(townList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end towns**********************************************************************/

                    /*****************Freeslab**********************************************************************/
                    String freeslab = "";
                    try {
                        freeslab = networkFunctions.getFreeSlab();
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (free)...");
                        }
                    });

                    FreeSlabController freeslabController = new FreeSlabController(getActivity());
                    freeslabController.deleteAll();
                    // Processing freeslab
                    try {
                        JSONObject freeslabJSON = new JSONObject(freeslab);
                        JSONArray freeslabJSONArray = freeslabJSON.getJSONArray("FfreeslabResult");
                        ArrayList<FreeSlab> freeslabList = new ArrayList<FreeSlab>();
                        for (int i = 0; i < freeslabJSONArray.length(); i++) {
                            freeslabList.add(FreeSlab.parseFreeSlab(freeslabJSONArray.getJSONObject(i)));
                        }
                        freeslabController.createOrUpdateFreeSlab(freeslabList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end freeSlab**********************************************************************/
                    /*****************freeMslab**********************************************************************/
                    String freeMslab = "";
                    try {
                        freeMslab = networkFunctions.getFreeMslab();
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (free)...");
                        }
                    });

                    FreeMslabController freeMslabController = new FreeMslabController(getActivity());
                    freeMslabController.deleteAll();
                    // Processing freeMslab
                    try {
                        JSONObject freeMslabJSON = new JSONObject(freeMslab);
                        JSONArray taxJSONArray = freeMslabJSON.getJSONArray("fFreeMslabResult");
                        ArrayList<FreeMslab> freeMslabList = new ArrayList<FreeMslab>();
                        for (int i = 0; i < taxJSONArray.length(); i++) {
                            freeMslabList.add(FreeMslab.parseFreeMslab(taxJSONArray.getJSONObject(i)));
                        }
                        freeMslabController.createOrUpdateFreeMslab(freeMslabList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end freeMSlab**********************************************************************/

                    /*****************FreeHed**********************************************************************/
                    String freehed = "";
                    try {
                        freehed = networkFunctions.getFreeHed(repcode);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (free)...");
                        }
                    });

                    FreeHedController freeHedController = new FreeHedController(getActivity());
                    freeHedController.deleteAll();
                    // Processing freehed
                    try {
                        JSONObject freeHedJSON = new JSONObject(freehed);
                        JSONArray freeHedJSONArray = freeHedJSON.getJSONArray("FfreehedResult");
                        ArrayList<FreeHed> freeHedList = new ArrayList<FreeHed>();
                        for (int i = 0; i < freeHedJSONArray.length(); i++) {
                            freeHedList.add(FreeHed.parseFreeHed(freeHedJSONArray.getJSONObject(i)));
                        }
                        freeHedController.createOrUpdateFreeHed(freeHedList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end freeHed**********************************************************************/
                    /*****************Freedet**********************************************************************/
                    String freedet = "";
                    try {
                        freedet = networkFunctions.getFreeDet();
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (free)...");
                        }
                    });

                    FreeDetController freedetController = new FreeDetController(getActivity());
                    freedetController.deleteAll();
                    // Processing freedet
                    try {
                        JSONObject freedetJSON = new JSONObject(freedet);
                        JSONArray freedetJSONArray = freedetJSON.getJSONArray("FfreedetResult");
                        ArrayList<FreeDet> freedetList = new ArrayList<FreeDet>();
                        for (int i = 0; i < freedetJSONArray.length(); i++) {
                            freedetList.add(FreeDet.parseFreeDet(freedetJSONArray.getJSONObject(i)));
                        }
                        freedetController.createOrUpdateFreeDet(freedetList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end freedet**********************************************************************/
                    /*****************freedeb**********************************************************************/
                    String freedeb = "";
                    try {
                        freedeb = networkFunctions.getFreeDebs();
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (free)...");
                        }
                    });
                    FreeDebController freedebController = new FreeDebController(getActivity());
                    freedebController.deleteAll();
                    // Processing freedeb
                    try {
                        JSONObject freedebJSON = new JSONObject(freedeb);
                        JSONArray freedebJSONArray = freedebJSON.getJSONArray("FfreedebResult");
                        ArrayList<FreeDeb> freedebList = new ArrayList<FreeDeb>();

                        for (int i = 0; i < freedebJSONArray.length(); i++) {
                            freedebList.add(FreeDeb.parseFreeDeb(freedebJSONArray.getJSONObject(i)));
                        }
                        freedebController.createOrUpdateFreeDeb(freedebList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end freedeb**********************************************************************/
                    /*****************freeItem**********************************************************************/
                    String freeitem = "";
                    try {
                        freeitem = networkFunctions.getFreeItems();
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (free)...");
                        }
                    });

                    FreeItemController freeitemController = new FreeItemController(getActivity());
                    freeitemController.deleteAll();
                    // Processing freeItem
                    try {
                        JSONObject freeitemJSON = new JSONObject(freeitem);
                        JSONArray freeitemJSONArray = freeitemJSON.getJSONArray("fFreeItemResult");
                        ArrayList<FreeItem> freeitemList = new ArrayList<FreeItem>();
                        for (int i = 0; i < freeitemJSONArray.length(); i++) {
                            freeitemList.add(FreeItem.parseFreeItem(freeitemJSONArray.getJSONObject(i)));
                        }
                        freeitemController.createOrUpdateFreeItem(freeitemList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end freeItem**********************************************************************/
                    /*****************discdeb**********************************************************************/
                    String debdisc = "";
                    try {
                        debdisc = networkFunctions.getDiscDeb(repcode);
                    } catch (IOException e) {
                        errors.add(e.toString());
                        e.printStackTrace();
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (discount)...");
                        }
                    });

                    DiscdebController discdebController = new DiscdebController(getActivity());
                    discdebController.deleteAll();
                    // Processing discdeb
                    try {
                        JSONObject discdebPriJSON = new JSONObject(debdisc);
                        JSONArray discdebJSONArray = discdebPriJSON.getJSONArray("FdiscdebResult");
                        ArrayList<Discdeb> discdebList = new ArrayList<Discdeb>();
                        for (int i = 0; i < discdebJSONArray.length(); i++) {
                            discdebList.add(Discdeb.parseDiscDeb(discdebJSONArray.getJSONObject(i)));
                        }
                        discdebController.createOrUpdateDiscdeb(discdebList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end discdeb**********************************************************************/
                    /*****************discdet**********************************************************************/
                    String discdet = "";
                    try {
                        discdet = networkFunctions.getDiscDet();
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (discount)...");
                        }
                    });

                    // Processing discdet

                    DiscdetController discdetController = new DiscdetController(getActivity());
                    discdetController.deleteAll();
                    try {
                        JSONObject discdetJSON = new JSONObject(discdet);
                        JSONArray discdetJSONArray = discdetJSON.getJSONArray("FdiscdetResult");
                        ArrayList<Discdet> discdetList = new ArrayList<Discdet>();
                        for (int i = 0; i < discdetJSONArray.length(); i++) {
                            discdetList.add(Discdet.parseDiscDet(discdetJSONArray.getJSONObject(i)));
                        }
                        discdetController.createOrUpdateDiscdet(discdetList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end discdet**********************************************************************/
                    /*****************discshed**********************************************************************/
                    String disched = "";
                    try {
                        disched = networkFunctions.getDiscHed(repcode);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (discount)...");
                        }
                    });

                    DischedController dischedController = new DischedController(getActivity());
                    dischedController.deleteAll();
                    // Processing disched
                    try {
                        JSONObject dischedJSON = new JSONObject(disched);
                        JSONArray dischedJSONArray = dischedJSON.getJSONArray("FDischedResult");
                        ArrayList<Disched> dischedList = new ArrayList<Disched>();
                        for (int i = 0; i < dischedJSONArray.length(); i++) {
                            dischedList.add(Disched.parseDisched(dischedJSONArray.getJSONObject(i)));
                        }
                        dischedController.createOrUpdateDisched(dischedList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end disched**********************************************************************/
                    /*****************discslab**********************************************************************/
                    String discslab = "";
                    try {
                        discslab = networkFunctions.getDiscSlab();
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (discount)...");
                        }
                    });

                    DiscslabController discslabController = new DiscslabController(getActivity());
                    discslabController.deleteAll();
                    // Processing discslab
                    try {
                        JSONObject discslabJSON = new JSONObject(discslab);
                        JSONArray discslabJSONArray = discslabJSON.getJSONArray("FdiscslabResult");
                        ArrayList<Discslab> discslabList = new ArrayList<Discslab>();
                        for (int i = 0; i < discslabJSONArray.length(); i++) {
                            discslabList.add(Discslab.parseDiscslab(discslabJSONArray.getJSONObject(i)));
                        }
                        discslabController.createOrUpdateDiscslab(discslabList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }
                    /*****************end discslab**********************************************************************/
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Prices downloaded\nDownloading iteanery details...");
                        }
                    });

                    String itenaryhed = "";
                    try {
                        itenaryhed = networkFunctions.getItenaryHed(repcode);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (Iteanery)...");
                        }
                    });
                    FItenrHedController itenaryHedController = new FItenrHedController(getActivity());
                    itenaryHedController.deleteAll();
                    // Processing itenaryhed
                    try {
                        JSONObject itenaryHedJSON = new JSONObject(itenaryhed);
                        JSONArray itenaryHedJSONArray = itenaryHedJSON.getJSONArray("fItenrHedResult");
                        ArrayList<FItenrHed> itenaryHedList = new ArrayList<FItenrHed>();
                        for (int i = 0; i < itenaryHedJSONArray.length(); i++) {
                            itenaryHedList.add(FItenrHed.parseIteanaryHed(itenaryHedJSONArray.getJSONObject(i)));
                        }
                        itenaryHedController.createOrUpdateFItenrHed(itenaryHedList);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }


                    /*****************invoice sale**********************************************************************/

                    String tmInvSale = "";
                    try {
                        tmInvSale = networkFunctions.getTMInvoiceSale(repcode);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    try {
                        SharedPref.getInstance(getActivity()).setTMInvSale("0");

                        JSONObject invoiceSaleJSON = new JSONObject(tmInvSale);
                        String tminvoiceSale = invoiceSaleJSON.getString("invoiceSaleResult");
                        SharedPref.getInstance(getActivity()).setTMInvSale(tminvoiceSale);
                        /////////////////MMS2019-11-14////////////////
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
                        throw e;
                    }

                    String pmInvSale = "";
                    try {
                        pmInvSale = networkFunctions.getPMInvoiceSale(repcode);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    try {
                        SharedPref.getInstance(getActivity()).setPMInvSale("0");

                        JSONObject invoiceSaleJSON = new JSONObject(pmInvSale);
                        String pminvoiceSale = invoiceSaleJSON.getString("invoiceSaleResult");
                        SharedPref.getInstance(getActivity()).setPMInvSale(pminvoiceSale);
                        /////////////////MMS2019-11-14////////////////
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
                        throw e;

                    }
                    /*****************order sale**********************************************************************/

                    String tmOrdSale = "";
                    try {
                        tmOrdSale = networkFunctions.getTMOrderSale(repcode);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    try {
                        SharedPref.getInstance(getActivity()).setTMOrdSale("0");

                        JSONObject orderSaleJSON = new JSONObject(tmOrdSale);
                        String tmorderSale = orderSaleJSON.getString("orderSaleTillTodayResult");
                        SharedPref.getInstance(getActivity()).setTMOrdSale(tmorderSale);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
                        throw e;
                    }

                    String pmOrdSale = "";
                    try {
                        pmOrdSale = networkFunctions.getPMOrderSale(repcode);
                    } catch (IOException e) {
                        errors.add(e.toString());
                        e.printStackTrace();
                        throw e;
                    }

                    try {
                        SharedPref.getInstance(getActivity()).setPMOrdSale("0");

                        JSONObject orderSaleJSON = new JSONObject(pmOrdSale);
                        String pmorderSale = orderSaleJSON.getString("orderSaleResult");
                        SharedPref.getInstance(getActivity()).setPMOrdSale(pmorderSale);
                        /////////////////MMS2019-11-14////////////////
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
                        throw e;
                    }

                    /*****************returns**********************************************************************/

                    String tmReturns = "";
                    try {
                        tmReturns = networkFunctions.getTMReturns(repcode);
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw e;
                    }

                    try {
                        SharedPref.getInstance(getActivity()).setTMReturn("0");

                        JSONObject orderSaleJSON = new JSONObject(tmReturns);
                        String tmreturnSale = orderSaleJSON.getString("getReturnsResult");
                        SharedPref.getInstance(getActivity()).setTMReturn(tmreturnSale);
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
                        throw e;
                    }

                    String pmReturn = "";
                    try {
                        pmReturn = networkFunctions.getPMReturns(repcode);
                    } catch (IOException e) {
                        errors.add(e.toString());
                        e.printStackTrace();
                        throw e;
                    }

                    try {
                        SharedPref.getInstance(getActivity()).setPMReturn("0");

                        JSONObject orderSaleJSON = new JSONObject(pmReturn);
                        String pmreturn = orderSaleJSON.getString("getReturnsResult");
                        SharedPref.getInstance(getActivity()).setPMReturn(pmreturn);
                        /////////////////MMS2019-11-14////////////////
                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
                        throw e;
                    }
                    /*****************itenary det**********************************************************************/

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Prices downloaded\nDownloading iteanery details...");
                        }
                    });

                    String itenarydet = "";
                    try {
                        itenarydet = networkFunctions.getItenaryDet(repcode);
                    } catch (IOException e) {
                        e.printStackTrace();
                        errors.add(e.toString());
                        throw e;
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdialog.setMessage("Processing downloaded data (Iteanery)...");
                        }
                    });

                    // Processing itenarydet
                    try {
                        FItenrDetController itenaryDetController = new FItenrDetController(getActivity());
                        itenaryDetController.deleteAll();
                        JSONObject itenaryDetJSON = new JSONObject(itenarydet);
                        JSONArray itenaryDetJSONArray = itenaryDetJSON.getJSONArray("fItenrDetResult");
                        //Log.d("ITEANERY det arraycount", ">>>>>>>>>>>>>>>>>>>>>>" + itenaryDetJSONArray.length());
                        ArrayList<FItenrDet> itenaryDetList = new ArrayList<FItenrDet>();

                        for (int i = 0; i < itenaryDetJSONArray.length(); i++) {
                            itenaryDetList.add(FItenrDet.parseIteanaryDet(itenaryDetJSONArray.getJSONObject(i)));
                        }
                        // Log.d("ITEANERY det arraylist", ">>>>>>>>>>>>>>>>>>>>>>" + itenaryDetList.size());
                        int count = itenaryDetController.createOrUpdateFItenrDet(itenaryDetList);
                        // Log.d("ITEANERY det", ">>>>>>>>>>>>>>>>>>>>>>" + count);
                        if (count > 0) {
                            Log.d("ITEANERY det", ">>>>>>>>>>>>>>>>>>>>>>SUCCESS");
                        }

                    } catch (JSONException | NumberFormatException e) {
                        errors.add(e.toString());
//                        ErrorUtil.logException("LoginActivity -> Authenticate -> doInBackground() # Process Routes and Outlets",
//                                e, routes, BugReport.SEVERITY_HIGH);

                        throw e;
                    }

                    /*****************end iteanerydet**********************************************************************/
                    /*****************itenary hed**********************************************************************/


                    /*****************end itenaryhed**********************************************************************/
                    return true;
                } else {
                    errors.add("SharedPref.getInstance(getActivity()).getLoginUser() = null OR !SharedPref.getInstance(getActivity()).isLoggedIn()");
                    Log.d("ERROR>>>>>", "Login USer" + SharedPref.getInstance(getActivity()).getLoginUser().toString() + " IS LoggedIn --> " + SharedPref.getInstance(getActivity()).isLoggedIn());
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                errors.add(e.toString());
                // errors.add("Unable to reach the server.");

//                ErrorUtil.logException(LoginActivity.this, "LoginActivity -> Authenticate -> doInBackground # Login",
//                        e, null, BugReport.SEVERITY_LOW);

                return false;
            } catch (JSONException e) {
                e.printStackTrace();
                errors.add(e.toString());
                // errors.add("Received an invalid response from the server.");

//                ErrorUtil.logException(LoginActivity.this, "LoginActivity -> Authenticate -> doInBackground # Login",
//                        e, loginResponse, BugReport.SEVERITY_HIGH);

                return false;
            } catch (NumberFormatException e) {
                errors.add(e.toString());
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean result) {
            super.onPostExecute(result);

            pdialog.setMessage("Finalizing data");
            pdialog.setMessage("Download Completed..");
            if (result) {
                if (pdialog.isShowing()) {
                    pdialog.dismiss();
                }

            } else {
                if (pdialog.isShowing()) {
                    pdialog.dismiss();
                }
                StringBuilder sb = new StringBuilder();
                if (errors.size() == 1) {
                    sb.append(errors.get(0));
                } else {
                    sb.append("Following errors occurred");
                    for (String error : errors) {
                        sb.append("\n - ").append(error);
                    }
                }
                showErrorText(sb.toString());
            }
            if (fmc.getAllMediaforCheckIfIsExist("IMG") > 0) {
                imgImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_img_notification));
            } else {
                imgImage.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_image));
            }

            if (fmc.getAllMediaforCheckIfIsExist("VDO") > 0) {
                imgVideo.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_video_notification));
            } else {
                imgVideo.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_video));
            }
        }
    }

    private void showErrorText(String s) {
        Toast.makeText(getActivity(), "" + s, Toast.LENGTH_LONG).show();
    }

    /////////////***********************secondory sync finish***********************************/
    /*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*/

    private void addRefNoResults_exp(String ref, int count) {
        resultListExpense.add(ref);
        if(count == resultListExpense.size()) {
            mUploadResult(resultListExpense);
        }
    }


    public void mUploadResult(List<String> messages) {
        String msg = "";
        for (String s : messages) {
            msg += s;
        }
          AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
          alertDialogBuilder.setMessage(msg);
          alertDialogBuilder.setTitle("Upload Summary");

          alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {

              public void onClick(DialogInterface dialog, int id) {
                  dialog.cancel();
              }
          });
          AlertDialog alertD = alertDialogBuilder.create();
          alertD.show();
          alertD.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }
    public void mUploadResult(String message) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setTitle("Upload Summary");

        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alertD = alertDialogBuilder.create();
        alertD.show();
        alertD.getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

    }

    private class Validate extends AsyncTask<String, Integer, Boolean> {
        int totalRecords = 0;
        CustomProgressDialog pdialog;
        private String macId, url, db;

        public Validate(String macId, String url, String db) {
            this.macId = macId;
            this.url = url;
            this.db = db;
            this.pdialog = new CustomProgressDialog(getActivity());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            pdialog.setMessage("Validating...");
            pdialog.show();
        }

        @Override
        protected Boolean doInBackground(String... arg0) {

            try {
                int recordCount = 0;
                int totalBytes = 0;
                String validateResponse = null;
                JSONObject validateJSON;
                try {
                    validateResponse = networkFunctions.validate(getActivity(), macId, url, db);
                    Log.d("validateResponse", validateResponse);
                    validateJSON = new JSONObject(validateResponse);


                    if (validateJSON != null) {
                        pref = SharedPref.getInstance(getActivity());
                        //dbHandler.clearTables();
                        // Login successful. Proceed to download other items

                        JSONArray repArray = validateJSON.getJSONArray("fSalRepResult");
                        ArrayList<SalRep> salRepList = new ArrayList<>();
                        for (int i = 0; i < repArray.length(); i++) {
                            JSONObject expenseJSON = repArray.getJSONObject(i);
                            salRepList.add(SalRep.parseUser(expenseJSON));
                        }
                        new SalRepController(getActivity()).createOrUpdateSalRep(salRepList);
                        User user = User.parseUser(repArray.getJSONObject(0));
                        networkFunctions.setUser(user);
                        pref.storeLoginUser(user);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pdialog.setMessage("Authenticated...");
                            }
                        });

                        return true;
                    } else {
                        Toast.makeText(getActivity(), "Invalid response from server when getting sales rep data", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                } catch (IOException e) {
                    Log.e("networkFunctions ->", "IOException -> " + e.toString());
                    throw e;
                } catch (JSONException e) {
                    Log.e("networkFunctions ->", "JSONException -> " + e.toString());
                    throw e;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (pdialog.isShowing())
                pdialog.cancel();
            // pdialog.cancel();
            if (result) {
                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                syncMasterDataDialog(getActivity());
            } else {
                Toast.makeText(getActivity(), "Invalid Mac Id", Toast.LENGTH_LONG).show();
            }
        }
    }

}