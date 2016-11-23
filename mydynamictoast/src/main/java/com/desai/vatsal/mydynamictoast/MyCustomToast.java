package com.desai.vatsal.mydynamictoast;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by vatsaldesai on 15-11-2016.
 */

public class MyCustomToast {

    private Context context;
    private Toast toast;
    private View toastRoot;
    private ImageView iv_top_icon;
    private ImageView iv_left_icon;
    private ImageView iv_right_icon;
    private ImageView iv_bottom_icon;
    private TextView tv_msg;
    private LinearLayout parent_layout;

    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;
    public static final String POSITION_LEFT = "left";
    public static final String POSITION_RIGHT = "right";
    public static final String POSITION_TOP = "top";
    public static final String POSITION_BOTTOM = "bottom";

    public MyCustomToast(Context context) {
        this.context = context;

        init(context);
    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        toastRoot = inflater.inflate(R.layout.my_dynamic_toast, null);

        iv_top_icon = (ImageView) toastRoot.findViewById(R.id.iv_top_icon);
        iv_left_icon = (ImageView) toastRoot.findViewById(R.id.iv_left_icon);
        iv_right_icon = (ImageView) toastRoot.findViewById(R.id.iv_right_icon);
        iv_bottom_icon = (ImageView) toastRoot.findViewById(R.id.iv_bottom_icon);
        tv_msg = (TextView) toastRoot.findViewById(R.id.tv_msg);
        parent_layout = (LinearLayout) toastRoot.findViewById(R.id.parent_layout);

        toast = new Toast(context);
        toast.setView(toastRoot);
        toast.setDuration(Toast.LENGTH_SHORT);
    }

    public void setCustomMessageText(String text) {
        tv_msg.setText(text);
    }

    public void setCustomMessageTypeface(String path) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), path);
        tv_msg.setTypeface(typeface);
    }

    public void setCustomMessageTypeface(Typeface typeface) {
        tv_msg.setTypeface(typeface);
    }

    public void setCustomMessageTextSize(float textSize) {
        tv_msg.setTextSize(textSize);
    }

    public void setCustomMessageTextColor(int textColor) {
        tv_msg.setTextColor(textColor);
    }

    public void setCustomMessageTextColor(String textColor) {
        tv_msg.setTextColor(Color.parseColor(textColor));
    }

    public void setCustomMessageIcon(int icon, String iconPosition) {

        if (iconPosition.equals(POSITION_LEFT)) {
            iv_top_icon.setVisibility(View.GONE);
            iv_left_icon.setVisibility(View.VISIBLE);
            iv_right_icon.setVisibility(View.GONE);
            iv_bottom_icon.setVisibility(View.GONE);

            iv_left_icon.setImageResource(icon);

        } else if (iconPosition.equals(POSITION_RIGHT)) {
            iv_top_icon.setVisibility(View.GONE);
            iv_left_icon.setVisibility(View.GONE);
            iv_right_icon.setVisibility(View.VISIBLE);
            iv_bottom_icon.setVisibility(View.GONE);

            iv_right_icon.setImageResource(icon);

        } else if (iconPosition.equals(POSITION_TOP)) {
            iv_top_icon.setVisibility(View.VISIBLE);
            iv_left_icon.setVisibility(View.GONE);
            iv_right_icon.setVisibility(View.GONE);
            iv_bottom_icon.setVisibility(View.GONE);

            iv_top_icon.setImageResource(icon);

        } else if (iconPosition.equals(POSITION_BOTTOM)) {
            iv_top_icon.setVisibility(View.GONE);
            iv_left_icon.setVisibility(View.GONE);
            iv_right_icon.setVisibility(View.GONE);
            iv_bottom_icon.setVisibility(View.VISIBLE);

            iv_bottom_icon.setImageResource(icon);
        }

    }

    public void setCustomMessageIconColor(int iconColor) {
        iv_top_icon.setColorFilter(iconColor);
        iv_left_icon.setColorFilter(iconColor);
        iv_right_icon.setColorFilter(iconColor);
        iv_bottom_icon.setColorFilter(iconColor);
    }

    public void setCustomMessageIconColor(String iconColor) {
        iv_top_icon.setColorFilter(Color.parseColor(iconColor));
        iv_left_icon.setColorFilter(Color.parseColor(iconColor));
        iv_right_icon.setColorFilter(Color.parseColor(iconColor));
        iv_bottom_icon.setColorFilter(Color.parseColor(iconColor));
    }

    public void setCustomMessageBackgroundColor(int backgroundColor) {
        parent_layout.setBackgroundColor(backgroundColor);
//        parent_layout.setBackgroundDrawable(setCornerRadious(parent_layout, backgroundColor));
    }

    public void setCustomMessageBackgroundColor(String backgroundColor) {
        parent_layout.setBackgroundColor(Color.parseColor(backgroundColor));
    }

    public void setCustomMessageBackgroundDrawable(int backgroundDrawable) {
        parent_layout.setBackgroundResource(backgroundDrawable);
    }

    public void setCustomMessageDuration(int duration) {
        toast.setDuration(duration);
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        toast.setGravity(gravity, xOffset, yOffset);
    }

    public void show() {
        toast.show();
    }

    private BitmapDrawable setCornerRadious(View view, int color) {
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

//        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 20;

//        paint.setColor(Color.YELLOW);
        paint.setColor(color);
//        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        paint.setAntiAlias(true);
//        canvas.drawARGB(0, 0, 0, 0);
//        canvas.drawColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);

        return drawable;
    }


}
