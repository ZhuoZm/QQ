package com.QQ.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.QQ.entity.Message;

public class SerializableUtil{
	public static byte[] serializableMessage(Message message)throws Exception{
		//�ֽ����������
		ByteArrayOutputStream byteArrayOutputStream=null;
		//���л���
		ObjectOutputStream objectOutputStream=null;
		try{
			byteArrayOutputStream =new ByteArrayOutputStream();
			//���ֽ����������Ϊ���Ŀ�ĵأ��������л���
			objectOutputStream=new ObjectOutputStream(byteArrayOutputStream);
			//��message�������л���������ֽ�������
			objectOutputStream.writeObject(message);
			//�õ��ֽ��������е�����
			return byteArrayOutputStream.toByteArray();
			
	}finally{
			if(byteArrayOutputStream !=null)
				byteArrayOutputStream.close();
		
		 if(objectOutputStream !=null)
			 objectOutputStream.close();
	}
	}
	 
   

 


	public static Message unSerializableUtil(byte[] cache)throws Exception{
		//�ֽ�����������
				ByteArrayInputStream byteArrayInputStream=null;
				//���л���
				ObjectInputStream objectInputStream=null;
				try{
					byteArrayInputStream =new ByteArrayInputStream(cache);
					
					objectInputStream=new ObjectInputStream(byteArrayInputStream);
					
					return(Message)objectInputStream.readObject();
					
			}finally{
					if(byteArrayInputStream !=null)
						byteArrayInputStream.close();
			
				 if(objectInputStream !=null)
					 objectInputStream.close();
			}
}
}
