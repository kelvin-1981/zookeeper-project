package com.yykj.zookeeper.distribut;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class DistributClient {

	private static ZooKeeper zkClient = null;

	private static String connectString = "node21:2181,node22:2181,node23:2181";

	private static int sessionTimeout = 2000;
	
	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		
		DistributClient client = new DistributClient();
		
		//1.连接Zookeeper集群
		client.getConnect();
		
		//2.注册监听
		client.regist();
		
		//3.业务逻辑 
		client.business();
	}

	/**
	 * @throws InterruptedException 
	 * 
	 */
	private void business() throws InterruptedException {
		// TODO Auto-generated method stub
		Thread.sleep(Long.MAX_VALUE);
	}

	/**
	 * @throws InterruptedException 
	 * @throws KeeperException 
	 * 
	 */
	private void regist() throws KeeperException, InterruptedException {
		
		List<String> children = zkClient.getChildren("/test", true);
		
		ArrayList<String> hosts = new ArrayList<String>();
		for (String info : children) {
			
			byte[] data = zkClient.getData("/test/" + info, false, null);
			
			hosts.add(new String(data));
		}
		
		System.out.println(hosts);
	}

	/**
	 * @throws IOException 
	 * 
	 */
	private void getConnect() throws IOException {
		// TODO Auto-generated method stub
		zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher(){
			@Override
			public void process(WatchedEvent arg0) {
				// TODO Auto-generated method stub
				try {
					regist();
				} catch (KeeperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
