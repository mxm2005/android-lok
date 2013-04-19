package sf.hmg.turntest;

import java.text.DecimalFormat;
import java.util.Vector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

public class ReadView extends ImageView {

	private Vector<String> m_lines = new Vector<String>();
	private Paint mTextPaint;
	private int m_mbBufBegin;
	private static int m_mbBufLen;
	private int textSize = 28;
	
	private int screen_W, screen_H;// �ɼ���Ļ�Ŀ�߶�

	private int bitmap_W, bitmap_H;// ��ǰͼƬ���

	private int MAX_W, MAX_H, MIN_W, MIN_H;// ����ֵ

	private int current_Top, current_Right, current_Bottom, current_Left;// ��ǰͼƬ������������

	private int start_Top = -1, start_Right = -1, start_Bottom = -1,
			start_Left = -1;// ��ʼ��Ĭ��λ��.

	private int start_x, start_y, current_x, current_y;// ����λ��

	private float beforeLenght, afterLenght;// ���������

	private float scale_temp;// ���ű���

	/**
	 * ģʽ NONE���� DRAG����ק. ZOOM:����
	 * 
	 * @author zhangjia
	 * 
	 */
	private enum MODE {
		NONE, DRAG, ZOOM

	};

	private MODE mode = MODE.NONE;// Ĭ��ģʽ

	private boolean isControl_V = false;// ��ֱ���

	private boolean isControl_H = false;// ˮƽ���

	private ScaleAnimation scaleAnimation;// ���Ŷ���

	private boolean isScaleAnim = false;// ���Ŷ���
	
	public void setM_mbBufBegin(int m_mbBufBegin) {
		this.m_mbBufBegin = m_mbBufBegin;
	}

	@SuppressWarnings("static-access")
	public void setM_mbBufLen(int m_mbBufLen) {
		this.m_mbBufLen = m_mbBufLen;
	}


	public void setM_lines(Vector<String> m_lines) {
		this.m_lines = m_lines;
	}

