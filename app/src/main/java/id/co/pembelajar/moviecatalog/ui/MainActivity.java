package id.co.pembelajar.moviecatalog.ui;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import id.co.pembelajar.moviecatalog.R;
import id.co.pembelajar.moviecatalog.adapter.TabAdapter;

public class MainActivity extends AppCompatActivity {
    private TypedArray icTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_widget);
        setSupportActionBar(toolbar);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        LoadedFragment(viewPager, tabLayout);

    }

    private void LoadedFragment(ViewPager pager, TabLayout tabLayout) {
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), 0);
        String titleMovie = getResources().getString(R.string.title_movie);
        String titleTv = getResources().getString(R.string.title_tv);
        adapter.addTabFragment(new HomeMovieFragment(), titleMovie);
        adapter.addTabFragment(new HomeTvShowsFragment(), titleTv);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_change_language, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.change_language){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
