package com.test;

import java.util.*;
public class Main{
	public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();//输出的组数
        Msg msg=new Msg(n);//初始化一些参数
        
        Thread t1=new Thread(new arrA(msg));
        t1.start();
        Thread t2=new Thread(new arrB(msg));
        t2.start();
        Thread t3=new Thread(new arrC(msg));
        t3.start();
        Thread t4=new Thread(new arrD(msg));
        t4.start();
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*System.out.println("t1:"+t1.isAlive());
        System.out.println("t2:"+t2.isAlive());
        System.out.println("t3:"+t3.isAlive());
        System.out.println("t4:"+t4.isAlive());*/
        msg.print();
        System.out.println(Thread.currentThread().getName());
    }
}



class Msg{
    
	public char[] arr;//输出的数组
	public int index=1;//指定那个对应的方法执行添加字符
	public int num;//用户输入的组数
	public int count=0;//数组下标
	
	//利用构造函数去初始化数组
	Msg(int num){
		this.num=num;
		arr=new char[4*num];//初始化数组的大小
	}
	
	public void print(){
		for(int i=0;i<4*num;i++){
			System.out.print(arr[i]);
		}
	}
	
	
	//添加A的方法：下面类似
	public synchronized void appendA(){//当某个对象的一个方法被同步后，该对象的其他方法不能再被别的线程访问，因为锁只有一把
		while(this.count<this.num*4){
			while(this.index!=1){
				if(this.count==this.num*4){//这个条件有可能满足吗？？？
					break;
				}
				try{ //执行到此处，释放对象锁，开始等待
					this.wait();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			//因为是while循环，
			if(this.count==this.num*4){
				System.out.println("A"+this.count);
				break;
			}
			//只有index的值符合了，才会执行下面的语句
			this.arr[count]='A';
			this.count++;//数组下标值加1
			this.index=2;
			this.notifyAll();//唤醒其他所有线程，此时并没有释放对象锁
		}
	}
	public synchronized void appendB(){
		while(this.count<this.num*4){
			while(this.index!=2){
				if(this.count==this.num*4){
					break;
				}
				try{ //执行到此处，释放对象锁
					this.wait();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			if(this.count==this.num*4){
				System.out.println("B"+this.count);
				break;
			}
			//只有index的值符合了，才会执行下面的语句
			this.arr[count]='B';
			this.count++;
			this.index=3;
			this.notifyAll();
		}
	}
	public synchronized void appendC(){
		while(this.count<this.num*4){
			while(this.index!=3){
				if(this.count==this.num*4){
					break;
				}
				try{ //执行到此处，释放对象锁
					this.wait();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			if(this.count==this.num*4){
				System.out.println("C"+this.count);
				break;
			}
			//只有index的值符合了，才会执行下面的语句
			this.arr[count]='C';
			this.count++;
			this.index=4;
			this.notifyAll();
		}
	}
	public synchronized void appendD(){
		while(this.count<this.num*4){
			while(this.index!=4){
				if(this.count==this.num*4){
					break;
				}
				try{ //执行到此处，释放对象锁
					this.wait();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			if(this.count==this.num*4){
				System.out.println("D"+this.count);
				break;
			}
			//只有index的值符合了，才会执行下面的语句
			this.arr[count]='D';
			this.count++;
			this.index=1;
			this.notifyAll();
		}
	}
	
	
}
class arrA implements Runnable{

	private Msg msg=null;
	public arrA(Msg msg){
		this.msg=msg;
	}
	@Override
	public void run() {
		this.msg.appendA();//调用添加A字符的方法
	}
}
class arrD implements Runnable{

	private Msg msg=null;
	public arrD(Msg msg){
		this.msg=msg;
	}
	@Override
	public void run() {
		this.msg.appendD();//调用添加A字符的方法
	}
}
class arrB implements Runnable{

	private Msg msg=null;
	public arrB(Msg msg){
		this.msg=msg;
	}
	@Override
	public void run() {
		this.msg.appendB();//调用添加A字符的方法
	}
}
class arrC implements Runnable{

	private Msg msg=null;
	public arrC(Msg msg){
		this.msg=msg;
	}
	@Override
	public void run() {
		this.msg.appendC();//调用添加A字符的方法
	}
}

