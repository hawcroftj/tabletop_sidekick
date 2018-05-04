package hawcroftj.github.com.tabletopsidekick;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * @author Jadon H
 */
public class MainActivity extends AppCompatActivity implements
        MainFragment.OnFragmentInteractionListener,
        DiceRoller.OnFragmentInteractionListener,
        RandomNumberGenerator.OnFragmentInteractionListener{

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // add a toolbar to the activity
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // actionbar will house navigation drawer menu button
        try {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        } catch (NullPointerException e) { /* TODO */ }

        // load main (default) fragment into content frame
        MainFragment mainFragment = new MainFragment();
        commitFragmentTransaction(mainFragment);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        // prepare navigation view
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_dice_roller: // load DiceRoller fragment into content frame
                        DiceRoller roller = new DiceRoller();
                        commitFragmentTransaction(roller);
                        break;
                    case R.id.nav_random_number: // load RandomNumberGenerator fragment into content frame
                        RandomNumberGenerator generator = new RandomNumberGenerator();
                        commitFragmentTransaction(generator);
                        break;
                }
                // set item as 'checked'
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // TODO
    }

    /**
     * Commits a FragmentTransaction to force a new Fragment into the content frame.
     * @param fragment The fragment to be loaded into the content frame.
     */
    private void commitFragmentTransaction(Fragment fragment) {
        // load provided fragment into content frame
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}
