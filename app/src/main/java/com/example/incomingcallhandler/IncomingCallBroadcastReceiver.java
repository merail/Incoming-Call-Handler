package com.example.incomingcallhandler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.incomingcallhandler.db.DatabaseRepository;

import java.util.Date;

public class IncomingCallBroadcastReceiver extends BroadcastReceiver {

    private WindowManager wm;
    private static LinearLayout linearLayout1;
    private WindowManager.LayoutParams params1;

    @Override
    public void onReceive(final Context context, Intent intent) {
//        if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
//            showToast(context, "Call started...");
//        } else if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_IDLE)) {
//            showToast(context, "Call ended...");
//        } else if (intent.getStringExtra(TelephonyManager.EXTRA_STATE).equals(TelephonyManager.EXTRA_STATE_RINGING)) {
//            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
//            showToast(context, "Incoming call..." + number);
//        }
        TelephonyManager telephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        telephony.listen(new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String phoneNumber) {
                super.onCallStateChanged(state, phoneNumber);
                if (state == TelephonyManager.CALL_STATE_RINGING) {
                    String info = "";
                    if (phoneNumber != null)
                        info = DatabaseRepository.getInstance(context.getApplicationContext()).getInfo(phoneNumber);

//                    LayoutInflater inflater = (LayoutInflater) context
//                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                    View popUp = inflater.inflate(R.layout.popup_window, null);
//
//                    int width = LinearLayout.LayoutParams.MATCH_PARENT;
//                    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
//                    boolean focusable = true;
//                    final PopupWindow popupWindow = new PopupWindow(popUp, width, height, focusable);
//
//                    View rootView = popUp.findViewById(android.R.id.content).getRootView();
//                    popupWindow.showAtLocation(rootView, Gravity.CENTER, 0, 0);
                    showToast(context, phoneNumber + " " + info + " incoming call");
                }
                if (state == TelephonyManager.CALL_STATE_IDLE) {
                    showToast(context, phoneNumber + " call finished");
                }

//                if (state == TelephonyManager.CALL_STATE_OFFHOOK) {
//                    wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//                    params1 = new WindowManager.LayoutParams(
//                            ViewGroup.LayoutParams.MATCH_PARENT,
//                            ViewGroup.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT |
//                            WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
//                            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//                            PixelFormat.TRANSPARENT);
//                    params1.height = 75;
//                    params1.width = 512;
//                    params1.x = 265;
//                    params1.y = 400;
//                    params1.format = PixelFormat.TRANSLUCENT;
//                    linearLayout1 = new LinearLayout(context);
//                    linearLayout1.setOrientation(LinearLayout.VERTICAL);
//
//                    View view = LayoutInflater.from(context).inflate(R.layout.popup_window, linearLayout1, false);
//                    TextView number = view.findViewById(R.id.incoming_number);
//                    TextView info = view.findViewById(R.id.additional_info);
//                    number.setText(phoneNumber);
//                    linearLayout1.addView(number);
//                    linearLayout1.addView(info);
//                    if (phoneNumber != null)
//                        info.setText(DatabaseRepository.getInstance(context.getApplicationContext()).getInfo(phoneNumber));
//
//                    wm.addView(linearLayout1, params1);
//                }
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }

    void showToast(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
