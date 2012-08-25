package com.test;

import java.math.BigInteger;
import java.util.Random;

/**
 * RSA算法简单实现
 * 
 * @author windy
 */
public class RSA {
	/** 生成素数的长度 按照bit */
	public static int bit = 16; // 最好是30以下
	/** 几个char为一组 */
	public static int s = 3;

	// 大数参数
	BigInteger p;
	BigInteger q;
	BigInteger n;
	BigInteger xn;
	BigInteger e;
	BigInteger d;

	/**
	 * 初始化所有参数
	 */
	public RSA() {
		Random r = new Random();
		p = BigInteger.probablePrime(bit, r);
		q = BigInteger.probablePrime(bit, r);
		n = p.multiply(q);
		xn = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		e = this.getE();

		d = this.getMod();
	}

	/** 获取E参数 */
	public BigInteger getE() {

		BigInteger bi = new BigInteger("123");
		int k = new Random().nextInt(655) + 200;
		int count = 1;
		for (long i = 3; i < xn.longValue(); i++) {
			k = Math.abs(k);
			bi = new BigInteger("" + i);
			if (bi.gcd(xn).equals(BigInteger.ONE)) {
				if (count > k) {
					return bi;
				} else {
					count++;
				}
			}
		}
		return bi;
	}

	public void print(char[] c) {
		System.out.println();
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i] + ", ");
		}
	}

	public void print(byte[] c) {
		System.out.println();
		for (int i = 0; i < c.length; i++) {
			System.out.print(c[i] + ", ");
		}
	}

	/**
	 * 获取加密数据
	 * 
	 * @param meg
	 * @return String
	 */
	public String getEncryptMeg(String meg) {

		BigInteger[] bi = this.diliverMeg(meg);
		BigInteger[] re = new BigInteger[bi.length];
		for (int i = 0; i < bi.length; i++) {
			re[i] = this.getWordsEncrypt(bi[i]);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < re.length; i++) {
			sb.append(re[i]);
		}
		return sb.toString();
	}

	/**
	 * 传入连接分组后的BI数组,进行加密和解密 (演示用..)
	 * 
	 * @param bi
	 * @return String
	 */
	public String getBigIntegerArrayUnencrypt(BigInteger[] bi) {
		BigInteger[] re = new BigInteger[bi.length];
		BigInteger[] ree = new BigInteger[bi.length];
		for (int i = 0; i < bi.length; i++) {
			re[i] = this.getWordsEncrypt(bi[i]);
			System.out.println("原始:" + bi[i]);
			System.out.println("加密:" + re[i]);
		}

		for (int i = 0; i < re.length; i++) {
			ree[i] = this.getWordsUnencrypt(re[i]);
			System.out.println("解密:" + ree[i]);
		}
		StringBuilder sb = new StringBuilder();
		String s = this.combainateMeg(ree);
		return s;
	}

	/**
	 * 将字符串按照ASCII码分组连接处理 返回一个biginteger数组
	 * 
	 * @param meg
	 * @return {@link BigInteger}[]
	 */
	public BigInteger[] diliverMeg(String meg) {
		int len = meg.length() / s;
		if (meg.length() % s != 0) {
			for (int i = meg.length() % s; i < s; i++) {
				meg += ' ';
			}
			len++;
		}
		BigInteger[] bi = new BigInteger[len];
		for (int i = 0; i < bi.length; i++) {
			String c = "";
			for (int j = 0; j < s; j++) {
				char a = meg.charAt(i * s + j);
				System.out.print((int) a + ",");
				int b = Math.abs((int) a);
				if (b < 100) {
					c += "0" + String.valueOf(b);
				} // 不足百的前补零
				else {
					c += String.valueOf(b);
				} // 产生了一个字符串

			}
			bi[i] = new BigInteger(c);
		}
		System.out.println();
		return bi;
	}

	/**
	 * 把大数数组按照编码还原为内容
	 * 
	 * @param bi
	 * @return String
	 */
	public String combainateMeg(BigInteger[] bi) {
		char[] re = new char[bi.length * s]; // 解密字符串数组的容器
		int index = 0;
		for (int i = 0; i < bi.length; i++) {
			String a = bi[i].toString();
			if (a.length() % 3 == 1) {
				// System.out.println("%3==1");
				a = "00" + a;
			} else if (a.length() % 3 == 2) {
				// System.out.println("%3==2");
				a = "0" + a;
			}
			for (int j = 0; j < s; j++) {
				String b = a.substring(j * 3, (j + 1) * 3);
				int c = Integer.parseInt(b);
				System.out.print(c + ",");
				re[index] = (char) c;
				index++;
			}

		}
		System.out.println();
		return String.copyValueOf(re);
	}

	public BigInteger getWordsEncrypt(BigInteger bi) {
		return bi.modPow(e, n);
	}

	public BigInteger getWordsUnencrypt(BigInteger bi) {
		return bi.modPow(d, n);
	}

	/**
	 * 计算D的 需要一个好点的算法
	 * 
	 * @return {@link BigInteger}
	 */
	public BigInteger getMod() {
		long k = 1;
		BigInteger re;
		BigInteger temp;
		for (long i = 1; i < 200000; i++) { // 基本适合32bit的素数了
			re = xn.multiply(BigInteger.valueOf(i));
			// System.out.println(re);
			re = re.add(BigInteger.ONE);
			temp = re.mod(this.e);
			if (temp.equals(BigInteger.ZERO)) {

				return re.divide(e);
			}
		}
		return null; // 时间太长 退出
	}

	/**
	 * 产生一个bit长度的素数
	 * 
	 * @return prime
	 */
	public BigInteger getPrime() {
		Random random = new Random();
		BigInteger bi = BigInteger.probablePrime(RSA.bit, random);
		System.out.println(bi);
		return bi;
	}

	public static void main(String[] args) {
		RSA rsa = new RSA();
		System.out.println("q = " + rsa.q);
		System.out.println("p = " + rsa.p);
		System.out.println("n = " + rsa.n);
		System.out.println("xn =" + rsa.xn);
		System.out.println("e = " + rsa.e);
		System.out.println("d = " + rsa.d);
		String meg = "Test RSA encrypt method. 900";
		System.out.println("原始信息: " + meg);
		BigInteger[] re = rsa.diliverMeg(meg);
		String s = rsa.getBigIntegerArrayUnencrypt(re);
		System.out.println("还原的信息: " + s);
		// System.out.println(rsa.getEncryptMeg("Test RSA encrypt method. 900"));
	}
}