//
//This sample program is provided AS IS and may be used, executed, copied and
//modified without royalty payment by customer (a) for its own instruction and 
//study, (b) in order to develop applications designed to run with an IBM 
//WebSphere product, either for customer's own internal use or for redistribution 
//by customer, as part of such an application, in customer's own products. "
//
//5724-J34 (C) COPYRIGHT International Business Machines Corp. 2005
//All Rights Reserved * Licensed Materials - Property of IBM
//
package com.devwebsphere.wxsutils.wxsmap;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.devwebsphere.wxsutils.WXSMapOfSets;
import com.devwebsphere.wxsutils.WXSUtils;
import com.devwebsphere.wxsutils.filter.Filter;
import com.devwebsphere.wxsutils.jmx.listset.WXSMapOfSetsMBeanImpl;
import com.devwebsphere.wxsutils.jmx.listset.WXSMapOfSetsMBeanManager;
import com.devwebsphere.wxsutils.wxsmap.SetAddRemoveAgent.Operation;
import com.ibm.websphere.objectgrid.ObjectGridRuntimeException;
import com.ibm.websphere.objectgrid.datagrid.EntryErrorValue;


public class WXSMapOfSetsImpl<K,V extends Serializable> extends WXSBaseMap implements WXSMapOfSets<K, V> 
{
	static Logger logger = Logger.getLogger(WXSMapOfSetsImpl.class.getName());
	
	static LazyMBeanManagerAtomicReference<WXSMapOfSetsMBeanManager> wxsMapOfSetsMBeanManager = new LazyMBeanManagerAtomicReference<WXSMapOfSetsMBeanManager>(WXSMapOfSetsMBeanManager.class);
	public WXSMapOfSetsImpl(WXSUtils utils, String mapName)
	{
		super(utils, mapName);
	}

	public void add(K key, V... values) 
	{
		addRemove(key, Operation.ADD, values);
	}

