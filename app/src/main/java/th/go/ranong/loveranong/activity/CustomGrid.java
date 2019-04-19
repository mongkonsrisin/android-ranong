package th.go.ranong.loveranong.activity;

import android.content.Context;
import android.util.AttributeSet;

public class CustomGrid extends android.support.v7.widget.AppCompatImageView {

    public CustomGrid(Context context) {
        super(context);
    }

    public CustomGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomGrid(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }
}
