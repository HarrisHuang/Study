package com.te.rpc.serializable;

import java.io.IOException;

public interface Serialization {
	
	/**
	 * @Desc ���л� �ͷ�������
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	  byte[] serialize(Object obj) throws IOException;

	   <T> T deserialize(byte[] bytes, Class<T> clz) throws IOException;
}
