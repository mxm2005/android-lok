package sf.hmg.turntest.widget;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.Vector;

import sf.hmg.turntest.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.TextView;

public class PageView extends TextView implements OnGestureListener {

	private Paint mTextPaint;
	private String mTextCode = "";
	
	private int mScreenWidth = 0;
	private int mScreenHeight = 0;
	private int mFontHeight = 0;
	private int mPageLineNum = 0;
//	public  static int mTotalTextHeight = 0;
	private File book_file = null;
	private int m_mbBufLen = 0;//书的长度
	private int m_mbBufBegin = 0;
	private int m_mbBufEnd = 0;
	private String m_strCharsetName = "GBK";
	private MappedByteBuffer m_mbBuf = null;//书的byte流
	public Vector<String> m_lines = new Vector<String>();//每页的所有行的string
	private boolean m_isfirstPage, m_islastPage;
	
	public PageView(Context context) {
		super(context);
		initTxtViewer(context);
	}
	
	public PageView(Context context,  AttributeSet attrs) {
		super(context,attrs);
		initTxtViewer(context);
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        CharSequence s = typeArray.getString(R.styleable.MyView_Text);
        if(s != null) {
        	setText(s.toString());
        }
        
        setTextColor(typeArray.getColor(R.styleable.MyView_Color, Color.BLACK));
        int textSize = typeArray.getDimensionPixelOffset(R.styleable.MyView_TextSize, 0);
        if(textSize > 0) {
        	setTextSize(textSize);
        }
        typeArray.recycle();
        
	}
	
	public void openbook(String strFilePath) throws IOException {
		this.book_file = new File(strFilePath);
		long lLen = book_file.length();
		this.m_mbBufLen = (int) lLen;
		this.m_mbBuf = new RandomAccessFile(book_file, "r").getChannel().map(
				FileChannel.MapMode.READ_ONLY, 0, lLen);
	}
	
	private final void initTxtViewer(Context context) {
		System.out.println("Text Code = " + mTextCode);
    	this.mTextPaint = new Paint();
    	this.mTextPaint.setAntiAlias(true);
    	this.mTextPaint.setTextSize(12);
    	this.mTextPaint.setColor(Color.BLACK);
    	this.mTextPaint.setStrokeWidth(12);
    	WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
    	this.mScreenWidth = wm.getDefaultDisplay().getWidth();
    	this.mScreenHeight = wm.getDefaultDisplay().getHeight();
		FontMetrics fm = mTextPaint.getFontMetrics();
		this.mFontHeight = (int) Math.ceil(fm.bottom - fm.top) + 4;
		this.mPageLineNum = mScreenHeight / mFontHeight;
    }
	
	
	@Override
	public void onDraw(Canvas canvas) {
		Log.i("", "onDraw........................................................................");

		canvas.drawColor(0xffff9e85);
		mTextPaint.setAntiAlias(true);
		FontMetrics fm = mTextPaint.getFontMetrics();
		mFontHeight = (int) Math.ceil(fm.bottom - fm.top) + 4;
		mPageLineNum = mScreenHeight / mFontHeight;

		int x = 0;
		
		if (m_lines.size() == 0) {
			Log.i("", "m_lines..is 0");
			m_lines = pageDown();
			Log.i("", "pageDown() " + pageDown().size());
		}
		if (m_lines.size() > 0) {
			Log.i("", "m_lines..is >>>>>>>>>>>>> 0" + mFontHeight);
			int y = mFontHeight;
			for (String strLine : m_lines) {
				System.out.println("strLine   " + strLine);
				y += (mFontHeight);
				canvas.drawText(strLine, x, y, mTextPaint);
			}
			float fPercent = (float) (m_mbBufBegin * 1.0 / m_mbBufLen);
			DecimalFormat df = new DecimalFormat("#0.0");
			String strPercent = df.format(fPercent * 100) + "%";
			int nPercentWidth = (int) mTextPaint.measureText("999.9%") + 1;//999.9%字符串的宽度
			canvas.drawText(strPercent, mScreenWidth - nPercentWidth, mScreenHeight - 5, mTextPaint);
		}
		super.onDraw(canvas);
	}

	
 	public void setTextSize(int size) {
		mTextPaint.setTextSize(size);
		requestLayout();
		invalidate();
	}

 	@Override
	public void setTextColor(int color) {
		mTextPaint.setColor(color);
		invalidate();
	}
	
	public void setTextCode(String textCode) {
		mTextCode = textCode;
		requestLayout();
		invalidate();
	}
	
	
	