	void addRemove(K key, Operation op, V... values) {
		WXSMapOfSetsMBeanImpl mbean = wxsMapOfSetsMBeanManager.getLazyRef().getBean(grid.getName(), mapName);
		long start = System.nanoTime();
		try
		{
			SetAddRemoveAgent<V> a = new SetAddRemoveAgent<V>();
			a.op = op;
			a.values = values;
			Map<K,Object> rc = tls.getMap(mapName).getAgentManager().callMapAgent(a, Collections.singletonList(key));
			Object rcV = rc.get(key);
			if(rcV != null && rcV instanceof EntryErrorValue)
			{
				logger.log(Level.SEVERE, "set addRemove failed");
				throw new ObjectGridRuntimeException(rcV.toString());
			}
			mbean.getAddRemoveMetrics().logTime(System.nanoTime() - start);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "Exception", e);
			mbean.getAddRemoveMetrics().logException(e);
			throw new ObjectGridRuntimeException(e);
		}
	}
	
	public int size(K key) {
		WXSMapOfSetsMBeanImpl mbean = wxsMapOfSetsMBeanManager.getLazyRef().getBean(grid.getName(), mapName);
		long start = System.nanoTime();
		try
		{
			SetSizeAgent<V> a = new SetSizeAgent<V>();
			Map<K,Object> rc = tls.getMap(mapName).getAgentManager().callMapAgent(a, Collections.singletonList(key));
			Object rcV = rc.get(key);
			if(rcV != null && rcV instanceof EntryErrorValue)
			{
				logger.log(Level.SEVERE, "put(K,V) failed");
				throw new ObjectGridRuntimeException(rcV.toString());
			}
			Integer size = (Integer)rcV;
			mbean.getSizeMetrics().logTime(System.nanoTime() - start);
			return size;
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "Exception", e);
			mbean.getSizeMetrics().logException(e);
			throw new ObjectGridRuntimeException(e);
		}
	}

	public boolean isEmpty(K key) {
		WXSMapOfSetsMBeanImpl mbean = wxsMapOfSetsMBeanManager.getLazyRef().getBean(grid.getName(), mapName);
		long start = System.nanoTime();
		try
		{
			SetIsEmptyAgent<V> a = new SetIsEmptyAgent<V>();
			Map<K,Object> rc = tls.getMap(mapName).getAgentManager().callMapAgent(a, Collections.singletonList(key));
			Object rcV = rc.get(key);
			if(rcV != null && rcV instanceof EntryErrorValue)
			{
				logger.log(Level.SEVERE, "isEmpty failed");
				throw new ObjectGridRuntimeException(rcV.toString());
			}
			Boolean b = (Boolean)rcV;
			mbean.getSizeMetrics().logTime(System.nanoTime() - start);
			return b;
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "Exception", e);
			mbean.getSizeMetrics().logException(e);
			throw new ObjectGridRuntimeException(e);
		}
	}

	public boolean contains(K key, Contains op, V... values) {
		WXSMapOfSetsMBeanImpl mbean = wxsMapOfSetsMBeanManager.getLazyRef().getBean(grid.getName(), mapName);
		long start = System.nanoTime();
		try
		{
			SetIsMemberAgent<V> a = new SetIsMemberAgent<V>();
			a.op = op;
			a.values = values;
			Map<K,Object> rc = tls.getMap(mapName).getAgentManager().callMapAgent(a, Collections.singletonList(key));
			Object rcV = rc.get(key);
			if(rcV != null && rcV instanceof EntryErrorValue)
			{
				logger.log(Level.SEVERE, "ismember failed");
				throw new ObjectGridRuntimeException(rcV.toString());
			}
			Boolean present = (Boolean)rcV;
			mbean.getContainsMetrics().logTime(System.nanoTime() - start);
			return present;
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "Exception", e);
			mbean.getContainsMetrics().logException(e);
			throw new ObjectGridRuntimeException(e);
		}
	}

	public Set<V> get(K key)
	{
		return get(key, null);
	}
	
	public Set<V> get(K key, Filter filter) {
		WXSMapOfSetsMBeanImpl mbean = wxsMapOfSetsMBeanManager.getLazyRef().getBean(grid.getName(), mapName);
		long start = System.nanoTime();
		try
		{
			SetGetAgent<V> a = new SetGetAgent<V>();
			a.filter = filter;
			Map<K,Object> rc = tls.getMap(mapName).getAgentManager().callMapAgent(a, Collections.singletonList(key));
			Object rcV = rc.get(key);
			if(rcV != null && rcV instanceof EntryErrorValue)
			{
				logger.log(Level.SEVERE, "put(K,V) failed");
				throw new ObjectGridRuntimeException(rcV.toString());
			}
//			mbean.getSizeMetrics().logTime(System.nanoTime() - start);
			Set<V> set = (Set<V>)rcV;
			return set.size() > 0 ? set : null;
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "Exception", e);
//			mbean.getSizeMetrics().logException(e);
			throw new ObjectGridRuntimeException(e);
		}
	}

	public void remove(K key, V... values) 
	{
		addRemove(key, Operation.REMOVE, values);
	}

	public void remove(K key) 
	{
		WXSMapOfSetsMBeanImpl mbean = wxsMapOfSetsMBeanManager.getLazyRef().getBean(grid.getName(), mapName);
		long start = System.nanoTime();
		try
		{
			SetRemoveAgent<V> a = new SetRemoveAgent<V>();
			Map<K,Object> rc = tls.getMap(mapName).getAgentManager().callMapAgent(a, Collections.singletonList(key));
			Object rcV = rc.get(key);
			if(rcV != null && rcV instanceof EntryErrorValue)
			{
				logger.log(Level.SEVERE, "put(K,V) failed");
				throw new ObjectGridRuntimeException(rcV.toString());
			}
			mbean.getSizeMetrics().logTime(System.nanoTime() - start);
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "Exception", e);
			mbean.getSizeMetrics().logException(e);
			throw new ObjectGridRuntimeException(e);
		}
	}

	public Set<V> addAndFlush(K key, int maxSize, V... values) {
		WXSMapOfSetsMBeanImpl mbean = wxsMapOfSetsMBeanManager.getLazyRef().getBean(grid.getName(), mapName);
		long start = System.nanoTime();
		try
		{
			SetAddFlushAgent<V> a = new SetAddFlushAgent<V>();
			a.maxSize = maxSize;
			a.values = values;
			Map<K,Object> rc = tls.getMap(mapName).getAgentManager().callMapAgent(a, Collections.singletonList(key));
			Object rcV = rc.get(key);
			if(rcV != null && rcV instanceof EntryErrorValue)
			{
				logger.log(Level.SEVERE, "set addAndFlush failed: " + rcV.toString());
				throw new ObjectGridRuntimeException(rcV.toString());
			}
			mbean.getAddRemoveMetrics().logTime(System.nanoTime() - start);
			return (Set<V>)rcV;
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE, "Exception", e);
			mbean.getAddRemoveMetrics().logException(e);
			throw new ObjectGridRuntimeException(e);
		}
	}

}
