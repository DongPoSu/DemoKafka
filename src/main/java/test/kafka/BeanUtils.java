package test.kafka;

import java.io.*;

/**
* @ClassName:BeanUtils.java
* @Description:消息序列化byte转换
* @author:  zenglinhua
* @date:    2015-10-23
 */
public class BeanUtils {
	public static Object bytes2Object(byte[] bytes) {
		Object obj = null;
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			obj = (Object) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return obj;

	}

	public static byte[] object2Bytes(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			bytes = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}
}
