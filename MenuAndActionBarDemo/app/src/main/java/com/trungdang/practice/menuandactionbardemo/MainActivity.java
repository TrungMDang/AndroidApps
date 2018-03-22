package com.trungdang.practice.menuandactionbardemo;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tb = findViewById(R.id.tb);
        tb.setTitle(R.string.toolbar);
        setSupportActionBar(tb);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint(getResources().getText(R.string.hint));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        //searchView.setIconifiedByDefault(false);
        return true;
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        super.onPrepareOptionsMenu(menu);
//        if (Build.VERSION.SDK_INT > 11) {
//            invalidateOptionsMenu();
//            menu.findItem(R.id.open).setVisible(false);
//            menu.findItem(R.id.edit).setVisible(true);
//        }
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                makeToast("Search");
                return true;
            case R.id.open:
                makeToast("Open");
                return true;
            case R.id.edit:
                makeToast("Edit");
                return true;
            case R.id.save:
                makeToast("Save");
                return true;
            case R.id.new_menu:
                makeToast("New Menu");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void makeToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void showPopup(View v) {

        PopupMenu pm = new PopupMenu(this, findViewById(R.id.tv));
        MenuInflater inflater = pm.getMenuInflater();
        inflater.inflate(R.menu.actions, pm.getMenu());
        pm.show();
    }
}
