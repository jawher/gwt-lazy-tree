package com.iptech.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.iptech.client.TreeService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class TreeServiceImpl extends RemoteServiceServlet implements
		TreeService {
	private static final int CHILDREN_COUNT = 10;

	public ArrayList<String> getChildren(String parent)
			throws IllegalArgumentException {
		String prefix = parent == null ? "item " : parent + "-";
		ArrayList<String> res = new ArrayList<String>(CHILDREN_COUNT);
		for (int i = 1; i <= CHILDREN_COUNT; i++) {
			res.add(prefix + i);
		}
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return res;
	}
}
