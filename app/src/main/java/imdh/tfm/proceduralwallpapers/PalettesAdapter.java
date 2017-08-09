package imdh.tfm.proceduralwallpapers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by CarlosAB on 09/08/2017.
 */

public class PalettesAdapter extends RecyclerView.Adapter<PalettesAdapter.ViewHolder>{

    private List<Palette> mPalettesList;
    private Context mContext;

    public PalettesAdapter(Context context, List<Palette> palettes){
        this.mPalettesList = palettes;
        this.mContext = context;
    }

    @Override
    public PalettesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View paletteView = inflater.inflate(R.layout.item_palette, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(paletteView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PalettesAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Palette palette = mPalettesList.get(position);
        List<OneColor> colorsList = palette.getColorsList();

        // Set item views based on your views and data model
        ImageView imageView0 = viewHolder.imageView0;
        ImageView imageView1 = viewHolder.imageView1;
        ImageView imageView2 = viewHolder.imageView2;
        ImageView imageView3 = viewHolder.imageView3;
        ImageView imageView4 = viewHolder.imageView4;

        imageView0.setBackgroundColor(colorsList.get(0).getColor());
        imageView1.setBackgroundColor(colorsList.get(1).getColor());
        imageView2.setBackgroundColor(colorsList.get(2).getColor());
        imageView3.setBackgroundColor(colorsList.get(3).getColor());
        imageView4.setBackgroundColor(colorsList.get(4).getColor());
    }

    @Override
    public int getItemCount() {
        return mPalettesList.size();
    }

    // Provide a direct reference to each of the views within a data item
// Used to cache the views within the item layout for fast access
public class ViewHolder extends RecyclerView.ViewHolder {
    // Your holder should contain a member variable
    // for any view that will be set as you render a row
    public ImageView imageView0;
    public ImageView imageView1;
    public ImageView imageView2;
    public ImageView imageView3;
    public ImageView imageView4;

    public ViewHolder(View itemView) {
        super(itemView);
        imageView0 = (ImageView) itemView.findViewById(R.id.color0);
        imageView1 = (ImageView) itemView.findViewById(R.id.color1);
        imageView2 = (ImageView) itemView.findViewById(R.id.color2);
        imageView3 = (ImageView) itemView.findViewById(R.id.color3);
        imageView4 = (ImageView) itemView.findViewById(R.id.color4);
    }
}

    private Context getContext() {
        return mContext;
    }

}
