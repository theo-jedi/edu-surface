package com.theost.drawapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private static final int CIRCLE_RADIUS = 40;

    private Thread drawThread;
    private boolean isRunning;

    private final SurfaceHolder surfaceHolder;
    private final Paint paint;

    int x = 0;
    int y = 0;

    public MySurfaceView(Context context) {
        super(context);
        paint = new Paint();
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this); // required
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this); // required
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        // do something
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        // do something
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawClick(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                performClick();
                break;
        }

        return super.onTouchEvent(event);
    }

    private void drawClick(float x, float y) {
        if (surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            paint.setColor(Color.GREEN);
            canvas.drawCircle(x, y, CIRCLE_RADIUS, paint);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        x += 1;
        y += 1;
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            paint.setColor(Color.BLUE);
            canvas.drawCircle(x, y, CIRCLE_RADIUS, paint);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        isRunning = true;
        drawThread = new Thread(this);
        // drawThread.start();
    }

    public void stop() {
        isRunning = false;
    }

}
