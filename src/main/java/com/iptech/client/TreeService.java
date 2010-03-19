package com.iptech.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("treeService")
public interface TreeService extends RemoteService {
	ArrayList<String> getChildren(String parent)
			throws IllegalArgumentException;
}
