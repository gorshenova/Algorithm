package com.test.algorithms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class RecursionFragment extends Fragment {

    private ViewGroup rootLayout;
    private TextView countTextView;

    public static RecursionFragment get() {
        return new RecursionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recursion, container, false);
        rootLayout = (ViewGroup) view.findViewById(R.id.root_layout);
        countTextView = (TextView) view.findViewById(R.id.tv_all_views_count);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Task #1: The layout with multiply hierarchy is done.
        // It needs to highlight the TextView which contains the highlighted text.
        String highlightedText;
        int colorId;

        //1 solution of the task
        highlightedText = "friend";
        colorId = android.R.color.holo_orange_dark;
        TextView  foundedTV1 = findTextViewByText(highlightedText, rootLayout);
        highlightTextView(foundedTV1, colorId, highlightedText);

        //2 solution of the task
        highlightedText = "dear";
        colorId = android.R.color.holo_blue_dark;
        List<TextView> tvItems = getAllTextViews(rootLayout);
        for (TextView tv : tvItems) {
            if (tv.getText().toString().contains(highlightedText)) {
                highlightTextView(tv, colorId, highlightedText);
                break;
            }
        }


        //Task #2: The layout with multiply hierarchy is done.
        // It needs found all views from this layout and display the amount of views
        List<View> allViews = getAllView(rootLayout);
        String msg = "The amount of all views is " + allViews.size() + ".";
        countTextView.setVisibility(View.VISIBLE);
        countTextView.setText(msg);
    }

    private void highlightTextView(TextView foundedTV, int colorId, String highlightedText){
        if (foundedTV != null) {
            foundedTV.setBackgroundColor(getResources().getColor(colorId));
        } else {
            Toast.makeText(getContext(), "TextView with text '" + highlightedText + "' is not found.", Toast.LENGTH_SHORT).show();
        }

    }

    private TextView findTextViewByText(String text, View v) {
        TextView result = null;
        if (!(v instanceof ViewGroup)) {
            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                if (tv.getText() != null && tv.getText().toString().contains(text)) {
                    //Трудность: как отсюда получить результат и вернуть его в метод findTextViewByText
                    //Решение: в рекурсивном вызове методе присваивать результат,
                    //т.е. сделать result = findTextViewByText(text, child);
                    result = tv;
                }
            }
        } else {
            ViewGroup viewGroup = (ViewGroup) v;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                result = findTextViewByText(text, child);
            }

        }
        return result;
    }

    private List<TextView> getAllTextViews(View v) {
        List<TextView> result = new ArrayList<>();
        if (!(v instanceof ViewGroup)) {
            if (v instanceof TextView) {
                List<TextView> tvList = new ArrayList<>();
                tvList.add((TextView) v);
                return tvList;
            }
        } else {
            ViewGroup viewGroup = (ViewGroup) v;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                result.addAll(getAllTextViews(child));
            }
        }
        return result;
    }


    private List<View> getAllView(View view) {
        List<View> result = new ArrayList<>();
        if (!(view instanceof ViewGroup)) {
            List<View> viewItems = new ArrayList<>();
            viewItems.add(view);
            return viewItems;
        }

        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            result.add(child);
            result.addAll(getAllView(child));
        }
        return result;
    }

}