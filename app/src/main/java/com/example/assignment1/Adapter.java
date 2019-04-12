//package com.example.assignment1;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
///**
// * Provide views to RecyclerView with data from mDataSet.
// */
//public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
//    private static final String TAG = "Adapter";
//
//    private String[] mDataSet;
//
//    // BEGIN_INCLUDE(recyclerViewSampleViewHolder)
//    /**
//     * Provide a reference to the type of views that you are using (custom ViewHolder)
//     */
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        private final TextView textView;
//
//        public ViewHolder(View v) {
//            super(v);
//            // Define click listener for the ViewHolder's View.
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
//                }
//            });
//            textView = (TextView) v.findViewById(R.id.textView);
//        }
//
//        public TextView getTextView() {
//            return textView;
//        }
//    }
//    // END_INCLUDE(recyclerViewSampleViewHolder)
//
//    /**
//     * Initialize the dataset of the Adapter.
//     *
//     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
//     */
//    public Adapter(String[] dataSet) {
//        mDataSet = dataSet;
//    }
//
//    // BEGIN_INCLUDE(recyclerViewOnCreateViewHolder)
//    // Create new views (invoked by the layout manager)
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        // Create a new view.
//        View v = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.text_view, viewGroup, false);
//
//        return new ViewHolder(v);
//    }
//    // END_INCLUDE(recyclerViewOnCreateViewHolder)
//
//    // BEGIN_INCLUDE(recyclerViewOnBindViewHolder)
//    // Replace the contents of a view (invoked by the layout manager)
//    @Override
//    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
//        Log.d(TAG, "Element " + position + " set.");
//
//        // Get element from your dataset at this position and replace the contents of the view
//        // with that element
//        viewHolder.getTextView().setText(mDataSet[position]);
//    }
//    // END_INCLUDE(recyclerViewOnBindViewHolder)
//
//    // Return the size of your dataset (invoked by the layout manager)
//    @Override
//    public int getItemCount() {
//        return mDataSet.length;
//    }
//}