	public ReadView(Context context) {
		super(context);
		this.mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    	this.mTextPaint.setTextAlign(Align.LEFT);
    	this.mTextPaint.setTextSize(textSize);
    	this.mTextPaint.setColor(Color.BLACK);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(0xffff9e85);
		mTextPaint.setAntiAlias(true);
		int y = textSize;
		int x = 32;
		for (String strLine : m_lines) {
//			System.out.println("strLine   " + strLine);
			y += (textSize);
			canvas.drawText(strLine, x, y, mTextPaint);
		}
//		System.out.println("���ȣ� " + m_mbBufLen + "\n" + "begin����" + m_mbBufBegin);
		float fPercent = (float) (m_mbBufBegin * 1.0 / m_mbBufLen);
		DecimalFormat df = new DecimalFormat("#0.0");
		String strPercent = df.format(fPercent * 100) + "%";
		int nPercentWidth = (int) mTextPaint.measureText("9999.9%") + 1;//999.9%�ַ����Ŀ��
		canvas.drawText(strPercent, 960 - nPercentWidth, 540 - 5, mTextPaint);
		super.onDraw(canvas);
	}
	
	
//	/** �ɼ���Ļ��� **/
//	public void setScreen_W(int screen_W) {
//		this.screen_W = screen_W;
//	}
//
//	/** �ɼ���Ļ�߶� **/
//	public void setScreen_H(int screen_H) {
//		this.screen_H = screen_H;
//	}
//
//	/***
//	 * ������ʾͼƬ
//	 */
//	@Override
//	public void setImageBitmap(Bitmap bm) {
//		super.setImageBitmap(bm);
//		/** ��ȡͼƬ��� **/
//		bitmap_W = bm.getWidth();
//		bitmap_H = bm.getHeight();
//
//		MAX_W = bitmap_W * 3;
//		MAX_H = bitmap_H * 3;
//
//		MIN_W = bitmap_W / 2;
//		MIN_H = bitmap_H / 2;
//
//	}
//
//	@Override
//	protected void onLayout(boolean changed, int left, int top, int right,
//			int bottom) {
//		super.onLayout(changed, left, top, right, bottom);
//		if (start_Top == -1) {
//			start_Top = top;
//			start_Left = left;
//			start_Bottom = bottom;
//			start_Right = right;
//		}
//
//	}
//	
//	/***
//	 * touch �¼�
//	 */
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		/** �����㡢��㴥�� **/
//		switch (event.getAction() & MotionEvent.ACTION_MASK) {
//		case MotionEvent.ACTION_DOWN:
//			onTouchDown(event);
//			break;
//		// ��㴥��
//		case MotionEvent.ACTION_POINTER_DOWN:
//			onPointerDown(event);
//			break;
//
//		case MotionEvent.ACTION_MOVE:
//			onTouchMove(event);
//			break;
//		case MotionEvent.ACTION_UP:
//			mode = MODE.NONE;
//			break;
//
//		// ����ɿ�
//		case MotionEvent.ACTION_POINTER_UP:
//			mode = MODE.NONE;
//			/** ִ�����Ż�ԭ **/
//			if (isScaleAnim) {
//			}
//			break;
//		}
//
//		return true;
//	}
//
//	/** ���� **/
//	void onTouchDown(MotionEvent event) {
//		mode = MODE.DRAG;
//
//		current_x = (int) event.getRawX();
//		current_y = (int) event.getRawY();
//
//		start_x = (int) event.getX();
//		start_y = current_y - this.getTop();
//
//	}
//
//	/** ������ָ ֻ�ܷŴ���С **/
//	void onPointerDown(MotionEvent event) {
//		if (event.getPointerCount() == 2) {
//			mode = MODE.ZOOM;
//			beforeLenght = getDistance(event);// ��ȡ����ľ���
//		}
//	}
//
//	/** �ƶ��Ĵ��� **/
//	void onTouchMove(MotionEvent event) {
//		int left = 0, top = 0, right = 0, bottom = 0;
//		/** �����϶� **/
//		if (mode == MODE.DRAG) {
//
//			/** ������Ҫ�����жϴ�����ֹ��dragʱ��Խ�� **/
//
//			/** ��ȡ��Ӧ��l��t,r ,b **/
//			left = current_x - start_x;
//			right = current_x + this.getWidth() - start_x;
//			top = current_y - start_y;
//			bottom = current_y - start_y + this.getHeight();
//
//			/** ˮƽ�����ж� **/
//			if (isControl_H) {
//				if (left >= 0) {
//					left = 0;
//					right = this.getWidth();
//				}
//				if (right <= screen_W) {
//					left = screen_W - this.getWidth();
//					right = screen_W;
//				}
//			} else {
//				left = this.getLeft();
//				right = this.getRight();
//			}
//			/** ��ֱ�ж� **/
//			if (isControl_V) {
//				if (top >= 0) {
//					top = 0;
//					bottom = this.getHeight();
//				}
//
//				if (bottom <= screen_H) {
//					top = screen_H - this.getHeight();
//					bottom = screen_H;
//				}
//			} else {
//				top = this.getTop();
//				bottom = this.getBottom();
//			}
//			if (isControl_H || isControl_V)
//				this.setPosition(left, top, right, bottom);
//
//			current_x = (int) event.getRawX();
//			current_y = (int) event.getRawY();
//
//		}
//		/** �������� **/
//		else if (mode == MODE.ZOOM) {
//
//			afterLenght = getDistance(event);// ��ȡ����ľ���
//
//			float gapLenght = afterLenght - beforeLenght;// �仯�ĳ���
//
//			if (Math.abs(gapLenght) > 5f) {
//				scale_temp = afterLenght / beforeLenght;// ������ŵı���
//
//				this.setScale(scale_temp);
//
//				beforeLenght = afterLenght;
//			}
//		}
//
//	}
//
//	/** ��ȡ����ľ��� **/
//	float getDistance(MotionEvent event) {
//		float x = event.getX(0) - event.getX(1);
//		float y = event.getY(0) - event.getY(1);
//
//		return FloatMath.sqrt(x * x + y * y);
//	}
//
//	/** ʵ�ִ����϶� **/
//	private void setPosition(int left, int top, int right, int bottom) {
//		this.layout(left, top, right, bottom);
//	}
//
//	/** �������� **/
//	void setScale(float scale) {
//		int disX = (int) (this.getWidth() * Math.abs(1 - scale)) / 4;// ��ȡ����ˮƽ����
//		int disY = (int) (this.getHeight() * Math.abs(1 - scale)) / 4;// ��ȡ���Ŵ�ֱ����
//
//		// �Ŵ�
//		if (scale > 1 && this.getWidth() <= MAX_W) {
//			current_Left = this.getLeft() - disX;
//			current_Top = this.getTop() - disY;
//			current_Right = this.getRight() + disX;
//			current_Bottom = this.getBottom() + disY;
//
//			this.setFrame(current_Left, current_Top, current_Right,
//					current_Bottom);
//			/***
//			 * ��ʱ��Ϊ���ǵ��Գƣ�����ֻ��һ���жϾͿ����ˡ�
//			 */
//			if (current_Top <= 0 && current_Bottom >= screen_H) {
//		//		Log.e("jj", "��Ļ�߶�=" + this.getHeight());
//				isControl_V = true;// ������ֱ���
//			} else {
//				isControl_V = false;
//			}
//			if (current_Left <= 0 && current_Right >= screen_W) {
//				isControl_H = true;// ����ˮƽ���
//			} else {
//				isControl_H = false;
//			}
//
//		}
//		// ��С
//		else if (scale < 1 && this.getWidth() >= MIN_W) {
//			current_Left = this.getLeft() + disX;
//			current_Top = this.getTop() + disY;
//			current_Right = this.getRight() - disX;
//			current_Bottom = this.getBottom() - disY;
//			/***
//			 * ������Ҫ�������Ŵ���
//			 */
//			// �ϱ�Խ��
//			if (isControl_V && current_Top > 0) {
//				current_Top = 0;
//				current_Bottom = this.getBottom() - 2 * disY;
//				if (current_Bottom < screen_H) {
//					current_Bottom = screen_H;
//					isControl_V = false;// �رմ�ֱ����
//				}
//			}
//			// �±�Խ��
//			if (isControl_V && current_Bottom < screen_H) {
//				current_Bottom = screen_H;
//				current_Top = this.getTop() + 2 * disY;
//				if (current_Top > 0) {
//					current_Top = 0;
//					isControl_V = false;// �رմ�ֱ����
//				}
//			}
//
//			// ���Խ��
//			if (isControl_H && current_Left >= 0) {
//				current_Left = 0;
//				current_Right = this.getRight() - 2 * disX;
//				if (current_Right <= screen_W) {
//					current_Right = screen_W;
//					isControl_H = false;// �ر�
//				}
//			}
//			// �ұ�Խ��
//			if (isControl_H && current_Right <= screen_W) {
//				current_Right = screen_W;
//				current_Left = this.getLeft() + 2 * disX;
//				if (current_Left >= 0) {
//					current_Left = 0;
//					isControl_H = false;// �ر�
//				}
//			}
//
//			if (isControl_H || isControl_V) {
//				this.setFrame(current_Left, current_Top, current_Right,
//						current_Bottom);
//			} else {
//				this.setFrame(current_Left, current_Top, current_Right,
//						current_Bottom);
//				isScaleAnim = false;// �������Ŷ���
//			}
//
//		}
//
//	}

}
