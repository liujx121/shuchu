package zuidazishuzu;

import java.util.Scanner;

public class Zuidazishuzu {
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		System.out.println("�������ά������С��У�");
		int x;
		int y;
		x=in.nextInt();
		y=in.nextInt();
		int[][] a=new int[x][y];
		for(int i=0;i<x;i++)
		{
			for(int t=0;t<y;t++)
			{
				a[i][t]=in.nextInt();
			}
		}
		int maxstart=0;
		int maxsum=a[0][0];
		int line_first=0,line_second=0;
		int row_first=0,row_second=0;
		for(int row=0;row<y;row++)//��
		{
			for(int line=0;line<x;line++)//��
			{
				for(int line_1=line;line_1<x;line_1++)//��
				{
					for(int row_1=row;row_1<y;row_1++)//��
					{
						for(int line_2=line;line_2<=line_1;line_2++)//
						{
							for(int row_2=row;row_2<=row_1;row_2++)//��
							{
								maxstart=maxstart+a[line_2][row_2];
							}
							
						}
						if(maxsum<=maxstart)
						{
							maxsum=maxstart;
							line_first=line;
							row_first=row;
							line_second=line_1;
							row_second=row_1;
						}
						maxstart=0;
					}
				}
			}
		}
		System.out.println("����������Ϊ��"+maxsum);
		System.out.println("���������Ϊ��");
		for(int line=line_first;line<=line_second;line++)
		{
			for(int row=row_first;row<=row_second;row++)
			{
				System.out.print(a[line][row]+"\t");
			}
			System.out.println("");
		}
	}

}
