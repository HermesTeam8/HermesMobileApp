package com.example.hermes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class StartHere extends AppCompatActivity {

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView messageList;
    private Toolbar mToolbar;

    private BottomNavigationView bottomNavigationView;
    private Fragment selectorFragment;

    private Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_starthere);


        register = findViewById(R.id.registerButton);
        login = findViewById(R.id.loginButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartHere.this, RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartHere.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                                | Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();


        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(StartHere.this , MainActivity.class));
            finish();
        }
    }
}

//            bottomNavigationView = findViewById(R.id.bottom_navigation);
//
//            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//                switch (menuItem.getItemId()){
//                    case R.id.nav_home :
//                    Toast.makeText(MainActivity.this,"HOME",Toast.LENGTH_SHORT).show();
//                    selectorFragment = new HomeFragment();
//                    break;
//
//                    case R.id.nav_search :
//                    Toast.makeText(MainActivity.this, "Search Connections",Toast.LENGTH_SHORT).show();
//                    selectorFragment = new SearchFragment();
//                    break;
//
//                    case R.id.nav_add :
//                    //this not showing because fragment is never implemented
//                    //selectorFragment = null;
//                    Toast.makeText(MainActivity.this, "Write Message",Toast.LENGTH_SHORT).show();
//                    //may have to rename or change this activity to better reflect Write Message Activity
//                    startActivity(new Intent(MainActivity.this, PostActivity.class));
//                    break;
//
//                    case R.id.nav_messages:
//                    Toast.makeText(MainActivity.this, "Messages",Toast.LENGTH_SHORT).show();
//                    selectorFragment = new NotificationFragment();
//                    break;
//
//                    case R.id.nav_profile :
//                    Toast.makeText(MainActivity.this, "Profile",Toast.LENGTH_SHORT).show();
//                    selectorFragment = new ProfileFragment();
//                    break;
//                    }
//
//                    if (selectorFragment != null){
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , selectorFragment).commit();
//                    }
//
//                    return  true;
//
//                }
//            });
//
//            Bundle intent = getIntent().getExtras();
//            if (intent != null) {
//                String profileId = intent.getString("publisherId");
//
//                getSharedPreferences("PROFILE", MODE_PRIVATE).edit().putString("profileId", profileId).apply();
//
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
//                bottomNavigationView.setSelectedItemId(R.id.nav_profile);
//            } else {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container , new HomeFragment()).commit();
//            }
//        }

//        navigationView = (NavigationView)   findViewById(R.id.navigation_view);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//
//        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);
//        mToolbar = (Toolbar) navigationView.findViewById(R.id.main_app_bar);
//        //setSupportActionBar(mToolbar);
//        // getSupportActionBar().setTitle("HOME PAGE");
//        //mToolbar.setTitle("HOMEPAGE!!");
//        drawerLayout = (DrawerLayout)   navigationView.findViewById(R.id.drawable_layout);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, R.string.drawer_open,R.string.drawer__close);
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                UserMenuSelector(item);
//                return false;
//            }
//
//
//        });

//        login = (Button) findViewById(R.id.login);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openActivityLogin();
//            }
//        });


//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void UserMenuSelector(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.nav_home:
//                Toast.makeText(this,"HOME", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.nav_connections:
//                Toast.makeText(this,"Connections", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.nav_messages:
//                Toast.makeText(this,"Messages", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.nav_settings:
//                Toast.makeText(this,"Settings", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }
