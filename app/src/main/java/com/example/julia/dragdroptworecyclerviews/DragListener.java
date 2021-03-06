package com.example.julia.dragdroptworecyclerviews;

import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.View;

import java.util.List;

public class DragListener implements View.OnDragListener {

    private boolean isDropped = false;


    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DROP:
                isDropped = true;
                int positionTarget = -1;

                View viewSource = (View) event.getLocalState();
                int viewId = v.getId();
                final int flItem = R.id.frame_layout_item;
                final int rvTop = R.id.rvTop;

                switch (viewId) {
                    case flItem:
                    case rvTop:

                        RecyclerView target;
                        switch (viewId) {
                            case rvTop:
                                target = (RecyclerView) v.getRootView().findViewById(rvTop);
                                break;
                            default:
                                target = (RecyclerView) v.getParent();
                                positionTarget = (int) v.getTag();
                        }

                        if (viewSource != null) {
                            RecyclerView source = (RecyclerView) viewSource.getParent();

                            ListAdapter adapterSource = (ListAdapter) source.getAdapter();
                            int positionSource = (int) viewSource.getTag();
                            String list = adapterSource.getList().get(positionSource);
                            List<String> listSource = adapterSource.getList();
                            listSource.remove(positionSource);
                            adapterSource.updateList(listSource);
                            adapterSource.notifyDataSetChanged();
                            ListAdapter adapterTarget = (ListAdapter) target.getAdapter();
                            List<String> customListTarget = adapterTarget.getList();
                            if (positionTarget >= 0) {
                                customListTarget.add(positionTarget, list);
                            } else {
                                customListTarget.add(list);
                            }
                            adapterTarget.updateList(customListTarget);
                            adapterTarget.notifyDataSetChanged();
                        }
                        break;
                }
                break;
        }

        if (!isDropped && event.getLocalState() != null) {
            ((View) event.getLocalState()).setVisibility(View.VISIBLE);
        }
        return true;
    }
}