package test.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class DetailFragment extends Fragment {

    public static final String ARG_DETAIL_LIST = "DL";

    private ArrayList<String> mDetailList;
    private TermListAdapter adapter;
    private ListView listView;

    private OnDetailFragmentInteractionListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DetailFragment.OnDetailFragmentInteractionListener) {
            mListener = (DetailFragment.OnDetailFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDetailFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public static DetailFragment newInstance(ArrayList<String> list) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_DETAIL_LIST, list);
        fragment.setArguments(args);
        return fragment;
    }

    private void setDefaultMasterDetailView() {
        if (adapter != null && adapter.getCount() > 0)
            onDetailFragmentInteraction((String) listView.getAdapter().getItem(1));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (MainActivity.mTwoPane)
            setDefaultMasterDetailView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        adapter = new TermListAdapter(this);
        if (getArguments() != null) {
            mDetailList = getArguments().getStringArrayList(ARG_DETAIL_LIST);
            adapter.setTermsList(mDetailList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        listView = (ListView) rootView.findViewById(R.id.listview);
        listView.setAdapter(adapter);

        return rootView;
    }

    public void onDetailFragmentInteraction(String term) {
        if (mListener != null) {
            Log.d("DETAILFRAGMENT", "onDetailFragmentInteraction");
            mListener.onDetailFragmentInteraction(term);
        }
    }

    public interface OnDetailFragmentInteractionListener {
        // TODO: Update argument type and name
        void onDetailFragmentInteraction(String term);
    }
}
