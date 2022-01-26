package com.theost.drawapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyView extends View {

    private boolean isReversed = false;
    private int x = 0;

    public MyView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        // #fafafa
        // rgb(255, 255, 255)
        // 14234

        // Color.parseColor("#fafafa")
        // Color.WHITE

        paint.setColor(Color.BLUE);
        canvas.drawLine(0, 250, 400, 600, paint);

        paint.setColor(Color.RED);

        canvas.drawCircle(getWidth() / 2f, getHeight() / 2f, 60, paint);

        paint.setColor(Color.GREEN);


        int rectWidth = 400;
        int rectHeight = 400;

        if (x == 0) {
            isReversed = false;
        } else if (x + rectWidth == getWidth()) {
            isReversed = true;
        }

        if (isReversed) {
            x -= 1;
        } else {
            x += 1;
        }

        canvas.drawRect(x, 60, x + rectWidth, 60 + rectHeight, paint);
        invalidate();
    }

}
