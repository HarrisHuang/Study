package com.te.rpc.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class Test {
    public static void main(String[] args) throws Exception{
    	/**
    	 * Kryo ��ܣ� ���л���ʱ�������һ���fileout������࣬����Ч�ʼ���
    	 */
//        Kryo kryo = new Kryo();
//        Output output = new Output(new FileOutputStream("D://Test//student.txt"));
//        Student kirito = new Student("kirito");
//        kryo.writeObject(output, kirito);
//        output.close();
//        Input input = new Input(new FileInputStream("D://Test//student.txt"));
//        Student kiritoBak = kryo.readObject(input, Student.class);
//        input.close();
//      System.out.println(   "kirito".equals(kiritoBak.getName()));
//        System.out.println(kiritoBak);
    	/**
    	 * �Ż���
    	 * RPC��ܶ��������з�ʽ�����е�
    	 */
    	
//      Kryo kryo = new Kryo();
//      Output output = new Output(new FileOutputStream("D://Test//student.txt"));
//      Student kirito = new Student("kirito");
//      kryo.writeClassAndObject(output, kirito);
//      output.close();
//      Input input = new Input(new FileInputStream("D://Test//student.txt"));
//
//      // ���ַ�������û��class�ֽ����ļ�������£�Ҳ�ܷ����л��ɹ�
//      Object object = kryo.readClassAndObject(input);
//      if (object instanceof Student) {
//    	Student ss =(Student) object;
//    	  System.out.println(   "kirito".equals(ss.getName()));
//          System.out.println(ss);
//      }
//      input.close();
        /**
         * ʹ��hessian �������һ�����л�
         */
    	 Student kirito = new Student("kirito");
    	Hessian2Serialization hessian2Serialization = new Hessian2Serialization();
    	byte[] serialize = hessian2Serialization.serialize(kirito);
    	System.out.println(serialize.length);
    	Student deserialize = hessian2Serialization.deserialize(serialize, Student.class);
    	System.out.println(deserialize);
    }
}
