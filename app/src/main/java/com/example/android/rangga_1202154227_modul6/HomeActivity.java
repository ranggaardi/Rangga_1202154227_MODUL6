package com.example.android.rangga_1202154227_modul6;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static com.example.android.rangga_1202154227_modul6.R.layout.home;

public class HomeActivity extends AppCompatActivity {
    ViewPager vp; TabLayout tab; AppBarLayout abl; Toolbar tl;
    FirebaseAuth auth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(home);

        vp = findViewById(R.id.vp);
        tab = findViewById(R.id.tabs);
        abl = findViewById(R.id.appbar);
        tl = findViewById(R.id.tb);
        auth = FirebaseAuth.getInstance();


        setSupportActionBar(tl);
        setupPager(vp);


        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setIcon(R.drawable.group);
        tab.getTabAt(1).setIcon(R.drawable.person);
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    abl.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }else{
                    abl.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menulogout) {
            auth.signOut();
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            finish();
        }
        return true;
    }



    public void setupPager(ViewPager v){
        VPAdapter adapter = new VPAdapter(getSupportFragmentManager());
        adapter.addFragment(new MenuSemuaActivity(), "TERBARU");
        adapter.addFragment(new MenuUserActivity(), "SAYA");

        v.setAdapter(adapter);
    }


    public void Post(View view) {
        startActivity(new Intent(HomeActivity.this, UploadActivity.class));
    }


    class VPAdapter extends FragmentPagerAdapter{
        private final List<Fragment> listfragment = new ArrayList<>();
        private final List<String> listfragmenttitle = new ArrayList<>();
        public VPAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listfragment.get(position);
        }

        public void addFragment(Fragment f, String title){
            listfragment.add(f);
            listfragmenttitle.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return listfragment.size();
        }
    }
}
