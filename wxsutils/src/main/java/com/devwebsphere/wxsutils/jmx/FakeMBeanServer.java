package com.devwebsphere.wxsutils.jmx;

import java.io.ObjectInputStream;
import java.util.Set;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.InvalidAttributeValueException;
import javax.management.ListenerNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.NotCompliantMBeanException;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.OperationsException;
import javax.management.QueryExp;
import javax.management.ReflectionException;
import javax.management.loading.ClassLoaderRepository;

public class FakeMBeanServer implements MBeanServer {

	public void addNotificationListener(ObjectName name,
			NotificationListener listener, NotificationFilter filter,
			Object handback) throws InstanceNotFoundException {
		// TODO Auto-generated method stub

	}

	public void addNotificationListener(ObjectName name, ObjectName listener,
			NotificationFilter filter, Object handback)
			throws InstanceNotFoundException {
		// TODO Auto-generated method stub

	}

	public ObjectInstance createMBean(String className, ObjectName name)
			throws ReflectionException, InstanceAlreadyExistsException,
			MBeanRegistrationException, MBeanException,
			NotCompliantMBeanException {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectInstance createMBean(String className, ObjectName name,
			ObjectName loaderName) throws ReflectionException,
			InstanceAlreadyExistsException, MBeanRegistrationException,
			MBeanException, NotCompliantMBeanException,
			InstanceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectInstance createMBean(String className, ObjectName name,
			Object[] params, String[] signature) throws ReflectionException,
			InstanceAlreadyExistsException, MBeanRegistrationException,
			MBeanException, NotCompliantMBeanException {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectInstance createMBean(String className, ObjectName name,
			ObjectName loaderName, Object[] params, String[] signature)
			throws ReflectionException, InstanceAlreadyExistsException,
			MBeanRegistrationException, MBeanException,
			NotCompliantMBeanException, InstanceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectInputStream deserialize(ObjectName name, byte[] data)
			throws InstanceNotFoundException, OperationsException {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectInputStream deserialize(String className, byte[] data)
			throws OperationsException, ReflectionException {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectInputStream deserialize(String className,
			ObjectName loaderName, byte[] data)
			throws InstanceNotFoundException, OperationsException,
			ReflectionException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getAttribute(ObjectName name, String attribute)
			throws MBeanException, AttributeNotFoundException,
			InstanceNotFoundException, ReflectionException {
		// TODO Auto-generated method stub
		return null;
	}

	public AttributeList getAttributes(ObjectName name, String[] attributes)
			throws InstanceNotFoundException, ReflectionException {
		// TODO Auto-generated method stub
		return null;
	}

	public ClassLoader getClassLoader(ObjectName loaderName)
			throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public ClassLoader getClassLoaderFor(ObjectName mbeanName)
			throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public ClassLoaderRepository getClassLoaderRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDefaultDomain() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getDomains() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getMBeanCount() {
		// TODO Auto-generated method stub
		return null;
	}

	public MBeanInfo getMBeanInfo(ObjectName name)
			throws InstanceNotFoundException, IntrospectionException,
			ReflectionException {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectInstance getObjectInstance(ObjectName name)
			throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object instantiate(String className) throws ReflectionException,
			MBeanException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object instantiate(String className, ObjectName loaderName)
			throws ReflectionException, MBeanException,
			InstanceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object instantiate(String className, Object[] params,
			String[] signature) throws ReflectionException, MBeanException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object instantiate(String className, ObjectName loaderName,
			Object[] params, String[] signature) throws ReflectionException,
			MBeanException, InstanceNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object invoke(ObjectName name, String operationName,
			Object[] params, String[] signature)
			throws InstanceNotFoundException, MBeanException,
			ReflectionException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isInstanceOf(ObjectName name, String className)
			throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isRegistered(ObjectName name) {
		// TODO Auto-generated method stub
		return false;
	}

	public Set<ObjectInstance> queryMBeans(ObjectName name, QueryExp query) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<ObjectName> queryNames(ObjectName name, QueryExp query) {
		// TODO Auto-generated method stub
		return null;
	}

	public ObjectInstance registerMBean(Object object, ObjectName name)
			throws InstanceAlreadyExistsException, MBeanRegistrationException,
			NotCompliantMBeanException {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeNotificationListener(ObjectName name, ObjectName listener)
			throws InstanceNotFoundException, ListenerNotFoundException {
		// TODO Auto-generated method stub

	}

	public void removeNotificationListener(ObjectName name,
			NotificationListener listener) throws InstanceNotFoundException,
			ListenerNotFoundException {
		// TODO Auto-generated method stub

	}

	public void removeNotificationListener(ObjectName name,
			ObjectName listener, NotificationFilter filter, Object handback)
			throws InstanceNotFoundException, ListenerNotFoundException {
		// TODO Auto-generated method stub

	}

	public void removeNotificationListener(ObjectName name,
			NotificationListener listener, NotificationFilter filter,
			Object handback) throws InstanceNotFoundException,
			ListenerNotFoundException {
		// TODO Auto-generated method stub

	}

	public void setAttribute(ObjectName name, Attribute attribute)
			throws InstanceNotFoundException, AttributeNotFoundException,
			InvalidAttributeValueException, MBeanException, ReflectionException {
		// TODO Auto-generated method stub

	}

	public AttributeList setAttributes(ObjectName name, AttributeList attributes)
			throws InstanceNotFoundException, ReflectionException {
		// TODO Auto-generated method stub
		return null;
	}

	public void unregisterMBean(ObjectName name)
			throws InstanceNotFoundException, MBeanRegistrationException {
		// TODO Auto-generated method stub

	}

}
