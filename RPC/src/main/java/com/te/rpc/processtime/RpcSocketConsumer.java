package com.te.rpc.processtime;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.te.rpc.serializable.Hessian2Serialization;
import com.te.rpc.serializable.Serialization;

public class RpcSocketConsumer {

    public static void main(String[] args) throws Exception {

        //���л���ʵ�ֲο�֮ǰ���½�
        Serialization serialization = new Hessian2Serialization();

        //����һ�����Ӷ˿ں��ṩ�˽���ͨ��
        Socket socket = new Socket("localhost", 8088);
        // �õ���Ӧ������Ϣ
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        //��װrpc����
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setRequestId(12345L);
        //���л� rpcRequest => requestBody
        //��ΪRPC����ͨ��������д��䣬ֻ֧��ʶ�� ����������
        byte[] requestBody = serialization.serialize(rpcRequest);
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeInt(requestBody.length);
        dos.write(requestBody);
        dos.flush();
        DataInputStream dis = new DataInputStream(is);
        //���������ȴ����ṩ�ߵ����ݷ���
        int length = dis.readInt();
        byte[] responseBody = new byte[length];
        dis.read(responseBody);
        //�����л� responseBody => rpcResponse
        RpcResponse rpcResponse = serialization.deserialize(responseBody, RpcResponse.class);
        is.close();
        os.close();
        socket.close();

        System.out.println(rpcResponse);
    }
}