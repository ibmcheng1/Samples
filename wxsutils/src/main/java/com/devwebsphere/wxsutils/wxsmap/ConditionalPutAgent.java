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

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.devwebsphere.wxsutils.WXSUtils;
import com.devwebsphere.wxsutils.jmx.agent.AgentMBeanImpl;
import com.devwebsphere.wxsutils.utils.ClassSerializer;
import com.ibm.websphere.objectgrid.ObjectGridException;
import com.ibm.websphere.objectgrid.ObjectMap;
import com.ibm.websphere.objectgrid.Session;
import com.ibm.websphere.objectgrid.datagrid.ReduceGridAgent;

/**
 * This is used to conditionally put a chunk of records for a given partition using a single hop. It returns a Map for
 * each key with whether the conditional update succeeded. Keys that do not existed are inserted regardless.
 * 
 * @see WXSUtils#cond_putAll(java.util.Map, java.util.Map, com.ibm.websphere.objectgrid.BackingMap)
 */
public class ConditionalPutAgent<K, V> implements ReduceGridAgent, Externalizable {
	static Logger logger = Logger.getLogger(ConditionalPutAgent.class.getName());
	/**
	 * 
	 */
	private static final long serialVersionUID = 6568906743945108310L;

	public java.util.Map<K, V> batchBefore;
	public java.util.Map<K, V> newValues;

	public Object reduce(Session sess, ObjectMap map) {
		return null;
	}

	public Object reduce(Session sess, ObjectMap map, Collection arg2) {
		AgentMBeanImpl agent = WXSUtils.getAgentMBeanManager().getBean(sess.getObjectGrid().getName(), this.getClass().getName());
		long startNS = System.nanoTime();
		Session s = null;
		try {
			s = SessionPool.getSessionForThread(sess.getObjectGrid());
			ObjectMap m = s.getMap(map.getName());
			s.beginNoWriteThrough();
			ArrayList<K> keys = new ArrayList<K>(batchBefore.keySet());
			List<V> oldValues = m.getAll(keys);
			HashMap<K, Boolean> results = new HashMap<K, Boolean>();
			for (K key : keys) {
				V currValue = (V) m.getForUpdate(key);
				V origValue = batchBefore.get(key);
				Boolean b = false;
				if (currValue != null) {
					if (origValue != null && currValue.equals(batchBefore.get(key))) {
						m.update(key, newValues.get(key));
						b = true;
					}
				} else {
					if (origValue == null)
						m.insert(key, newValues.get(key));
					b = true;
				}
				results.put(key, b);
			}
			s.commit();
			agent.getKeysMetric().logTime(System.nanoTime() - startNS);
			return results;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception", e);
			agent.getKeysMetric().logException(e);
			return Boolean.FALSE;
		} finally {
			if (s != null && s.isTransactionActive()) {
				try {
					s.rollback();
				} catch (ObjectGridException e) {
					logger.log(Level.SEVERE, "Exception", e);
				}
			}
			SessionPool.returnSession(s);
		}
	}

	/**
	 * Combine the Boolean results of the process calls using AND
	 */
	public Object reduceResults(Collection arg0) {
		HashMap<K, Boolean> rc = new HashMap<K, Boolean>();
		for (Object o : arg0) {
			if (o instanceof Boolean) {
				return new HashMap<K, Boolean>(); // empty indicates a failure
			} else {
				Map<K, Boolean> item = (Map<K, Boolean>) o;
				rc.putAll(item);
			}
		}
		return rc;
	}

	public ConditionalPutAgent() {
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		ClassSerializer serializer = WXSUtils.getSerializer();
		batchBefore = serializer.readMap(in);
		newValues = serializer.readMap(in);
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		ClassSerializer serializer = WXSUtils.getSerializer();
		serializer.writeMap(out, batchBefore);
		serializer.writeMap(out, newValues);
	}
}
