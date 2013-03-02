package com.example.tutorial;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener {

	MyBringBackSurface ourSurfaceView;
	float x, y, sX, sY, fX, fY, dX, dY, aniX, aniY, scaledX, scaledY;
	Bitmap test, plus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourSurfaceView = new MyBringBackSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		y = x = sX = sY = fX = fY = dX = dY = aniX = aniY = scaledX = scaledY = 0;
		test = BitmapFactory.decodeResource(getResources(),
				R.drawable.greenball);
		plus = BitmapFactory.decodeResource(getResources(),
				R.drawable.plusgreen);
		setContentView(ourSurfaceView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
	}

	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub

		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		x = event.getX();
		y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			sX = event.getX();
			sY = event.getY();
			dX = dY = aniX = aniY = scaledX = scaledY = fX = fY = 0;
			break;

		case MotionEvent.ACTION_UP:
			fX = event.getX();
			fY = event.getY();
			dX = fX - sX;
			dY = fY - sY;
			scaledX = dX / 30;
			scaledY = dY / 30;
			x = y = 0;
			break;

		default:
			break;
		}

		return true;
	}

	public class MyBringBackSurface extends SurfaceView implements Runnable {

		SurfaceHolder ourHolder;
		Thread ourThread = null;
		boolean isRunnig = false;

		public MyBringBackSurface(Context context) {
			super(context);

			ourHolder = getHolder();
		}

		public void pause() {
			isRunnig = false;
			while (true) {
				try {
					ourThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}

			ourThread = null;
		}

		public void resume() {
			isRunnig = true;
			ourThread = new Thread(this);
			ourThread.start();
		}

		public void run() {
			// TODO Auto-generated method stub
			while (isRunnig) {
				if (!ourHolder.getSurface().isValid())
					continue;

				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(02, 02, 150);
				if (x != 0 && y != 0) {
					canvas.drawBitmap(test, x - ((float)test.getWidth() / 2), y
							- ((float)test.getHeight() / 2), null);
				}
				if (sX != 0 && sY != 0) {
					canvas.drawBitmap(plus, sX - ((float)plus.getWidth() / 2), sY
							- ((float)plus.getHeight() / 2), null);
				}
				if (fX != 0 && fY != 0) {
					canvas.drawBitmap(test, fX - ((float)test.getWidth() / 2) - aniX,
							fY - ((float)test.getHeight() / 2) - aniY, null);
					canvas.drawBitmap(plus, fX - ((float)plus.getWidth() / 2), fY
							- ((float)plus.getHeight() / 2), null);
				}
				aniX += scaledX;
				aniY += scaledY;

				ourHolder.unlockCanvasAndPost(canvas);
			}
		}

	}

}
