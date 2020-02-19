package com.codesroots.mac.cards.presentaion.Printer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;
import com.codesroots.mac.cards.R;
import com.codesroots.mac.cards.models.Buypackge;
import com.codesroots.mac.cards.models.Pencode;
import woyou.aidlservice.jiuiv5.ICallback;
import woyou.aidlservice.jiuiv5.IWoyouService;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class AidlUtil {
    private static final String SERVICE＿PACKAGE = "woyou.aidlservice.jiuiv5";
    private static final String SERVICE＿ACTION = "woyou.aidlservice.jiuiv5.IWoyouService";

    private IWoyouService woyouService;
    private static AidlUtil mAidlUtil = new AidlUtil();
    private Context context;
    Bitmap bitmap, bitmap1;

    private AidlUtil() {
    }

    public static AidlUtil getInstance() {
        return mAidlUtil;
    }

    /**
     * 连接服务
     *
     * @param context context
     */
    public void connectPrinterService(Context context) {
        this.context = context.getApplicationContext();
        Intent intent = new Intent();
        intent.setPackage(SERVICE＿PACKAGE);
        intent.setAction(SERVICE＿ACTION);
        context.getApplicationContext().startService(intent);
        context.getApplicationContext().bindService(intent, connService, Context.BIND_AUTO_CREATE);
    }

    /**
     * 断开服务
     *
     * @param context context
     */
    public void disconnectPrinterService(Context context) {
        if (woyouService != null) {
            context.getApplicationContext().unbindService(connService);
            woyouService = null;
        }
    }

    public boolean isConnect() {
        return woyouService != null;
    }

    private ServiceConnection connService = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            woyouService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            woyouService = IWoyouService.Stub.asInterface(service);
        }
    };

    public ICallback generateCB(final PrinterCallback printerCallback){
        return new ICallback.Stub(){


            @Override
            public void onRunResult(boolean isSuccess) throws RemoteException {

            }

            @Override
            public void onReturnString(String result) throws RemoteException {
                printerCallback.onReturnString(result);
            }

            @Override
            public void onRaiseException(int code, String msg) throws RemoteException {

            }

            @Override
            public void onPrintResult(int code, String msg) throws RemoteException {

            }
        };
    }

    /**
     * 设置打印浓度
     */
    private int[] darkness = new int[]{0x0600, 0x0500, 0x0400, 0x0300, 0x0200, 0x0100, 0,
            0xffff, 0xfeff, 0xfdff, 0xfcff, 0xfbff, 0xfaff};

    public void setDarkness(int index) {
        if (woyouService == null) {
            Toast.makeText(context, R.string.toast_2, Toast.LENGTH_LONG).show();
            return;
        }

        int k = darkness[index];
        try {
            woyouService.sendRAWData(ESCUtil.setPrinterDarkness(k), null);
            woyouService.printerSelfChecking(null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 取得打印机系统信息，放在list中
     *
     * @return list
     */
    public List<String> getPrinterInfo(PrinterCallback printerCallback1, PrinterCallback printerCallback2) {
        if (woyouService == null) {
            Toast.makeText(context, R.string.toast_2, Toast.LENGTH_LONG).show();
            return null;
        }

        List<String> info = new ArrayList<>();
        try {
            woyouService.getPrintedLength(generateCB(printerCallback1));
            woyouService.getPrinterFactory(generateCB(printerCallback2));
            info.add(woyouService.getPrinterSerialNo());
            info.add(woyouService.getPrinterModal());
            info.add(woyouService.getPrinterVersion());
            info.add(printerCallback1.getResult());
            info.add(printerCallback2.getResult());
            //info.add(woyouService.getServiceVersion());
            PackageManager packageManager = context.getPackageManager();
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(SERVICE＿PACKAGE, 0);
                if(packageInfo != null){
                    info.add(packageInfo.versionName);
                    info.add(packageInfo.versionCode+"");
                }else{
                    info.add("");info.add("");
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * 初始化打印机
     */
    public void initPrinter() {
        if (woyouService == null) {
            Toast.makeText(context, "he service has been disconnected!", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            woyouService.printerInit(null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印二维码
     */
    public void printQr(String data, int modulesize, int errorlevel) {
        if (woyouService == null) {
            Toast.makeText(context, R.string.toast_2, Toast.LENGTH_LONG).show();
            return;
        }


        try {
			woyouService.setAlignment(1, null);
            woyouService.printQRCode(data, modulesize, errorlevel, null);
            woyouService.lineWrap(3, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印条形码
     */
    public void printBarCode(String data, int symbology, int height, int width, int textposition) {
        if (woyouService == null) {
            Toast.makeText(context, R.string.toast_2, Toast.LENGTH_LONG).show();
            return;
        }


        try {
            woyouService.printBarCode(data, symbology, height, width, textposition, null);
            woyouService.lineWrap(3, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印文字
     */
    public void printText(String content, float size, boolean isBold, boolean isUnderLine) {
        if (woyouService == null) {
            Toast.makeText(context, R.string.toast_2, Toast.LENGTH_LONG).show();
            return;
        }

        try {
            if (isBold) {
                woyouService.sendRAWData(ESCUtil.boldOn(), null);
            } else {
                woyouService.sendRAWData(ESCUtil.boldOff(), null);
            }

            if (isUnderLine) {
                woyouService.sendRAWData(ESCUtil.underlineWithOneDotWidthOn(), null);
            } else {
                woyouService.sendRAWData(ESCUtil.underlineOff(), null);
            }

            woyouService.printTextWithFont(content, null, size, null);
            woyouService.lineWrap(3, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    public void printRecipte(Buypackge value,Bitmap bitmaps) {
        if (woyouService == null) {
            Toast.makeText(context,"he service has been disconnected!", Toast.LENGTH_LONG).show();
            return;
        }

        try {
            if (!value.getPencode().isEmpty()) {
                for (Integer i = 0; i < value.getPencode().size(); i++) {
//
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inTargetDensity = 160;
                    options.inDensity = 160;
                    woyouService.setAlignment(1, null);
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.logo, options);
                   woyouService.printBitmap(bitmaps, null);
                    woyouService.lineWrap(3, null);

                    woyouService.setAlignment(1, null);
                    woyouService.sendRAWData(ESCUtil.boldOn(), null);
                    ;

                    woyouService.printTextWithFont(value.getPrice(), null, 20f, null);
                    woyouService.lineWrap(1, null);

                    woyouService.printTextWithFont("PIN", null, 30f, null);
                    woyouService.lineWrap(1, null);

                    woyouService.printTextWithFont(value.getPencode().get(i).getPencode(), null, 30f, null);
                    woyouService.lineWrap(1, null);

                    woyouService.printTextWithFont(value.getNotes(), null, 18f, null);
                    woyouService.lineWrap(2, null);

                    woyouService.setAlignment(0, null);
                    woyouService.printTextWithFont("SERIAL :", null, 22f, null);
                    woyouService.printTextWithFont(value.getPencode().get(i).getSerial(), null, 22f, null);
                    woyouService.lineWrap(2, null);
                    woyouService.printTextWithFont("EXPIRY :", null, 22f, null);
                    woyouService.printTextWithFont("20-10-2019", null, 22f, null);
                    woyouService.lineWrap(2, null);
                    woyouService.setAlignment(1, null);

                    woyouService.printTextWithFont("Merchant :" + value.getSalor()+"\n",null, 32, null);
                    woyouService.lineWrap(2, null);

                    woyouService.printTextWithFont("التاريخ والوقت", null, 20f, null);

                    woyouService.lineWrap(1, null);
                    woyouService.printTextWithFont(value.getTime(), null, 20f, null);
                    woyouService.lineWrap(2, null);
                    woyouService.setAlignment(0, null);

                   woyouService.printBitmap(bitmap, null);

                    woyouService.lineWrap(3, null);


                }
            }
        }catch (RemoteException e) {
            e.printStackTrace();
        }
    }

                public Bitmap getBitmapFromURL (String string){
                    try {
                        java.net.URL url = new URL(string);
                        Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        return image;
                    } catch (IOException e) {
                        System.out.println(e);
                    }

                    return null;
                }
                /*
                 *打印图片
                 */
                public void printBitmap (Bitmap bitmap){
                    if (woyouService == null) {
                        Toast.makeText(context, R.string.toast_2, Toast.LENGTH_LONG).show();
                        return;
                    }

                    try {
                        woyouService.setAlignment(1, null);
                        woyouService.printBitmap(bitmap, null);
                        woyouService.lineWrap(3, null);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }


                /**
                 *  打印图片和文字按照指定排列顺序
                 */
                public void printBitmap (Bitmap bitmap,int orientation){
                    if (woyouService == null) {
                        Toast.makeText(context, "服务已断开！", Toast.LENGTH_LONG).show();
                        return;
                    }

                    try {
                        if (orientation == 0) {
                            woyouService.printBitmap(bitmap, null);
                            woyouService.printText("横向排列\n", null);
                            woyouService.printBitmap(bitmap, null);
                            woyouService.printText("横向排列\n", null);
                        } else {
                            woyouService.printBitmap(bitmap, null);
                            woyouService.printText("\n纵向排列\n", null);
                            woyouService.printBitmap(bitmap, null);
                            woyouService.printText("\n纵向排列\n", null);
                        }
                        woyouService.lineWrap(3, null);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                /**
                 * 打印表格
                 */


                /*
                 * 空打三行！
                 */
                public void print3Line () {
                    if (woyouService == null) {
                        Toast.makeText(context, R.string.toast_2, Toast.LENGTH_LONG).show();
                        return;
                    }

                    try {
                        woyouService.lineWrap(3, null);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }


                public void sendRawData ( byte[] data){
                    if (woyouService == null) {
                        Toast.makeText(context, R.string.toast_2, Toast.LENGTH_LONG).show();
                        return;
                    }

                    try {
                        woyouService.sendRAWData(data, null);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }

                public void sendRawDatabyBuffer ( byte[] data, ICallback iCallback){
                    if (woyouService == null) {
                        Toast.makeText(context, R.string.toast_2, Toast.LENGTH_LONG).show();
                        return;
                    }

                    try {
                        woyouService.enterPrinterBuffer(true);
                        woyouService.sendRAWData(data, iCallback);
                        woyouService.exitPrinterBufferWithCallback(true, iCallback);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }

