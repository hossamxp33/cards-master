package com.codesroots.mac.cards.presentaion.Printer;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.codesroots.mac.cards.R;
import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.device.Printer.Format;
import com.landicorp.android.eptapi.exception.RequestException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hossam on 9/20/2018.
 */
public abstract class PosPrinter {
    public Context context;
    /**
     * Make a print progress to design the receipt.
     */
    private Printer.Progress progress = new Printer.Progress() {

        @Override
        public void doPrint(Printer printer) throws Exception {
            // Design the receipt
            Printer.Format format = new Printer.Format();
            // Use this 5x7 dot and 1 times width, 2 times height

            int total = 0;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
            String currentDateandTime = sdf.format(new Date());

            format.setHzScale(Format.HZ_SC1x1);
            format.setHzSize(Format.HZ_DOT16x16);

            printer.printText("zVendo POS\n");
            printer.printText("https://www.zvendo.com/\n");
            printer.setFormat(format);
            printer.printText("Order Date:\n");
            printer.printText(currentDateandTime);
            printer.printText("\n");
            //format.setHzScale(Format.HZ_SC1x1);
            printer.setFormat(format);
            printer.printText("\n");
            printer.printText("Transaction : payment\n");
            printer.printText("Order number : "  );
//            printer.printText("Credit Card No.: XXXX XXXX XXXX XXXX\n");
            printer.printText("\n");
            printer.printText("\n");

            printer.printText("\n");
            int discount = 0, extrafee = 0, delv_fee = 0, sub = 0;
            boolean dis_perc;


//          printer.printText("Term No.: 2200306\n");
            printer.printText("Total Price: " + String.valueOf((total + extrafee + delv_fee) - discount));
//       printer.printText("Reference No.: 191017234668\n");
            printer.printText("\n");
            printer.printText("\n");
            printer.printText("--- Thank you for visiting   \n");
            printer.printText("    zVendo Stores ---\n");
            printer.printText("\n");
            // CHS Text Format - 16x16 dot and 1 times width, 1 times height
            format.setHzScale(Format.HZ_SC1x1);
            format.setHzSize(Format.HZ_DOT16x16);
//       printer.printText("-------\n");
            printer.printText("\n");
//       printer.printBarCode("8799128883");
            printer.feedLine(3);
//       Log.d("#PrinterSample","打印结束.");
        }

        public String fixedLengthString(String string, int length) {
            return String.format("%1$" + length + "s", string);
        }

        @Override
        public void onCrash() {
//            onDeviceServiceCrash();
        }

        @Override
        public void onFinish(int code) {
            /**
             * The result is fine.
             */

            if (code == Printer.ERROR_NONE) {
//                displayPrinterInfo("正常结束打印");
//                Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
            }
            /**
             * Has some error. Here is display it, but you may want to hanle
             * the error such as ERROR_OVERHEAT��ERROR_BUSY��ERROR_PAPERENDED
             * to start again in the right time later.
             */
            else {
                displayPrinterInfo("PRINT ERR - " + getErrorDescription(code));
            }
        }

        public String getErrorDescription(int code) {
            switch (code) {
                case Printer.ERROR_PAPERENDED:
                    Toast.makeText(context, "Paper-out, the operation is invalid this time", Toast.LENGTH_LONG).show();
                    return "Paper-out, the operation is invalid this time";
                case Printer.ERROR_HARDERR:
                    Toast.makeText(context, "Hardware fault, can not find HP signal", Toast.LENGTH_LONG).show();
                    return "Hardware fault, can not find HP signal";
                case Printer.ERROR_OVERHEAT:
                    Toast.makeText(context, "Overheat", Toast.LENGTH_LONG).show();
                    return "Overheat";
                case Printer.ERROR_BUFOVERFLOW:
                    Toast.makeText(context, "The operation buffer mode position is out of range", Toast.LENGTH_LONG).show();
                    return "The operation buffer mode position is out of range";
                case Printer.ERROR_LOWVOL:
                    Toast.makeText(context, "Low voltage protect", Toast.LENGTH_LONG).show();
                    return "Low voltage protect";
                case Printer.ERROR_PAPERENDING:
                    Toast.makeText(context, "Paper-out, permit the latter operation", Toast.LENGTH_LONG).show();
                    return "Paper-out, permit the latter operation";
                case Printer.ERROR_MOTORERR:
                    Toast.makeText(context, "The printer core fault (too fast or too slow)", Toast.LENGTH_LONG).show();
                    return "The printer core fault (too fast or too slow)";
                case Printer.ERROR_PENOFOUND:
                    Toast.makeText(context, "Automatic positioning did not find the alignment position, the paper back to its original position", Toast.LENGTH_LONG).show();
                    return "Automatic positioning did not find the alignment position, the paper back to its original position";
                case Printer.ERROR_PAPERJAM:
                    Toast.makeText(context, "paper got jammed", Toast.LENGTH_LONG).show();
                    return "paper got jammed";
                case Printer.ERROR_NOBM:
                    Toast.makeText(context, "Black mark not found", Toast.LENGTH_LONG).show();
                    return "Black mark not found";
                case Printer.ERROR_BUSY:
                    Toast.makeText(context, "The printer is busy", Toast.LENGTH_LONG).show();
                    return "The printer is busy";
                case Printer.ERROR_BMBLACK:
                    Toast.makeText(context, "Black label detection to black signal", Toast.LENGTH_LONG).show();
                    return "Black label detection to black signal";
                case Printer.ERROR_WORKON:
                    Toast.makeText(context, "The printer power is open", Toast.LENGTH_LONG).show();
                    return "The printer power is open";
                case Printer.ERROR_LIFTHEAD:
                    Toast.makeText(context, "Printer head lift", Toast.LENGTH_LONG).show();
                    return "Printer head lift";
                case Printer.ERROR_LOWTEMP:
                    Toast.makeText(context, "Low temperature protect", Toast.LENGTH_LONG).show();
                    return "Low temperature protect";
            }
            return "unknown error (" + code + ")";
        }
    };

    public PosPrinter(Context context) {
        this.context = context;
    }

    public PosPrinter() {
      //  Toast.makeText(context, "Low temperature protect", Toast.LENGTH_LONG).show();

        // Init print progress. You can also do it in 'Progress.doPrint' method,
        // but it would not be done after all step invoked.
        progress.addStep(printer -> {
            // Make the print method can print more than one line.
            printer.setAutoTrunc(true);
            // Default mode is real mode, now set it to virtual mode.
            printer.setMode(Printer.MODE_VIRTUAL);
        });
    }

    /**
     * Search card and show all track info
     */
    public void startPrint(Context contexts) {
        try {
            progress.start();
        } catch (RequestException e) {

            Toast.makeText(contexts, e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }



    /**
     * Display printer info
     *
     * @param info
     */
    protected abstract void displayPrinterInfo(String info);
}
