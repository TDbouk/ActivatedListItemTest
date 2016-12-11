package test.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private ResultFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        /* Show animation upon activity transition */
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Intent intent = getIntent();
            String term = intent.getParcelableExtra("term");
            fragment = ResultFragment.newInstance(term);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.result_detail_container, fragment)
                    .commit();

        }
    }
}