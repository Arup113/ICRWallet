package com.nurdcoder.android.icr_wallet.ui.transfer_amount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;
import com.nurdcoder.android.icr_wallet.R;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class ScanActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    BarcodeReader barcodeReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get the barcode reader instance
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);
    }

    @Override
    public void onScanned(Barcode barcode) {

        // playing barcode reader beep sound
        barcodeReader.playBeep();

//        // ticket details activity by passing barcode
//        Intent intent = new Intent(ScanActivity.this, TicketResultActivity.class);
//        intent.putExtra("code", barcode.displayValue);
//        startActivity(intent);

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",barcode.displayValue);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    @Override
    public void onScannedMultiple(List<Barcode> list) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String s) {
        Toast.makeText(getApplicationContext(), "Error occurred while scanning " + s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCameraPermissionDenied() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_CANCELED, returnIntent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
