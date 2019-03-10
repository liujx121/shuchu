package shuzhi;

import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		int n;
		int maxsum = 0;
		int maxstart = 0;//用于判断子数组是否小于0
		Scanner in = new Scanner(System.in);
		System.out.println("输入数组的长度");
		n = in.nextInt();
		int num[]=new int[n];
		System.out.println("输入数组中的值");
		for(int i = 0;i < n;i++)
		{
			num[i] = in.nextInt();
		}
		maxsum = num[0];
		for(int i = 0;i < n;i++)
		{
			if (maxstart <= 0) {//忽略掉和为负数和0的子数组
				maxstart = num[i];
			}else {
				maxstart += num[i];
			}
			
			if (maxsum < maxstart) {
				maxsum = maxstart;
			}
		}
		System.out.println("最大值为：" + maxsum);
	}
}
