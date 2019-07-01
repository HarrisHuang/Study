package com.te.rpc;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ��ΪRPC���ԭ�����
 *  ��ΪRPC��
 *  1.�����ľ��ǲ��ڱ��ؽ��е����ˣ�����ֱ�Ӵ�controller��ֱ�ӵ���service��
 *  ����ͨ�����������е��ã���ֻ�õ�����Ϳ�����,�������˴����֮�������
 * ���԰����쾸��
 * 
 * RPC�����Ϊ���зֲ�ʽ��ܻ���������Ҫ��������
 * ����debugһ��һ����
 * @author liuxy
 *
 */

public class RpcFramework {

    /**
     * ��¶����
     *
     * @param service ����ʵ��
     * @param port ����˿�
     * @throws Exception
     */
    public static void export(final Object service, int port) throws Exception {
        if (service == null)
            throw new IllegalArgumentException("service instance == null");
        if (port <= 0 || port > 65535)
            throw new IllegalArgumentException("Invalid port " + port);
        System.out.println("Export service " + service.getClass().getName() + " on port " + port);
        ServerSocket server = new ServerSocket(port);
        for(;;) {
            try {
            	//�ȴ��ͻ������ӣ������൱���ڵȴ������߷�����Ϣ��û�������߾ͽ������������������˾�newһ���߳�
                final Socket socket = server.accept();
                //����һ���ڲ��߳�
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            try {
                            	//�õ�����������Ķ�����������һ��Socket[addr=/127.0.0.1,port=52453,localport=1234]
                                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                                try {
                                	//�õ��������֣�������һ���ӿ����֡�����ӿ����־���consumer������õĽӿ�����
                                    String methodName = input.readUTF();
                                    Class<?>[] parameterTypes = (Class<?>[])input.readObject();
                                    //�õ�����hello���������������
                                    Object[] arguments = (Object[])input.readObject();
                                    //����һ��socket�����
                                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                                    try {
                                    	//ͨ�����似���õ�hello������ʵ����
                                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                                        //ͨ���õ���ʵ���࣬Ȼ�����service(com.te.rpc.HelloServiceImpl@4e81cf3)Ҳ���Ǿ����ʵ�ַ���
                                        //arguments(arguments)���ǲ��� World0
                                        Object result = method.invoke(service, arguments);
                                      //�����������
                                        output.writeObject(result);
                                    } catch (Throwable t) {
                                    	//
                                        output.writeObject(t);
                                    } finally {
                                        output.close();
                                    }
                                } finally {
                                    input.close();
                                }
                            } finally {
                                socket.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * ���÷���
     *
     * @param <T> �ӿڷ���
     * @param interfaceClass �ӿ�����
     * @param host ������������
     * @param port �������˿�
     * @return Զ�̷���
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) throws Exception {
        if (interfaceClass == null)
            throw new IllegalArgumentException("Interface class == null");
        if (! interfaceClass.isInterface())
            throw new IllegalArgumentException("The " + interfaceClass.getName() + " must be interface class!");
        if (host == null || host.length() == 0)
            throw new IllegalArgumentException("Host == null!");
        if (port <= 0 || port > 65535)
            throw new IllegalArgumentException("Invalid port " + port);
        //���͵�ͨ���ӿڵ�class���õ�����Ľӿ���Ϣ
        System.out.println("Get remote service " + interfaceClass.getName() + " from server " + host + ":" + port);
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] {interfaceClass}, new InvocationHandler() {
         //ͨ��interfaceClass�ӿڵ���Ϣ����һ�������࣬Ȼ��ѽӿ����֣��Ͳ���������export ����ʵ����
        	//����ʵ����õ��ӿ�����ͨ������õ��˽ӿڵ�ʵ���࣬Ȼ���������������ͨ�� input.readObject();
        	//�õ����÷����ķ��ؽ��
        	public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
               //����socket����,��ʱ���Ѿ����ṩ������������ 
            	Socket socket = new Socket(host, port);
                try {
                	//�ȵõ�һ�������
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    try {
                    	//��������Ķ�������49�п�ʼ�õ�
                    	//�ѽӿ��е��������
                        output.writeUTF(method.getName());
                        //����������
                        output.writeObject(method.getParameterTypes());
                        //�Ѳ������
                        output.writeObject(arguments);
                        //�õ���64������Ľ��
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            Object result = input.readObject();
                            if (result instanceof Throwable) {
                                throw (Throwable) result;
                            }
                            return result;
                        } finally {
                            input.close();
                        }
                    } finally {
                        output.close();
                    }
                } finally {
                    socket.close();
                }
            }
        });
    }
}
