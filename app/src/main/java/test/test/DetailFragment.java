package test.test;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class DetailFragment extends Fragment {

    public static final String ARG_DETAIL_LIST = "DL";

    private ArrayList<String> mDetailList;
    private TermListAdapter adapter;
    private ListView listView;
    private int selectedItemPosition = ListView.INVALID_POSITION;
    private final String EXTRA_SELECTED_ITEM_POSITION = "selected_item_position";

    private OnDetailFragmentInteractionListener mListener;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (selectedItemPosition != ListView.INVALID_POSITION)
            outState.putInt(EXTRA_SELECTED_ITEM_POSITION, selectedItemPosition);
        super.onSaveInstanceState(outState);
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

        if (savedInstanceState == null)
            if (MainActivity.mTwoPane)
                setDefaultMasterDetailView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null)
            mDetailList = getArguments().getStringArrayList(ARG_DETAIL_LIST);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        listView = (ListView) rootView.findViewById(R.id.listview);
        adapter = new TermListAdapter(this);
        adapter.setTermsList(mDetailList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onDetailFragmentInteraction((String) listView.getItemAtPosition(i));
                selectedItemPosition = i;
            }
        });

        if (savedInstanceState != null && savedInstanceState.containsKey(EXTRA_SELECTED_ITEM_POSITION)) {
            selectedItemPosition = savedInstanceState.getInt(EXTRA_SELECTED_ITEM_POSITION);
            if (selectedItemPosition != ListView.INVALID_POSITION) {
                listView.smoothScrollToPosition(selectedItemPosition);
            }
        }

        return rootView;
    }

    public void onDetailFragmentInteraction(String term) {
        if (mListener != null) {
            mListener.onDetailFragmentInteraction(term);
        }
    }

    public interface OnDetailFragmentInteractionListener {
        void onDetailFragmentInteraction(String term);
    }
}
