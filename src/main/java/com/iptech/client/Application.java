package com.iptech.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class Application implements EntryPoint {
	private static final String CHARGEMENT_EN_COURS = "Chargement en cours ...";
	private final TreeServiceAsync treeService = GWT.create(TreeService.class);

	public void onModuleLoad() {
		TreeItem root = new TreeItem(CHARGEMENT_EN_COURS);

		Tree browseTree = new Tree();
		browseTree.addItem(root);

		treeService.getChildren(null, new TreeRootCallback(browseTree));

		browseTree.addOpenHandler(new OpenHandler<TreeItem>() {

			public void onOpen(OpenEvent<TreeItem> event) {
				if (needsLoading(event.getTarget())) {

					treeService.getChildren(event.getTarget().getText(),
							new TreeItemCallback(event.getTarget()));
				}
			}
		});
		RootPanel.get().add(browseTree);
	}

	private boolean needsLoading(TreeItem item) {
		return item.getChildCount() == 1
				&& CHARGEMENT_EN_COURS.equals(item.getChild(0).getText());
	}

	public static final class TreeRootCallback implements
			AsyncCallback<ArrayList<String>> {

		private Tree browseTree;

		public TreeRootCallback(Tree browseTree) {
			super();
			this.browseTree = browseTree;
		}

		public void onFailure(Throwable caught) {
			caught.printStackTrace();
		}

		public void onSuccess(ArrayList<String> names) {
			browseTree.removeItems();
			for (String name : names) {
				TreeItem ti = new TreeItem(name);
				ti.addItem(CHARGEMENT_EN_COURS);
				browseTree.addItem(ti);
			}
		}

	}

	public static final class TreeItemCallback implements
			AsyncCallback<ArrayList<String>> {

		private TreeItem treeItem;

		public TreeItemCallback(TreeItem treeItem) {
			super();
			this.treeItem = treeItem;
		}

		public void onFailure(Throwable caught) {
			caught.printStackTrace();
		}

		public void onSuccess(ArrayList<String> names) {
			treeItem.removeItems();
			for (String name : names) {
				TreeItem ti = new TreeItem(name);
				ti.addItem(CHARGEMENT_EN_COURS);
				treeItem.addItem(ti);
			}
		}
	}
}
