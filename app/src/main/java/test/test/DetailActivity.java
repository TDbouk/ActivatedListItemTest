package test.test;

/**
 * Created by toufik on 11/29/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements
        DetailFragment.OnDetailFragmentInteractionListener {

    private DetailFragment fragment;
    private boolean isTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            ArrayList<String> list = getIntent().getStringArrayListExtra("list");
            fragment = DetailFragment.newInstance(list);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.system_detail_container, fragment)
                    .commit();
        }
        if (findViewById(R.id.result_detail_container) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    @Override
    public void onDetailFragmentInteraction(String term) {
        if (isTwoPane) {
            ResultFragment fragment = ResultFragment.newInstance(term);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.result_detail_container, fragment, "RFTAG")
                    .commit();
        } else {
            Intent i = new Intent(this, ResultActivity.class);
            i.putExtra("term", term);
            startActivity(i);
        }
    }
}