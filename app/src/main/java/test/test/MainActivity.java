package test.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        SystemsFragment.OnFragmentInteractionListener, DetailFragment.OnDetailFragmentInteractionListener {

    public static boolean mTwoPane;
    private static final String DETAILFRAGMENT_TAG = "DFTAG";
    private static final String RESULTFRAGMENT_TAG = "RFTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.system_detail_container) != null) {
            mTwoPane = true;
         /*   if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.system_detail_container, new DetailFragment(), DETAILFRAGMENT_TAG)
                        .commit();

                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.result_detail_container, new ResultFragment(), RESULTFRAGMENT_TAG)
                            .commit();
                }
            }*/
        } else {
            mTwoPane = false;
            getSupportActionBar().setElevation(0f);
        }
    }

    @Override
    public void onFragmentInteraction(ArrayList<String> list) {
        if (mTwoPane) {
            DetailFragment fragment = DetailFragment.newInstance(list);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.system_detail_container, fragment, DETAILFRAGMENT_TAG)
                    .commit();
        } else {
            Intent i = new Intent(this, DetailActivity.class);
            i.putStringArrayListExtra("list", list);
            startActivity(i);
        }
    }

    @Override
    public void onDetailFragmentInteraction(String term) {
        if (mTwoPane) {
            ResultFragment fragment = ResultFragment.newInstance(term);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.result_detail_container, fragment, RESULTFRAGMENT_TAG)
                    .commit();
        } else {
            Intent i = new Intent(this, ResultActivity.class);
            i.putExtra("term", term);
            startActivity(i);
        }
    }
}
