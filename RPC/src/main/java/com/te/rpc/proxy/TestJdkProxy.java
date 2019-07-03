package com.te.rpc.proxy;

public class TestJdkProxy {

	/**
	 * ������̬��������ʵ�ֶ��ǻ��ڷ���ġ���19��or  14�д������е�ʱ��ͻ���intercept ����invoke
	 * Ȼ���Խӿڵ���ʵ���࣬��������ķ�ʽ�����е���
	 * @param args
	 * @throws Exception
	 */
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