	@Override
	public boolean onDown(MotionEvent arg0) {
		if(arg0.getX() < (mScreenWidth / 2))
		{
			Log.i("", "上一页。。。。。。。。。。。。。。。。。。。。。");
			try
			{
				prePage();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			requestLayout();
			invalidate();
			if(isfirstPage())
				return false;
		}
		else
		{
			Log.i("", "下一页。。。。。。。。。。。。。。。。。。。。。");
			try
			{
				nextPage();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			requestLayout();
			invalidate();
			if(islastPage())
				return false;
		}
		return false;
	}
	
	protected byte[] readParagraphBack(int nFromPos) {
		int nEnd = nFromPos;
		int i;
		byte b0, b1;
		if (m_strCharsetName.equals("UTF-16LE")) {
			i = nEnd - 2;
			while (i > 0) {
				b0 = m_mbBuf.get(i);
				b1 = m_mbBuf.get(i + 1);
				if (b0 == 0x0a && b1 == 0x00 && i != nEnd - 2) {
					i += 2;
					break;
				}
				i--;
			}

		} else if (m_strCharsetName.equals("UTF-16BE")) {
			i = nEnd - 2;
			while (i > 0) {
				b0 = m_mbBuf.get(i);
				b1 = m_mbBuf.get(i + 1);
				if (b0 == 0x00 && b1 == 0x0a && i != nEnd - 2) {
					i += 2;
					break;
				}
				i--;
			}
		} else {
			i = nEnd - 1;
			while (i > 0) {
				b0 = m_mbBuf.get(i);
				if (b0 == 0x0a && i != nEnd - 1) {//0x0a==\r
					i++;
					break;
				}
				i--;
			}
		}
		if (i < 0)
			i = 0;
		int nParaSize = nEnd - i;
		int j;
		byte[] buf = new byte[nParaSize];
		for (j = 0; j < nParaSize; j++) {
			buf[j] = m_mbBuf.get(i + j);
		}
		return buf;
	}


	// 读取上一段落
	protected byte[] readParagraphForward(int nFromPos) {
		int nStart = nFromPos;
		int i = nStart;
		byte b0, b1;
		// 根据编码格式判断换行
		if (m_strCharsetName.equals("UTF-16LE")) {
			while (i < m_mbBufLen - 1) {
				b0 = m_mbBuf.get(i++);
				b1 = m_mbBuf.get(i++);
				if (b0 == 0x0a && b1 == 0x00) {
					break;
				}
			}
		} else if (m_strCharsetName.equals("UTF-16BE")) {
			while (i < m_mbBufLen - 1) {
				b0 = m_mbBuf.get(i++);
				b1 = m_mbBuf.get(i++);
				if (b0 == 0x00 && b1 == 0x0a) {
					break;
				}
			}
		} else {
			while (i < m_mbBufLen) {
				b0 = m_mbBuf.get(i++);
				if (b0 == 0x0a) {
					break;
				}
			}
		}
		int nParaSize = i - nStart;
		byte[] buf = new byte[nParaSize];
		for (i = 0; i < nParaSize; i++) {
			buf[i] = m_mbBuf.get(nFromPos + i);
		}
		return buf;
	}
	
	
	protected Vector<String> pageDown() {
		String strParagraph = "";
		Vector<String> lines = new Vector<String>();
		while (lines.size() < mPageLineNum && m_mbBufEnd < m_mbBufLen) {
			byte[] paraBuf = readParagraphForward(m_mbBufEnd); // 读取一个段落
			m_mbBufEnd += paraBuf.length;
			try {
				strParagraph = new String(paraBuf, m_strCharsetName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String strReturn = "";
			if (strParagraph.indexOf("\r\n") != -1) {
				strReturn = "\r\n";
				strParagraph = strParagraph.replaceAll("\r\n", "");
			} else if (strParagraph.indexOf("\n") != -1) {
				strReturn = "\n";
				strParagraph = strParagraph.replaceAll("\n", "");
			}

			if (strParagraph.length() == 0) {
				lines.add(strParagraph);
			}
			while (strParagraph.length() > 0) {
				int nSize = mTextPaint.breakText(strParagraph, true, mScreenWidth, null);
				lines.add(strParagraph.substring(0, nSize));
				strParagraph = strParagraph.substring(nSize);
				if (lines.size() >= mPageLineNum) {
					break;
				}
			}
			if (strParagraph.length() != 0) {
				try {
					m_mbBufEnd -= (strParagraph + strReturn).getBytes(m_strCharsetName).length;
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return lines;
	}

	protected void pageUp() {
		if (m_mbBufBegin < 0)
			m_mbBufBegin = 0;
		Vector<String> lines = new Vector<String>();
		String strParagraph = "";
		while (lines.size() < mPageLineNum && m_mbBufBegin > 0) {
			Vector<String> paraLines = new Vector<String>();
			byte[] paraBuf = readParagraphBack(m_mbBufBegin);
			m_mbBufBegin -= paraBuf.length;
			try {
				strParagraph = new String(paraBuf, m_strCharsetName);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			strParagraph = strParagraph.replaceAll("\r\n", "");
			strParagraph = strParagraph.replaceAll("\n", "");

			if (strParagraph.length() == 0) {
				paraLines.add(strParagraph);
			}
			while (strParagraph.length() > 0) {
				int nSize = mTextPaint.breakText(strParagraph, true, mScreenWidth,
						null);
				paraLines.add(strParagraph.substring(0, nSize));
				strParagraph = strParagraph.substring(nSize);
			}
			lines.addAll(0, paraLines);
		}
		while (lines.size() > mPageLineNum) {
			try {
				m_mbBufBegin += lines.get(0).getBytes(m_strCharsetName).length;
				lines.remove(0);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		m_mbBufEnd = m_mbBufBegin;
		return;
	}
	
	protected void prePage() throws IOException {
		if (m_mbBufBegin <= 0) {
			m_mbBufBegin = 0;
			m_isfirstPage = true;
			return;
		} else {
			m_isfirstPage = false;
		}
		m_lines.clear();
		pageUp();
		m_lines = pageDown();
	}

	public void nextPage() throws IOException {
		if (m_mbBufEnd >= m_mbBufLen) {
			m_islastPage = true;
			return;
		} else
			m_islastPage = false;
		m_lines.clear();
		m_mbBufBegin = m_mbBufEnd;
		m_lines = pageDown();
	}
	
	public boolean isfirstPage() {
		return m_isfirstPage;
	}
	public boolean islastPage() {
		return m_islastPage;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		return false;
	}

}
