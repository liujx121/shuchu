package shuzhi;

import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		int n;
		int maxsum = 0;
		int maxstart = 0;//�����ж��������Ƿ�С��0
		Scanner in = new Scanner(System.in);
		System.out.println("��������ĳ���");
		n = in.nextInt();
		int num[]=new int[n];
		System.out.println("���������е�ֵ");
		for(int i = 0;i < n;i++)
		{
			num[i] = in.nextInt();
		}
		maxsum = num[0];
		for(int i = 0;i < n;i++)
		{
			if (maxstart <= 0) {//���Ե���Ϊ������0��������
				maxstart = num[i];
			}else {
				maxstart += num[i];
			}
			
			if (maxsum < maxstart) {
				maxsum = maxstart;
			}
		}
		System.out.println("���ֵΪ��" + maxsum);
	}
}
