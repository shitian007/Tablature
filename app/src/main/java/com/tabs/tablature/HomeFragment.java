package com.tabs.tablature;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private ListView featuredTabsList;

    private ArrayList<String> featuredTabs;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Tablature");

        // TODO: Setup service to load tabs
        featuredTabs = new ArrayList<>();
        featuredTabs.add("Featured Tab 1");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        featuredTabsList = view.findViewById(R.id.featured_tabs);

        ListAdapter featuredTabsListAdapter = new FeaturedTabsListAdapter(getContext(), featuredTabs);
        featuredTabsList.setAdapter(featuredTabsListAdapter);

        return view;
    }

    class FeaturedTabsListAdapter extends ArrayAdapter<String> {

        private Context context;
        private ArrayList<String> featuredTabs;

        public FeaturedTabsListAdapter(Context context, ArrayList<String> featuredTabs) {
            super(context, R.layout.layout_ftabs_item, featuredTabs);
            this.featuredTabs = featuredTabs;
            this.context = context;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(context).
                    inflate(R.layout.layout_ftabs_item, viewGroup, false);

            TextView textViewItemName = view.findViewById(R.id.ftabs_item_name);
            textViewItemName.setText(getItem(position));
            return view;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
