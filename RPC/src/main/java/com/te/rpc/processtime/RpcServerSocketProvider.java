package com.te.rpc.processtime;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.te.rpc.serializable.Hessian2Serialization;
import com.te.rpc.serializable.Serialization;

/**
 * @Desc ����socket���д������
 * @author liuxy
 * Disadvantage
 *           ������ṩ�ߵ�ÿ�μ�����������һ���̣߳��ڲ�����С��ʱ�����ⲻ��
 *           ���ǵ��������ر�ߵ�ʱ�����߳̿϶������ã�ϵͳ�����ˡ�
 *           ����dubbo��springcloud�� RPC��ܣ���ʹ����Netty������ʵ�ִ���
 */
public class RpcServerSocketProvider {


    public static void main(String[] args) throws Exception {

        //���л���ʵ�ֲο�֮ǰ���½�
        Serialization serialization = new Hessian2Serialization();

        //����һ������˿�
        ServerSocket serverSocket = new ServerSocket(8088);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        while (true) {
        	//�����������ȴ������ߵ����ӣ�Ȼ�󴥷�34�д���
            final Socket socket = serverSocket.accept();
            executorService.execute(() -> {
                try {
                	//�����������ߵ�36�У�����������Ȼ���ṩ�߸��� ������35֮ǰ��  dos.flush()����;�ᴥ���������
                    InputStream is = socket.getInputStream();
                    OutputStream os = socket.getOutputStream();
                    try {
                        DataInputStream dis = new DataInputStream(is);
                        //����ʽ�Ļ�ȡ
                        int length = dis.readInt();
                        byte[] requestBody = new byte[length];
                        //Ҳ������ʽ�Ļ�ȡ
                        dis.read(requestBody);
                        //�����л�requestBody => RpcRequest
                        RpcRequest rpcRequest = serialization.deserialize(requestBody, RpcRequest.class);
                        //�������������Ӧ ����װ�� rpcResponse
                        RpcResponse rpcResponse = invoke(rpcRequest);
                        //���л�rpcResponse => responseBody
                        byte[] responseBody = serialization.serialize(rpcResponse);
                        DataOutputStream dos = new DataOutputStream(os);
                        dos.writeInt(responseBody.length);
                        dos.write(responseBody);
                        dos.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        is.close();
                        os.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public static RpcResponse invoke(RpcRequest rpcRequest) {
    	/**
    	 * ���������ʹ�õ���ͨ�� �ӿ����֣��������֣��Ͳ������з�����ã�Ȼ�󷵻�����
    	 */
        //ģ�ⷴ�����
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setRequestId(rpcRequest.getRequestId());
        rpcResponse.setValue("������ý���󷵻�");
        //... some operation
        return rpcResponse;
    }

}