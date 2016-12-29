package google.com.lab3;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sergey on 12.12.2016.
 */

public class RandomTextAdapter extends RecyclerView.Adapter<RandomTextAdapter.RandomTextViewHolder> {

    HashSet<Integer> setChecker;
    private int checkedCount = 0;

    @Override
    public RandomTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        setChecker = new HashSet<>();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_random, parent, false);
        return new RandomTextViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RandomTextViewHolder holder, final int position) {
        holder.bind(position, new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    setChecker.add(position);
                    checkedCount ++;
                }else{
                    setChecker.remove(position);
                    checkedCount --;
                }
            }
        }, setChecker.contains(position));
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    public int getCheckedCount() {
        return checkedCount;
    }

    class RandomTextViewHolder extends RecyclerView.ViewHolder{


        private CircleImageView imageView;
        private TextView textView;
        private CheckBox checkBox;

        public RandomTextViewHolder(View itemView) {
            super(itemView);
            initView();
        }

        private void initView() {
            imageView = (CircleImageView) itemView.findViewById(R.id.image_view);
            textView = (TextView) itemView.findViewById(R.id.text_view);
            checkBox = (CheckBox) itemView.findViewById(R.id.check_box);
        }

        public void setRandomColor(){
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            ColorDrawable colorDrawable = new ColorDrawable(color);
            imageView.setImageDrawable(colorDrawable);
        }

        public void bind(int position, CompoundButton.OnCheckedChangeListener listener, boolean isChecked) {
            setRandomColor();
            textView.setText("Random item #%d" + position);
            checkBox.setOnCheckedChangeListener(listener);
            checkBox.setChecked(isChecked);
        }
    }

}
