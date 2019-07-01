package com.te.rpc.serializable;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.Data;
/**
 * ��һ�� Serializable �ؼ���
 * ����������������
 * 1.ʲô��java���л�����
 * 2.Ϊʲô����Serializable �ؼ��־Ϳ���ʵ�����л���
 * 3.Ϊʲô�������˴˹ؼ���transient ���޷����л���
 * 4.Ϊʲô����static�ؼ���Ҳ���������л�����
 * 
 * @author liuxy
  *      һһ���
 * java���л����ƾ��ǵ�����Ҫ�����ݽ��д����ʱ����Ҫ������ת���� �ַ���(String Ĭ��ʵ����serializable�ؼ���) ��
 * �ֽ����
  * ��Ϊֻ���������ֽ����飬�����������Ͻ��д��䡣�����л����ǽ��ļ����� byte��Ϣ����������
   2.��java Output inPut�ȹؼ���.�ڽ������л���ʱ�������ж������� ���� stirng��byte��Serializable �����͵�
   ���ݶ����ǲ��������д��䣬����ͱ���
   ������ΪSerializable ����һ����ǣ���������ؼ���˵�������ǿ��Ա����л��Ķ����ˡ�
   3.transient �ؼ����ڽ������л���ʱ�򣬿������л��ɹ������Ƿ����л���ʱ����޷���ȡ��������
   4.static���������л���ֱ�����ε����л��Ĺ��̡�
 */
@Data
class Student implements Serializable{  
	   /**
	 * 
	 */
	//ΪʲôҪ��һ�����л��汾ID�ء�
	/**
	 * ��Ϊ���л��е�ʱ����Ҫͨ�����紫��ģ���һ���ı��۸ķ��ա����˰汾id��ÿ�η����л�֮��
	 * ���Աȶ�һ�°汾�������Ƿ��޸���
	 */
	private static final long serialVersionUID =9856L;
	private String name;  
	
	public Student (String name) {
		this.name =name;
	}
	public Student () {}
	
	} 
