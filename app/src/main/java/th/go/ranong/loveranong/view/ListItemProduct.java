package th.go.ranong.loveranong.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.inthecheesefactory.thecheeselibrary.view.BaseCustomViewGroup;
import com.inthecheesefactory.thecheeselibrary.view.state.BundleSavedState;

import th.go.ranong.loveranong.R;


public class ListItemProduct extends BaseCustomViewGroup {

    TextView tvName,tvName2;
    ImageView ivPhoto;
    Typeface typeface;

    public ListItemProduct(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public ListItemProduct(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs, 0, 0);
    }

    public ListItemProduct(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public ListItemProduct(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
        initWithAttrs(attrs, defStyleAttr, defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(),   R.layout.list_item_product, this);
    }

    private void initInstances() {
        // findViewById here
        typeface = Typeface.createFromAsset(getContext().getAssets(),  "superspace_bold.ttf");
        tvName = findViewById(R.id.tvName);
        tvName2 = findViewById(R.id.tvName2);
        ivPhoto = findViewById(R.id.ivPhoto);
        tvName.setTypeface(typeface);
        tvName2.setTypeface(typeface);



    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        BundleSavedState savedState = new BundleSavedState(superState);
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);

        return savedState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        BundleSavedState ss = (BundleSavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        Bundle bundle = ss.getBundle();
        // Restore State from bundle here
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width * 9 / 16 ;
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
        setMeasuredDimension(width,height);
    }
    public void setNameText(String text) {
        tvName.setText(text);
    }
    public void setName2Text(String text) {
        tvName2.setText(text+" บาท");
    }
    public void  setPhotoUrl(String url) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.mock)
                .error(R.drawable.mock);
        Glide
                .with(getContext())
                .applyDefaultRequestOptions(options)
                .load(url).into(ivPhoto);
    }
}
