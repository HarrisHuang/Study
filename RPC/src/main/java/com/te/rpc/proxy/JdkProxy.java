package com.te.rpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * jdk�������ʵ��
 * @author liuxy
 *
 */
public class JdkProxy {
	//����һ��jdk��̬����
	/**
	 *  ����һ����̬�����࣬ �����Լ�������ʵ�֣���һ���������������ؾ����ʵ��
	 * @param delegate
	 * @return
	 */
		static BookApi createJdkDynamicProxy(final BookApi delegate) {
	        BookApi jdkProxy = (BookApi) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
	                new Class[]{BookApi.class}, new JdkHandler(delegate));
	        return jdkProxy;
	}
		/**
		 * ��������˾���ķ�����ô���������invoice���� ����Ĳ��� delegate ��������õķ�����ϵ������Te
		 * @author liuxy
		 *
		 */
		
		//jdk��̬�����࣬�ǻ��ڽӿ�ʵ�ֵ�
		private static class JdkHandler implements InvocationHandler {

	        final Object delegate;

	        JdkHandler(Object delegate) {
	            this.delegate = delegate;
	        }

	        public Object invoke(Object object, Method method, Object[] objects)
	                throws Throwable {
	        	//ֻ�е����˷���ֻ�вŻ���44�е��ж�
	            //��Ӵ����߼�<1>
	            if(method.getName().equals("Te")){
	                System.out.println("�������ʵ��");
	                /**
	                                  *      ͨ��������������ã�����ķ���
	                                  *      �ӿڵ���ʵ��
	                 */
	               // Method metho = delegate.getClass().getMethod(method.getName(), method.getParameterTypes());
	                //delegate����ʵ����ĵ�ַcom.te.rpc.proxy.BookImpl@3fb6a447
	                //method ��public abstract java.lang.String com.te.rpc.proxy.BookApi.Te(java.lang.String)
	                 
	                return  method.invoke(delegate,objects);
	            }
	            return null;
//	            return method.invoke(delegate, objects);
	        }
		}
}
