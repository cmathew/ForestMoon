package com.example.cmathew.forestmoon;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.cmathew.forestmoon.bfs.BfsFragment;
import com.example.cmathew.forestmoon.fliptree.FlipTreeFragment;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {
    private FrameLayout contentContainer;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.contentContainer = findViewById(R.id.content_container);
        this.drawerLayout = findViewById(R.id.drawer_layout);
        this.navigationView = findViewById(R.id.nav_view);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            setupDrawerNavigation();
        }

        navigateToIslandFinder();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state
        drawerToggle.syncState();
    }

    private void navigateToTreeFlipper() {
        FlipTreeFragment treeFragment = FlipTreeFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_container, treeFragment).commit();
    }

    private void navigateToIslandFinder() {
        BfsFragment breadthFragment = BfsFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_container, breadthFragment).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.isChecked()) {
            return true;
        }

        item.setChecked(true);
        drawerLayout.closeDrawers();

        if (item.getItemId() == R.id.tree_navigation) {
            navigateToTreeFlipper();
            return true;
        } else if (item.getItemId() == R.id.dfs_navigation) {
            navigateToIslandFinder();
            return true;
        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerNavigation() {
        navigationView.setNavigationItemSelectedListener(this);
        this.drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open_content_desc, R.string.nav_close_content_desc) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.setDrawerIndicatorEnabled(true);
    }
}
