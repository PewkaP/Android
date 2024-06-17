package com.example.zad52;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

public class PaintSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    public static class ColoredPath {
        Path path;
        int color;

        ColoredPath(Path path, int color) {
            this.path = path;
            this.color = color;
        }
    }

    private Paint mPaint;
    private Path mCurrentPath;
    private SurfaceHolder mSurfaceHolder;
    private int mCurrentColor = Color.RED;
    private List<ColoredPath> mPaths;

    public PaintSurfaceView(Context context) {
        super(context);
        init();
    }

    public PaintSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintSurfaceView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);

        mCurrentPath = new Path();
        mPaths = new ArrayList<>();

        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);

        setFocusable(true);
    }

    public List<ColoredPath> returnDrawing() {
        // Return a copy of the current drawing
        List<ColoredPath> tempPaths = new ArrayList<>(mPaths);
        mPaths.clear();
        return tempPaths;
    }

    public void setColor(int color) {
        Log.d("setColor", "New color: " + color);
        mCurrentColor = color;
    }

    private Boolean mThreadRunning = true;

    private class DrawingThread extends Thread {
        public void run() {
            while (mThreadRunning) {
                Canvas canvas = null;
                try {
                    canvas = mSurfaceHolder.lockCanvas(null);
                    synchronized (mSurfaceHolder) {
                        synchronized (mThreadRunning) {
                            if (mThreadRunning) {
                                drawFrame(canvas);
                            }
                        }
                    }
                } finally {
                    if (canvas != null) {
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private DrawingThread mDrawingThread;

    private void startAnimation() {
        synchronized (mThreadRunning) {
            mThreadRunning = true;
        }
        mDrawingThread = new DrawingThread();
        mDrawingThread.start();
    }

    private void stopAnimation() {
        synchronized (mThreadRunning) {
            mThreadRunning = false;
        }
        try {
            mDrawingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        startAnimation();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Handle surface changes if necessary
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stopAnimation();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_SPACE) {
            pauseResume();
        }
        return super.onKeyUp(keyCode, event);
    }

    private void pauseResume() {
        if (mThreadRunning) {
            stopAnimation();
        } else {
            startAnimation();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mCurrentPath = new Path();
                mCurrentPath.moveTo(x, y);
                mPaths.add(new ColoredPath(mCurrentPath, mCurrentColor));
                return true;
            case MotionEvent.ACTION_MOVE:
                mCurrentPath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                return false;
        }

        invalidate();
        return true;
    }

    protected void drawFrame(Canvas canvas) {
        // Clear the canvas with white color
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        for (ColoredPath coloredPath : mPaths) {
            mPaint.setColor(coloredPath.color);
            canvas.drawPath(coloredPath.path, mPaint);
        }
    }

    public void clearCanvas() {
        mPaths.clear();
        invalidate();
    }
}
