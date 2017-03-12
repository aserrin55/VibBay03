package com.example.asier.vibbay03;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.asier.vibbay03.Fragments.AllArticlesFragment;
import com.example.asier.vibbay03.Fragments.loginFragment;
import com.example.asier.vibbay03.Fragments.MyArticlesFragment;
import com.example.asier.vibbay03.Fragments.NewArticleFragment;
import com.example.asier.vibbay03.Services.Retro;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Llamar a AllArticlesFragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.include_main, new AllArticlesFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id== R.id.action_search){
            Toast toast = Toast.makeText(context, "Search", duration);
            toast.show();


        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        boolean fragmentTransaction = false;
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.nav_login:
                fragment = new loginFragment();
                fragmentTransaction = true;
                break;
            case R.id.nav_myArticles:
                if(Retro.loggedIn != null){
                    fragment = new MyArticlesFragment();
                    fragmentTransaction = true;
                }else{
                    fragment = new loginFragment();
                    fragmentTransaction = true;
                }
                break;
            case R.id.nav_newArticle:
                if(Retro.loggedIn != null){
                    fragment = new NewArticleFragment();
                    fragmentTransaction = true;
                }else{
                    fragment = new loginFragment();
                    fragmentTransaction = true;
                }
                break;
            case R.id.nav_main:
                fragment = new AllArticlesFragment();
                fragmentTransaction = true;
                break;
        }

        if(fragmentTransaction) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.include_main, fragment)
                    .commit();


//            menuItem.setChecked(true);
//            getSupportActionBar().setTitle(menuItem.getTitle());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
