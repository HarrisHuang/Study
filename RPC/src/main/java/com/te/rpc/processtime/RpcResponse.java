package com.te.rpc.processtime;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;

/**
 * @Desc ��Ӧ
 * @author liuxy
 *
 */
@Data
public class RpcResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @Desc  ��Ӧ����
	 */
    private Object value;
    /**
     * @Desc ��Ӧ�쳣
     */
    private Exception exception;
    
    /**
     * @Desc ����id,���������id��ͬ��
     */
    private long requestId;
    /**
     * @Desc ��Ӧʱ��
     */
    private long processTime;
    /**
     * @Desc  ��ʱʱ��
     */
    private int timeout;
    /**
     * @Desc ��������
     * // rpcЭ��汾����ʱ���Իش�һЩ�������Ϣ
     */
    private Map<String, String> attachments;
    /**
     * @Desc RPCЭ��汾
     */
    private byte rpcProtocolVersion;
}
