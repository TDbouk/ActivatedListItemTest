package test.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResultFragment extends Fragment {

    public static final String ARG_TERM = "TERM_RESULT";
    String term;

    public ResultFragment() {
        // Required empty public constructor
    }

    public static ResultFragment newInstance(String term) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TERM, term);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            term = getArguments().getString(ARG_TERM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_result, container, false);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        TextView tv = (TextView) rootView.findViewById(R.id.tv);
        tv.setText(term);
        return rootView;
    }
}
