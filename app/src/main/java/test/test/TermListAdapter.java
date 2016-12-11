package test.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by toufik on 2/9/2016.
 */
public class TermListAdapter extends BaseAdapter {

    private ArrayList<String> termsList = new ArrayList<>();
    private static LayoutInflater inflater = null;
    private DetailFragment context;

    public TermListAdapter(DetailFragment context) {
        this.context = context;
        inflater = (LayoutInflater) context.getActivity().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setTermsList(ArrayList<String> list) {
        this.termsList = list;
    }

    @Override
    public int getCount() {
        if (termsList == null)
            return 0;
        return termsList.size();
    }

    @Override
    public Object getItem(int i) {
        return termsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.listview_terms_item, viewGroup, false);
            holder.term = (TextView) convertView.findViewById(R.id.term);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.onDetailFragmentInteraction((String) holder.term.getText());
            }
        });

        holder.term.setText(termsList.get(i));
        return convertView;
    }

    public class ViewHolder {
        TextView term;
    }
}
