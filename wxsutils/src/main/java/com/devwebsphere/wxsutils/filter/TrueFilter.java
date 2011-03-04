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

/**
 * This always returns TRUE, useful for arguments on ANDFilters and such.
 * @author bnewport
 *
 */
public class TrueFilter extends Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6302574803546026626L;

	public TrueFilter()
	{
	}
	
	@Override
	public boolean filter(Object fo) {
		// TODO Auto-generated method stub
		return true;
	}

}
