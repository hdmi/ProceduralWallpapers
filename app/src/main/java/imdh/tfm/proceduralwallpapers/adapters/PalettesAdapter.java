package imdh.tfm.proceduralwallpapers.adapters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import imdh.tfm.proceduralwallpapers.R;
import imdh.tfm.proceduralwallpapers.models.Palette;

/**
 * Created by CarlosAB on 09/08/2017.
 */

public class PalettesAdapter extends RecyclerView.Adapter<PalettesAdapter.ViewHolder> {

    private List<Palette> mPalettesList;
    private AppCompatActivity mAppCompatActivity;

    public PalettesAdapter(AppCompatActivity mAppCompatActivity, List<Palette> palettes) {
        this.mPalettesList = palettes;
        this.mAppCompatActivity = mAppCompatActivity;
    }

    @Override
    public PalettesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View paletteView = inflater.inflate(R.layout.item_palette, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(mAppCompatActivity, paletteView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PalettesAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Palette palette = mPalettesList.get(position);

        // Set item views based on your views and data model
        ImageView imageView0 = viewHolder.imageView0;
        ImageView imageView1 = viewHolder.imageView1;
        ImageView imageView2 = viewHolder.imageView2;
        ImageView imageView3 = viewHolder.imageView3;
        ImageView imageView4 = viewHolder.imageView4;

        imageView0.setBackgroundColor(palette.getC0());
        imageView1.setBackgroundColor(palette.getC1());
        imageView2.setBackgroundColor(palette.getC2());
        imageView3.setBackgroundColor(palette.getC3());
        imageView4.setBackgroundColor(palette.getC4());

    }

    @Override
    public int getItemCount() {
        return mPalettesList.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView imageView0;
        public ImageView imageView1;
        public ImageView imageView2;
        public ImageView imageView3;
        public ImageView imageView4;
        private AppCompatActivity mAppCompatActivity;

        public ViewHolder(AppCompatActivity mAppCompatActivity, View itemView) {
            super(itemView);
            this.mAppCompatActivity = mAppCompatActivity;
            imageView0 = (ImageView) itemView.findViewById(R.id.color0);
            imageView1 = (ImageView) itemView.findViewById(R.id.color1);
            imageView2 = (ImageView) itemView.findViewById(R.id.color2);
            imageView3 = (ImageView) itemView.findViewById(R.id.color3);
            imageView4 = (ImageView) itemView.findViewById(R.id.color4);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Palette palette = mPalettesList.get(position);
    
//                Intent resultIntent = new Intent();
//                resultIntent.putExtra("PALETTE", palette);
//                mAppCompatActivity.setResult(Activity.RESULT_OK, resultIntent);
//                mAppCompatActivity.finish();

            }
        }
    }

    private Context getContext() {
        return mAppCompatActivity;
    }

}
