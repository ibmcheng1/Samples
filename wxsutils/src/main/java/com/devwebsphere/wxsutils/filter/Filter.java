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
package com.devwebsphere.wxsutils.filter;

import java.io.Serializable;

/**
 * This is an abstract filter. It defines a filter for testing
 * if a specified object passed the filter.
 * @author bnewport
 *
 */
public abstract class Filter implements Serializable
{
	/**
	 * This checks if the supplied object passes the filter
	 * @param o
	 * @return
	 */
	public abstract boolean filter(Object o);
}
