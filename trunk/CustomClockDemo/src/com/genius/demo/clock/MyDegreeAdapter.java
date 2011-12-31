package com.genius.demo.clock;

import android.graphics.Point;



//		本类的参考坐标系是X轴向右递增，Y轴向上递增，四个象限依次是右上，右下，左下，左上
//		所谓点的偏移角度即点与原点的连线与Y正半轴形成的一个顺时针角度
public class MyDegreeAdapter {

	private final static float PI = (float) 3.1415926;
	
	enum _Quadrant{
		eQ_NONE,									//  无象限状态（在坐标轴上）
		eQ_ONE,										//  第一象限
		eQ_TWO,										//	第二象限
		eQ_THREE,									//	第三象限
		eQ_FOUR										//	第四象限
	}
	
	public static _Quadrant GetQuadrant(Point point){		// 根据点获得象限
			if (point.x == 0 || point.y == 0)
			{
				return _Quadrant.eQ_NONE;
			}
			
			if (point.x > 0)
			{
				if (point.y > 0)
				{
					return _Quadrant.eQ_ONE;
				}
				else
				{
					return _Quadrant.eQ_TWO;
				}

			}
			else
			{
				if (point.y < 0)
				{
					return _Quadrant.eQ_THREE;
				}
				else
				{
					return _Quadrant.eQ_FOUR;
				}
			}
	}
	
	public static int GetRadianByPos(Point point){			// 根据点获得偏移角度		
		double dAngle = GetRadianByPosEx(point);
		
		return (int) (dAngle * (360 / (2 * PI)));
	}
	
	private static double GetRadianByPosEx(Point point){	// 根据点获得偏移弧度
		
		if (point.x == 0 && point.y == 0)
		{
			return 0;
		}


		double Sin = point.x / Math.sqrt(point.x * point.x + point.y * point.y);
		double dAngle = Math.asin(Sin);

		switch(GetQuadrant(point))
		{
		case eQ_NONE:
			{
				if (point.x == 0 && point.y == 0)
				{
					return 0;
				}

				if (point.x == 0)
				{
					if (point.y > 0)
					{
						return 0;
					}
					else
					{
						return PI;
					}
				}
				
				if (point.y == 0)
				{
					if (point.x > 0)
					{
						return PI/2;
					}
					else
					{
						return (1.5*PI);
					}
				}
			}
			break;
		case eQ_ONE:
			{
				return dAngle;
			}
		case eQ_TWO:
			{
				dAngle = PI - dAngle;
			}
			break;
		case eQ_THREE:
			{
				dAngle = PI - dAngle;
			}
			break;
		case eQ_FOUR:
			{
				dAngle += 2*PI;
			}
			break;
		}

		return dAngle;
		
	}
}
