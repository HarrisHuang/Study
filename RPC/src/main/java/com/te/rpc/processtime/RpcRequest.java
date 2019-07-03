package com.te.rpc.processtime;

import java.io.Serializable;
import java.util.Map;

import lombok.Data;
/**
 * @Desc RPC ���������
 * @author liuxy
 *
 */

@Data
public class RpcRequest implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @Desc �ӿ�����
	 */
	private String interfaceName; 
	
	/**
	 * @Desc �ӿ��еķ�������
	 */
    private String methodName;
    /**
     * 
     * @Desc����˵��
     */
    private String parametersDesc;
    /**
     * @Desc �������
     */
    private Object[] arguments;
    /**
     * @Desc ��������
     */
    private Map<String, String> attachments;
    /**
     * @Desc ���Դ���
     */
    private int retries = 0;
    
    /**
     * @Desc �˴�����id
     */
    private long requestId;
    
    /**
     * @Desc RPC Э��汾
     */
    private byte rpcProtocolVersion;
}