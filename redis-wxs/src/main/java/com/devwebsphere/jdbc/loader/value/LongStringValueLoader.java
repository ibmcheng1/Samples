package com.devwebsphere.jdbc.loader.value;

//
//This sample program is provided AS IS and may be used, executed, copied and
//modified without royalty payment by customer (a) for its own instruction and
//study, (b) in order to develop applications designed to run with an IBM
//WebSphere product, either for customer's own internal use or for redistribution
//by customer, as part of such an application, in customer's own products. "
//
//5724-J34 (C) COPYRIGHT International Business Machines Corp. 2009
//All Rights Reserved * Licensed Materials - Property of IBM
//


import com.devwebsphere.jdbc.loader.BaseKeyValue;
import com.devwebsphere.jdbc.loader.JDBCValueLoader;
import com.ibm.websphere.objectgrid.plugins.LogElement;


public class LongStringValueLoader extends JDBCValueLoader <Long,String>
{
	static public class KV extends BaseKeyValue<Long, String>
	{
		public Long keyz;
		public String value;
		public KV() {};
		public KV(LogElement elem)
		{
			keyz = (Long)elem.getCacheEntry().getKey();
			if(elem.getCurrentValue() != null)
				value = (String)elem.getCurrentValue();
		}
		@Override
		public Long baseGetKey() {
			return keyz;
		}
		@Override
		public String baseGetValue() 
		{
			return value;
		}
		public String toString()
		{
			return "" + keyz + ":" + value;
		}
	}

	@Override
	public BaseKeyValue<Long,String> getPOJOProperty(LogElement e) 
	{
		return new KV(e);
	}

	@Override
	public Class getPOJOClass() {
		return KV.class;
	}
}
