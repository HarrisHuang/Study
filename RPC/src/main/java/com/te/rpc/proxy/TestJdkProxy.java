package com.te.rpc.proxy;

public class TestJdkProxy {

	
	public static void main(String[] args) throws Exception {
		//newһ��ʵ��������Թ�����
		BookApi bookApi =new BookImpl();
//		BookApi JdkDynamicProxy = JdkProxy.createJdkDynamicProxy(bookApi );
//		System.out.println(JdkDynamicProxy.Te("JDK�������"));
		/**
		 * cglib���÷�ʽ,����һ��һ�������������Լ���api��װ
		 */
		BookApi CglibDynamicProxy = CglibProxy.createCglibDynamicProxy(bookApi);
		System.out.println(CglibDynamicProxy.Te("Cglib�������"));
	}
}
